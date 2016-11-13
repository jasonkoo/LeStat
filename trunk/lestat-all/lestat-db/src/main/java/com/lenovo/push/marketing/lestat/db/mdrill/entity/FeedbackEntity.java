package com.lenovo.push.marketing.lestat.db.mdrill.entity;

public class FeedbackEntity extends BaseEntity {

	private String thedate;
	private String adId;
	private String pid;
	private String pkgName; // ac_id
	private String minute5;
	private String hour;
	
	private double arrived;
	private double displayed;
	private double sysmsgclicked;
	private double s2nddisplayed;
	private double s2ndclicked;
	private double downloaded;
	private double installed;
	private double activated;
	
	private double sum;

	public String getThedate() {
		return thedate;
	}

	public void setThedate(String thedate) {
		this.thedate = thedate;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
	}

	public String getMinute5() {
		return minute5;
	}

	public void setMinute5(String minute5) {
		this.minute5 = minute5;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public double getArrived() {
		return arrived;
	}

	public void setArrived(double arrived) {
		this.arrived = arrived;
	}

	public double getDisplayed() {
		return displayed;
	}

	public void setDisplayed(double displayed) {
		this.displayed = displayed;
	}

	public double getSysmsgclicked() {
		return sysmsgclicked;
	}

	public void setSysmsgclicked(double sysmsgclicked) {
		this.sysmsgclicked = sysmsgclicked;
	}

	public double getS2nddisplayed() {
		return s2nddisplayed;
	}

	public void setS2nddisplayed(double s2nddisplayed) {
		this.s2nddisplayed = s2nddisplayed;
	}

	public double getS2ndclicked() {
		return s2ndclicked;
	}

	public void setS2ndclicked(double s2ndclicked) {
		this.s2ndclicked = s2ndclicked;
	}

	public double getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(double downloaded) {
		this.downloaded = downloaded;
	}

	public double getInstalled() {
		return installed;
	}

	public void setInstalled(double installed) {
		this.installed = installed;
	}

	public double getActivated() {
		return activated;
	}

	public void setActivated(double activated) {
		this.activated = activated;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}
	
	
	
	

	
	
}
