package com.lenovo.push.marketing.lestat.db.mysql1.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.param.Param;

@Service("mysql1AppFeedbackService")
public class AppFeedbackService {

	@Autowired
	private AppFeedbackDao mysql1AppFeedbackDao;
	
	public int insertAppFeedbackResult(AppFeedbackResult afr, boolean overallFlag) {
		afr.setOverallFlag(overallFlag);
		return mysql1AppFeedbackDao.insertAppFeedbackResult(afr);
	}
	
/*	public AppFeedbackResult getOverAllAppFeedback(String sid, String adId) {
		
		List<AppFeedbackResult> afrList = mysql1AppFeedbackDao.getOverAllAppFeedback(sid, adId);
		
		if (afrList != null && afrList.size() > 0) {
			if ( afrList.size() == 1 ) {
				return afrList.get(0);
			} else {
				long maxLastModifiedTime = 0;
				AppFeedbackResult maxAfr = null;
				for (AppFeedbackResult afr : afrList) {
					long lastModifiedTime = afr.getLastModifiedTime().getTime();
					if ( lastModifiedTime > maxLastModifiedTime ) {
						maxLastModifiedTime = lastModifiedTime;
						maxAfr = afr;
					}
				}
				return maxAfr;
			}
		} else {			
			afrList = mysql1AppFeedbackDao.getDetailAppFeedback(sid, adId, null, null);
			AppFeedbackResult result = new AppFeedbackResult();
			result.setSid(sid);
			result.setAdId(adId);
			if (afrList == null || afrList.size() == 0) {
				return result;
			} else {				
				result.setEndDate(afrList.get(0).getStartDate());
				for (AppFeedbackResult afr : afrList ) {
					result.setPushed(result.getPushed() + afr.getPushed());
					result.setArrived(result.getArrived() + afr.getArrived());
					result.setDisplayed(result.getDisplayed() + afr.getDisplayed());
					result.setClicked(result.getClicked() + afr.getClicked());
					result.setStartDate(afr.getStartDate());
				}
				return result;
			}
		}		
	}*/
	
	public List<AppFeedbackResult> getAppFeedbackByDateRange(String sid, String adId, String startDate, String endDate) {
		List<AppFeedbackResult> appFeedbackResults = new ArrayList<AppFeedbackResult>();
		// Stores <thedate, index> pair
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String thedate = null;
		int index = 0;
		List<AppFeedbackEntity> appFeedbackEntities =  mysql1AppFeedbackDao.getDetailAppFeedback(sid, adId, startDate, endDate);
		if (appFeedbackEntities != null && appFeedbackEntities.size() > 0) {
			for (AppFeedbackEntity appFeedbackEntity : appFeedbackEntities) {
				thedate = appFeedbackEntity.getThedate();
				if (map.containsKey(thedate)) {
					AppFeedbackResult appFeedbackResult = appFeedbackResults.get(map.get(thedate));
					setEventValue(appFeedbackResult, appFeedbackEntity);
				} else {
					AppFeedbackResult appFeedbackResult = new AppFeedbackResult();
					appFeedbackResult.setStartDate(appFeedbackEntity.getThedate());
					appFeedbackResult.setEndDate(appFeedbackEntity.getThedate());
					setEventValue(appFeedbackResult, appFeedbackEntity);
					appFeedbackResults.add(appFeedbackResult);
					map.put(appFeedbackEntity.getThedate(), index++);
				}
			}
 		}
		
		return appFeedbackResults;
	
	}
	
	private void setEventValue(AppFeedbackResult appFeedbackResult, AppFeedbackEntity appFeedbackEntity) {
		String eventtype = appFeedbackEntity.getEventtype();
		if (eventtype.equals(Param.ORIGINAL_PUSHED)) {
			appFeedbackResult.setPushed(appFeedbackEntity.getValue());
		} else if (eventtype.equals(Param.ORIGINAL_ARRIVED)) {
			appFeedbackResult.setArrived(appFeedbackEntity.getValue());
		} else if (eventtype.equals(Param.ORIGINAL_DISPLAYED)) {
			appFeedbackResult.setDisplayed(appFeedbackEntity.getValue());
		} else if (eventtype.equals(Param.ORIGINAL_CLICKED)) {
			appFeedbackResult.setClicked(appFeedbackEntity.getValue());
		}
	}

}
