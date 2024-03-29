package com.swing.foodserving.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class JwtAuthFailureHandler implements AuthenticationFailureHandler {
	
	/*@Autowired
	UserDao userDao; */

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		System.out.println("Inside failure handler --------");
		response.setContentType("application/json");
		//response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
		System.out.println("on failuer");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getOutputStream().println("{ \"error\": \"" + exception.getMessage() + "\" }");
	}

}
