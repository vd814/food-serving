package com.swing.foodserving.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


public class AuthFailureHandler implements AuthenticationFailureHandler {
	
	/*@Autowired
	UserDao userDao; */

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println(request.getParameter("email"));
		String errMsg="invalid User credentials";
		String path=request.getContextPath();
		HttpSession hs = request.getSession();
		String err="User account is locked";
		String enable="User is disabled";
		
		if(err==exception.getMessage())
		{
			errMsg="You are inactive please contact to Admin !";
		}
		
		else if("Bad credentials"==exception.getMessage())
		{
			errMsg="Invalid Username or Password !";
		}
		else if(exception.getMessage().contains("You are blocked untill"))
		{
			errMsg=exception.getMessage();
		}
		else if(exception.getMessage().contains("This User not found"))
		{
			errMsg=exception.getMessage();
		}
		else
		{
			errMsg=errMsg;
		}
		System.out.println("error message -: "+errMsg);
		hs.setAttribute("errorMsg",errMsg);
		response.sendRedirect("signIn?failed=true");
	}

}
