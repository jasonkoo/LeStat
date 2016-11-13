package com.lenovo.push.marketing.lestat.db.mysql1.entity;

public class AppDeviceModelResult extends BaseResult {
	private String sid;
	private String deviceModel;
	private long actNum;
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public long getActNum() {
		return actNum;
	}
	public void setActNum(long actNum) {
		this.actNum = actNum;
	}
	
	
}
