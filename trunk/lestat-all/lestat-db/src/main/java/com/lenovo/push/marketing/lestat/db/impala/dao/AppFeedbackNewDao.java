package com.lenovo.push.marketing.lestat.db.impala.dao;

import java.sql.SQLException;
import java.util.List;

import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;

public interface AppFeedbackNewDao  {
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate) throws SQLException;
}
