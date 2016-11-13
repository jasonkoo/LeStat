package com.lenovo.push.marketing.lestat.db.mysql0.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;

public interface DisturbanceMapper extends BaseMapper<DisturbanceResult> {
	public int insertDisturbanceResults(List<DisturbanceResult> list);
	public ArrayList<DisturbanceResult> getDisturbanceResultsByDate(Map<String, Object> params);
	public int getDisturbanceResultTotalByDate(String thedate);
}
