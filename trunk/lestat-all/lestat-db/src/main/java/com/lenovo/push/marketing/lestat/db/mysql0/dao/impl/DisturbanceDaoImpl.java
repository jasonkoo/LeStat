package com.lenovo.push.marketing.lestat.db.mysql0.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql0.dao.DisturbanceDao;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.db.mysql0.mapper.DisturbanceMapper;

@Repository("disturbanceDao")
public class DisturbanceDaoImpl extends BaseDaoImpl<DisturbanceResult, DisturbanceMapper> implements
		DisturbanceDao {
	
	public DisturbanceDaoImpl() {
		setMapperClass(DisturbanceMapper.class);
	}
	
	@Override
	public int insertDisturbanceResults(List<DisturbanceResult> list) {
		return this.getMapper().insertDisturbanceResults(list);		
	}
	
	@Override
	public List<DisturbanceResult> getDisturbanceResultsByDate(String thedate, int limit, int offset) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("thedate", thedate);
		params.put("limit", new Integer(limit));
		params.put("offset", new Integer(offset));
		return this.getMapper().getDisturbanceResultsByDate(params);
	}
	
	@Override
	public int getDisturbanceResultTotalByDate(String thedate) {		
		return this.getMapper().getDisturbanceResultTotalByDate(thedate);
	}
	
}
