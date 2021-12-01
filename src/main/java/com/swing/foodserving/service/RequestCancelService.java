package com.swing.foodserving.service;

import java.util.List;

public interface RequestCancelService {
	
	public void cancleRequest(int requestID,String reason,int cancelBy);
	public List getRequestCancelByRequestID(int requestID);
}
