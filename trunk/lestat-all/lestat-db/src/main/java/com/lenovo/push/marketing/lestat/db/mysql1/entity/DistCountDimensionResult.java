package com.lenovo.push.marketing.lestat.db.mysql1.entity;


public class DistCountDimensionResult extends BaseResult {

	private String key;
	private long value;
	public long getValue() {
		return value;
	}
	public void setValue(long value) {
		this.value = value;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

}
