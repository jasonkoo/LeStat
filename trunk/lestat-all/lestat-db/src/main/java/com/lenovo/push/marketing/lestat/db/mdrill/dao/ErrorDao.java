package com.lenovo.push.marketing.lestat.db.mdrill.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mdrill.entity.ErrorEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.mapper.ErrorMapper;

public interface ErrorDao extends BaseDao<ErrorEntity, ErrorMapper> {
	public List<ErrorEntity> getCfError(String adId, String sd, String ed);
}
