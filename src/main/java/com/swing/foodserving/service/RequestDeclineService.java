package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestDecline;

public interface RequestDeclineService {
	public void saveRequestDecline(int requestID, String declineReason, HttpSession hs);
	public void updateRequestDecline(int requestDeclineID, String declineReason);
	public List getAllDeclineRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,String sortingValue, String columeNumber);
	public RequestDecline getRequestDeclineByID(int requestDeclineID);
}
