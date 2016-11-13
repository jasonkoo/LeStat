package com.lenovo.push.marketing.lestat.engine.server.http;

import org.jboss.netty.handler.codec.http.HttpRequest;

import com.lenovo.push.marketing.lestat.engine.result.BaseJsonResult;

public interface EngineHttpRequestHandler {

	public BaseJsonResult handleHttpRequest(HttpRequest httpRequest) throws Exception;
}
