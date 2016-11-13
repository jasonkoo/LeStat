package com.lenovo.push.marketing.lestat.db.mysql0.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mysql0.dao.DisturbanceDao;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;

@Service("disturbanceService")
public class DisturbanceService {
	
	@Autowired
	private DisturbanceDao disturbanceDao;
	
	public int insertDisturbanceResults(List<DisturbanceResult> list) {
		return disturbanceDao.insertDisturbanceResults(list);
	}
}
