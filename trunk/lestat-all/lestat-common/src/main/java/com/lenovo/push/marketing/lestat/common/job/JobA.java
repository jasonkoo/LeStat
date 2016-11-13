package com.lenovo.push.marketing.lestat.common.job;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobA implements Job {

	private static Logger logger = Logger.getLogger(JobA.class);
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {

		logger.debug("jobA execute");
	}

}
