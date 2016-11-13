package com.lenovo.push.marketing.lestat.db.mysql1.entity;

public class TinySUSFeedbackResult extends BaseResult {
	private String appKey;
	private String targetVercode;
	private String channelKey;
	
	private String startDate = "19700101";
	private String endDate = "19700101";	
	
	private String eventName;
	private long cnt;
	
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getTargetVercode() {
		return targetVercode;
	}
	public void setTargetVercode(String targetVercode) {
		this.targetVercode = targetVercode;
	}
	public String getChannelKey() {
		return channelKey;
	}
	public void setChannelKey(String channelKey) {
		this.channelKey = channelKey;
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
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public long getCnt() {
		return cnt;
	}
	public void setCnt(long cnt) {
		this.cnt = cnt;
	}	
}
