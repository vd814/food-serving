package com.swing.foodserving.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_detail")
public class Request{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "r_id")
	private int rID;
	
	@Column(name = "user_id")
	private int userID;
	
	@Column(name = "vendor_id")
	private int vendorID;
	
	@Column(name = "request_type")
	private int requestType;
	
	@Column(name = "request_discription")
	private String requestDiscription;
	
	@Column(name = "request_data")
	private String requestData;
	
	@Column(name = "request_status")
	private int requestStatus;
	
	@Column(name = "no_of_person")
	private int noOfPerson;
	
	@Column(name = "request_address")
	private String requestAddress;
	
	public int getrID() {
		return rID;
	}

	public void setrID(int rID) {
		this.rID = rID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public String getRequestDiscription() {
		return requestDiscription;
	}

	public void setRequestDiscription(String requestDiscription) {
		this.requestDiscription = requestDiscription;
	}

	public int getRequestStatus() {
		return requestStatus;
	}

	public void setRequestStatus(int requestStatus) {
		this.requestStatus = requestStatus;
	}

	public int getNoOfPerson() {
		return noOfPerson;
	}

	public void setNoOfPerson(int noOfPerson) {
		this.noOfPerson = noOfPerson;
	}

	public String getRequestAddress() {
		return requestAddress;
	}

	public void setRequestAddress(String requestAddress) {
		this.requestAddress = requestAddress;
	}

	public String getRequestData() {
		return requestData;
	}

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	public int getVendorID() {
		return vendorID;
	}

	public void setVendorID(int vendorID) {
		this.vendorID = vendorID;
	}
}
