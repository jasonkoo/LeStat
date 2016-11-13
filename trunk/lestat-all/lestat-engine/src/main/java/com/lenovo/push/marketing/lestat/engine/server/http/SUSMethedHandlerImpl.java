package com.lenovo.push.marketing.lestat.engine.server.http;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Component;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql1.service.AppFeedbackService;
import com.lenovo.push.marketing.lestat.db.mysql1.service.AppInfoService;
import com.lenovo.push.marketing.lestat.db.mysql1.service.SUSFeedbackService;
import com.lenovo.push.marketing.lestat.db.mysql1.service.result.BigSUSFeedbackResult;
import com.lenovo.push.marketing.lestat.db.redis.util.BigCache;
import com.lenovo.push.marketing.lestat.db.redis.util.RedisUtil;
import com.lenovo.push.marketing.lestat.engine.param.Param;
import com.lenovo.push.marketing.lestat.engine.result.SUSFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.SUSFeedbackJsonResultList;
import com.lenovo.push.marketing.lestat.engine.util.ParamUtil;

@Component("susMethodHandlerImpl")
public class SUSMethedHandlerImpl {

	@Resource(name = "mysql1AppFeedbackService")
	private AppFeedbackService appFeedbackService;
	
	@Resource(name = "mysql1SUSFeedbackService")
	private SUSFeedbackService susFeedbackService;

	@Resource(name = "mysql1AppInfoService")
	private AppInfoService appInfoService;
	
	@Resource(name = "bigCache")
	private BigCache bigCache;

	public AppFeedbackService getAppFeedbackService() {
		return appFeedbackService;
	}

	public void setAppFeedbackService(AppFeedbackService appFeedbackService) {
		this.appFeedbackService = appFeedbackService;
	}


	public SUSFeedbackJsonResultList getSUSAppFeedback(
			List<NameValuePair> params) throws JsonGenerationException,
			JsonMappingException, IOException, ParseException {

		String appKey = ParamUtil.getParameter(params, Param.APP_KEY);
		//String pkgId = ParamUtil.getParameter(params, Param.PACKAGE_ID);
		String sd = ParamUtil.getParameter(params, Param.START_DATE);
		String ed = ParamUtil.getParameter(params, Param.END_DATE);
		
		long df = DateUtil.getDateDiff(ed,sd,DateUtil.DATE_PATTERN);
		if (df<0) {
			throw new IllegalArgumentException("invalid sd-ed pair");
		}
		if (df>Param.SUS_MAX_DATE_DIFF) {
			throw new IllegalArgumentException("maximal time span of " + Param.SUS_MAX_DATE_DIFF + " days");
		}
//		if (!DateUtil.isEarlierThan(sd, ed))
		
		String version = ParamUtil.getOptionalParameter(params, Param.VERTION);
		String channel = ParamUtil.getOptionalParameter(params, Param.CHANNEL);

		List<BigSUSFeedbackResult> list = susFeedbackService.getSUSFeedbackByDateRange(
				appKey,version,channel,sd,ed);
		
		int dfInt = (int) df;
		List<SUSFeedbackJsonResult> jsonList = computeJsonList(sd, dfInt,list);
		SUSFeedbackJsonResultList jsonResult = new SUSFeedbackJsonResultList(jsonList);
		return jsonResult;
	}

	private List<SUSFeedbackJsonResult> computeJsonList(String sd, int df,List<BigSUSFeedbackResult> list) throws ParseException {
		List<SUSFeedbackJsonResult> jsonList = new ArrayList<SUSFeedbackJsonResult>();
		
		List<String> dateList = new ArrayList<String>();
		for (int i=0;i<df+1;i++) {
			String dStr = DateUtil.getNAfterDate(sd, i);
			dateList.add(dStr);
		}
		
		if (list != null) {
			for (BigSUSFeedbackResult feedback : list) {
				Date sd1 = feedback.getStartDate();
				Date ed1 = feedback.getEndDate();
				if (DateUtil.getDateDiff(sd1, ed1)==0 && dateList.contains(DateUtil.dateToString(sd1, DateUtil.DATE_PATTERN))) {
					jsonList.add(new SUSFeedbackJsonResult(feedback));
					dateList.remove(sd1);
				}
			}
		}
		
		for (String dStr : dateList) {
			BigSUSFeedbackResult feedback = new BigSUSFeedbackResult();
			feedback.setStartDate(DateUtil.stringToDate(dStr, DateUtil.DATE_PATTERN));
			feedback.setEndDate(DateUtil.stringToDate(dStr, DateUtil.DATE_PATTERN));
			jsonList.add(new SUSFeedbackJsonResult(feedback));
		}
		
		return jsonList;
	}
	
	
	public SUSFeedbackJsonResult getTodaySUSFeedback(
			List<NameValuePair> params) throws JsonGenerationException,
			JsonMappingException, IOException, ParseException {

		String appKey = ParamUtil.getParameter(params, Param.APP_KEY);
		//String pkgId = ParamUtil.getParameter(params, Param.PACKAGE_ID);
		
		String version = ParamUtil.getParameter(params, Param.VERTION);
		String channel = ParamUtil.getParameter(params, Param.CHANNEL);
		
		String today = DateUtil.getToday();
		com.lenovo.push.marketing.lestat.db.redis.entity.SUSFeedback  feedback = RedisUtil.getSUSFeedback(bigCache, appKey, version, channel, today);
		//com.lenovo.push.marketing.lestat.db.redis.entity.SUSFeedback  feedback = new com.lenovo.push.marketing.lestat.db.redis.entity.SUSFeedback();
		
		SUSFeedbackJsonResult jsonResult = new SUSFeedbackJsonResult(feedback);
		return jsonResult;
	}

}
