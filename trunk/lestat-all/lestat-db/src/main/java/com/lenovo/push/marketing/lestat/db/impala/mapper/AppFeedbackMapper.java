package com.lenovo.push.marketing.lestat.db.impala.mapper;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;

public interface AppFeedbackMapper extends BaseMapper<AdTaskStatResult> {
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate);
}
