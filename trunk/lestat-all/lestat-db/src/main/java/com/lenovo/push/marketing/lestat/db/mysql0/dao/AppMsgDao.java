package com.lenovo.push.marketing.lestat.db.mysql0.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.mapper.AppMsgMapper;

public interface AppMsgDao extends BaseDao<AppMsgEntity, AppMsgMapper> {
	public List<AppMsgEntity> getActiveAppMsgList(String lowerEndDate, List<String> adIdList);
	public List<AppMsgEntity> getExpiredAppMsgList(String lowerEndDate, String upperEndDate, List<String> adIdList);
}
