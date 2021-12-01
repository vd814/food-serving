package com.swing.foodserving.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestDecline;

public interface RequestDeclineDao {
	
	public void addRequestDecline(RequestDecline requestDecline);
	public void editRequestDecline(RequestDecline requestDecline);
	public List getAllDeclineRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,String sortingValue, String columeNumber);
	public RequestDecline getRequestDeclineByID(int requestDeclineID);
}
