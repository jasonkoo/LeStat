package com.lenovo.push.marketing.lestat.db.mysql1.service.result;

import java.util.Date;
import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.TinySUSFeedbackResult;
import com.lenovo.push.marketing.lestat.db.param.Param;

public class BigSUSFeedbackResult {
	
	private Date startDate;
	private Date endDate;

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

	
	public BigSUSFeedbackResult() {
		
	}
	
	public BigSUSFeedbackResult(List<TinySUSFeedbackResult> tinySUSFeedbackResults) {
		 for(TinySUSFeedbackResult tinySUSFeedbackResult : tinySUSFeedbackResults) {
			 String eventName = tinySUSFeedbackResult.getEventName();
			 long cnt = tinySUSFeedbackResult.getCnt();
			 if (eventName.equals(Param.ORIGINAL_NOTIFY_SHOW)) {
				 this.notifyShow = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_NOTIFY_CLICK)) {
				 this.notifyClick = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_ALERT_SHOW)) {
				 this.alertShow = cnt;				 
			 } else if (eventName.equals(Param.ORIGINAL_ALERT_CONFORM)) {
				 this.alertConform = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_ALERT_CANCLE)) {
				 this.alertCancle = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_DOWNLOAD_START)) {
				 this.downloadStart = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_DOWNLOAD_SUCCESS)) {
				 this.downloadSuccess = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_INSTALL_ALERT_SHOW)) {
				 this.installAlertShow = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_INSTALL_ALERT_CONFORM)) {
				 this.installAlertConform = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_INSTALL_ALERT_CANCLE)) {
				 this.installAlertCancle = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_INSTALL_START)) {
				 this.installStart = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_CHECK_UPGRADE)) {
				 this.checkUpgrade = cnt;
			 } else if (eventName.equals(Param.ORIGINAL_ERROR)) {
				 this.error = cnt;
			 }
		 }
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
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public void print() {
		System.out.println("startDate: " + this.getStartDate());
		System.out.println("endDate: " + this.getEndDate());
		System.out.println("notifyShow: " + this.getNotifyShow());
		System.out.println("notifyClick: " + this.getNotifyClick());
		System.out.println("alertShow: " + this.getAlertShow());
		System.out.println("alertConform: " + this.getAlertConform());
		System.out.println("alertCancle:" + this.getAlertCancle());
		System.out.println("downloadStart: " + this.getDownloadStart());
		System.out.println("downloadSuccess: " + this.getDownloadSuccess());
		System.out.println("installAlertShow: " + this.getInstallAlertShow());
		System.out.println("installAlertConform: " + this.getInstallAlertConform());
		System.out.println("installAlertCancle: " + this.getInstallAlertCancle());
		System.out.println("installStart: " + this.getInstallStart());
		System.out.println("checkUpgrade: " + this.getCheckUpgrade());
		System.out.println("error: " + this.getError());
			
	}
}
