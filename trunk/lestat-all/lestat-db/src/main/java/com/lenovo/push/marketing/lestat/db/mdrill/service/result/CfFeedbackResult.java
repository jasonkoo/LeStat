package com.lenovo.push.marketing.lestat.db.mdrill.service.result;

import java.util.List;

public class CfFeedbackResult {

	private String sd;
	private String ed;
	private String adId;
	private long sumArrived;
	private List<FeedbackResult> list;

	public long getSumArrived() {
		return sumArrived;
	}

	public void setSumArrived(long sumArrived) {
		this.sumArrived = sumArrived;
	}

	public List<FeedbackResult> getList() {
		return list;
	}

	public void setList(List<FeedbackResult> list) {
		this.list = list;
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

}
