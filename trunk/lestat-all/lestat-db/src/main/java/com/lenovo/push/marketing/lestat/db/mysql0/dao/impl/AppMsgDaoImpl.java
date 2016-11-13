package com.lenovo.push.marketing.lestat.db.mysql0.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql0.dao.AppMsgDao;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.mapper.AppMsgMapper;

@Repository("mysql0AppMsgDao")
public class AppMsgDaoImpl extends BaseDaoImpl<AppMsgEntity, AppMsgMapper> implements AppMsgDao {
	
	public AppMsgDaoImpl() {
		setMapperClass(AppMsgMapper.class);
	}

	@Override
	public List<AppMsgEntity> getActiveAppMsgList(String lowerEndDate, List<String> adIdList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lowerEndDate", lowerEndDate);	
		params.put("adIdList", adIdList);
		return this.getMapper().getActiveAppMsgList(params);
	}

	@Override
	public List<AppMsgEntity> getExpiredAppMsgList(String lowerEndDate, String upperEndDate, List<String> adIdList) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("lowerEndDate", lowerEndDate);		
		params.put("upperEndDate", upperEndDate);
		params.put("adIdList", adIdList);
		return this.getMapper().getExpiredAppMsgList(params);
	}
}
