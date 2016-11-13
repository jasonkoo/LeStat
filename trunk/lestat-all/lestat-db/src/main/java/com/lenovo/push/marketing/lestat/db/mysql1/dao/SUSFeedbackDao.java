package com.lenovo.push.marketing.lestat.db.mysql1.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.TinySUSFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.SUSFeedbackMapper;

public interface SUSFeedbackDao extends BaseDao<TinySUSFeedbackResult, SUSFeedbackMapper> {
	public List<TinySUSFeedbackResult> getDailySUSFeedback(String appKey, String targetVercode, String channelKey, String thedate);
	public List<TinySUSFeedbackResult> getWeeklySUSFeedback(String appKey, String targetVercode, String channelKey, String startdate, String enddate);
}
