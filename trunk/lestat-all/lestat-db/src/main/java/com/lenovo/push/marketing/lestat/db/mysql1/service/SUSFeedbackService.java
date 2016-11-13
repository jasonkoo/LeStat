package com.lenovo.push.marketing.lestat.db.mysql1.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql1.dao.SUSFeedbackDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.TinySUSFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.service.result.BigSUSFeedbackResult;

@Service("mysql1SUSFeedbackService")
public class SUSFeedbackService {
	
	@Autowired
	private SUSFeedbackDao mysql1SUSFeedbackDao;
	
	public List<BigSUSFeedbackResult> getSUSFeedbackByDateRange(String appKey, String version, String channel, String sd, String ed) throws ParseException {
		long df = DateUtil.getDateDiff(ed,sd,DateUtil.DATE_PATTERN);
		int dfInt = (int) df;
		String thedate;
		List<TinySUSFeedbackResult> tinySUSFeedbackResults;
		List<BigSUSFeedbackResult> bigSUSFeedbackResults = new ArrayList<BigSUSFeedbackResult>();
		for (int i = 0; i < dfInt+1; i++) {
			thedate = DateUtil.getNAfterDate(sd, i);
			tinySUSFeedbackResults = mysql1SUSFeedbackDao.getDailySUSFeedback(appKey, version, channel, thedate);
			if (tinySUSFeedbackResults != null && tinySUSFeedbackResults.size() > 0) {
				BigSUSFeedbackResult bigSUSFeedbackResult = new BigSUSFeedbackResult(tinySUSFeedbackResults);
				bigSUSFeedbackResult.setStartDate(DateUtil.stringToDate(thedate, DateUtil.DATE_PATTERN));
				bigSUSFeedbackResult.setEndDate(DateUtil.stringToDate(thedate, DateUtil.DATE_PATTERN));				
				bigSUSFeedbackResults.add(bigSUSFeedbackResult);
			}
		}
		return bigSUSFeedbackResults;
	}
	
	public BigSUSFeedbackResult getWeeklySUSFeedback(String appKey, String version, String channel, String sd, String ed) throws ParseException {
		List<TinySUSFeedbackResult> tinySUSFeedbackResults = mysql1SUSFeedbackDao.getWeeklySUSFeedback(appKey, version, channel, sd, ed);
		if (tinySUSFeedbackResults != null && tinySUSFeedbackResults.size() > 0 ) {
			BigSUSFeedbackResult bigSUSFeedbackResult = new BigSUSFeedbackResult(tinySUSFeedbackResults);
			bigSUSFeedbackResult.setStartDate(DateUtil.stringToDate(sd, DateUtil.DATE_PATTERN));
			bigSUSFeedbackResult.setEndDate(DateUtil.stringToDate(ed, DateUtil.DATE_PATTERN));
			return bigSUSFeedbackResult;
		} else {
			return null;
		}
	}
}
