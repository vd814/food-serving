package com.swing.foodserving.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.dao.UserDao;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.UserService;
import com.swing.foodserving.utility.PasswordEncoder;

@Controller
public class RegisterUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/registerUser",method = RequestMethod.POST)
	public String addUser(
			@RequestParam int role,
			@RequestParam String email,
			@RequestParam String password,
			@RequestParam String userName,
			RedirectAttributes attr,
			HttpServletRequest request) {
		userService.registerUser(role, email, userName, password);
		attr.addFlashAttribute("mailPageTitle", "Registration Mail");
		attr.addFlashAttribute("mailTitle", MessageConstant.REGISTRATION_SUCCESSFULL);
		attr.addFlashAttribute("mailMsg", MessageConstant.REGISTRATION_SUCCESSFULL_MSG);
		return "redirect:/afterMailSend";
	}
	
	@RequestMapping(value = "/afterMailSend")
	public String afterMailSend () {
		return "afterMailSending";
	}
	
	@RequestMapping(value = "redirectForgotPasswordPage")
	public String redirectForgotPasswordPage() {
		return "forgotPassword";
	} 
	
	@RequestMapping(value = "/userNameExist", method = RequestMethod.POST)
	@ResponseBody
	public boolean userNameExist(@RequestParam String userName) {
		return userService.userNameExist(userName);
	}
	
	@RequestMapping(value = "/emailExist", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailExist(@RequestParam String email) {
		return userService.emailExist(email);
	}
	
	
	
}
