package com.lenovo.push.marketing.lestat.common.vo;

import java.util.Date;

public class SUSFeedback {
	/*
	=============defined by Chen============= 
	deviceId,packageId,appKey,currentVername,currentVercode,targetVername,targetVercode,channelKey,eventName,errorCode,系统保留1,系统保留2,系统保留3,系统保留4,系统保留5, 
	eventName可能值：
	notify_show     通知栏更新通知已显示
	notify_click       通知栏更新通知已点击
	alert_show       显示提示升级对话框
	alert_conform 用户点击确认升级按钮
	alert_cancle     用户点击取消升级按钮
	download_start        开始下载
	download_success   下载完成
	install_alert_show   显示安装提示对话框
	install_alert_conform      用户确认安装
	install_alert_cancle 用户取消安装
	install_start      开始安装
	check_upgrade        服务端收到检测升级请求
	error  错误
	*/
	public final static String EVENTNAME_NOTIFY_SHOW = "notify_show";
	public final static String EVENTNAME_NOTIFY_CLICK = "notify_click";
	public final static String EVENTNAME_ALERT_SHOW = "alert_show";
	public final static String EVENTNAME_ALERT_CONFORM = "alert_conform";
	public final static String EVENTNAME_ALERT_CANCLE = "alert_cancle";
	public final static String EVENTNAME_DOWNLOAD_START = "download_start";
	public final static String EVENTNAME_DOWNLOAD_SUCCESS = "download_success";
	public final static String EVENTNAME_INSTALL_ALERT_SHOW = "install_alert_show";
	public final static String EVENTNAME_INSTALL_ALERT_CONFORM = "install_alert_conform";
	public final static String EVENTNAME_INSTALL_ALERT_CANCLE = "install_alert_cancle";
	public final static String EVENTNAME_INSTALL_START = "install_start";
	public final static String EVENTNAME_CHECK_UPGRADE = "check_upgrade";
	public final static String EVENTNAME_ERROR = "error";
	
	
	private String deviceId;
	private String packageId;
	private String appKey;
	private String currentVername;
	private String currentVercode;
	private String targetVername;
	private String targetVercode;
	private String channelKey;
	private String eventName;
	private String errorCode;
	
	private Date logTime;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getPackageId() {
		return packageId;
	}
	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getCurrentVername() {
		return currentVername;
	}
	public void setCurrentVername(String currentVername) {
		this.currentVername = currentVername;
	}
	public String getCurrentVercode() {
		return currentVercode;
	}
	public void setCurrentVercode(String currentVercode) {
		this.currentVercode = currentVercode;
	}
	public String getTargetVername() {
		return targetVername;
	}
	public void setTargetVername(String targetVername) {
		this.targetVername = targetVername;
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
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public Date getLogTime() {
		return logTime;
	}
	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}
	
	
	
}
