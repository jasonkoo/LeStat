package com.lenovo.push.marketing.lestat.engine.dbproxy;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mdrill.service.ErrorService;
import com.lenovo.push.marketing.lestat.db.mdrill.service.FeedbackService;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfErrorResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.SimpleFeedbackResult;

@Repository("dbProxy")
public class DbProxy {
	
	@Resource(name = "mdrillFeedbackService")
	private FeedbackService feedbackService;
	
	@Resource(name = "mdrillErrorService")
	private ErrorService errorService;
	
	
	public SimpleFeedbackResult getFb(String adId, String thedate) {
		return feedbackService.getFeedbackResultDup(adId, thedate);
	}
	
	public SimpleFeedbackResult getDist(String adId, String thedate, String colx) {
		return feedbackService.getDistCountByColx(adId, thedate, colx);
	}
	
	public CfFeedbackResult getCfFeedback(String adId, List<String> pnList, String sd, String ed) {
		return feedbackService.getDailyCfFeedbackResultDup(adId, pnList, sd, ed);
		//return feedbackService.getDailyCfFeedbackResultDist(adId, pnList, sd, ed);
		
		//return feedbackService.getDateRangeCfFeedbackResultDup(adId, pnList, sd, ed);
		//return feedbackService.getDateRangeCfFeedbackResultDist(adId, pnList, sd, ed);
	}
	
	public List<CfErrorResult> getCfError(String adId, String sd, String ed){
		return errorService.getCfErrorResult(adId, sd, ed);
	}
}
