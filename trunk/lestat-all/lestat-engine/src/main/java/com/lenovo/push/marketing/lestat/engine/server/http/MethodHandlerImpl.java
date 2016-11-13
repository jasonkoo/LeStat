package com.lenovo.push.marketing.lestat.engine.server.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Component;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.mysql1.service.AppFeedbackService;
import com.lenovo.push.marketing.lestat.db.mysql1.service.AppInfoService;
import com.lenovo.push.marketing.lestat.db.redis.entity.AppFeedback;
import com.lenovo.push.marketing.lestat.db.redis.util.BigCache;
import com.lenovo.push.marketing.lestat.db.redis.util.RedisUtil;
import com.lenovo.push.marketing.lestat.engine.param.Param;
import com.lenovo.push.marketing.lestat.engine.result.AppFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.AppFeedbackJsonResultList;
import com.lenovo.push.marketing.lestat.engine.result.AppInfoResultList;
import com.lenovo.push.marketing.lestat.engine.result.BaseJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.CfErrorJsonResultList;
import com.lenovo.push.marketing.lestat.engine.result.CfFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.DistCountDimensionJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.SUSFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.SUSFeedbackJsonResultList;
import com.lenovo.push.marketing.lestat.engine.result.SimpleFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.util.ParamUtil;

@Component("methodHandlerImpl")
public class MethodHandlerImpl implements MethodHandler {

	@Resource(name = "mysql1AppFeedbackService")
	private AppFeedbackService appFeedbackService;

	@Resource(name = "mysql1AppInfoService")
	private AppInfoService appInfoService;
	
	@Resource(name = "bigCache")
	private BigCache bigCache;
	
	@Resource(name = "susMethodHandlerImpl")
	private SUSMethedHandlerImpl susMethedHandlerImpl;
	
	@Resource(name = "fbMethodHandlerImpl")
	private FeedbackMethodHandlerImpl fbMethedHandlerImpl;
	
	@Resource(name = "errorMethodHandlerImpl")
	private ErrorMethodHandlerImpl errorMethodHandlerImpl;

	public AppFeedbackService getAppFeedbackService() {
		return appFeedbackService;
	}

	public void setAppFeedbackService(AppFeedbackService appFeedbackService) {
		this.appFeedbackService = appFeedbackService;
	}

	@Override
	public BaseJsonResult handleMethod(String methodName,
			List<NameValuePair> params, HttpBody body) {

		BaseJsonResult result = null;
		try {
			Class<? extends MethodHandlerImpl> classThis = this.getClass();
			Method method = classThis.getDeclaredMethod(methodName,
					new Class[] { List.class, HttpBody.class });
			Object[] args = new Object[]{params, body};
			result = (BaseJsonResult) method.invoke(this, args);

		} catch (InvocationTargetException ex) {
			result = new BaseJsonResult();
			result.setMessage(ex.getTargetException().getMessage());
		} catch (NoSuchMethodException ex) {
			result = new BaseJsonResult();
			result.setMessage("no such method: " + methodName);
		} catch (Exception ex) {
			result = new BaseJsonResult();
			result.setMessage("internal error occurred: please contact the admin");
		}
		return result;

	}

	@SuppressWarnings("unused")
	private BaseJsonResult getA(List<NameValuePair> params, HttpBody body) {
		// only for testing
		//return "a received";
		BaseJsonResult result = new BaseJsonResult();
		result.setMessage("a received");
		return result;
	}

	/*@SuppressWarnings("unused")
	private AppFeedbackJsonResult getOverallAppFeedback(
			List<NameValuePair> params, HttpBody body) throws JsonGenerationException,
			JsonMappingException, IOException {

		String sid = ParamUtil.getParameter(params, Param.SID);
		String adId = ParamUtil.getParameter(params, Param.AD_ID);

		AppFeedbackResult result = appFeedbackService.getOverAllAppFeedback(
				sid, adId);

		AppFeedbackJsonResult jsonResult = new AppFeedbackJsonResult(result);
		return jsonResult;
	}*/

	@SuppressWarnings("unused")
	private AppFeedbackJsonResultList getAppFeedbackByDateRange(
			List<NameValuePair> params, HttpBody body) throws JsonGenerationException,
			JsonMappingException, IOException {

		String sid = ParamUtil.getParameter(params, Param.SID);
		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		String sd = ParamUtil.getParameter(params, Param.START_DATE);
		String ed = ParamUtil.getParameter(params, Param.END_DATE);

		List<AppFeedbackResult> list = appFeedbackService
				.getAppFeedbackByDateRange(sid, adId, sd, ed);
		List<AppFeedbackJsonResult> jsonList = new ArrayList<AppFeedbackJsonResult>();
		if (list != null) {
			for (AppFeedbackResult feedback : list) {
				jsonList.add(new AppFeedbackJsonResult(feedback));
			}
		}

		AppFeedbackJsonResultList newList = new AppFeedbackJsonResultList(
				jsonList);

		return newList;
	}

	@SuppressWarnings("unused")
	private AppInfoResultList getDistCountByDimension(List<NameValuePair> params, HttpBody body)
			throws JsonGenerationException, JsonMappingException, IOException {

		String sid = ParamUtil.getParameter(params, Param.SID);
		String dim = ParamUtil.getParameter(params, Param.DIM);
		
		if (!Param.DIM_LIST.contains(dim)) {
			throw new IllegalArgumentException("invalid dim: " + dim);
		}

		List<DistCountDimensionResult> list = appInfoService
				.getDistCountByDimension(sid, dim);
		List<DistCountDimensionJsonResult> jsonList = new ArrayList<DistCountDimensionJsonResult>();
		if (list != null) {
			for (DistCountDimensionResult result : list) {
				jsonList.add(new DistCountDimensionJsonResult(result));
			}
		}

		AppInfoResultList newList = new AppInfoResultList(jsonList);

		return newList;
	}
	
	//method=getTodayAppFeedback&adId=4&sid=5
	@SuppressWarnings("unused")
	private AppFeedbackJsonResult getTodayAppFeedback(List<NameValuePair> params, HttpBody body)
			throws JsonGenerationException, JsonMappingException, IOException {

		String sid = ParamUtil.getParameter(params, Param.SID);
		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		String today = DateUtil.getToday();
		AppFeedback feedback = RedisUtil.getAppFeedback(bigCache, sid, adId, today);
		AppFeedbackJsonResult jsonResult = new AppFeedbackJsonResult(feedback);
		return jsonResult;
	}
	
	//http://localhost:8081/engine?method=getSUSFeedbackByDateRange&sd=20141103&ed=20141104&ver=3&ak=1&cha=2
	@SuppressWarnings("unused")
	private SUSFeedbackJsonResultList getSUSFeedbackByDateRange(
			List<NameValuePair> params, HttpBody body) throws JsonGenerationException,
			JsonMappingException, IOException, ParseException {
		return susMethedHandlerImpl.getSUSAppFeedback(params);
	}
	
	@SuppressWarnings("unused")
	private SUSFeedbackJsonResult getTodaySUSFeedback(List<NameValuePair> params, HttpBody body)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {

		return susMethedHandlerImpl.getTodaySUSFeedback(params);
	}
	
	
	@SuppressWarnings("unused")
	private SimpleFeedbackJsonResult getFb(List<NameValuePair> params, HttpBody body) 
			throws UnsupportedEncodingException {
		return fbMethedHandlerImpl.getFb(params, body);
	}
	
	@SuppressWarnings("unused")
	private SimpleFeedbackJsonResult getDist(List<NameValuePair> params, HttpBody body) 
			throws UnsupportedEncodingException{
		return fbMethedHandlerImpl.getDist(params, body);
	}
	
	
	@SuppressWarnings("unused")
	private CfFeedbackJsonResult getCfFeedback(List<NameValuePair> params, HttpBody body)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {

		return fbMethedHandlerImpl.getCfFeedback(params, body);
	}
	
	@SuppressWarnings("unused")
	private CfErrorJsonResultList getCfError(List<NameValuePair> params, HttpBody body)
			throws JsonGenerationException, JsonMappingException, IOException, ParseException {
		return errorMethodHandlerImpl.getCfError(params, body);
	}
	
}
