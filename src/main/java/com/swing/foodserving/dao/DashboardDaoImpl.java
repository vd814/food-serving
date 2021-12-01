package com.swing.foodserving.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;

@Repository
@EnableTransactionManagement
@Transactional
public class DashboardDaoImpl implements DashboardDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	
	@Override
	public int getUserCount() {
		String query = "SELECT COUNT(*) FROM User "
				+ "WHERE isActive = " + Constant.ACTIVE + " "
				+ "AND isDeleted = " + Constant.DELETED_FALSE;
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}

	@Override
	public int getUserCountByRole(int role) {
		String query = "SELECT COUNT(*) FROM User "
				+ "WHERE isActive = " + Constant.ACTIVE + " "
				+ "AND isDeleted = " + Constant.DELETED_FALSE +" "
				+ "AND role = " + role;
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}

	@Override
	public int getRequestCount() {
		String query = "SELECT COUNT(*) FROM Request";
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}

	@Override
	public int getRequestCountByStatus(int status) {
		String query = "SELECT COUNT(*) FROM Request "
					+ "WHERE requestStatus = " + status +" ";
		if (status == Constant.REQUEST_ACCEPT) {
			query += "OR requestStatus = " + Constant.REQUEST_ON_THE_WAY + " "
					+ "OR requestStatus = " + Constant.REQUEST_FOOD_COLLECTED;
		}
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}

	
	@Override
	public float getAverageRatingOfVendor(int vendorID) {
		String query = "SELECT AVG(rt.rating) FROM RequestReviewAndRating rt "
				+ "INNER JOIN Request r ON r.rID = rt.requestID "
				+ "INNER JOIN User u ON u.userId = r.vendorID "
				+ "WHERE u.userId = " + vendorID;
		Double rating  = (Double)getSession().createQuery(query).uniqueResult();
		return rating != null ? rating.floatValue() : 0;
	}

	@Override
	public int getVendorComplainCount(int vendorID) {
		String query = "SELECT COUNT(*) FROM RequestComplain rc "
				+ "INNER JOIN Request r ON r.rID = rc.requestID "
				+ "INNER JOIN User u ON u.userId = r.vendorID "
				+ "WHERE u.userId = " + vendorID;
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}


	@Override
	public int getRequestCountByUser(int userID, int role) {
		String query = "SELECT COUNT(*) FROM Request ";
		if (role == Constant.ROLE_VENDOR) {
			query += "WHERE vendorID = " + userID;
		}else {
			query += "WHERE userID = " + userID;
		}
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}


	@Override
	public int getRequestCountByUserAndStatus(int userID, int role, int status) {
		String query = "SELECT COUNT(*) FROM Request ";
		if (role == Constant.ROLE_VENDOR) {
			query += "WHERE vendorID = " + userID + " ";
		}else {
			query += "WHERE userID = " + userID + " ";
		}
		query += "AND requestStatus = " + status;
		return ((Long)getSession().createQuery(query).uniqueResult()).intValue();
	}
	
}
