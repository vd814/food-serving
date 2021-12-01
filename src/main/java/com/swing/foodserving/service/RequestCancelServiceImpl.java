package com.swing.foodserving.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.dao.RequestCancelDao;
import com.swing.foodserving.entity.RequestCancel;

@Service
@Transactional
public class RequestCancelServiceImpl implements RequestCancelService{

	@Autowired
	RequestCancelDao requestCancelDao;
	
	
	@Override
	public void cancleRequest(int requestID, String reason, int cancelBy) {
		RequestCancel rc = new RequestCancel();
		rc.setCancelBy(cancelBy);
		rc.setRequestID(requestID);
		rc.setCancleReason(reason);
		requestCancelDao.cancelRequest(rc);
	}

	@Override
	public List getRequestCancelByRequestID(int requestID) {
		return requestCancelDao.getRequestCancelByRequestId(requestID);
	}

}
