package com.lenovo.push.marketing.lestat.db.mysql1.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppDeviceModelMapper;

public interface AppDeviceModelDao extends BaseDao<DistCountDimensionResult, AppDeviceModelMapper> {
	public List<DistCountDimensionResult> getActNumByAppDeviceModelPattern(String sid, String adid, String deviceModelPattern);
}
