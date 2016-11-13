package com.lenovo.push.marketing.lestat.db.mysql1.entity;


public class AppFeedbackResult extends BaseResult {

	private String sid;
	private String adId;

	private String startDate = "19700101";
	private String endDate = "19700101";
	private long pushed;
	private long arrived;
	private long displayed;
	private long clicked;
	private boolean overallFlag;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public long getPushed() {
		return pushed;
	}
	public void setPushed(long pushed) {
		this.pushed = pushed;
	}
	public long getArrived() {
		return arrived;
	}
	public void setArrived(long arrived) {
		this.arrived = arrived;
	}
	public long getDisplayed() {
		return displayed;
	}
	public void setDisplayed(long displayed) {
		this.displayed = displayed;
	}
	public long getClicked() {
		return clicked;
	}
	public void setClicked(long clicked) {
		this.clicked = clicked;
	}
	public boolean isOverallFlag() {
		return overallFlag;
	}
	public void setOverallFlag(boolean overallFlag) {
		this.overallFlag = overallFlag;
	}
	
	
}
