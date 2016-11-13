package com.lenovo.push.marketing.lestat.kafka.writer.pmservice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.lenovo.lps.push.marketing.drill.common.vo.ErrorDataEntry;
import com.lenovo.lps.push.marketing.drill.common.vo.FeedbackDataEntry;
import com.lenovo.lps.push.marketing.drill.common.vo.HitDataEntry;
import com.lenovo.push.marketing.lestat.common.param.Param;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppDataEntry;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppFeedbackDataEntry;

/**
 * 
 * @author liuhk2
 * 
 */
public class LogTransporterImpl {

	private static final Logger LOG = Logger
			.getLogger(LogTransporterImpl.class);

	//private final static String PRODUCER_PROPERTIES_FILENAME = "/kafka-producer.properties";
	private static String feedbackTopicName;
	private static String hitTopicName;
	private static String errorTopicName;
	
	private static String appInfoTopicName;
	private static String appFeedbackTopicName;
	
	private final static String MESSAGE_KEY = "key";

	private static Producer<String, String> producer;

	static {
		InputStream is = null;
		try {
			
			Properties properties = new Properties();
			String confDir = Param.getLestatConfDir();
			if (confDir!=null) {
				File propertiesFile = new File(confDir,"producer.properties");
				if (!propertiesFile.exists()) {
					throw new IOException("consumer.properties does not exist!");
				}
				properties.load(new FileInputStream(propertiesFile));
			} else {
				throw new IOException("-DlestatConfDir is null!");
			}
			
			ProducerConfig config = new ProducerConfig(properties);
			producer = new Producer<String, String>(config);
			feedbackTopicName = "feedback-topic";
			hitTopicName = "hit-topic";
			errorTopicName = "error-topic";
			appInfoTopicName = "appinfo-topic";
			appFeedbackTopicName = "appfeedback-topic";
		} catch (Exception e) {
			if (producer != null) {
				producer.close();
				producer = null;
			}
			LOG.error("cann't initialize logTransporter: " + e.getMessage());
			LOG.error(e,e);
		} finally {
			IOUtils.closeQuietly(is);
		}

	}

	private void send(String topicName, String message) {
		if (topicName == null || message == null || producer == null) {
			return;
		}

		KeyedMessage<String, String> km = new KeyedMessage<String, String>(
				topicName, MESSAGE_KEY, message);
		producer.send(km);
		LOG.debug("LogTransporterImpl.send , messgae :" + message + "topic: " + topicName);
	}

	private void send(String topicName, Collection<String> messages) {
		if (topicName == null || messages == null || producer == null) {
			return;
		}
		if (messages.isEmpty()) {
			return;
		}
		List<KeyedMessage<String, String>> kms = new ArrayList<KeyedMessage<String, String>>();
		for (String entry : messages) {
			KeyedMessage<String, String> km = new KeyedMessage<String, String>(
					topicName, MESSAGE_KEY, entry);
			LOG.debug("LogTransporterImpl.send , messgae :" + entry + "topic: " + topicName);
//			if(feedbackTopicName.equals(topicName)){
//				String[] cols = entry.trim().split("\\|");
//				if (cols.length == 17 && cols[11]!=null && cols[11].getBytes().length!=3) {
//					LOG.warn("Illegal operation type: " + cols[11]);
//					LOG.warn("entry to kafuka : " + entry);
//				}
//			}
			kms.add(km);
		}
		producer.send(kms);
	}
	
	public void shutdownProducer(){
		if (producer!=null){
			producer.close();
			producer=null;
		}
	}

	public String transportFeedback(FeedbackDataEntry arg0) {
		send(feedbackTopicName, arg0.toStringForParsing());
		return "OK";
	}

	public String transportFeedback(Collection<FeedbackDataEntry> arg0) {
		Collection<String> feedbackStrCol = new HashSet<String>();
		for (FeedbackDataEntry feedback : arg0) {
			feedbackStrCol.add(feedback.toStringForParsing());
		}
		send(feedbackTopicName, feedbackStrCol);
		return "OK";
	}
	
	public String transportFeedbackError(ErrorDataEntry arg0) {
		send(errorTopicName, arg0.toStringForParsing());
		return "OK";
	}

	public String transportFeedbackError(Collection<ErrorDataEntry> arg0) {
		Collection<String> feedbackErrorStrCol = new HashSet<String>();
		for (ErrorDataEntry error : arg0) {
			feedbackErrorStrCol.add(error.toStringForParsing());
		}
		send(errorTopicName, feedbackErrorStrCol);
		return "OK";
	}

	public String transportHitresult(HitDataEntry arg0) {
		send(hitTopicName, arg0.toStringForParsing());
		return "OK";
	}

	public String transportHitresult(Collection<HitDataEntry> arg0) {
		Collection<String> hitResultStrCol = new HashSet<String>();
		for (HitDataEntry hitResult : arg0) {
			hitResultStrCol.add(hitResult.toStringForParsing());
		}
		send(hitTopicName, hitResultStrCol);
		return "OK";
	}
	
	public String transportAppInfo(AppDataEntry arg0) {
		send(appInfoTopicName, arg0.toStringForParsing());
		return "OK";
	}

	public String transportAppInfo(Collection<AppDataEntry> arg0) {
		Collection<String> col = new HashSet<String>();
		for (AppDataEntry appInfo : arg0) {
			col.add(appInfo.toStringForParsing());
		}
		send(appInfoTopicName, col);
		return "OK";
	}

	
	public String transportAppFeedback(AppFeedbackDataEntry arg0) {
		send(appFeedbackTopicName, arg0.toStringForParsing());
		return "OK";
	}

	public String transportAppFeedback(Collection<AppFeedbackDataEntry> arg0) {
		Collection<String> col = new HashSet<String>();
		for (AppFeedbackDataEntry appFeedback : arg0) {
			col.add(appFeedback.toStringForParsing());
		}
		send(appFeedbackTopicName, col);
		return "OK";
	}
}
