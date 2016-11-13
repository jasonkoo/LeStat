package com.lenovo.push.marketing.lestat.db.mdrill.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.FeedbackEntity;

public interface FeedbackMapper extends BaseMapper<FeedbackEntity> {
	public FeedbackEntity getFeedbackResultDup(Map<String, Object> params);
	public FeedbackEntity getDistCountByColx(Map<String, Object> params);
	public List<FeedbackEntity> getDailyCfFeedbackResultDup(Map<String, Object> params);
	public List<FeedbackEntity> getDailyCfFeedbackResultDist(Map<String, Object> params);
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDup(Map<String, Object> params);
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDist(Map<String, Object> params);

}
