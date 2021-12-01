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
import com.swing.foodserving.entity.RequestReviewAndRating;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.ReviewAndRatingService;
import com.swing.foodserving.utility.UserUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/u/admin")
public class AdminReviewRatingController {
	
	@Autowired
	ReviewAndRatingService reviewAndRatingService;
	
	@RequestMapping(value = "/reviewRatingList", method = RequestMethod.GET)
	public String reviewRatingList() {
		return "../admin/reviewRatingList";
	}
	
	@RequestMapping(value = "/getAllCompetedRequestForAdminWithDatatable", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllCompetedRequestForAdminWithDatatable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		List<Object[]> requestList = reviewAndRatingService.getAllCompetedRequestWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		

		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		
		for (Object objects[] : requestList) {
			Request r = (Request)objects[0];
			RequestReviewAndRating rr= (RequestReviewAndRating)objects[1];
			User u = (User)objects[2];
			User v = (User)objects[3];
			RequestComplain rc = (RequestComplain)objects[4];
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
			String reviewBy = u.getName();
			String vendorName = v.getName();
			String review = "----------";
			String rating = "----------";
			if (rr != null) {
				review = rr.getReview();
				rating = ""+rr.getRating()+" / 5";
				if (u.getRole() == Constant.ROLE_ADMIN) {
					action.append("<br><span onclick='editReviewRating("+rr.getId()+")' class='cursorPointer badge bg-soft-success text-success'>"
								+ "Edit Review & Rating"
							+ "</span>");				
				}
			}else {
				if (u.getRole() == Constant.ROLE_ADMIN) {
					action.append("<br><span onclick='giveReviewRating("+r.getrID()+")' class='cursorPointer badge bg-soft-success text-success'>"
								+ "Give Review & Rating"
							+ "</span>");				
				}
			}
			
			
			if (rc != null) {
				if (u.getRole() == Constant.ROLE_ADMIN) {
					action.append("<br><a href='"+Constant.AFTER_LOGIN_ADMIN_BASEURL+"complainList' class='cursorPointer badge bg-soft-danger text-danger'>"
									+ "Show Complain Detail"
								+ "</a>");
				}
			}else {
				if (u.getRole() == Constant.ROLE_ADMIN) {
					action.append("<br><span onclick='doComplain("+r.getrID()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
									+ "Complain"
								+ "</span>");
				}
			}
			
			sb.append("[\""+index+"\",\""+requestType+"\",\""+reviewBy+"\",\""+vendorName+"\",\""+review+"\",\""+rating+"\",\""+action+"\",],");
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
	
	@RequestMapping(value = "/addReviewRating", method = RequestMethod.POST)
	public String addReviewRating(@RequestParam int requestID, 
			@RequestParam String review, 
			@RequestParam float rating,
			RedirectAttributes redir) {
		reviewAndRatingService.addReviewAndRating(requestID, review, rating);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REVIEW_AND_RATING_ADD);
		return "redirect:reviewRatingList";
	}
	
	@RequestMapping(value = "/getReviewByID", method = RequestMethod.POST)
	@ResponseBody
	public RequestReviewAndRating getReviewByID(@RequestParam int reviewID) {
		return reviewAndRatingService.getReviewByID(reviewID);
	}
	
	@RequestMapping(value = "/editReviewRating", method = RequestMethod.POST)
	public String editReviewRating(@RequestParam int reviewID, 
			@RequestParam String editReview, 
			@RequestParam float editRating, 
			RedirectAttributes redir) {
		reviewAndRatingService.updateReviewAndRating(reviewID, editReview, editRating);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.REVIEW_AND_RATING_EDIT);
		return "redirect:reviewRatingList";
	}
}
