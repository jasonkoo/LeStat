package com.lenovo.push.marketing.lestat.db.mysql0.entity;

import java.util.Date;

public class DisturbanceResult {
	private String thedate;
	private int hitCount;
	private int numUsers;
	
	private Date ts;
	
	public String getThedate() {
		return thedate;
	}
	public void setThedate(String thedate) {
		this.thedate = thedate;
	}
	public int getHitCount() {
		return hitCount;
	}
	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}
	public int getNumUsers() {
		return numUsers;
	}
	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}	
	public Date getTs() {
		return ts;
	}
	public void setTs(Date ts) {
		this.ts = ts;
	}
	
}
