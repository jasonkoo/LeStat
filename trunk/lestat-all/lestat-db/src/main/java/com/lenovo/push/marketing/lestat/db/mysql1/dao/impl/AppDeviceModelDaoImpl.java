package com.lenovo.push.marketing.lestat.db.mysql1.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppDeviceModelDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppDeviceModelMapper;

@Repository("mysql1AppDeviceModelDao")
public class AppDeviceModelDaoImpl extends BaseDaoImpl<DistCountDimensionResult, AppDeviceModelMapper> implements
		AppDeviceModelDao {
	
	public AppDeviceModelDaoImpl() {
		setMapperClass(AppDeviceModelMapper.class);
	}

	@Override
	public List<DistCountDimensionResult> getActNumByAppDeviceModelPattern(
			String sid, String adid, String deviceModelPattern) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("adid", adid);
		params.put("deviceModelPattern", deviceModelPattern);
		return this.getMapper().getActNumByAppDeviceModelPattern(params);
	}
	
	

}
