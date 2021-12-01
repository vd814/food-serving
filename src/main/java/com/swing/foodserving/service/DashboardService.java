package com.swing.foodserving.service;

public interface DashboardService {

	public int getUserCount();
	public int getUserCountByRole(int role);
	public int getRequestCount();
	public int getRequestCountByStatus(int status);
	
	public float getAverageRatingOfVendor(int vendorID);
	public int getVendorComplainCount(int vendorID);
	
	public int getRequestCountByUser(int userID, int role);
	public int getRequestCountByUserAndStatus(int userID, int role, int status);
	
}
