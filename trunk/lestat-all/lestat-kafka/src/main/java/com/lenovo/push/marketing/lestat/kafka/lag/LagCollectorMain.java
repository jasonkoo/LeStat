package com.lenovo.push.marketing.lestat.kafka.lag;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.lenovo.czlib.nodex.ZKHelper;
import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;

public class LagCollectorMain {
	
	private static Logger logger = Logger.getLogger(LagCollectorMain.class);
	
	public final static String MODULE_KAFKA_LAG = "kafka-lag";
	
	private final static String ZK_CON = "10.0.4.110:2181,10.0.4.111:2181,10.0.4.112:2181,10.0.4.113:2181,10.0.4.118:2181";
	
	private final static String CONSUMER_PATH = "/consumers";
	
	private final static String OUTPUTPATH = "/data/kafka/consumer-lag";
	
	private final static HashMap<String, Long> consumerThreshold = new HashMap<String, Long>() {
		
		private static final long serialVersionUID = 1L;

		{
			put("hitred:hit-topic", 100L);
			put("hit:hit-topic", 100L);
			put("feedback:feedback-topic", 100L);
			put("feedbackred:feedback-topic", 100L);
			put("error:error-topic", 100L);
			
			put("logstash:hit-topic", 100L);
			put("logstashfb:feedback-topic", 100L);
			put("logstash-appinfo:appinfo-topic", 100L);
			put("logstash-appfeedback:appfeedback-topic", 100L);
			
			put("lestat-kafka-redis:appfeedback-topic", 100L);
			put("lestat.kafka.precision:push.marketing.feedback", 100L);
			
			put("flume.push.bigdata.hdfs:push.bigdata.hdfs", 100L);
			put("PMADService:push.marketing.feedback", 100L);
			put("MsgRouterService:push.core.poll", 100L);
			put("DataReportCollector:push.marketing.feedback", 100L);
			
			put("readtrace:trace", 100L);
			
			
			
			
			//put("lestat:appfeedback-topic", 100L);
			//put("flume-feedback:feedback", 100L);
			//put("DataReportCollector:push.core.poll", 100L);
			
			
		}		
	};

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.kafka.lag.LagCollectorMain -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> kafka-lag");
			return;
		}
		
		String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();
		File log4jConfigFile = new File(confDir, MODULE_KAFKA_LAG + com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		System.out.println("log4j_path : " + log4jConfigFile.getAbsolutePath());


		if (!log4jConfigFile.exists()) {
			log4jConfigFile = new File(confDir, com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_DEFAULT 
					+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		}
		
		// 初始化log4j
		ConfigUtil.configLog4j(log4jConfigFile.getPath());
		logger.info("config log4j: done");
		
		ZKHelper zkHelper = new ZKHelper(ZK_CON);
		
		File outFile = new File(OUTPUTPATH);
		try {
			if (!outFile.exists()) {
				outFile.createNewFile();
			} else {
				outFile.delete();
				outFile.createNewFile();
			}
			FileWriter fw = new FileWriter(outFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			List<String> consumers = zkHelper.children(CONSUMER_PATH);
			if (consumers != null && consumers.size() > 0) {
				for (String consumer : consumers) {
					String offsetPath = CONSUMER_PATH + "/" + consumer + "/offsets";
					List<String> topics = zkHelper.children(offsetPath);
					if (topics != null && topics.size() > 0) {
						for (String topic : topics) {
							String topicPath = offsetPath + "/" + topic;
							List<String> partitions = zkHelper.children(topicPath);
							if (partitions != null && partitions.size() > 0) {
								long totalLag = 0;
								for (String partition : partitions) {
									String partitionPath = topicPath + "/" + partition;
									String value = new String(zkHelper.get(partitionPath));
									totalLag += Long.parseLong(value);
									//System.out.println(partitionPath + ":" + value);
									
								}
								if (consumerThreshold.containsKey(consumer + ":" + topic)) {
									bw.write(consumer + ":" + topic +  "\t" + totalLag + "\t" + consumerThreshold.get(consumer + ":" + topic) + "\n");
								}
							}
						}
					}
				}
			}
			if (bw != null) bw.close();
			if (fw != null) fw.close();			
			
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

}
