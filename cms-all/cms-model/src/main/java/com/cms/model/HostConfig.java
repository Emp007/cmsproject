package com.cms.model;

public class HostConfig {

	private String welcomePage;
	private boolean isActive;
	private String statusName;
	private long activeAt;
	private long inactiveAt;
	private long activeInactivePeriod;
	private EditableInfo editableInfo;
	
	public String getWelcomePage() {
		return welcomePage;
	}
	public void setWelcomePage(String welcomePage) {
		this.welcomePage = welcomePage;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public long getActiveAt() {
		return activeAt;
	}
	public void setActiveAt(long activeAt) {
		this.activeAt = activeAt;
	}
	public long getActiveInactivePeriod() {
		return activeInactivePeriod;
	}
	public void setActiveInactivePeriod(long activeInactivePeriod) {
		this.activeInactivePeriod = activeInactivePeriod;
	}
	public EditableInfo getEditableInfo() {
		return editableInfo;
	}
	public void setEditableInfo(EditableInfo editableInfo) {
		this.editableInfo = editableInfo;
	}
	public long getInactiveAt() {
		return inactiveAt;
	}
	public void setInactiveAt(long inactiveAt) {
		this.inactiveAt = inactiveAt;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
