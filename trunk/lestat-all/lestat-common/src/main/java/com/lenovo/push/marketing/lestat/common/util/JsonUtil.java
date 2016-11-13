package com.lenovo.push.marketing.lestat.common.util;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JsonUtil {

	private final static ObjectMapper MAPPER = new ObjectMapper();

	public static String entity2JsonString(Object o)
			throws JsonGenerationException, JsonMappingException, IOException {
		return MAPPER.writeValueAsString(o);
	}
	
	public static Object jsonString2Entity(String s, Class<?> valueType) throws JsonParseException, JsonMappingException, IOException {
		return MAPPER.readValue(s, valueType);
	}
}
