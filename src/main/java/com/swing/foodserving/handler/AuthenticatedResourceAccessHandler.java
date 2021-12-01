package com.swing.foodserving.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class AuthenticatedResourceAccessHandler implements Filter{
	
	private List<String> byPassRequest = null;

	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		byPassRequest = new ArrayList<String>();
		byPassRequest.add("/u/selectRole");
		byPassRequest.add("/u/selectedRole");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String requestedPath = req.getServletPath();
		/*HttpSession hs=req.getSession();
		if(byPassRequest.contains(requestedPath)){
			 chain.doFilter(request, response);
			 return;
		}
		Set<RoleAction> roleActionSet = (Set<RoleAction>) hs.getAttribute("selectedRoleAction");
		System.out.println("set:"+roleActionSet);
		Iterator<RoleAction> iterator = roleActionSet.iterator();
		System.out.println(iterator.hasNext());
		while(iterator.hasNext()) {
			RoleAction roleAction =iterator.next();
			
			if(("/u/"+roleAction.getAction().getName()).contains(requestedPath)) {
				chain.doFilter(request, response);
				return;
			}
		}
		res.sendRedirect("../denied");*/
		
		chain.doFilter(request, response);

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
