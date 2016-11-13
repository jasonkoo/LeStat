package com.lenovo.push.marketing.lestat.db.impala.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;
import com.lenovo.push.marketing.lestat.db.impala.mapper.AppFeedbackMapper;

public interface AppFeedbackDao extends BaseDao<AdTaskStatResult, AppFeedbackMapper> {
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate);
}
