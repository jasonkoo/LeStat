package com.lenovo.push.marketing.lestat.db.impala.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.impala.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;
import com.lenovo.push.marketing.lestat.db.impala.mapper.AppFeedbackMapper;

@Repository("impalaAppFeedbackDao")
public class AppFeedbackDaoImpl extends BaseDaoImpl<AdTaskStatResult, AppFeedbackMapper> implements
		AppFeedbackDao {
	
	public AppFeedbackDaoImpl() {
		setMapperClass(AppFeedbackMapper.class);
	}

	@Override
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate) {		
		return this.getMapper().getAppFeedbackStat(thedate);
	}
}
