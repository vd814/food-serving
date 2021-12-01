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
import com.swing.foodserving.service.RequestService;
import com.swing.foodserving.service.ReviewAndRatingService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/u")
public class ReviewRatingController {
	
	@Autowired
	RequestService requestService;
	
	@Autowired
	ReviewAndRatingService reviewAndRatingService;
	
	@RequestMapping(value = "/userReviewRatingList", method = RequestMethod.GET)
	public String userReviewRatingList() {
		return "userReviewRatingList";
	}
	
	@RequestMapping(value = "/getAllCompetedRequestWithDatatable", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllCompetedRequestWithDatatable(HttpServletRequest req) {
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
			RequestComplain rc = (RequestComplain)objects[2];
			User v = (User)objects[3];
			String requestType = "";
			String requestDetail = "";
			if (r.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				requestDetail = "<span onclick='viewRequestDetail("+r.getrID()+",1)' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Show Request Detail"
								+ "</span>";
			}else {
				requestType = "Need Food";
				requestDetail = "<span onclick='viewRequestDetail("+r.getrID()+",2)' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Show Request Detail"
								+ "</span>";
			}
			String venodorName = v.getName();
			String review = "----------";
			String rating = "----------";
			String reviewAndRating = "<span onclick='giveReviewRating("+r.getrID()+")' class='cursorPointer badge bg-soft-success text-success'>"
										+ "Give Review & Rating"
									+ "</span>";
			if (rr != null) {
				review = rr.getReview();
				rating = ""+rr.getRating()+" / 5";
				reviewAndRating = "<span onclick='editReviewRating("+rr.getId()+")' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Edit Review & Rating"
								+ "</span>";
			}
			String complain = "<span onclick='doComplain("+r.getrID()+")' class='cursorPointer badge bg-soft-danger text-danger'>"
								+ "Complain"
							+ "</span>";
			if (rc != null) {
				complain = "<a href='"+Constant.AFTER_LOGIN_BASEURL+"userComplainList' class='cursorPointer badge bg-soft-danger text-danger'>"
							+ "Show Complain Detail"
						+ "</a>";
			}
			
			sb.append("[\""+index+"\",\""+requestType+"\",\""+venodorName+"\",\""+review+"\",\""+rating+"\",\""+reviewAndRating+"\",\""+complain+"\",\""+requestDetail+"\",],");
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
		return "redirect:userReviewRatingList";
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
		return "redirect:userReviewRatingList";
	}
	
	@RequestMapping(value = "/vendorReviewRatingList", method = RequestMethod.GET)
	public String vendorReviewRatingList() {
		return "vendorReviewRatingList";
	}
	
	@RequestMapping(value = "/getAllCompetedRequestForVendorWithDatatable", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllCompetedRequestForVendorWithDatatable(HttpServletRequest req) {
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
			String requestType = "";
			String requestDetail = "";
			if (r.getRequestType() == Constant.FOOD_EXTRA) {
				requestType = "Extra Food";
				requestDetail = "<span onclick='viewRequestDetail("+r.getrID()+",1)' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Show Request Detail"
								+ "</span>";
			}else {
				requestType = "Need Food";
				requestDetail = "<span onclick='viewRequestDetail("+r.getrID()+",2)' class='cursorPointer badge bg-soft-success text-success'>"
									+ "Show Request Detail"
								+ "</span>";
			}
			String reviewBy = u.getName();
			String review = "----------";
			String rating = "----------";
			if (rr != null) {
				review = rr.getReview();
				rating = ""+rr.getRating()+" / 5";
			}
			
			sb.append("[\""+index+"\",\""+requestType+"\",\""+reviewBy+"\",\""+review+"\",\""+rating+"\",\""+requestDetail+"\",],");
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
