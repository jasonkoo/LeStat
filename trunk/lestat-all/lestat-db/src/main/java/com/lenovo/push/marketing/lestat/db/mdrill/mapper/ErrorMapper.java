package com.lenovo.push.marketing.lestat.db.mdrill.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.ErrorEntity;

public interface ErrorMapper extends BaseMapper<ErrorEntity> {
	public List<ErrorEntity> getCfError(Map<String, Object> params);
}
