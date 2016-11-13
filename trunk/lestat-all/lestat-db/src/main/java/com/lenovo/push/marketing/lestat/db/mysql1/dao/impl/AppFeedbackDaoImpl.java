package com.lenovo.push.marketing.lestat.db.mysql1.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql1.dao.impl.BaseDaoImpl;
import com.lenovo.push.marketing.lestat.db.mysql1.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackEntity;
import com.lenovo.push.marketing.lestat.db.mysql1.entity.AppFeedbackResult;
import com.lenovo.push.marketing.lestat.db.mysql1.mapper.AppFeedbackMapper;

@Repository("mysql1AppFeedbackDao")
public class AppFeedbackDaoImpl extends BaseDaoImpl<AppFeedbackResult, AppFeedbackMapper> implements
		AppFeedbackDao {
	
	public AppFeedbackDaoImpl() {
		setMapperClass(AppFeedbackMapper.class);
	}

	@Override
	public int insertAppFeedbackResult(AppFeedbackResult afr) {		
		return this.getMapper().insertAppFeedbackResult(afr);
	}

/*	@Override
	public List<AppFeedbackResult> getOverAllAppFeedback(String sid, String adid) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("adid", adid);
		return this.getMapper().getOverAllAppFeedback(params);
	}*/

	@Override
	public List<AppFeedbackEntity> getDetailAppFeedback(String sid, String adid, String startdate, String enddate) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("sid", sid);
		params.put("adid", adid);
		params.put("startdate", startdate);
		params.put("enddate", enddate);
		return this.getMapper().getDetailAppFeedback(params);
	}	

}
