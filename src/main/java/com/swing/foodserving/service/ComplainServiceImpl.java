package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.dao.ComplainDao;
import com.swing.foodserving.entity.RequestComplain;

@Service
@Transactional
public class ComplainServiceImpl implements ComplainService{
	
	@Autowired
	ComplainDao complainDao;

	@Override
	public void saveComplain(int requestID, String complain) {
		RequestComplain rc = new RequestComplain();
		rc.setRequestID(requestID);
		rc.setComplain(complain);
		complainDao.saveOrUpdateComplain(rc);
	}

	@Override
	public List getAllComplainWithDatatable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		return complainDao.getAllComplainWithDatatable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
	}

	@Override
	public RequestComplain getComplainByID(int complainID) {
		return complainDao.getComplainByID(complainID);
	}

	@Override
	public void updateComplain(int complainID, String complain) {
		RequestComplain rc = complainDao.getComplainByID(complainID);
		rc.setComplain(complain);
		complainDao.saveOrUpdateComplain(rc);
	}

	@Override
	public void deleteComplain(int complainID) {
		complainDao.deleteComplain(complainDao.getComplainByID(complainID));
	}

	@Override
	public void addOrEditReplay(int complainID, String replay, int replayBy) {
		RequestComplain rc = complainDao.getComplainByID(complainID);
		if (replayBy == Constant.ROLE_VENDOR) {
			rc.setVendorReplay(replay);
		}else{
			rc.setAdminReplay(replay);
		}
		complainDao.saveOrUpdateComplain(rc);
	}
}
