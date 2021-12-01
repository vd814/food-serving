package com.swing.foodserving.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.RequestComplain;

public interface ComplainDao {
	
	public void saveOrUpdateComplain(RequestComplain rc);
	public List getAllComplainWithDatatable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public RequestComplain getComplainByID(int complainID);
	public void deleteComplain(RequestComplain rc);
}
