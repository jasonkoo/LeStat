package com.lenovo.push.marketing.lestat.engine.result;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.lenovo.push.marketing.lestat.common.util.JsonUtil;

public class BaseJsonResult {
	private String message;

	public String toJsonString() throws JsonGenerationException,
			JsonMappingException, IOException {
		return JsonUtil.entity2JsonString(this);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
