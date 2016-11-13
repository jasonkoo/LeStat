package com.lenovo.push.marketing.lestat.kafka.appfeedback;

import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.service.AppMsgService;

public class UpdateActiveTasksJob extends QuartzJobBean{
	
	private static Logger logger = Logger.getLogger(UpdateActiveTasksJob.class);
	
	
	private AppMsgService appMsgService;	
    private ActiveAppMsgService activeAppMsgService;  

	public AppMsgService getAppMsgService() {
		return appMsgService;
	}

	public void setAppMsgService(AppMsgService appMsgService) {
		this.appMsgService = appMsgService;
	}

	public ActiveAppMsgService getActiveAppMsgService() {
		return activeAppMsgService;
	}

	public void setActiveAppMsgService(ActiveAppMsgService activeAppMsgService) {
		this.activeAppMsgService = activeAppMsgService;
	}

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {	
		if (appMsgService == null) {
			throw new RuntimeException("-----appMsgService null-------");
		}
		String thedate = DateUtil.getToday();
		logger.debug("Fetch Active App Msgs List");
		List<AppMsgEntity> appMsgEntities = appMsgService.getActiveAppMsgList(thedate, null);
		//List<String> activeTasks = new ArrayList<String>();
		HashSet<String> activeAppMsgSet = new HashSet<String>();
		
		logger.debug("Active App Msg List");
		if (appMsgEntities != null) {
			for (AppMsgEntity appMsgEntity : appMsgEntities) {
				logger.debug("***sid: " + appMsgEntity.getAppSid() + ", adid: " + appMsgEntity.getAppMsgid() + "***");
				activeAppMsgSet.add(appMsgEntity.getAppMsgid());				
			}
		}		
		activeAppMsgService.setActiveAppMsgs(activeAppMsgSet);
	}

}
