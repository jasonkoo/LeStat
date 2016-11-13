package com.lenovo.push.marketing.lestat.engine.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.http.NameValuePair;

public class ParamUtil {
	
	private final static String ENC = "UTF-8";

	public static String getParameter(List<NameValuePair> params, String name) throws UnsupportedEncodingException {
		if (name == null) {
			throw new IllegalArgumentException("invalid " + name + ": " + null);
		}
		for (NameValuePair param : params) {
			if (name.equals(param.getName())) {
				String value = param.getValue();
				if (StringUtils.isEmpty(value)) {
					throw new IllegalArgumentException("invalid " + name + ": "
							+ value);
				}
				return URLDecoder.decode(value,ENC);
				//return value;
			}
		}
		throw new IllegalArgumentException("invalid " + name + ": " + null);
	}
	
	
	public static String getOptionalParameter(List<NameValuePair> params, String name) throws UnsupportedEncodingException {
		if (name == null) {
			throw new IllegalArgumentException("invalid " + name + ": " + null);
		}
		for (NameValuePair param : params) {
			if (name.equals(param.getName())) {
				String value = param.getValue();
				//return value;
				return URLDecoder.decode(value,ENC);
			}
		}
		return null;
	}

}
