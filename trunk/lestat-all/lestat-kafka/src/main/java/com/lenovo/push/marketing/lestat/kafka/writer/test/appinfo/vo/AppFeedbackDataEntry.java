package com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo;

import com.lenovo.lps.push.marketing.drill.common.vo.FeedbackDataEntry;

public class AppFeedbackDataEntry extends FeedbackDataEntry {
	
	
	// (必须，是否成功:   true|false)
	private boolean success;
	//  (必须, 产生该事件的SID)
	private String sid;
	//  (当 success=true时， 必须提供errCode)
	private String errCode;
	// (可选，当eventName为install|engineUpgrade时，表示事件相关的包名)
	private String packName;
	// (可选，当eventName为install|engineUpgrade时，表示事件相关的安装前版本号，如果是初次安装，则该属性为空)
	private String currVer;
	//  (可选，当eventName为install|engineUpgrade时，表示事件相关的当前版本号)
	private String targetVer;
	//  (预留，可选： 用于带有值的事件，如点击购买按钮时该按钮所对应的商品价格)
	private String value;
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


	
}
