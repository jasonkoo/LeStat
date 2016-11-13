package com.lenovo.push.marketing.lestat.db.mysql1.entity;

import java.sql.Timestamp;


public class BaseResult {

	private long id;
	private Timestamp lastModifiedTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Timestamp getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Timestamp lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}
}
