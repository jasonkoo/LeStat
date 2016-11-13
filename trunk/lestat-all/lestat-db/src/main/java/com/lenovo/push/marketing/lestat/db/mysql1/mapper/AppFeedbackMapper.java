package com.lenovo.push.marketing.lestat.db.mysql1.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;

public interface AppFeedbackMapper extends BaseMapper<AppFeedbackResult> {
	public int insertAppFeedbackResult(AppFeedbackResult afr);
//	public List<AppFeedbackResult> getOverAllAppFeedback(Map<String, Object> params);
	public List<AppFeedbackEntity> getDetailAppFeedback(Map<String, Object> params);
}
