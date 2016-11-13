package com.lenovo.push.marketing.lestat.common.vo;

import java.util.Date;

import org.junit.Test;

import com.google.gson.Gson;

public class SUSFeedbackTest {

	@Test
	public void test() {
		SUSFeedback susFeedback = new SUSFeedback();
		susFeedback.setDeviceId("A1000038A6D8606");
		susFeedback.setPackageId("packageId");
		susFeedback.setCurrentVercode("currentVercode");
		susFeedback.setCurrentVername("currentVername");
		susFeedback.setTargetVercode("targetVercode");
		susFeedback.setTargetVername("targetVername");
		susFeedback.setAppKey("1009");
		susFeedback.setChannelKey("channelKey");
		susFeedback.setEventName("notify_show");
		susFeedback.setErrorCode("errorCode");
		susFeedback.setLogTime(new Date());
		
		Gson g = new Gson();
		System.out.println(g.toJson(susFeedback));
	}

}
