package com.lenovo.push.marketing.lestat.engine.result;

import java.util.List;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class AppInfoResultList extends BaseJsonResult {

	private List<DistCountDimensionJsonResult> list;
	
	public AppInfoResultList(List<DistCountDimensionJsonResult> list) {
		super();
		this.setList(list);
	}

	public List<DistCountDimensionJsonResult> getList() {
		return list;
	}

	public void setList(List<DistCountDimensionJsonResult> list) {
		this.list = list;
	}

	
}
