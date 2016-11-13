package com.lenovo.push.marketing.lestat.db.mysql1.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.SUSFeedbackDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.TinySUSFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.SUSFeedbackMapper;

@Repository("mysql1SUSFeedbackDao")
public class SUSFeedbackDaoImpl extends BaseDaoImpl<TinySUSFeedbackResult, SUSFeedbackMapper> implements
		SUSFeedbackDao {
	
	public SUSFeedbackDaoImpl() {
		setMapperClass(SUSFeedbackMapper.class);
	}

	@Override
	public List<TinySUSFeedbackResult> getDailySUSFeedback(String appKey,
			String targetVercode, String channelKey, String thedate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appKey", appKey);
		params.put("targetVercode", targetVercode);
		params.put("channelKey", channelKey);
		params.put("thedate", thedate);
		
		return this.getMapper().getDailySUSFeedback(params);
	}

	@Override
	public List<TinySUSFeedbackResult> getWeeklySUSFeedback(String appKey,
			String targetVercode, String channelKey, String startdate,
			String enddate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("appKey", appKey);
		params.put("targetVercode", targetVercode);
		params.put("channelKey", channelKey);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		
		return this.getMapper().getWeeklySUSFeedback(params);
	}

	
}
