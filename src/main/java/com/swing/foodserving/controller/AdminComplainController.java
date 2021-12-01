package com.swing.foodserving.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.entity.Request;
import com.swing.foodserving.entity.RequestComplain;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.ComplainService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/u/admin")
public class AdminComplainController {

	@Autowired
	ComplainService complainService;
	
	@RequestMapping(value = "/addComplain", method = RequestMethod.POST)
	public String addComplain(@RequestParam int requestIdForComplain, 
			@RequestParam String complain, 
			RedirectAttributes redir) {
		complainService.saveComplain(requestIdForComplain, complain);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.COMPLAIN_ADDED);
		return "redirect:reviewRatingList";
	}
	
	@RequestMapping(value = "/complainList")
	public String complainList() {
		return "../admin/complainList";
	}
	
	@RequestMapping(value = "/getAllComplainForAdminWithDatatable",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllComplainForAdminWithDatatable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		List<Object[]> requestList = complainService.getAllComplainWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		
		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		
		for (Object objects[] : requestList) {
			RequestComplain rc = (RequestComplain)objects[0];
			Request r = (Request)objects[1];
			User u = (User)objects[2];
			User v = (User)objects[3];
			String requestType = "";
			StringBuffer action = new StringBuffer();
			if (r.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				action.append("<span onclick='viewRequestDetail("+r.getrID()+",1)' class='cursorPointer badge bg-soft-success text-success'>"
								+ "Show Request Detail"
							+ "</span>");
			}else {
				requestType = "Need Food";
				action.append("<span onclick='viewRequestDetail("+r.getrID()+",2)' class='cursorPointer badge bg-soft-success text-success'>"
								+ "Show Request Detail"
							+ "</span>");
			}
			String requestBy = u.getName();
			String vendorName = v.getName();
			String complain = rc.getComplain();
			String vendorReplay = "";
			String adminReplay = "";
			
			if (rc.getAdminReplay() != null) {
				adminReplay = rc.getAdminReplay();
				if (u.getRole() != Constant.ROLE_ADMIN) {
					action.append("<br><span onclick='editReplay("+rc.getId()+")' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Edit Replay"
								+ "</span>");
				}
			}else {
				if (u.getRole() != Constant.ROLE_ADMIN) {
					action.append("<br><span onclick='addReplay("+rc.getId()+")' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Replay of Complain"
								+ "</span>");
				}
			}
			if (rc.getVendorReplay() != null) {
				vendorReplay = rc.getVendorReplay();
			}
			if (u.getRole() == Constant.ROLE_ADMIN) {
				action.append("<br><span onclick='editComplain("+ rc.getId()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
								+ "Edit Complain"
							+ "</span>");
				action.append("<br><span onclick='deleteComplain("+rc.getId()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
								+ "Delete Complain"
							+ "</span>");
			}
			
			sb.append("[\""+index+"\",\""+requestType+"\",\""+requestBy+"\",\""+vendorName+"\",\""+complain+"\",\""+vendorReplay+"\",\""+adminReplay+"\",\""+action+"\",],");
			index++;
		}
		
		String dt=sb.toString();
		if(requestList.size()!=0){
			dt=dt.substring(0, dt.lastIndexOf(","))+"]";
		}else{
			dt+="]";
		}
		jo.put("draw", Integer.parseInt(draw));
		jo.put("recordsTotal", (Integer)hs.getAttribute("numbrOfRecord"));
		jo.put("recordsFiltered", (Integer)hs.getAttribute("numbrOfRecord"));
		jo.put("data", dt);
		
		return jo;
	}
	
	@RequestMapping(value = "/getComplainByID", method = RequestMethod.POST)
	@ResponseBody
	public RequestComplain getComplainByID(@RequestParam int complainID) {
		return complainService.getComplainByID(complainID);
	}
	
	@RequestMapping(value = "/editComplain", method = RequestMethod.POST)
	public String editComplain(RedirectAttributes redir, 
			@RequestParam int editComplainID, 
			@RequestParam String editComplain) {
		complainService.updateComplain(editComplainID, editComplain);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.COMPLAIN_EDITED);
		return "redirect:complainList";
	}
	
	@RequestMapping(value = "/deleteComplain", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteComplain(@RequestParam int complainID) {
		complainService.deleteComplain(complainID);
		return true;
	}
	
	@RequestMapping(value = "/addReplayByAdmin", method = RequestMethod.POST)
	public String addReplayByAdmin(RedirectAttributes redir,
			@RequestParam int complainID,
			@RequestParam String replay) {
		complainService.addOrEditReplay(complainID, replay, Constant.ROLE_ADMIN);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.COMPLAIN_REPLAY_ADDED);
		return "redirect:complainList";
	}
	
	@RequestMapping(value = "/editReplayByAdmin", method = RequestMethod.POST)
	public String editReplayByAdmin(RedirectAttributes redir,
			@RequestParam int editComplainIdforReplay,
			@RequestParam String editReplay) {
		complainService.addOrEditReplay(editComplainIdforReplay, editReplay, Constant.ROLE_ADMIN);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.COMPLAIN_REPLAY_EDITED);
		return "redirect:complainList";
	}
}
