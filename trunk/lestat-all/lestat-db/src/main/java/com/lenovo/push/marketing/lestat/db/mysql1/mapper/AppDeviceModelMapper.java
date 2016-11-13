package com.lenovo.push.marketing.lestat.db.mysql1.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;

public interface AppDeviceModelMapper extends BaseMapper<DistCountDimensionResult> {
	public List<DistCountDimensionResult> getActNumByAppDeviceModelPattern(Map<String, Object> params);
}
