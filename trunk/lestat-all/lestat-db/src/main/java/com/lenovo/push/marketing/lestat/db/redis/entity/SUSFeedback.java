package com.lenovo.push.marketing.lestat.db.redis.entity;


public class SUSFeedback {
	
	

	private long notifyShow;
	private long notifyClick;
	private long alertShow;
	private long alertConform;
	private long alertCancle;
	private long downloadStart;
	private long downloadSuccess;
	private long installAlertShow;
	private long installAlertConform;
	private long installAlertCancle;
	private long installStart;
	private long checkUpgrade;
	private long error;
	
	private String date = "19700101";
	
	private String version;
	private String channel;
	private String appKey;
	
	
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public long getNotifyShow() {
		return notifyShow;
	}
	public void setNotifyShow(long notifyShow) {
		this.notifyShow = notifyShow;
	}
	public long getNotifyClick() {
		return notifyClick;
	}
	public void setNotifyClick(long notifyClick) {
		this.notifyClick = notifyClick;
	}
	public long getAlertShow() {
		return alertShow;
	}
	public void setAlertShow(long alertShow) {
		this.alertShow = alertShow;
	}
	public long getAlertConform() {
		return alertConform;
	}
	public void setAlertConform(long alertConform) {
		this.alertConform = alertConform;
	}
	public long getAlertCancle() {
		return alertCancle;
	}
	public void setAlertCancle(long alertCancle) {
		this.alertCancle = alertCancle;
	}
	public long getDownloadStart() {
		return downloadStart;
	}
	public void setDownloadStart(long downloadStart) {
		this.downloadStart = downloadStart;
	}
	public long getDownloadSuccess() {
		return downloadSuccess;
	}
	public void setDownloadSuccess(long downloadSuccess) {
		this.downloadSuccess = downloadSuccess;
	}
	public long getInstallAlertShow() {
		return installAlertShow;
	}
	public void setInstallAlertShow(long installAlertShow) {
		this.installAlertShow = installAlertShow;
	}
	public long getInstallAlertConform() {
		return installAlertConform;
	}
	public void setInstallAlertConform(long installAlertConform) {
		this.installAlertConform = installAlertConform;
	}
	public long getInstallAlertCancle() {
		return installAlertCancle;
	}
	public void setInstallAlertCancle(long installAlertCancle) {
		this.installAlertCancle = installAlertCancle;
	}
	public long getInstallStart() {
		return installStart;
	}
	public void setInstallStart(long installStart) {
		this.installStart = installStart;
	}
	public long getCheckUpgrade() {
		return checkUpgrade;
	}
	public void setCheckUpgrade(long checkUpgrade) {
		this.checkUpgrade = checkUpgrade;
	}
	public long getError() {
		return error;
	}
	public void setError(long error) {
		this.error = error;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	

}
