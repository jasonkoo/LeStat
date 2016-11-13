package com.lenovo.push.marketing.lestat.kafka.susfeedback;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.common.vo.SUSFeedback;
import com.lenovo.push.marketing.lestat.db.redis.service.SUSFeedbackService;
import com.lenovo.push.marketing.lestat.kafka.param.Param;

public class SUSFeedbackDataTransporter  implements Runnable{  

	private static Logger logger = Logger.getLogger(SUSFeedbackDataTransporter.class);

	// @Resource
	private Config config;		
	private KafkaDataReader reader;	
    private ExecutorService threadPool;
    
    @Resource(name = "redisSUSFeedbackService")
    private SUSFeedbackService susFeedbackService;    
    
    public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	private void transport(KafkaDataReader reader) {
		//AppFeedbackDataEntry appFeedbackDataEntry;
		SUSFeedback feedback;
		while (true) {
			try {
				List<Object> list = reader.read();
				if (list != null) {
					for (Object o : list) {
						if (o != null) {
							String str = (String) o;
							if (StringUtils.isNotEmpty(str)) {								
								feedback =  parseLine(str);
								
								// If logtime is later than startTime param and today								
								if (DateUtil.isEarlierThan(Param.getStartTime(), feedback.getLogTime()) && 
										DateUtil.isToday(feedback.getLogTime())) {
									logger.debug("*#* Saving Active Task: " + new Gson().toJson(feedback) + "*#*");
									susFeedbackService.saveAppFeedback(feedback);
								} else {								
									logger.debug("taskTime: " + new Gson().toJson(feedback) + ", startTime: " + Param.getStartTime());
								}
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
	

	
	private SUSFeedback parseLine(String line) {
		Gson g = new Gson();
		return g.fromJson(line,	SUSFeedback.class);
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
