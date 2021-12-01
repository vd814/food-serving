package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.dao.RequestDao;
import com.swing.foodserving.entity.Request;
import com.swing.foodserving.entity.RequestCancel;
import com.swing.foodserving.utility.UserUtil;

@Service
@Transactional
public class RequestServiceImpl implements RequestService{
	
	@Autowired
	RequestDao requestDao;

	@Override
	public void createRequest(int noOfPerson, String address, String description, int requestType, String requestData, HttpSession session) {
		Request request = new Request();
		request.setNoOfPerson(noOfPerson);
		request.setRequestAddress(address);
		request.setRequestDiscription(description);
		request.setRequestData(requestData);
		request.setUserID(UserUtil.getUser(session).getUserId());
		request.setVendorID(0);
		request.setRequestType(requestType);
		request.setRequestStatus(Constant.REQUEST_PENDDING);
		requestDao.saveOrUpdateRequest(request);
	}
	
	@Override
	public void editRequest(int requestID, int noOfPerson,String address, String description, String requestData) {
		Request request = requestDao.getRequestByID(requestID);
		request.setNoOfPerson(noOfPerson);
		request.setRequestAddress(address);
		request.setRequestDiscription(description);
		request.setRequestData(requestData);
		requestDao.saveOrUpdateRequest(request);
	}

	@Override
	public List getAllRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		return requestDao.getAllRequestWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		
	}

	@Override
	public void requestAssign(int requestID, int vendorID) {
		
		Request re = requestDao.getRequestByID(requestID);
		re.setRequestStatus(Constant.REQUEST_ASSIGN);
		re.setVendorID(vendorID);
		requestDao.saveOrUpdateRequest(re);
		
	}

	@Override
	public void changeRequestStatus(int requestID, int requestStatus) {
		Request re = requestDao.getRequestByID(requestID);
		re.setRequestStatus(requestStatus);
		if (requestStatus == Constant.REQUEST_PENDDING) {
			re.setVendorID(0);
		}
		requestDao.saveOrUpdateRequest(re);
	}

	@Override
	public List getVendorRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber, int userId) {
		return requestDao.getVendorRequestWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber, userId);
	}

	@Override
	public List getUserRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber, int userId) {
		return requestDao.getUserRequestWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber, userId);
	}

	@Override
	public Request getRequestByRequestID(int requestID) {
		return requestDao.getRequestByID(requestID);
	}

}
