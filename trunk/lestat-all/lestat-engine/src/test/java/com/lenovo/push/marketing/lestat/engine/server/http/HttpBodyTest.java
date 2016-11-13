package com.lenovo.push.marketing.lestat.engine.server.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.lenovo.push.marketing.lestat.common.util.JsonUtil;

import junit.framework.TestCase;

public class HttpBodyTest extends TestCase {

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSetPnl() throws JsonGenerationException, JsonMappingException, IOException {
		//fail("Not yet implemented");
		HttpBody body = new HttpBody();
		List<String> pnl = new ArrayList<String>();
		pnl.add("fake_com.xxx.yyy");
		body.setPnl(pnl);
		System.out.println(JsonUtil.entity2JsonString(body));
	}

}
