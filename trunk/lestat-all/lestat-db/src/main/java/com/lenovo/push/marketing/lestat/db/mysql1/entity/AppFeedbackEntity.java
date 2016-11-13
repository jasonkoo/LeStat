package com.lenovo.push.marketing.lestat.db.mysql1.entity;

public class AppFeedbackEntity {
	private String sid;
	private String adId;
	
	private String thedate;
	private String eventtype;
	private long value;
	
	
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
	public String getThedate() {
		return thedate;
	}
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	public String getEventtype() {
		return eventtype;
	}
	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}	
}
