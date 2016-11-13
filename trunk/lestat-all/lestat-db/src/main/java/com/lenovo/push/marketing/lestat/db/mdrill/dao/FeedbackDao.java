package com.lenovo.push.marketing.lestat.db.mdrill.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.FeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.mapper.FeedbackMapper;

public interface FeedbackDao extends BaseDao<FeedbackEntity, FeedbackMapper>{
	public FeedbackEntity getFeedbackResultDup(String adId, String thedate);
	public FeedbackEntity getDistCountByColx(String adId, String thedate, String colx);
	
	public List<FeedbackEntity> getDailyCfFeedbackResultDup(String adId, List<String> pnList, String sd, String ed);
	public List<FeedbackEntity> getDailyCfFeedbackResultDist(String adId, List<String> pnList, String sd, String ed, String colx, String eventName);
	
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDup(String adId, List<String> pnList, String sd, String ed);
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDist(String adId, List<String> pnList, String sd, String ed, String colx, String eventName);
}
