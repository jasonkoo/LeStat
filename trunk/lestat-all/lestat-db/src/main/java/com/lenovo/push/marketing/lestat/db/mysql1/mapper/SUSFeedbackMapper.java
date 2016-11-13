package com.lenovo.push.marketing.lestat.db.mysql1.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.TinySUSFeedbackResult;

public interface SUSFeedbackMapper extends BaseMapper<TinySUSFeedbackResult> {
	public List<TinySUSFeedbackResult> getDailySUSFeedback(Map<String, Object> params);
	public List<TinySUSFeedbackResult> getWeeklySUSFeedback(Map<String, Object> params);
}
