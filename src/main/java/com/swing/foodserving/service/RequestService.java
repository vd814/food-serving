package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.Request;

public interface RequestService {

	public void createRequest(int noOfPerson,String address, String description, int requestType, String requestData, HttpSession session);
	public void editRequest(int requestID, int noOfPerson,String address, String description, String requestData);
	public List getAllRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public List getVendorRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber,int userId);
	public List getUserRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber,int userId);
	public void requestAssign(int requestID,int vendorID);
	public void changeRequestStatus(int requestID,int requestStatus);
	public Request getRequestByRequestID(int requestID);
	
}
