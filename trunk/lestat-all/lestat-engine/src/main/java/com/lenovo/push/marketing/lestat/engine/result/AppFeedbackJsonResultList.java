package com.lenovo.push.marketing.lestat.engine.result;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AppFeedbackJsonResultList extends BaseJsonResult {

	private List<AppFeedbackJsonResult> list;
	
	public AppFeedbackJsonResultList(List<AppFeedbackJsonResult> list) {
		super();
		this.setList(list);
	}

	public List<AppFeedbackJsonResult> getList() {
		return list;
	}

	public void setList(List<AppFeedbackJsonResult> list) {
		this.list = list;
	}

	
}
