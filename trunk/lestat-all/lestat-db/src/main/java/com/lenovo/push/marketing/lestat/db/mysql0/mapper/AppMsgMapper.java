package com.lenovo.push.marketing.lestat.db.mysql0.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;

public interface AppMsgMapper extends BaseMapper<AppMsgEntity> {
	public List<AppMsgEntity> getActiveAppMsgList(Map<String, Object> params);
	public List<AppMsgEntity> getExpiredAppMsgList(Map<String, Object> params);
}
