package com.lenovo.push.marketing.lestat.db.mdrill.service.result;

/**
 * @author gulei2
 */
public class SimpleFeedbackResult {

	String date;
	String adId;

	long arrived = 0;
	long displayed = 0;
	long sysmsgclicked = 0;
	long s2nddisplayed = 0;
	long s2ndclicked = 0;
	long downloaded = 0;
	long installed = 0;
	long activated = 0;
	
	long distCount = 0;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public long getArrived() {
		return arrived;
	}

	public void setArrived(long arrived) {
		this.arrived = arrived;
	}

	public long getDisplayed() {
		return displayed;
	}

	public void setDisplayed(long displayed) {
		this.displayed = displayed;
	}

	public long getSysmsgclicked() {
		return sysmsgclicked;
	}

	public void setSysmsgclicked(long sysmsgclicked) {
		this.sysmsgclicked = sysmsgclicked;
	}

	public long getS2nddisplayed() {
		return s2nddisplayed;
	}

	public void setS2nddisplayed(long s2nddisplayed) {
		this.s2nddisplayed = s2nddisplayed;
	}

	public long getS2ndclicked() {
		return s2ndclicked;
	}

	public void setS2ndclicked(long s2ndclicked) {
		this.s2ndclicked = s2ndclicked;
	}

	public long getDownloaded() {
		return downloaded;
	}

	public void setDownloaded(long downloaded) {
		this.downloaded = downloaded;
	}

	public long getInstalled() {
		return installed;
	}

	public void setInstalled(long installed) {
		this.installed = installed;
	}

	public long getActivated() {
		return activated;
	}

	public void setActivated(long activated) {
		this.activated = activated;
	}

	public long getDistCount() {
		return distCount;
	}

	public void setDistCount(long distCount) {
		this.distCount = distCount;
	}	

	
	
}
