package com.lenovo.push.marketing.lestat.kafka.feedback;

import java.io.Serializable;

import com.lenovo.lps.push.common.vo.FeedbackEvent;

public class PrecisionFeedbackEvent implements Serializable{
	
	private static final long serialVersionUID = 1234919598728795983L;
	
	
	private Long pid;
	private String bizType;
	private String eventName; // (必须，事件名称:   可能包含这些值：click|display|install|engineUpgrade|clike2end|display2end ... )
	private String feedbackId; //  (必须，反馈Id)
	private boolean success; // (必须，是否成功:   true|false)
	private String sid; // (必须, 产生该事件的SID)
	private String errCode; // (当 success=false时， 必须提供errCode)
	private String packName; // (可选，当eventName为install|engineUpgrade时，表示事件相关的包名)
	private String currVer; // (可选，当eventName为install|engineUpgrade时，表示事件相关的安装前版本号，如果是初次安装，则该属性为空)
	private String targetVer; //  (可选，当eventName为install|engineUpgrade时，表示事件相关的当前版本号)
	private String value;
	private String taskAppPkgName; // 应用包名
	
	
	public PrecisionFeedbackEvent() {
		
	}
	
	public PrecisionFeedbackEvent(FeedbackEvent fbEvent) {
		this.pid = fbEvent.getPid();
		this.bizType = fbEvent.getBizType();
		this.eventName = fbEvent.getEventName();
		this.feedbackId = fbEvent.getFeedbackId();
		this.success = fbEvent.isSuccess();
		this.sid = fbEvent.getSid();
		this.errCode = fbEvent.getErrCode();
		this.packName = fbEvent.getPackName();
		this.currVer = fbEvent.getCurrVer();
		this.targetVer = fbEvent.getTargetVer();
		this.value = fbEvent.getValue();
	}
	
	
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getFeedbackId() {
		return feedbackId;
	}
	public void setFeedbackId(String feedbackId) {
		this.feedbackId = feedbackId;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getErrCode() {
		return errCode;
	}
	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}
	public String getPackName() {
		return packName;
	}
	public void setPackName(String packName) {
		this.packName = packName;
	}
	public String getCurrVer() {
		return currVer;
	}
	public void setCurrVer(String currVer) {
		this.currVer = currVer;
	}
	public String getTargetVer() {
		return targetVer;
	}
	public void setTargetVer(String targetVer) {
		this.targetVer = targetVer;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTaskAppPkgName() {
		return taskAppPkgName;
	}
	public void setTaskAppPkgName(String taskAppPkgName) {
		this.taskAppPkgName = taskAppPkgName;
	}	
	
}
