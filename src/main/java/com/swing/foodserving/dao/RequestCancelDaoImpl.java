package com.swing.foodserving.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.entity.RequestCancel;

@Repository
@EnableTransactionManagement
@Transactional
public class RequestCancelDaoImpl implements RequestCancelDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void cancelRequest(RequestCancel requestCancel) {
		getSession().save(requestCancel);
	}

	@Override
	public List getRequestCancelByRequestId(int requestID) {
		String query = "SELECT r,u FROM RequestCancel r "
					+ "INNER JOIN User u ON u.userId = r.cancelBy "
					+ "WHERE r.requestID = " + requestID;
		return getSession().createQuery(query).list();
	}

}
