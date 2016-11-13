package com.lenovo.push.marketing.lestat.kafka.feedback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lenovo.lps.push.common.eventbus.PushEventBus;
import com.lenovo.lps.push.common.eventbus.Topics;
import com.lenovo.lps.push.common.eventbus.serializer.impl.HiveRecordSerializer;
import com.lenovo.lps.push.common.eventbus.serializer.impl.JsonSerializer;
import com.lenovo.lps.push.common.vo.FeedbackEvent;
import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;

public class FeedbackMain {
	private static Logger logger = Logger.getLogger(FeedbackMain.class);

	public final static String MODULE_KAFKA_FEEDBACK = "kafka-feedback";

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.kafka.feedback.FeedbackMain -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> kafka-feedback");
			return;
		}
		
		String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();
		File log4jConfigFile = new File(confDir, MODULE_KAFKA_FEEDBACK + com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		System.out.println("log4j_path : " + log4jConfigFile.getAbsolutePath());


		if (!log4jConfigFile.exists()) {
			log4jConfigFile = new File(confDir, com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_DEFAULT 
					+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		}

		// 初始化log4j
		ConfigUtil.configLog4j(log4jConfigFile.getPath());
		logger.info("config log4j: done");
		

		// 初始化Spring的ApplicationContext.xml配置文件
		// logger.info("appContextPath = " + spring_path);
		File applicationContextFile = new File(confDir, MODULE_KAFKA_FEEDBACK + com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("Kafka App.main : spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("Kafka App.main : fail to load spring config file !");
			return;
		}
		
		ApplicationContext ac = null;
		// 初始化spring
		if (Env.isLinux()) {
			ac = ConfigUtil.configSpring(com.lenovo.push.marketing.lestat.common.param.Param.PATH_SEPARATOR + applicationContextFile.getPath());
		} else {
			ac = ConfigUtil.configSpring(applicationContextFile.getPath());
		}
		logger.info("initialize spring ok!");
		
		TaskInfoCache cache = (TaskInfoCache)ac.getBean("taskInfoCache");
		logger.info("init cache!");
		cache.init();
		
	    Properties prop = new Properties();
	    InputStream is = null;
	    try {
			is = new FileInputStream(com.lenovo.push.marketing.lestat.common.param.Param.getKafkaEventBusPropertyFilePath());
			prop.load(is);
	    } catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	    
		PushEventBus pushEventBus = new PushEventBus(prop);
		
		while (true) {
			JsonSerializer<FeedbackEvent> fbEventSerializer = new JsonSerializer<FeedbackEvent>(FeedbackEvent.class);
			FeedbackEvent fbEvent = pushEventBus.take(Topics.PUSH_MARKETING_FEEDBACK, "lestat.kafka.precision", fbEventSerializer);
			if(fbEvent != null) {
				String feedbackId = fbEvent.getFeedbackId();
				if (! feedbackId.equals("null")) {
					PrecisionFeedbackEvent pfbEvent = new PrecisionFeedbackEvent(fbEvent);
					if (cache.exist(feedbackId)) {
						pfbEvent.setTaskAppPkgName(cache.getValue(feedbackId));
					} else {
						cache.add(feedbackId);
						pfbEvent.setTaskAppPkgName(cache.getValue(feedbackId));
					}
					HiveRecordSerializer<PrecisionFeedbackEvent> pfbEventSerializer = new HiveRecordSerializer<PrecisionFeedbackEvent>("precision.feedback", 
							new String[]{"pid","bizType","eventName","feedbackId","success","sid","packName","currVer","targetVer","value", "taskAppPkgName"});
					pushEventBus.publish(Topics.PUSH_BIGDATA_HDFS, pfbEvent, pfbEventSerializer);
				} else {
					logger.info("#### null feedbackId ###");
				}				
			}
			
		}		
	}
}
