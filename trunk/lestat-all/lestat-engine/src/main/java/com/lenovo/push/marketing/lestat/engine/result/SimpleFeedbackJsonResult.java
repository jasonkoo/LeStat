package com.lenovo.push.marketing.lestat.engine.result;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.push.marketing.lestat.db.mdrill.service.result.SimpleFeedbackResult;



/**
 * @author gulei2
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class SimpleFeedbackJsonResult extends BaseJsonResult {

	private String date;
	private String adId;

	private long arrived;
	private long displayed;
	private long sysmsgclicked;
	private long s2nddisplayed;
	private long s2ndclicked;
	private long downloaded;
	private long installed;
	private long activated;
	
	long distCount = 0;
	
	
	public SimpleFeedbackJsonResult(SimpleFeedbackResult feedback) {
		super();
		this.date = feedback.getDate();
		this.adId = feedback.getAdId();
		
		this.arrived = feedback.getArrived();
		this.displayed = feedback.getDisplayed();
		this.sysmsgclicked = feedback.getSysmsgclicked();
		this.s2nddisplayed = feedback.getS2nddisplayed();
		this.s2ndclicked = feedback.getS2ndclicked();
		this.downloaded = feedback.getDownloaded();
		this.installed = feedback.getInstalled();
		this.activated = feedback.getActivated();
		
		this.distCount = feedback.getDistCount();
	}



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
