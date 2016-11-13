package com.lenovo.push.marketing.lestat.db.mysql1.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppVersionDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppVersionMapper;

@Repository("mysql1AppVersionDao")
public class AppVersionDaoImpl extends BaseDaoImpl<DistCountDimensionResult, AppVersionMapper> implements
		AppVersionDao {
	
	public AppVersionDaoImpl() {
		setMapperClass(AppVersionMapper.class);
	}

	@Override
	public List<DistCountDimensionResult> getActNumByAppVersionPattern(String sid, String adid, String vernamePattern) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("adid", adid);
		params.put("vernamePattern", vernamePattern);
		return this.getMapper().getActNumByAppVersionPattern(params);
	}

}
