package com.lenovo.push.marketing.lestat.db.mdrill.service.result;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.FeedbackEntity;

public class FeedbackResult {

	private String sd;
	private String ed;
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
	
	
	public FeedbackResult() {
		
	}
	
	// Construct FeedbackResult Through FeedbackEntity
	public FeedbackResult(FeedbackEntity fbEntity) {
		this.setSd(fbEntity.getThedate());
		this.setEd(fbEntity.getThedate());
		this.setAdId(fbEntity.getAdId());
		this.setPkgName(fbEntity.getPkgName());
		
		this.setArrived( (long)fbEntity.getArrived() );
		this.setDisplayed( (long)fbEntity.getDisplayed() );
		this.setSysmsgclicked( (long)fbEntity.getSysmsgclicked() );
		this.setS2nddisplayed( (long)fbEntity.getS2nddisplayed() );
		this.setS2ndclicked( (long)fbEntity.getS2ndclicked() );
		this.setDownloaded( (long)fbEntity.getDownloaded() );
		this.setInstalled( (long)fbEntity.getInstalled() );
		this.setActivated( (long)fbEntity.getActivated() );
	}
	
	public FeedbackResult(FeedbackEntity fbEntity, String sd, String ed) {
		this.setSd(sd);
		this.setEd(ed);
		this.setAdId(fbEntity.getAdId());
		this.setPkgName(fbEntity.getPkgName());
		
		this.setArrived( (long)fbEntity.getArrived() );
		this.setDisplayed( (long)fbEntity.getDisplayed() );
		this.setSysmsgclicked( (long)fbEntity.getSysmsgclicked() );
		this.setS2nddisplayed( (long)fbEntity.getS2nddisplayed() );
		this.setS2ndclicked( (long)fbEntity.getS2ndclicked() );
		this.setDownloaded( (long)fbEntity.getDownloaded() );
		this.setInstalled( (long)fbEntity.getInstalled() );
		this.setActivated( (long)fbEntity.getActivated() );
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

	public String getAdId() {
		return adId;
	}

	public void setAdId(String adId) {
		this.adId = adId;
	}

	public String getPkgName() {
		return pkgName;
	}

	public void setPkgName(String pkgName) {
		this.pkgName = pkgName;
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

	public long getArrived() {
		return arrived;
	}

	public void setArrived(long arrived) {
		this.arrived = arrived;
	}

}
