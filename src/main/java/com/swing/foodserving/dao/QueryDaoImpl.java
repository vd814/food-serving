package com.swing.foodserving.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.entity.Query;

@Repository
@EnableTransactionManagement
@Transactional
public class QueryDaoImpl implements QueryDao{
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateQuery(Query query) {
		getSession().save(query);
	}
}
