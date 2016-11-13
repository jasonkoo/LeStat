package com.lenovo.push.marketing.lestat.logstash.job;


import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lenovo.push.marketing.lestat.logstash.task.CPTask;



public class JobA extends QuartzJobBean {
	
	private static Logger logger = Logger.getLogger(JobA.class);
	
	private CPTask cptask;

	public void setCptask(CPTask cptask) {
		this.cptask = cptask;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0)
			throws JobExecutionException {
		logger.info("JobA starts!");
		cptask.copyFiles(null);
		
	}
	
	/*@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
	}*/
}
