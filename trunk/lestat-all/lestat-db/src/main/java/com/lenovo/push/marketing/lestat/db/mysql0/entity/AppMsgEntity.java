package com.lenovo.push.marketing.lestat.db.mysql0.entity;

import java.sql.Timestamp;

public class AppMsgEntity {

	// from Junye
	// `app_sid` varchar(255) DEFAULT NULL, --应用SID标识
	// `app_msgid` varchar(255) DEFAULT NULL, --应用通知消息id
	// `startdate` datetime DEFAULT NULL, --应用通知推送开始时间
	// `enddate` datetime DEFAULT NULL, --应用通知推送结束时间

	private String appSid;
	private String appMsgid;
	private Timestamp startdate;
	private Timestamp enddate;
	
	public String getAppSid() {
		return appSid;
	}
	public void setAppSid(String appSid) {
		this.appSid = appSid;
	}
	public String getAppMsgid() {
		return appMsgid;
	}
	public void setAppMsgid(String appMsgid) {
		this.appMsgid = appMsgid;
	}
	public Timestamp getStartdate() {
		return startdate;
	}
	public void setStartdate(Timestamp startdate) {
		this.startdate = startdate;
	}
	public Timestamp getEnddate() {
		return enddate;
	}
	public void setEnddate(Timestamp enddate) {
		this.enddate = enddate;
	}	
	
	@Override
	public String toString() {		
		return "(sid: " + this.getAppSid() + ", adid: " + this.getAppMsgid() + ", startdate: " + this.getStartdate() + ", enddate: " + this.getEnddate() + ")";
	}
}
