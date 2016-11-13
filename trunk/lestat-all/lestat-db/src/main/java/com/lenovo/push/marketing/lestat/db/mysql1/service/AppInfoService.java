package com.lenovo.push.marketing.lestat.db.mysql1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppDeviceModelDao;
import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppVersionDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.DistCountDimensionResult;
import com.lenovo.push.marketing.lestat.db.param.Param;

@Service("mysql1AppInfoService")
public class AppInfoService {
	
	@Autowired
	private AppVersionDao mysql1AppVersionDao;
	@Autowired
	private AppDeviceModelDao mysql1AppDeviceModelDao;

	public List<DistCountDimensionResult> getDistCountByDimension(String sid, String dim) {
		if (Param.DIM_APP_VERSION.equals(dim)) {
			return mysql1AppVersionDao.getActNumByAppVersionPattern(sid, null, null);
		} else if (Param.DIM_DEVICE_MODEL.equals(dim)) {
			return mysql1AppDeviceModelDao.getActNumByAppDeviceModelPattern(sid, null, null);
		}		
		return null;
	}

}
