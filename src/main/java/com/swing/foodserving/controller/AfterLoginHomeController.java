package com.swing.foodserving.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.service.DashboardService;
import com.swing.foodserving.utility.UserUtil;

@Controller
@RequestMapping(value = "/u")
public class AfterLoginHomeController {
	
	@Autowired
	DashboardService dashboardService;

	@RequestMapping(value = "/comingSoon",method = RequestMethod.GET)
	public String commingSoon() {
		return "comingSoon";
	}
	
	@RequestMapping(value = "/userDashboard", method = RequestMethod.GET)
	public String redirectToHomePage(HttpSession hs, HttpServletRequest request) {
		
		Map<String, Integer> dashboardData = new HashedMap<String, Integer>();
		
		int userID = UserUtil.getUser(hs).getUserId();
		int role = UserUtil.getUser(hs).getRole();
		
		dashboardData.put("totalRequest", dashboardService.getRequestCountByUser(userID, role));
		dashboardData.put("completedRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_FOOD_DELIVERED));
		dashboardData.put("penddingRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_PENDDING));
		dashboardData.put("assignedRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_ASSIGN));
		dashboardData.put("canceledRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_CANCEL));
		
		request.setAttribute("dashboardData", dashboardData);
		return "userDashboard";
	}
	
	@RequestMapping(value = "/vendorDashbord", method = RequestMethod.GET)
	public String redirectToVendroDashbord(HttpSession hs, HttpServletRequest request) {
		Map<String, Integer> dashboardData = new HashedMap<String, Integer>();
		
		int userID = UserUtil.getUser(hs).getUserId();
		int role = UserUtil.getUser(hs).getRole();
		
		dashboardData.put("totalRequest", dashboardService.getRequestCountByUser(userID, role));
		dashboardData.put("completedRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_FOOD_DELIVERED));
		dashboardData.put("assignedRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_ASSIGN));
		dashboardData.put("canceledRequest", dashboardService.getRequestCountByUserAndStatus(userID, role, Constant.REQUEST_CANCEL));
		dashboardData.put("complain", dashboardService.getVendorComplainCount(userID));
		
		request.setAttribute("dashboardData", dashboardData);
		request.setAttribute("rating", dashboardService.getAverageRatingOfVendor(userID));
		return "vendorDashbord";
	}
}
