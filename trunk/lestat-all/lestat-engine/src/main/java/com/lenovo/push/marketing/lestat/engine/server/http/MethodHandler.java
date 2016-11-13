package com.lenovo.push.marketing.lestat.engine.server.http;

import java.util.List;

import org.apache.http.NameValuePair;

import com.lenovo.push.marketing.lestat.engine.result.BaseJsonResult;


public interface MethodHandler {

	public BaseJsonResult handleMethod(String method, List<NameValuePair> params, HttpBody body) throws Exception;

}
