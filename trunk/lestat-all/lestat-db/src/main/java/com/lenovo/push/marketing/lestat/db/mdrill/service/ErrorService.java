package com.lenovo.push.marketing.lestat.db.mdrill.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mdrill.dao.ErrorDao;
import com.lenovo.push.marketing.lestat.db.mdrill.entity.ErrorEntity;
import com.lenovo.push.marketing.lestat.db.mdrill.service.result.CfErrorResult;

@Service("mdrillErrorService")
public class ErrorService {
	
	@Autowired
	private ErrorDao mdrillErrorDao;
	
	public List<CfErrorResult> getCfErrorResult(String adId, String sd, String ed) {
		List<ErrorEntity> errorEntities = mdrillErrorDao.getCfError(adId, sd, ed);
		List<CfErrorResult> cfErrorResults = new ArrayList<CfErrorResult>();
		if (errorEntities != null && errorEntities.size() >0) {
			for ( ErrorEntity errorEntity : errorEntities ) {
				CfErrorResult cfErrorResult = new CfErrorResult(errorEntity);
				cfErrorResult.setAdId(adId);
				cfErrorResult.setSd(sd);
				cfErrorResult.setEd(ed);
				cfErrorResults.add(cfErrorResult);
			}
		}
		
		return cfErrorResults;
	}
}
