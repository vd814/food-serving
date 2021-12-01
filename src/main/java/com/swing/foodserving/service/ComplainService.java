package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestComplain;

public interface ComplainService {
	
	public void saveComplain(int requestID, String complain);
	public List getAllComplainWithDatatable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public RequestComplain getComplainByID(int complainID);
	public void updateComplain(int complainID, String complain);
	public void deleteComplain(int complainID);
	public void addOrEditReplay(int complainID, String replay, int replayBy);
}
