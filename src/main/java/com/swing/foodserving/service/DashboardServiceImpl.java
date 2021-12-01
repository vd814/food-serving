package com.swing.foodserving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.dao.DashboardDao;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService{

	@Autowired
	DashboardDao dashboardDao;

	
	@Override
	public int getUserCount() {
		return dashboardDao.getUserCount();
	}

	@Override
	public int getUserCountByRole(int role) {
		return dashboardDao.getUserCountByRole(role);
	}

	@Override
	public int getRequestCount() {
		return dashboardDao.getRequestCount();
	}

	@Override
	public int getRequestCountByStatus(int status) {
		return dashboardDao.getRequestCountByStatus(status);
	}

	
	@Override
	public float getAverageRatingOfVendor(int vendorID) {
		return dashboardDao.getAverageRatingOfVendor(vendorID);
	}

	@Override
	public int getVendorComplainCount(int vendorID) {
		return dashboardDao.getVendorComplainCount(vendorID);
	}

	@Override
	public int getRequestCountByUser(int userID, int role) {
		return dashboardDao.getRequestCountByUser(userID, role);
	}

	@Override
	public int getRequestCountByUserAndStatus(int userID, int role, int status) {
		return dashboardDao.getRequestCountByUserAndStatus(userID, role, status);
	}
	
}
