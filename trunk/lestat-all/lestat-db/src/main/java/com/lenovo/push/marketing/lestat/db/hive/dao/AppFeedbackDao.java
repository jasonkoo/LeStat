package com.lenovo.push.marketing.lestat.db.hive.dao;


import java.sql.SQLException;
import java.util.List;

import com.lenovo.push.marketing.lestat.db.hive.entity.AppMsgEventStatEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;



public interface AppFeedbackDao {
	public List<AppMsgEventStatEntity> getAppMsgEventStatForActiveAppMsgs(List<AppMsgEntity> appMsgEntities, String startDate, String endDate) throws SQLException;
	public List<AppMsgEventStatEntity> getAppMsgEventStatForExpiredAppMsg(String sid, String adId, String startDate, String endDate) throws SQLException;
}
