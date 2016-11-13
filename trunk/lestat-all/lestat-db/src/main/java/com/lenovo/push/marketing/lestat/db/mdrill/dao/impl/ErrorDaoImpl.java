package com.lenovo.push.marketing.lestat.db.mdrill.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mdrill.dao.ErrorDao;
import com.lenovo.push.marketing.lestat.db.mdrill.entity.ErrorEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.mapper.ErrorMapper;

@Repository("mdrillErrorDao")
public class ErrorDaoImpl extends BaseDaoImpl<ErrorEntity, ErrorMapper> implements ErrorDao {

	public ErrorDaoImpl() {
		setMapperClass(ErrorMapper.class);
	}
	
	@Override
	public List<ErrorEntity> getCfError(String adId, String sd, String ed) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("adId", adId);
		params.put("sd", sd);
		params.put("ed", ed);
		return this.getMapper().getCfError(params);
	}

}
