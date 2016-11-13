package com.lenovo.push.marketing.lestat.mr.job;


import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.mr.param.Param;
import com.lenovo.push.marketing.lestat.mr.task.DBTask;
import com.lenovo.push.marketing.lestat.mr.task.MRTask;
import com.lenovo.push.marketing.lestat.mr.task.RRTask;

public class JobB extends QuartzJobBean {
	
	private static Logger logger = Logger.getLogger(JobB.class);
	
	private Param param;
	
	private MRTask mrtask;
	private RRTask rrtask;
	private DBTask dbtask;	


	public void setParam(Param param) {
		this.param = param;
	}

	
	public void setMrtask(MRTask mrtask) {
		this.mrtask = mrtask;
	}


	public void setRrtask(RRTask rrtask) {
		this.rrtask = rrtask;
	}


	public void setDbtask(DBTask dbtask) {
		this.dbtask = dbtask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		// get hour of day (0-24)
		int hourOfDay = DateUtil.getHourOfDay();
		logger.info("Hour of day: " + hourOfDay);
		// If in 00:00 ~ 01:00, then compute yesterday
		String thedate;
		if (hourOfDay == 0) {
			thedate = DateUtil.getYesterday();
		} else {
			thedate = DateUtil.getToday();
		}

		logger.info("date: " + thedate);
		param.setThedate(thedate);

		
		// Task 1: run MapReduce
		mrtask.runMRJob();
		// Task 2: read Disturbance Results
		List<DisturbanceResult> list = rrtask.readResults();
		// Task 3: insert Disturbance Results into database
		dbtask.insertResults(list);	
		
	}	
}
