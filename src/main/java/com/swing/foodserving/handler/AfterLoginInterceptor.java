package com.swing.foodserving.handler;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

public class AfterLoginInterceptor implements HandlerInterceptor
{
	HttpServletRequest req;
	
	@Override
	 public boolean preHandle(HttpServletRequest req, HttpServletResponse res,
	         Object handler) throws Exception {
	      HttpSession hs=req.getSession();
	     // Set<RoleAction> roleActionSet=(Set<RoleAction>)hs.getAttribute("selectedRoleAction");
	      String requested_path=req.getServletPath();
	      //System.out.println("query string  path"+req.getQueryString());
	      //System.out.println("requested path=================="+requested_path);
	      
	     /* if(user!=null)
	      {
	    	
	    	  if(user.getRole().getRole_name().equals("ROLE_USER"))
	    	  {
	    		  String[] arp=Constant.ADMIN_PAGES;
	    		  for(int i=0;i<arp.length;i++)
		    	  {
		    		  if(requested_path.equals(  
		    			  res.sendError(403,





		    		  }
		    	  }
	    	  }
	    	  else
	    	  {
	    		  String[] arp=Constant.USER_PAGES;
	    		  for(int i=0;i<arp.length;i++)
		    	  {
		    		  if(requested_path.equals(arp[i]))
		    		  {
		    			  res.sendError(403, "ACCESS DENIEDED");
		    			  return false;
		    		  }
		    	  }
	    	  }
	    	  hs.setAttribute("userFound","yes");
	      }
	      else 
	      {
	    	  String[] arp=Constant.GUEST_NOT_USE;
	    	
	    	  hs.setAttribute("userFound","no");
	    	  for(int i=0;i<arp.length;i++)
	    	  {
	    		  if(requested_path.equals(arp[i]))
	    		  {
	    			  res.sendError(401, "resource not found");
	    			  return false;
	    		  }
	    	  }
	    	
	      }
	      
	      
	    
*/	      return true;
	   }
}
