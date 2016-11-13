package com.lenovo.push.marketing.lestat.engine.result;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.redis.entity.AppFeedback;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AppFeedbackJsonResult extends BaseJsonResult {

	private long pushed;
	private long arrived;
	private long displayed;
	private long clicked;

	private String sd;
	private String ed;

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

	public long getPushed() {
		return pushed;
	}

	public void setPushed(long pushed) {
		this.pushed = pushed;
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

	public long getClicked() {
		return clicked;
	}

	public void setClicked(long clicked) {
		this.clicked = clicked;
	}

	public AppFeedbackJsonResult(AppFeedbackResult feedback) {
		super();
		sd = feedback.getStartDate();
		ed = feedback.getEndDate();
		pushed = feedback.getPushed();
		arrived = feedback.getArrived();
		displayed = feedback.getDisplayed();
		clicked = feedback.getClicked();
	}

	public AppFeedbackJsonResult(AppFeedback feedback) {
		super();
		sd = feedback.getDate();
		ed = feedback.getDate();
		pushed = feedback.getPushed();
		arrived = feedback.getArrived();
		displayed = feedback.getDisplayed();
		clicked = feedback.getClicked();
	}

}
