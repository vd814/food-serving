package com.swing.foodserving.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	@RequestMapping(value = "/")
	public String BeforeLogin() {
		return "redirect:home1";
	}
}
