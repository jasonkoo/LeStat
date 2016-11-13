package com.lenovo.push.marketing.lestat.db.mysql1.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppFeedbackMapper;

public interface AppFeedbackDao extends BaseDao<AppFeedbackResult, AppFeedbackMapper> {
	public int insertAppFeedbackResult(AppFeedbackResult afr);
//	public List<AppFeedbackResult> getOverAllAppFeedback(String sid, String adid);
	public List<AppFeedbackEntity> getDetailAppFeedback(String sid, String adid, String startdate, String enddate);
}
