package com.swing.foodserving.utility;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.User;



public class UserUtil {
	public static User getUser(HttpSession session) {
		if(session!=null) {
			return (User) session.getAttribute("user");
		}
		return null;
	}
}
