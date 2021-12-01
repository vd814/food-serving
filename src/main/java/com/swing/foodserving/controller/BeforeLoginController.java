package com.swing.foodserving.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.service.QueryService;

@Controller
public class BeforeLoginController {
	
	@Autowired
	QueryService queryService;

	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String redirectToSignInPage() {
		return "signIn";
	}
	
	@RequestMapping(value = "/signUp")
	public String redirectToSignUpPage() {
		return	"signUp";
	}
	
	@RequestMapping(value = "/aboutUs", method = RequestMethod.GET)
	public String redirectToAboutUS() {
		return "aboutUs";
	}
	
	@RequestMapping(value = "/help", method = RequestMethod.GET)
	public String redirectToContactUs() {
		return "help";
	}
	
	@RequestMapping(value = "/home1", method = RequestMethod.GET)
	public String redirectToBeforeLoginHomePage() {
		return "beforeLoginHomePage";
	}
	
	@RequestMapping(value = "/addQuery", method = RequestMethod.POST)
	public String addQuery(@RequestParam String email, 
			@RequestParam int queryType, 
			@RequestParam String query, 
			RedirectAttributes redir) {
		
		queryService.saveOrUpdateQuery(email, queryType, query);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.QUERY_ADD_SUCCESSFULLY);
		return "redirect:help";
	}
}
