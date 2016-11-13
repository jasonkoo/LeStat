package com.lenovo.push.marketing.lestat.engine.result;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CfFeedbackJsonResult extends BaseJsonResult {

	
	private String sd;
	private String ed;
	private String adId;
	private long sumArrived;
	
	private List<FeedbackJsonResult> list;
	
	public CfFeedbackJsonResult(String sd, String ed, long sumArrived, List<FeedbackJsonResult> list) {
		super();
		this.sd = sd;
		this.ed = ed;
		this.sumArrived = sumArrived;
		this.setList(list);
	}

	public List<FeedbackJsonResult> getList() {
		return list;
	}

	public void setList(List<FeedbackJsonResult> list) {
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

	public long getSumArrived() {
		return sumArrived;
	}

	public void setSumArrived(long sumArrived) {
		this.sumArrived = sumArrived;
	}

}
