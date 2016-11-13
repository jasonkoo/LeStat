package com.lenovo.push.marketing.lestat.db.mysqlx.entity;

public class HostEntity extends BaseEntity {
	private String name;
	private String ip;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
