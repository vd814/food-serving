package com.swing.foodserving.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.mail.imap.protocol.Status;
import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.dao.RequestDeclineDao;
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
@RequestMapping(value = "u/admin")
public class AdminRequestController {
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RequestCancelService requestCancelService;
	
	@Autowired
	RequestDeclineService requestDeclineService;

	@RequestMapping(value = "/requestList", method = RequestMethod.GET)
	public String requestList(HttpServletRequest req) {
		req.setAttribute("vemdorList", userService.getVendorList());
		return "../admin/requestList";
	}
	
	@RequestMapping(value = "/getAllRequestWithDataTable",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllRequestWithDataTable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		List<Object> requestList = requestService.getAllRequestWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		
		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		for (Object objects : requestList) {
			Request request = (Request)objects;
			String requestType = "";
			String view = "";
			String editLink = "";
			if (request.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				view = "'viewRequestDetail("+request.getrID()+",1)'";
				editLink = "'"+Constant.AFTER_LOGIN_ADMIN_BASEURL+"adminExtraFoodRequest/"+request.getrID()+"/edit'";
			}else {
				requestType = "Need Food";
				view = "'viewRequestDetail("+request.getrID()+",2)'";
				editLink = "'"+Constant.AFTER_LOGIN_ADMIN_BASEURL+"adminNeedFoodRequest/"+request.getrID()+"/edit'";
			}
			String user = userService.getUserByUSerID(request.getUserID()).getName();
			String vendor = "";
			if (request.getVendorID() != 0) {
				vendor = userService.getUserByUSerID(request.getVendorID()).getName();
			}else {
				vendor = "--------";
			}
			String status = "";
			String assign = "";
			String cancel = "<span onclick='cancelRequest("+request.getrID()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
								+ "Cancel Request"
							+ "</span>";
			String edit = "<a href="+editLink+" class='action-icon mr-2 cursorPointer'>"
							+ "<i class='mdi mdi-square-edit-outline'></i>"
						+ "</a>";
			if (request.getRequestStatus() == Constant.REQUEST_PENDDING) {
				status = "Pendding";
				assign = "<span onclick='assignRequest("+request.getrID()+")' class='cursorPointer badge bg-soft-success text-success'>"
						+ "Assign Request"
					+ "</span>";
			}else if (request.getRequestStatus() == Constant.REQUEST_ASSIGN) {
				status = "Assign";
			}else if (request.getRequestStatus() == Constant.REQUEST_ACCEPT) {
				status = "Accepted";
			}else if (request.getRequestStatus() == Constant.REQUEST_ON_THE_WAY) {
				status = "On The Way";
			}else if (request.getRequestStatus() == Constant.REQUEST_FOOD_COLLECTED) {
				status = "Food Collected";
			}else if (request.getRequestStatus() == Constant.REQUEST_FOOD_DELIVERED) {
				status = "Food Delivered";
				cancel = "";
				edit = "";
			}else if (request.getRequestStatus() == Constant.REQUEST_DECLINE) {
				status = "Dicline";
				cancel = "";
				edit = "";
			}else {
				status = "Cancle";
				cancel = "<span onclick='requestCancelReason("+request.getrID()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
							+ "Cancel Reason"
						+ "</span>";
				edit = "";
			}	
			String setting = "<span onclick="+view+" class='action-icon mr-2 cursorPointer'>"
								+ "<i class='fa fa-eye'></i>"
							+ "</span>"+edit;
							
			sb.append("[\""+index+"\",\""+requestType+"\",\""+user+"\",\""+vendor+"\",\""+status+"\",\""+assign+"\",\""+cancel+"\",\""+setting+"\",],");
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
	
	@RequestMapping(value = "assignRequest")
	public String assignRequest(
			@RequestParam int requestID,
			@RequestParam int vendorID,
			RedirectAttributes redir) {
		requestService.requestAssign(requestID, vendorID);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REQUEST_ASSIGN_TO_VENDOR);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "/adminExtraFoodRequest", method = RequestMethod.GET)
	public String adminExtraFoodRequest() {
		return "../admin/adminExtraFoodRequest";
	}
	
	@RequestMapping(value = "/createExtraFoodRequest", method = RequestMethod.POST)
	public String createExtraFoodRequest(
			@RequestParam String foodItemData,
			@RequestParam(required = false,defaultValue = "") String description,
			@RequestParam String address,
			@RequestParam int requestType,
			@RequestParam String longitude,
			@RequestParam String latitude,
			HttpSession session,
			RedirectAttributes redir) {
		requestService.createRequest(0, address, description, requestType, foodItemData, session);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.CREATE_EXTRA_FOOD_REQUEST);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "/adminNeedFoodRequest", method = RequestMethod.GET)
	public String redirectToNeedFoodRequest() {
		return "../admin/adminNeedFoodRequest";
	}
	
	@RequestMapping(value = "/createNeedFoodRequest", method = RequestMethod.POST)
	public String createNeedFoodRequest(
			@RequestParam int noOfPerson,
			@RequestParam(required = false,defaultValue = "") String description,
			@RequestParam String address,
			@RequestParam int requestType,
			@RequestParam String longitude,
			@RequestParam String latitude,
			HttpSession session,
			RedirectAttributes redir) {
		requestService.createRequest(noOfPerson, address, description, requestType, "", session);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.CREATE_NEED_FOOD_REQUEST);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "/adminExtraFoodRequest/{requestID}/edit",method = RequestMethod.GET)
	public String userExtraFoodRequestEdit(@PathVariable int requestID, HttpServletRequest request) {
		request.setAttribute("request", requestService.getRequestByRequestID(requestID));
		return "../admin/adminExtraFoodRequestEdit";
	}
	
	@RequestMapping(value = "/editExtraFoodRequest", method = RequestMethod.POST)
	public String editExtraFoodRequest(
			@RequestParam String foodItemData,
			@RequestParam int requestID,
			@RequestParam String description,
			@RequestParam String address,
			RedirectAttributes redir) {
		requestService.editRequest(requestID, 0, address, description, foodItemData);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.EDIT_EXTRA_FOOD_REQUEST);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "adminNeedFoodRequest/{requestID}/edit",method = RequestMethod.GET)
	public String userNeedFoodRequestEdit(@PathVariable int requestID, HttpServletRequest request) {
		request.setAttribute("request", requestService.getRequestByRequestID(requestID));
		return "../admin/adminNeedFoodRequestEdit";
	}
	
	@RequestMapping(value = "/editNeedFoodRequest", method = RequestMethod.POST)
	public String editNeedFoodRequest(
			RedirectAttributes redir,
			@RequestParam int noOfPerson,
			@RequestParam int requestID,
			@RequestParam String description,
			@RequestParam String address) {
		
		requestService.editRequest(requestID, noOfPerson, address, description, "");
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.EDIT_NEED_FOOD_REQUEST);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "/getRequestByRequestID", method = RequestMethod.POST)
	@ResponseBody
	public Request getRequestByRequestID(@RequestParam int requestID) {
		return requestService.getRequestByRequestID(requestID);
	}
	
	@RequestMapping(value = "/requestCancel", method = RequestMethod.POST)
	public String requestCancel(
			@RequestParam int requestID,
			@RequestParam String reason,
			HttpSession hs,
			RedirectAttributes redir) {
		System.out.println("Role : "+UserUtil.getUser(hs).getRole());
		requestCancelService.cancleRequest(requestID, reason, UserUtil.getUser(hs).getUserId());
		requestService.changeRequestStatus(requestID, Constant.REQUEST_CANCEL);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REQUEST_CANCEL);
		return "redirect:requestList";
	}
	
	@RequestMapping(value = "/getRequestCancelByRequestId")
	@ResponseBody
	public List getRequestCancelByRequestId(@RequestParam int requestID) {
		return requestCancelService.getRequestCancelByRequestID(requestID);
	}
	
	@RequestMapping(value = "/requestDeclineList", method = RequestMethod.GET)
	public String requestDeclineList() {
		return "../admin/requestDeclineList";
	}
	
	@RequestMapping(value = "/getAllDeclineRequestWithDataTable")
	@ResponseBody
	public JSONObject getAllDeclineRequestWithDataTable(HttpServletRequest req) {
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
			User vendor = (User)objects[1];
			Request request = (Request)objects[2];
			User user = (User)objects[3];
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
			String declineBy = vendor.getName();
			String declineReason = requestDecline.getReason();
			sb.append("[\""+index+"\",\""+requestType+"\",\""+requestBy+"\",\""+declineBy+"\",\""+declineReason+"\",\""+requestDetail+"\",],");
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
}
