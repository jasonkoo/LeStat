package com.lenovo.push.marketing.lestat.kafka.appfeedback;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.lenovo.lps.push.marketing.common.vo.appinfo.AppFeedbackDataEntry;
import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.db.redis.service.AppFeedbackService;
import com.lenovo.push.marketing.lestat.kafka.param.Param;

public class AppFeedbackDataTransporter  implements Runnable{  

	private static Logger logger = Logger.getLogger(AppFeedbackDataTransporter.class);

	// @Resource
	private Config config;		
	private KafkaDataReader reader;	
    private ExecutorService threadPool;
    
    @Resource(name = "redisAppFeedbackService")
    private AppFeedbackService appFeedbackService;    
    
  /*  @Resource(name = "activeAppMsgServiceImpl")
    private ActiveAppMsgService activeAppMsgService;*/
   
    public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	private void transport(KafkaDataReader reader) {
		AppFeedbackDataEntry appFeedbackDataEntry;		
		while (true) {
			try {
				List<Object> list = reader.read();
				if (list != null) {
					for (Object o : list) {
						if (o != null) {
							String str = (String) o;
							if (StringUtils.isNotEmpty(str)) {								
								appFeedbackDataEntry =  parseLine(str);
								
								if (appFeedbackDataEntry.getSid().length() > 1 && !appFeedbackDataEntry.getSid().startsWith("r")) {
									appFeedbackService.saveAppFeedback(appFeedbackDataEntry);
								}
								
								// If logtime is later than startTime param and today								
								/*if (DateUtil.isEarlierThan(Param.getStartTime(), appFeedbackDataEntry.getLogTime()) && 
										DateUtil.isToday(appFeedbackDataEntry.getLogTime())) {									
									// If adid is active
									if (activeAppMsgService.isActive(appFeedbackDataEntry.getAdId())) {
										logger.debug("*#* Saving Active Task: " + appFeedbackDataEntry.getAdId() + "*#*");
										appFeedbackService.saveAppFeedback(appFeedbackDataEntry);
									} else {
										logger.debug("Task Not Active: " + appFeedbackDataEntry.getAdId());
									}
								} else {								
									logger.debug("taskTime: " + appFeedbackDataEntry.getLogTime() + ", startTime: " + Param.getStartTime());
								}*/
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("transport: " + e.getMessage());
				logger.error("transport: " + e);
				e.printStackTrace();
			}
		}

	}
	

	
	private AppFeedbackDataEntry parseLine(String line) {
		Gson g = new Gson();
		return g.fromJson(line,	AppFeedbackDataEntry.class);
	}
	

	public void init() {
		try {
			reader = new KafkaDataReader();
			reader.init(config, Param.getReaderIndex(), Param.getReaderCount());
			
			threadPool = Executors.newFixedThreadPool(1);
			threadPool.execute(this);
		} catch (Exception e) {
			logger.error("init: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			transport(reader);
		} catch (Exception e) {
			logger.error("run: " + e.getMessage());
		}
	}	
}
