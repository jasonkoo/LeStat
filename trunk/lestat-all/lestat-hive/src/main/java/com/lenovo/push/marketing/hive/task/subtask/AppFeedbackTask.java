package com.lenovo.push.marketing.hive.task.subtask;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.hive.task.dbproxy.DbProxy;
import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;

public class AppFeedbackTask { 
	private static Logger logger = Logger.getLogger(AppFeedbackTask.class);
	
	private List<String> adIdList;
	private List<String> dateList;

	
	private DbProxy dbProxy;
	
	public DbProxy getDbProxy() {
		return dbProxy;
	}

	public void setDbProxy(DbProxy dbProxy) {
		this.dbProxy = dbProxy;
	}

	public AppFeedbackTask(List<String> dateList, List<String> adIdList) {
		super();
		this.dateList = dateList;
		this.adIdList = adIdList;
	}

	public void calculate() throws ParseException, SQLException {
		if (dateList==null || dateList.size()==0) {
			throw new IllegalArgumentException("invalid dateList: " + dateList);
		}
		
		for (String date : dateList) {
			calculateOneday(date);
			calculateExpiredTasks(date);
		}
	}
	
	private void calculateOneday(String date) throws SQLException {
		
		List<AppMsgEntity> appMsgEntities = dbProxy.getActiveAppMsgList(date, adIdList);
		
		if (appMsgEntities != null && appMsgEntities.size() > 0) {
			logger.info("Active App Msg List: " + appMsgEntities);
			
			List<AppFeedbackResult> resultList = dbProxy.getAppFeedbackForActiveAppMsgs(appMsgEntities, date, date);
			
			for (AppFeedbackResult result : resultList) {
				dbProxy.insertAppFeedbackResult(result, false);
			}
			
		} else {
			logger.info("No App Msg for calc!");
		}	
	}
	
	
	private void calculateExpiredTasks(String date) throws ParseException, SQLException {
		
		List<AppMsgEntity> appMsgEntities = dbProxy.getExpiredAppMsgList(date, adIdList);
		
		if (appMsgEntities != null && appMsgEntities.size() > 0) {
			logger.info("Expired App Msg List: " + appMsgEntities);
			
			for (AppMsgEntity ame : appMsgEntities) {
				String sid = ame.getAppSid();
				String adId = ame.getAppMsgid();
				String startDate = DateUtil.timestamp2DateStr(ame.getStartdate());
				String endDate = date;
				AppFeedbackResult result = dbProxy.getAppFeedbackForExpiredAppMsg(sid, adId, startDate, endDate);
				dbProxy.insertAppFeedbackResult(result, true);
			}
			
			
 		} else {
			logger.info("No App Msg for calc!");
		}
	}
}
