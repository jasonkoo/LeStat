package com.lenovo.push.marketing.lestat.engine.result;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.lps.push.marketing.drill.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql1.service.result.BigSUSFeedbackResult;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SUSFeedbackJsonResult extends BaseJsonResult {
	
	
	/*
	notify_show     通知栏更新通知已显示
	notify_click       通知栏更新通知已点击
	alert_show       显示提示升级对话框
	alert_conform 用户点击确认升级按钮
	alert_cancle     用户点击取消升级按钮
	download_start        开始下载
	download_success   下载完成
	install_alert_show   显示安装提示对话框
	install_alert_conform      用户确认安装
	install_alert_cancle 用户取消安装
	install_start      开始安装
	check_ upgrade        服务端收到检测升级请求
	error  错误
	*/

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

	private String sd;
	private String ed;

	public SUSFeedbackJsonResult(BigSUSFeedbackResult feedback) {
		if (feedback!=null) {
			this.notifyShow = feedback.getNotifyShow();
			this.notifyClick = feedback.getNotifyClick();
			this.alertShow = feedback.getAlertShow();
			this.alertConform = feedback.getAlertCancle();
			this.alertCancle = feedback.getAlertCancle();
			this.downloadStart = feedback.getDownloadStart();
			this.downloadSuccess = feedback.getDownloadSuccess();
			this.installAlertShow = feedback.getInstallAlertShow();
			this.installStart = feedback.getInstallStart();
			this.installAlertConform = feedback.getInstallAlertConform();
			this.installAlertCancle = feedback.getInstallAlertCancle();
			this.checkUpgrade = feedback.getCheckUpgrade();
			this.error = feedback.getError();
			this.sd = DateUtil.dateToString(feedback.getStartDate(), DateUtil.DATE_PATTERN);
			this.ed = DateUtil.dateToString(feedback.getEndDate(), DateUtil.DATE_PATTERN);
		}
	}

	public SUSFeedbackJsonResult(
			com.lenovo.push.marketing.lestat.db.redis.entity.SUSFeedback feedback) {
		if (feedback!=null) {
			this.notifyShow = feedback.getNotifyShow();
			this.notifyClick = feedback.getNotifyClick();
			this.alertShow = feedback.getAlertShow();
			this.alertConform = feedback.getAlertCancle();
			this.alertCancle = feedback.getAlertCancle();
			this.downloadStart = feedback.getDownloadStart();
			this.downloadSuccess = feedback.getDownloadSuccess();
			this.installAlertShow = feedback.getInstallAlertShow();
			this.installStart = feedback.getInstallStart();
			this.installAlertConform = feedback.getInstallAlertConform();
			this.installAlertCancle = feedback.getInstallAlertCancle();
			this.checkUpgrade = feedback.getCheckUpgrade();
			this.error = feedback.getError();
			this.sd = feedback.getDate();
			this.ed = feedback.getDate();
		}
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







}
