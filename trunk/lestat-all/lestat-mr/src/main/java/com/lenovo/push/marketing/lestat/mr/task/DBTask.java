package com.lenovo.push.marketing.lestat.mr.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.db.mysql0.service.DisturbanceService;

public class DBTask {
	private static Logger logger = Logger.getLogger(DBTask.class);
	
	private DisturbanceService disturbanceService;	

	public DisturbanceService getDisturbanceService() {
		return disturbanceService;
	}
	
	public void setDisturbanceService(DisturbanceService disturbanceService) {
		this.disturbanceService = disturbanceService;
	}



	public void insertResults(List<DisturbanceResult> list) {
		logger.info("Insert Results Task Starts!");
		long start = System.currentTimeMillis();
		int status = disturbanceService.insertDisturbanceResults(list);
			
		if (status == 0) {
			logger.info("Insert Success!");
		} else {
			logger.info("Insert Failure!");
		}
		long end = System.currentTimeMillis();
		logger.info("Insert Results Task Ends!");
		logger.info("Insert Results Task Takes: " + (end - start) / 1000 + " s");
	}
}
