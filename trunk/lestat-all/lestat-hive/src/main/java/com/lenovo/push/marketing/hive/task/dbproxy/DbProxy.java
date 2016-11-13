package com.lenovo.push.marketing.hive.task.dbproxy;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.service.AppMsgService;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;

public class DbProxy {
	
	@Autowired
	private AppMsgService mysql0AppMsgService;
	@Autowired
	private com.lenovo.push.marketing.lestat.db.hive.service.AppFeedbackService hiveAppFeedbackService;
	@Autowired
	private com.lenovo.push.marketing.lestat.db.mysql1.service.AppFeedbackService mysql1AppFeedbackService;
	
	
	public List<AppMsgEntity> getActiveAppMsgList(String date, List<String> adIdList) {
		return mysql0AppMsgService.getActiveAppMsgList(date, adIdList);
	}

	public List<AppMsgEntity> getExpiredAppMsgList(String date, List<String> adIdList) throws ParseException {
		return mysql0AppMsgService.getExpiredAppMsgList(date, adIdList);
	}
	
	public List<AppFeedbackResult> getAppFeedbackForActiveAppMsgs(List<AppMsgEntity> appMsgEntities, String startDate, String endDate) throws SQLException {
		return hiveAppFeedbackService.getAppFeedbackForActiveAppMsgs(appMsgEntities, startDate, endDate);
	}
	
	public AppFeedbackResult getAppFeedbackForExpiredAppMsg(String sid, String adId, String startDate, String endDate) throws SQLException {
		return hiveAppFeedbackService.getAppFeedbackForExpiredAppMsg(sid, adId, startDate, endDate);
	}

	public void insertAppFeedbackResult(AppFeedbackResult result, boolean overallFlag) {
		mysql1AppFeedbackService.insertAppFeedbackResult(result, overallFlag);
	}
	
	public List<String> getAllSid() {
		// TODO
		return null;
	}
	
	public List<String> getDistCountDimensionResultList(String sid, String date, String dim) {
		// TODO
		return null;
	}
}
