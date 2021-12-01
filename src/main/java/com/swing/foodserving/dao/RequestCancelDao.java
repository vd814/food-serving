package com.swing.foodserving.dao;

import java.util.List;

import com.swing.foodserving.entity.RequestCancel;

public interface RequestCancelDao {

	public void cancelRequest(RequestCancel requestCancel);
	public List getRequestCancelByRequestId(int requestID);
}
