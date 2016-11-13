package com.lenovo.push.marketing.lestat.engine.server.http;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.http.NameValuePair;
import org.springframework.stereotype.Component;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.FeedbackResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.SimpleFeedbackResult;
import com.lenovo.push.marketing.lestat.engine.dbproxy.DbProxy;
import com.lenovo.push.marketing.lestat.engine.param.Param;
import com.lenovo.push.marketing.lestat.engine.result.FeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.CfFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.result.SimpleFeedbackJsonResult;
import com.lenovo.push.marketing.lestat.engine.util.ParamUtil;

@Component("fbMethodHandlerImpl")
public class FeedbackMethodHandlerImpl {

	@Resource(name = "dbProxy")
	private DbProxy dbProxy;
	
	public SimpleFeedbackJsonResult getFb(List<NameValuePair> params, HttpBody body) 
			throws UnsupportedEncodingException {
		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		String thedate = ParamUtil.getParameter(params, Param.THEDATE);
		SimpleFeedbackResult sfr = dbProxy.getFb(adId, thedate);
		SimpleFeedbackJsonResult sfjr = new SimpleFeedbackJsonResult(sfr);
		return sfjr;
	}
	
	public SimpleFeedbackJsonResult getDist(List<NameValuePair> params, HttpBody body) 
			throws UnsupportedEncodingException {
		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		String thedate = ParamUtil.getParameter(params, Param.THEDATE);
		String colx = ParamUtil.getParameter(params, Param.COL);
		SimpleFeedbackResult sfr = dbProxy.getDist(adId, thedate, colx);
		SimpleFeedbackJsonResult sfjr = new SimpleFeedbackJsonResult(sfr);
		return sfjr;		
	}
	
	
	public CfFeedbackJsonResult getCfFeedback(List<NameValuePair> params, HttpBody body) 
			throws UnsupportedEncodingException, ParseException {

		String adId = ParamUtil.getParameter(params, Param.AD_ID);
		List<String> pnList = null;
		if (body!=null) {
			pnList = body.getPnl();
		}
		//String pkgId = ParamUtil.getParameter(params, Param.PACKAGE_ID);
		String sd = ParamUtil.getParameter(params, Param.START_DATE);
		String ed = ParamUtil.getParameter(params, Param.END_DATE);
		
		long df = DateUtil.getDateDiff(ed,sd,DateUtil.DATE_PATTERN);
		if (df<0) {
			throw new IllegalArgumentException("invalid sd-ed pair");
		}
		if (df>=Param.FEEDBACK_MAX_DATE_DIFF) {
			throw new IllegalArgumentException("maximal time span of " + Param.FEEDBACK_MAX_DATE_DIFF + " days");
		}

		CfFeedbackResult cfFeedbackResult = dbProxy.getCfFeedback(adId,pnList,sd,ed);
		long sumArrived = cfFeedbackResult.getSumArrived();
		List<FeedbackResult> list = null;
		if (cfFeedbackResult!=null) {
			list = cfFeedbackResult.getList();
		}
		
		int dfInt = (int) df;
		List<FeedbackJsonResult> jsonList = computeJsonList(sd, dfInt,list);
		CfFeedbackJsonResult jsonResult = new CfFeedbackJsonResult(sd,ed,sumArrived,jsonList);
		
		return jsonResult;
	}

	private List<FeedbackJsonResult> computeJsonList(String sd, int df, List<FeedbackResult> list) throws ParseException {
		List<FeedbackJsonResult> jsonList = new ArrayList<FeedbackJsonResult>();
		
		if (list != null) {
			for (FeedbackResult feedback : list) {
				jsonList.add(new FeedbackJsonResult(feedback));
			}
		}
		
		return jsonList;
	}

}
