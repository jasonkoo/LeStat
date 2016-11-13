package com.lenovo.push.marketing.lestat.db.mysql0.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.db.mysql0.mapper.DisturbanceMapper;

public interface DisturbanceDao extends BaseDao<DisturbanceResult, DisturbanceMapper> {
	int insertDisturbanceResults(List<DisturbanceResult> list);
	public List<DisturbanceResult> getDisturbanceResultsByDate(String thedate, int limit, int offset);
	public int getDisturbanceResultTotalByDate(String thedate);
}
