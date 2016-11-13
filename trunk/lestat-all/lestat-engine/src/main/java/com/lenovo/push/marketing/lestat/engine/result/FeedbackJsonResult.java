package com.lenovo.push.marketing.lestat.engine.result;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.push.marketing.lestat.db.mdrill.service.result.FeedbackResult;



/**
 * @author liuhk2
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class FeedbackJsonResult extends BaseJsonResult {

	private String sd = "19700101";
	private String ed = "19700101";
	private String adId;
	private String pkgName;

	private long arrived = 0;
	private long displayed = 0;
	private long sysmsgclicked = 0;
	private long s2nddisplayed = 0;
	private long s2ndclicked = 0;
	private long downloaded = 0;
	private long installed = 0;
	private long activated = 0;
	
	public FeedbackJsonResult(FeedbackResult feedback) {
		super();
		this.sd = feedback.getSd();
		this.ed = feedback.getEd();
		this.adId = feedback.getAdId();
		this.pkgName = feedback.getPkgName();
		
		//this.arrived = feedback.getArrived();
		this.displayed = feedback.getDisplayed();
		this.sysmsgclicked = feedback.getSysmsgclicked();
		this.s2nddisplayed = feedback.getS2nddisplayed();
		this.s2ndclicked = feedback.getS2ndclicked();
		this.downloaded = feedback.getDownloaded();
		this.installed = feedback.getInstalled();
		this.activated = feedback.getActivated();
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

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
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
	
	
}
