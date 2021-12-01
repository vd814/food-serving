package com.swing.foodserving.constant;

public class Constant {
	//Role Constant
	public static final int ROLE_ADMIN = 1;
	public static final int ROLE_VENDOR = 2;
	public static final int ROLE_PUBLIC = 3;
	
	//URL Constant
	public static final String DOMAIN_URL = "http://localhost:8080";
	public static final String BASEURL = "http://localhost:8080/food_serving/";
	public static final String AFTER_LOGIN_BASEURL = BASEURL + "u/";
	public static final String AFTER_LOGIN_ADMIN_BASEURL = BASEURL + "u/admin/";
	
	//Status Constant
	public static final int DELETED_TRUE = 1;
	public static final int DELETED_FALSE = 0;
	
	public static final int ACTIVE = 1;
	public static final int DEACTIVE = 0;
	
	//Gender Constant
	public static final int MALE = 1;
	public static final int FEMALE = 2;
	public static final int OTHER = 3;
	
	//Request Type Constant
	public static final int FOOD_EXTRA = 1;
	public static final int FOOD_NEED = 2;
	
	//Request Status Constant
	public static final int REQUEST_PENDDING = 1;
	public static final int REQUEST_ASSIGN = 2;
	public static final int REQUEST_ACCEPT = 3;
	public static final int REQUEST_ON_THE_WAY = 4;
	public static final int REQUEST_FOOD_COLLECTED = 5;
	public static final int REQUEST_FOOD_DELIVERED = 6;
	public static final int REQUEST_DECLINE = 7;
	public static final int REQUEST_CANCEL = 8;
	
	//Query Status
	public static final int QUERY_LOGIN = 1;
	public static final int QUERY_OTHER = 2;
	
}
