package com.lenovo.push.marketing.lestat.db.hive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.hive.dao.DeviceDao;
import com.lenovo.push.marketing.lestat.db.hive.entity.DeviceResult;

@Service("deviceService")
public class DeviceService {
	
	@Autowired
	private DeviceDao deviceDao;
	
	public DeviceResult getActiveuv(String st) {
		return deviceDao.getActiveUv(st);
	}
}
