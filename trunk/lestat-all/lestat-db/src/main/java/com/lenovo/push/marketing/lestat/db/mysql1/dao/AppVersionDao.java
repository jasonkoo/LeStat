package com.lenovo.push.marketing.lestat.db.mysql1.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppVersionMapper;

public interface AppVersionDao extends BaseDao<DistCountDimensionResult, AppVersionMapper> {
	public List<DistCountDimensionResult> getActNumByAppVersionPattern(String sid, String adid, String vernamePattern);
}
