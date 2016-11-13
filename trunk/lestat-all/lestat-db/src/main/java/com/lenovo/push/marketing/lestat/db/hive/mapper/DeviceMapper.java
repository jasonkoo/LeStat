package com.lenovo.push.marketing.lestat.db.hive.mapper;


import com.lenovo.push.marketing.lestat.db.hive.entity.DeviceResult;

public interface DeviceMapper extends BaseMapper<DeviceResult> {
	public DeviceResult getActiveUv(String st);
}
