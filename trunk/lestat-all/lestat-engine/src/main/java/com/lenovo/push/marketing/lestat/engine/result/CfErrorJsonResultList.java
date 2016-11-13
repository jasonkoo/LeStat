package com.lenovo.push.marketing.lestat.engine.result;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class CfErrorJsonResultList extends BaseJsonResult {
	
	private List<CfErrorJsonResult> list;
	
	public CfErrorJsonResultList(List<CfErrorJsonResult> list) {
		super();
		this.list = list;
	}

	public List<CfErrorJsonResult> getList() {
		return list;
	}

	public void setList(List<CfErrorJsonResult> list) {
		this.list = list;
	}
	

}
