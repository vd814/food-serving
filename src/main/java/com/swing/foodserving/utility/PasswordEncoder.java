package com.swing.foodserving.utility;

import javax.servlet.http.HttpSession;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;



public class PasswordEncoder {
	public static String getEncodedPassword(String password) {
		BCryptPasswordEncoder bcpe = new BCryptPasswordEncoder();
		String encodedPassword = bcpe.encode(password);
		return encodedPassword;
	}
	public static boolean macthPassword(String oldPassword, String dbPassword) {
		return new BCryptPasswordEncoder().matches(oldPassword, dbPassword);
	}
	public static void main(String[] args) {
		System.out.println(getEncodedPassword("12345"));
	}
}
