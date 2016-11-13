package com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo;

import java.util.Date;

import com.lenovo.lps.push.marketing.drill.common.vo.UserDataEntry;

public class AppDataEntry extends UserDataEntry {

	// AppInfo中包含以下属性：
	private String sid;
	// (应用包名)
	private String appPkgName;
	// (应用版本名称)
	private String appVerName;
	// (应用版本号)
	private String appVerCode;
	// (预留:集成方式)
	private String integratedMode;
	// (预留:引擎工作方式)
	private String engineWorkMode;
	
	private Date logTime;

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getAppPkgName() {
		return appPkgName;
	}

	public void setAppPkgName(String appPkgName) {
		this.appPkgName = appPkgName;
	}

	public String getAppVerName() {
		return appVerName;
	}

	public void setAppVerName(String appVerName) {
		this.appVerName = appVerName;
	}

	public String getAppVerCode() {
		return appVerCode;
	}

	public void setAppVerCode(String appVerCode) {
		this.appVerCode = appVerCode;
	}

	public String getIntegratedMode() {
		return integratedMode;
	}

	public void setIntegratedMode(String integratedMode) {
		this.integratedMode = integratedMode;
	}

	public String getEngineWorkMode() {
		return engineWorkMode;
	}

	public void setEngineWorkMode(String engineWorkMode) {
		this.engineWorkMode = engineWorkMode;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

}
