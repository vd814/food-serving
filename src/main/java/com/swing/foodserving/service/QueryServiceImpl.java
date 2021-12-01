package com.swing.foodserving.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.dao.QueryDao;
import com.swing.foodserving.entity.Query;

@Service
@Transactional
public class QueryServiceImpl implements QueryService{

	@Autowired
	QueryDao queryDao;

	@Override
	public void saveOrUpdateQuery(String email, int queryType, String query) {
		Query q = new Query();
		q.setEmail(email);
		q.setQueryType(queryType);
		q.setQuery(query);
		queryDao.saveOrUpdateQuery(q);
	}
	
	
}
