package com.lenovo.push.marketing.lestat.db.hive.dao;

import com.lenovo.push.marketing.lestat.db.hive.entity.DeviceResult;

public interface DeviceDao {
	public DeviceResult getActiveUv(String st);
}
