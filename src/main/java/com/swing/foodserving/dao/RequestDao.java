package com.swing.foodserving.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.Request;
import com.swing.foodserving.entity.RequestCancel;

public interface RequestDao {

	public void saveOrUpdateRequest(Request request);
	public Request getRequestByID(int requestID);
	public List getAllRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public List getVendorRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber,int userID);
	public List getUserRequestWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber,int userID);
	
	
}
