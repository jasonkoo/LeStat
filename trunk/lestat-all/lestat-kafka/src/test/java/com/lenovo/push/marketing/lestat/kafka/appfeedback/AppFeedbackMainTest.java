package com.lenovo.push.marketing.lestat.kafka.appfeedback;

import java.util.Date;

import org.junit.Test;

import com.google.gson.Gson;
import com.lenovo.lps.push.marketing.common.vo.appinfo.AppFeedbackDataEntry;
import com.lenovo.push.marketing.lestat.db.param.Param;


public class AppFeedbackMainTest {

	@Test
	public void test() {	
		AppFeedbackDataEntry appFeedbackDataEntry = new AppFeedbackDataEntry();
		appFeedbackDataEntry.setSid("1");
		appFeedbackDataEntry.setAdId("2");
		appFeedbackDataEntry.setLogTime(new Date());
		appFeedbackDataEntry.setEventName(Param.ORIGINAL_PUSHED);
		appFeedbackDataEntry.setSuccess(true);
		System.out.println("===1===null deviceid : " + new Gson().toJson(appFeedbackDataEntry));
		
		appFeedbackDataEntry = new AppFeedbackDataEntry();
		appFeedbackDataEntry.setSid("10086");
		appFeedbackDataEntry.setAdId("10086_ad1");
		appFeedbackDataEntry.setLogTime(new Date());
		appFeedbackDataEntry.setEventName(Param.ORIGINAL_DISPLAYED);
		appFeedbackDataEntry.setSuccess(true);
		System.out.println(new Gson().toJson(appFeedbackDataEntry));
		
		
		appFeedbackDataEntry = new AppFeedbackDataEntry();
		appFeedbackDataEntry.setSid("10086");
		appFeedbackDataEntry.setAdId("10086_ad2");
		appFeedbackDataEntry.setLogTime(new Date());
		appFeedbackDataEntry.setEventName(Param.ORIGINAL_CLICKED);
		appFeedbackDataEntry.setSuccess(true);
		System.out.println(new Gson().toJson(appFeedbackDataEntry));	
		
	}

}
