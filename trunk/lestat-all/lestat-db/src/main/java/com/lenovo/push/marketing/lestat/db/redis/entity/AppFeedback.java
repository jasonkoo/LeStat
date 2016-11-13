package com.lenovo.push.marketing.lestat.db.redis.entity;


public class AppFeedback {

	private String sid;
	private String adId;

	private String date = "19700101";
	private long pushed;
	private long arrived;
	private long displayed;
	private long clicked;
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
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	
	
}
