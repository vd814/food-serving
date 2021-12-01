package com.swing.foodserving.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public  class AbstractTimestampEntityWithActiveAndDelete {
	
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at",insertable=true)
	private Date createdAt = new Date();

	
	@Column(name="is_active", columnDefinition="SMALLINT(1) default 0 ")
	private int isActive;
	
	@Column(name="is_deleted", columnDefinition="SMALLINT(1) default 0")
	private int isDeleted;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public AbstractTimestampEntityWithActiveAndDelete(int isActive) {
		this.isActive = isActive;
	}

	public AbstractTimestampEntityWithActiveAndDelete() {
		
	}
}