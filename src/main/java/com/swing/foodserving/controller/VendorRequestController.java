package com.swing.foodserving.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.entity.Request;
import com.swing.foodserving.entity.RequestDecline;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.RequestCancelService;
import com.swing.foodserving.service.RequestDeclineService;
import com.swing.foodserving.service.RequestService;
import com.swing.foodserving.service.UserService;
import com.swing.foodserving.utility.UserUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/u")
public class VendorRequestController {
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RequestDeclineService requestDeclineService;
	
	@Autowired
	RequestCancelService requestCancelService;
	
	@RequestMapping(value = "/vendorRequestList", method = RequestMethod.GET) 
	public String vendorRequestList() {
		return "vendorRequestList";
	}
	
	@RequestMapping(value = "/getVendorRequestWithDataTable",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getVendorRequestWithDataTable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		User u1 = UserUtil.getUser(hs);
		List<Object> requestList = requestService.getVendorRequestWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber, u1.getUserId());
		
		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		
		for (Object objects : requestList) {
			
			Request request = (Request)objects;
			String requestType = "";
			String view = "";
			
			if (request.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				view = "'viewRequestDetail("+request.getrID()+",1)'";
			}else {
				requestType = "Need Food";
				view = "'viewRequestDetail("+request.getrID()+",2)'";
			}
			String user = userService.getUserByUSerID(request.getUserID()).getName();
			String status = "";
			String changeStatus = "";
			if (request.getRequestStatus() == Constant.REQUEST_ASSIGN) {
				status = "Assign";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
										+ "Change Status <i class='mdi mdi-chevron-down'></i>"
									+ "</button>"
									+ "<div class='dropdown-menu'>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_ACCEPT+","+request.getrID()+")'>Accept</span>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_DECLINE+","+request.getrID()+")'>Dicline</span>"
									+"</div>"
							+ "</div>";
			}else if (request.getRequestStatus() == Constant.REQUEST_ACCEPT) {
				status = "Accepted";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
										+ "Change Status <i class='mdi mdi-chevron-down'></i>"
									+ "</button>"
									+ "<div class='dropdown-menu'>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_ON_THE_WAY+","+request.getrID()+")'>On The Way</span>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_CANCEL+","+request.getrID()+")'>Cancel</span>"
									+"</div>"
							+ "</div>";
			}else if (request.getRequestStatus() == Constant.REQUEST_ON_THE_WAY) {
				status = "On The Way";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
										+ "Change Status <i class='mdi mdi-chevron-down'></i>"
									+ "</button>"
									+ "<div class='dropdown-menu'>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_FOOD_COLLECTED+","+request.getrID()+")'>Food Collected</span>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_CANCEL+","+request.getrID()+")'>Cancel</span>"
									+"</div>"
							+ "</div>";
			}else if (request.getRequestStatus() == Constant.REQUEST_FOOD_COLLECTED) {
				status = "Food Collected";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<button type='button' class='btn btn-info dropdown-toggle' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>"
										+ "Change Status <i class='mdi mdi-chevron-down'></i>"
									+ "</button>"
									+ "<div class='dropdown-menu'>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_FOOD_DELIVERED+","+request.getrID()+")'>Food Delivered</span>"
										+ "<span class='dropdown-item cursorPointer' onclick='changeStatus("+Constant.REQUEST_CANCEL+","+request.getrID()+")'>Cancel</span>"
									+"</div>"
							+ "</div>";
			}else if (request.getRequestStatus() == Constant.REQUEST_FOOD_DELIVERED) {
				status = "Food DELIVERED";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<span class='btn btn-success'>"
										+ "Request Completed"
									+ "</span>"
							+ "</div>";
			}else if (request.getRequestStatus() == Constant.REQUEST_DECLINE) {
				status = "Dicline";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<span class='btn btn-danger'>"
										+ "Request Dilined"
									+ "</span>"
							+ "</div>";
			}else {
				status = "Cancel";
				changeStatus = "<div class='btn-group mb-2'>"
									+ "<span class='btn btn-danger'>"
										+ "Request Canceled"
									+ "</span>"
							+ "</div>";
			}
			String action = "<span onclick="+view+" class='action-icon mr-2' cursorPointer>"
								+ "<i class='fa fa-eye'></i>"
							+ "</span>";
			
			sb.append("[\""+index+"\",\""+requestType+"\",\""+user+"\",\""+status+"\",\""+changeStatus+"\",\""+action+"\",],");
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

	@RequestMapping(value = "/vendorDeclineRequest", method = RequestMethod.POST)
	public String vendorDeclineRequest(
			@RequestParam int declineRequestID,
			@RequestParam String declineReason, 
			HttpSession hs, 
			RedirectAttributes redir) {
		requestDeclineService.saveRequestDecline(declineRequestID, declineReason, hs);
		requestService.changeRequestStatus(declineRequestID, Constant.REQUEST_PENDDING);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REQUEST_DECLINE);
		return "redirect:vendorRequestList";
	}
	
	@RequestMapping(value = "/vendorChangeRequestStatus", method = RequestMethod.POST)
	@ResponseBody
	public boolean vendorChangeRequestStatus(
			@RequestParam int requestID,
			@RequestParam int status) {
		requestService.changeRequestStatus(requestID, status);
		return true;
	}
	
	@RequestMapping(value = "/vendorRequestCancel", method = RequestMethod.POST)
	public String vendorRequestCancel(
			@RequestParam int cancelRequestID,
			@RequestParam String cancelReason,
			HttpSession hs,
			RedirectAttributes redir) {
		requestCancelService.cancleRequest(cancelRequestID, cancelReason, UserUtil.getUser(hs).getUserId());
		requestService.changeRequestStatus(cancelRequestID, Constant.REQUEST_CANCEL);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REQUEST_CANCEL);
		return "redirect:vendorRequestList";
	}
	
	@RequestMapping(value = "/vendorRequestDeclineList", method = RequestMethod.GET)
	public String vendorRequestDeclineList() {
		return "vendorRequestDeclineList";
	}
	
	@RequestMapping(value = "/getAllVendorRequestDeclineWithDatatable",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllVendorRequestDeclineWithDatatable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		List<Object[]> requestList = requestDeclineService.getAllDeclineRequestWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		
		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		
		for (Object objects[] : requestList) {
			RequestDecline requestDecline = (RequestDecline)objects[0];
			Request request = (Request)objects[1];
			User user = (User)objects[2];
			String requestType = "";
			String requestDetail = "";
			if (request.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				requestDetail = "<span onclick='viewRequestDetail("+request.getrID()+",1)' class='cursorPointer badge bg-soft-success text-success'>"
						+ "Show Request Detail"
					+ "</span>";
			}else {
				requestType = "Need Food";
				requestDetail = "<span onclick='viewRequestDetail("+request.getrID()+",2)' class='cursorPointer badge bg-soft-success text-success'>"
						+ "Show Request Detail"
					+ "</span>";
			}
			String requestBy = user.getName();
			String declineReason = requestDecline.getReason();
			String setting = "<a href='javascript:void(0);' class='action-icon'><i onclick='editRequestDecline("+requestDecline.getId()+")' class='mdi mdi-square-edit-outline'></i>";
			sb.append("[\""+index+"\",\""+requestType+"\",\""+requestBy+"\",\""+declineReason+"\",\""+requestDetail+"\",\""+setting+"\",],");
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
	
	@RequestMapping(value = "/getRequestDeclineByID", method = RequestMethod.POST)
	@ResponseBody
	public RequestDecline getRequestDeclineByID(@RequestParam int requestDeclineID) {
		return requestDeclineService.getRequestDeclineByID(requestDeclineID);
	}
	
	@RequestMapping(value = "/vendorEditDeclineRequest", method = RequestMethod.POST)
	public String vendorEditDeclineRequest(@RequestParam String declineReason, 
			@RequestParam int requestDeclineID, 
			RedirectAttributes redir) {
		requestDeclineService.updateRequestDecline(requestDeclineID, declineReason);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REQUEST_DECLINE_EDIT);
		return "redirect:vendorRequestDeclineList";
	}
}
