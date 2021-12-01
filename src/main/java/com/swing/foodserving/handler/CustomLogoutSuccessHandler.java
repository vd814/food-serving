package com.swing.foodserving.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

import com.swing.foodserving.constant.MessageConstant;


public class CustomLogoutSuccessHandler extends
SimpleUrlLogoutSuccessHandler implements LogoutSuccessHandler {
	
	 @Override
	    public void onLogoutSuccess(
	      HttpServletRequest request, 
	      HttpServletResponse response, 
	      Authentication authentication) 
	      throws IOException, ServletException {
		 	authentication=null;
		 	System.out.println("Inside Logout Success handler");
		 	HttpSession hs= request.getSession();
			hs.setAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
			hs.setAttribute("msg",MessageConstant.LOGOUT_SUCCESSFULL);
	        super.onLogoutSuccess(request, response, authentication);
	    }

}
