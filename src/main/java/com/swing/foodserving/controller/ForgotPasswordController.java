package com.swing.foodserving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.UserService;
import com.swing.foodserving.utility.OTPGenerator;

@Controller
public class ForgotPasswordController {

	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/emailExistForForgotPassword", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailExistForForgotPassword(@RequestParam String email) {
		return userService.emailExistForForgotPassword(email);
	}
	
	@RequestMapping(value = "/sendOTP")
	public String sendOTP(@RequestParam String email) {
		User user = userService.getUserByUSerEmail(email);
		user.setPasswordResetToken(OTPGenerator.getOTP(6));
		System.out.println();
		userService.saveOrUpdateUser(user);
		return "otpValidation";
	}
}
