package com.lenovo.push.marketing.lestat.db.hive.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.hive.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.hive.entity.AppMsgEventStatEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.param.Param;

@Service("hiveAppFeedbackService")
public class AppFeedbackService {
	
	private static Logger logger = Logger.getLogger(AppFeedbackService.class);
	
	@Autowired
	private AppFeedbackDao hiveAppFeedbackDao;
	
	public List<AppFeedbackResult> getAppFeedbackForActiveAppMsgs(List<AppMsgEntity> appMsgEntities, String startDate, String endDate) throws SQLException {
		List<AppFeedbackResult> resultList = initAppFeedbackResultList(appMsgEntities, startDate, endDate);
		List<AppMsgEventStatEntity> appMsgEventStatEntities = hiveAppFeedbackDao.getAppMsgEventStatForActiveAppMsgs(appMsgEntities, startDate, endDate);
		if (appMsgEventStatEntities != null && appMsgEventStatEntities.size() > 0) {
			for (AppMsgEventStatEntity ames : appMsgEventStatEntities) {
				fillWith(resultList, ames);
			}
		}
		
		return resultList;
	}
	
	public AppFeedbackResult getAppFeedbackForExpiredAppMsg(String sid, String adId, String startDate, String endDate) throws SQLException{
		AppFeedbackResult appFb = new AppFeedbackResult();
		appFb.setSid(sid);
		appFb.setAdId(adId);
		appFb.setStartDate(startDate);
		appFb.setEndDate(endDate);
		long now = System.currentTimeMillis();
		appFb.setLastModifiedTime(new Timestamp(now));
		
		List<AppMsgEventStatEntity> appMsgEventStatEntities = hiveAppFeedbackDao.getAppMsgEventStatForExpiredAppMsg(sid, adId, startDate, endDate);
		if (appMsgEventStatEntities != null && appMsgEventStatEntities.size() > 0) {
			for (AppMsgEventStatEntity ames : appMsgEventStatEntities) {
				if(ames.getEventtype().equals(Param.ORIGINAL_PUSHED)) {
					appFb.setPushed(ames.getCnt());
				} else if (ames.getEventtype().equals(Param.ORIGINAL_ARRIVED)) {
					appFb.setArrived(ames.getCnt());
				} else if (ames.getEventtype().equals(Param.ORIGINAL_DISPLAYED)) {
					appFb.setDisplayed(ames.getCnt());
				} else if (ames.getEventtype().equals(Param.ORIGINAL_CLICKED)) {
					appFb.setClicked(ames.getCnt());
				} else {
					logger.error("!!! Unsupported eventType: " + ames.getEventtype());
				}
			}
		}		
		
		return appFb;
	}
	
	private List<AppFeedbackResult> initAppFeedbackResultList(List<AppMsgEntity> appMsgEntities, String startDate, String endDate) {
		List<AppFeedbackResult> resultList = new ArrayList<AppFeedbackResult>();
		if (resultList != null) {
			for (AppMsgEntity ame : appMsgEntities) {
				AppFeedbackResult result = new AppFeedbackResult();
				result.setSid(ame.getAppSid());
				result.setAdId(ame.getAppMsgid());
				result.setStartDate(startDate);
				result.setEndDate(endDate);
				long now = System.currentTimeMillis();
				result.setLastModifiedTime(new Timestamp(now));
				resultList.add(result);
			}
		}
		return resultList;
	}
	
	private void fillWith(List<AppFeedbackResult> resultList, AppMsgEventStatEntity ames){
		String sid = ames.getSid();
		String adId = ames.getAdid();
		String eventType = ames.getEventtype();
		long cnt = ames.getCnt();
		
		for (AppFeedbackResult appFb : resultList) {
			if(appFb.getSid().equals(sid) && appFb.getAdId().equals(adId)) {
				if(eventType.equals(Param.ORIGINAL_PUSHED)) {
					appFb.setPushed(cnt);
				} else if (eventType.equals(Param.ORIGINAL_ARRIVED)) {
					appFb.setArrived(cnt);
				} else if (eventType.equals(Param.ORIGINAL_DISPLAYED)) {
					appFb.setDisplayed(cnt);
				} else if (eventType.equals(Param.ORIGINAL_CLICKED)) {
					appFb.setClicked(cnt);
				} else {
					logger.error("!!! Unsupported eventType: " + eventType);
				}
			}
		}
	}
}
