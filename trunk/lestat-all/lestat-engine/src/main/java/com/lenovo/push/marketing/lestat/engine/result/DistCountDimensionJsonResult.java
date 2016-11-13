package com.lenovo.push.marketing.lestat.engine.result;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class DistCountDimensionJsonResult extends BaseJsonResult {

	public DistCountDimensionJsonResult(DistCountDimensionResult result) {
		super();
		key = result.getKey();
		value = result.getValue();
	}

	private String key;
	private long value;

	public long getValue() {
		return value;
	}

	public void setValue(long value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
