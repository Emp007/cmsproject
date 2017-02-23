package com.cms.model;

public class EditableInfo {

	private String createdBy;
	private long createdAt;
	private String updatedBy;
	private long updatedAt;
	
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt() {
		
		this.createdAt = System.currentTimeMillis();
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt() {
		this.updatedAt = System.currentTimeMillis();
	}
}
