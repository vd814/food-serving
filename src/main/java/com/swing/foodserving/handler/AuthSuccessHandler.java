package com.swing.foodserving.handler;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.dao.UserDao;
import com.swing.foodserving.entity.User;


public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	@Autowired
	UserDao userDao;

	@Override
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("inside");
		// Get the role of logged in user
		HttpSession hs=request.getSession(true);
		String checked=request.getParameter("rememberMe");
		if(checked!=null)
		{
			if(checked.equalsIgnoreCase("on"))
			{
				String uname=request.getParameter("email");
				String pwd=request.getParameter("password");
				Cookie email=new Cookie("email", uname);
				Cookie password=new Cookie("password", pwd);
				response.addCookie(email);
				response.addCookie(password);
			}
		}
		else
		{
			Cookie email=new Cookie("email", null);
			Cookie password=new Cookie("password", null);
			response.addCookie(email);
			response.addCookie(password);
		}

		User u=userDao.findByUserName(request.getParameter("email"));
		hs.setAttribute("user", u);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		List<GrantedAuthority> role = (List<GrantedAuthority>) auth.getAuthorities();
		String targetUrl = null;
		if (u.getRole() == Constant.ROLE_ADMIN) {
			targetUrl = "/u/admin/dashboard";
		}else if(u.getRole() == Constant.ROLE_PUBLIC){
			targetUrl = "/u/userDashboard";
		}else {
			targetUrl = "/u/vendorDashbord";
		}	
		return targetUrl;
	}
}