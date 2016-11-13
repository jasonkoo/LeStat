package com.lenovo.push.marketing.lestat.db.mdrill.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mdrill.dao.FeedbackDao;
import com.lenovo.push.marketing.lestat.db.mdrill.entity.FeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.mapper.FeedbackMapper;

@Repository("mdrillFeedbackDao")
public class FeedbackDaoImpl extends BaseDaoImpl<FeedbackEntity, FeedbackMapper> implements FeedbackDao{

	public FeedbackDaoImpl() {
		setMapperClass(FeedbackMapper.class);
	}
	
	
	@Override
	public FeedbackEntity getFeedbackResultDup(String adId, String thedate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("thedate", thedate);
		return this.getMapper().getFeedbackResultDup(params);
	}
	
	@Override
	public FeedbackEntity getDistCountByColx(String adId, String thedate, String colx) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("thedate", thedate);
		params.put("colx", colx);
		return this.getMapper().getDistCountByColx(params);
	}
	
	@Override
	public List<FeedbackEntity> getDailyCfFeedbackResultDup(String adId,
			List<String> pnList, String sd, String ed) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("pnList", pnList);
		params.put("sd", sd);
		params.put("ed", ed);
		return this.getMapper().getDailyCfFeedbackResultDup(params);
	}

	@Override
	public List<FeedbackEntity> getDailyCfFeedbackResultDist(String adId,
			List<String> pnList, String sd, String ed, String colx,
			String eventName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("pnList", pnList);
		params.put("sd", sd);
		params.put("ed", ed);
		params.put("colx", colx);
		params.put("eventName", eventName);
		return this.getMapper().getDailyCfFeedbackResultDist(params);
	}

	@Override
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDup(String adId,
			List<String> pnList, String sd, String ed) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("pnList", pnList);
		params.put("sd", sd);
		params.put("ed", ed);
		return this.getMapper().getDateRangeCfFeedbackResultDup(params);
	}

	@Override
	public List<FeedbackEntity> getDateRangeCfFeedbackResultDist(String adId,
			List<String> pnList, String sd, String ed, String colx,
			String eventName) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("pnList", pnList);
		params.put("sd", sd);
		params.put("ed", ed);
		params.put("colx", colx);
		params.put("eventName", eventName);
		return this.getMapper().getDateRangeCfFeedbackResultDist(params);
	}

	

}
