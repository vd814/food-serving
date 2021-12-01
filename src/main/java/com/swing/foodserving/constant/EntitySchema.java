package com.swing.foodserving.constant;

import java.util.HashMap;
import java.util.Map;

public class EntitySchema {
	private static final Map<Integer, String> userColumn = new HashMap<Integer, String>();
	private static final Map<Integer, String> requestColumnUser = new HashMap<Integer, String>();
	private static final Map<Integer, String> requestColumnAdmin = new HashMap<Integer, String>();
	private static final Map<Integer, String> requestColumnVendor = new HashMap<Integer, String>();
	private static final Map<Integer, String> requestDeclineColumn = new HashMap<Integer, String>();
	private static final Map<Integer, String> reviewRatingColumn = new HashMap<Integer, String>();
	private static final Map<Integer, String> complainColumn = new HashMap<Integer, String>();
	
	private static final Map<String, Map<Integer, String>> entityColumnContainer = new HashMap<String, Map<Integer,String>>();
	
	static {
		addUserColumn();
		addrequestColumnUser();
		addrequestColumnAdmin();
		addrequestColumnVendor();
		addrequestDeclineColumn();
		addReviewRatingColumn();
		addcomplainColumn();
		
		entityColumnContainer.put(DataTableEntityConstant.USER, userColumn);
		entityColumnContainer.put(DataTableEntityConstant.REQUEST_USER, requestColumnUser);
		entityColumnContainer.put(DataTableEntityConstant.REQUEST_ADMIN, requestColumnAdmin);
		entityColumnContainer.put(DataTableEntityConstant.REQUEST_VENDOR, requestColumnVendor);
		entityColumnContainer.put(DataTableEntityConstant.REQUEST_DECLINE, requestDeclineColumn);
		entityColumnContainer.put(DataTableEntityConstant.REVIEW_RATING, reviewRatingColumn);
		entityColumnContainer.put(DataTableEntityConstant.COMPLAIN, complainColumn);
		
	}
	
	private static void addUserColumn() {
		userColumn.put(0, "user.userId");
		userColumn.put(1, "user.name");
		userColumn.put(2, "user.username");
		userColumn.put(3, "user.email");
	}
	
	private static void addrequestColumnUser() {
		requestColumnUser.put(0, "re.rID");
		requestColumnUser.put(1, "re.requestType");
		requestColumnUser.put(3, "re.requestStatus");
	}
	
	private static void addrequestColumnAdmin() {
		requestColumnAdmin.put(0, "re.rID");
		requestColumnAdmin.put(1, "re.requestType");
		requestColumnAdmin.put(4, "re.requestStatus");
	}
	
	private static void addrequestColumnVendor() {
		requestColumnVendor.put(0, "re.rID");
		requestColumnVendor.put(1, "re.requestType");
		requestColumnVendor.put(3, "re.requestStatus");
	}
	
	private static void addrequestDeclineColumn() {
		requestDeclineColumn.put(0, "rd.requestID");
		requestDeclineColumn.put(1, "r.requestType");
	}
	
	private static void addReviewRatingColumn() {
		reviewRatingColumn.put(0, "r.rID");
		reviewRatingColumn.put(1, "r.requestType");
		reviewRatingColumn.put(2, "u.name");
		reviewRatingColumn.put(3, "v.name");
	}
	
	private static void addcomplainColumn() {
		complainColumn.put(0, "rc.id");
		complainColumn.put(1, "r.requestType");
		complainColumn.put(2, "u.name");
		complainColumn.put(3, "v.name");
	}
	
	public static String getSortingcolumnName(String entityName,Integer key) {
		return entityColumnContainer.get(entityName).get(key);
	}
}
