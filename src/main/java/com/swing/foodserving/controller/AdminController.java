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
import com.swing.foodserving.service.UserService;



@Controller
@RequestMapping("/u/admin")
public class AdminController {

	@Autowired
	DashboardService dashboardService;

	@RequestMapping(value = "/comingSoon",method = RequestMethod.GET)
	public String commingSoon() {
		return "comingSoon";
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String admin(HttpSession hs, HttpServletRequest request) {
		Map<String, Integer> dashboardData = new HashedMap<String, Integer>(); 
		
		dashboardData.put("totalUser", dashboardService.getUserCount());
		dashboardData.put("vendorUser", dashboardService.getUserCountByRole(Constant.ROLE_VENDOR));
		dashboardData.put("publicUser", dashboardService.getUserCountByRole(Constant.ROLE_PUBLIC));
		dashboardData.put("totalRequest", dashboardService.getRequestCount());
		dashboardData.put("pendingRequest", dashboardService.getRequestCountByStatus(Constant.REQUEST_PENDDING));
		dashboardData.put("assignedRequest", dashboardService.getRequestCountByStatus(Constant.REQUEST_ASSIGN));
		dashboardData.put("acceptedRequest", dashboardService.getRequestCountByStatus(Constant.REQUEST_ACCEPT));
		dashboardData.put("completedRequest", dashboardService.getRequestCountByStatus(Constant.REQUEST_FOOD_DELIVERED));
		dashboardData.put("cancelRequest", dashboardService.getRequestCountByStatus(Constant.REQUEST_CANCEL));
		
		request.setAttribute("dashboardData", dashboardData);
			return "../admin/dashboard";
	}
	
	
	
	
	
}
