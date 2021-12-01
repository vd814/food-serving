package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.dao.RequestDeclineDao;
import com.swing.foodserving.entity.RequestDecline;
import com.swing.foodserving.utility.UserUtil;

@Service
@Transactional
public class RequestDeclineServiceImpl implements RequestDeclineService{

	@Autowired
	RequestDeclineDao requestDeclineDao;
	
	@Override
	public void saveRequestDecline(int requestID, String declineReason, HttpSession hs) {
		RequestDecline rd = new RequestDecline();
		rd.setReason(declineReason);
		rd.setRequestID(requestID);
		rd.setUserID(UserUtil.getUser(hs).getUserId());
		
		requestDeclineDao.addRequestDecline(rd);
		
	}


	@Override
	public RequestDecline getRequestDeclineByID(int requestDeclineID) {
		return requestDeclineDao.getRequestDeclineByID(requestDeclineID);
	}

	@Override
	public void updateRequestDecline(int requestDeclineID, String declineReason) {
		RequestDecline rd = requestDeclineDao.getRequestDeclineByID(requestDeclineID);
		rd.setReason(declineReason);
		requestDeclineDao.editRequestDecline(rd);
	}

	@Override
	public List getAllDeclineRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber) {
		return requestDeclineDao.getAllDeclineRequestWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
	}

}
