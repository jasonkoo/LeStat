package com.lenovo.push.marketing.lestat.db.mdrill.service.result;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.ErrorEntity;

public class CfErrorResult {
	private String adId;
	private String sd;
	private String ed;
	private String type;
	private String errorCode;
	private long count;
	
	
	public CfErrorResult() {
		
	}
	
	public CfErrorResult(ErrorEntity errorEntity) {
		this.type = errorEntity.getType();
		this.errorCode = errorEntity.getErrorCode();
		this.count = (long) errorEntity.getCount();
	}
	
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getSd() {
		return sd;
	}
	public void setSd(String sd) {
		this.sd = sd;
	}
	public String getEd() {
		return ed;
	}
	public void setEd(String ed) {
		this.ed = ed;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
	
}
