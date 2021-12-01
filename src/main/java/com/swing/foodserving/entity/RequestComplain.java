package com.swing.foodserving.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_complain")
public class RequestComplain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "request_id")
	private int requestID;
	
	@Column(name = "complain")
	private String complain;
	
	@Column(name = "vendor_replay")
	private String vendorReplay;
	
	@Column(name = "admin_replay")
	private String adminReplay;

	public String getVendorReplay() {
		return vendorReplay;
	}

	public void setVendorReplay(String vendorReplay) {
		this.vendorReplay = vendorReplay;
	}

	public String getAdminReplay() {
		return adminReplay;
	}

	public void setAdminReplay(String adminReplay) {
		this.adminReplay = adminReplay;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public String getComplain() {
		return complain;
	}

	public void setComplain(String complain) {
		this.complain = complain;
	}
}
