package com.lenovo.push.marketing.lestat.db.mysql1.entity;

public class AppVersionResult extends BaseResult {
	
	private String sid;	
	private String verCode;
	private long actNum;
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getVerCode() {
		return verCode;
	}
	public void setVerCode(String verCode) {
		this.verCode = verCode;
	}
	public long getActNum() {
		return actNum;
	}
	public void setActNum(long actNum) {
		this.actNum = actNum;
	}
	
	
}
