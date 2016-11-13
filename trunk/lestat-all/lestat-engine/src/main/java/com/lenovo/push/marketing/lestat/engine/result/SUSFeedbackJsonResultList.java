package com.lenovo.push.marketing.lestat.engine.result;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class SUSFeedbackJsonResultList extends BaseJsonResult {

	private List<SUSFeedbackJsonResult> list;
	
	public SUSFeedbackJsonResultList(List<SUSFeedbackJsonResult> list) {
		super();
		this.setList(list);
	}

	public List<SUSFeedbackJsonResult> getList() {
		return list;
	}

	public void setList(List<SUSFeedbackJsonResult> list) {
		this.list = list;
	}

	
}
