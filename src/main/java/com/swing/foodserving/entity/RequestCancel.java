package com.swing.foodserving.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_cancel")
public class RequestCancel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "request_id")
	private int requestID;
	
	@Column(name = "cancel_by")
	private int cancelBy;
	
	@Column(name = "cnacel_reason")
	private String cancleReason;

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

	public int getCancelBy() {
		return cancelBy;
	}

	public void setCancelBy(int cancelBy) {
		this.cancelBy = cancelBy;
	}

	public String getCancleReason() {
		return cancleReason;
	}

	public void setCancleReason(String cancleReason) {
		this.cancleReason = cancleReason;
	}
	
	
}
