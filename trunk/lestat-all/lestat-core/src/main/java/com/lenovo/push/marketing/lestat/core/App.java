package com.lenovo.push.marketing.lestat.core;

import java.util.ArrayList;
import java.util.List;


/**
 * Hello world!
 * 
 */
public class App {

	public final static String MODULE_KAFKA = com.lenovo.push.marketing.lestat.kafka.App.MODULE_KAFKA;
	public final static String MODULE_MAPRED = com.lenovo.push.marketing.lestat.mr.App.MODULE_MAPRED;
	public final static String MODULE_DB = com.lenovo.push.marketing.lestat.db.App.MODULE_DB;
	public final static String MODULE_LOGSTASH = com.lenovo.push.marketing.lestat.logstash.App.MODULE_LOGSTASH;
	public final static String MODULE_ENGINE = com.lenovo.push.marketing.lestat.engine.App.MODULE_ENGINE;
	public final static String MODULE_KAFKA_APPFEEDBACK = com.lenovo.push.marketing.lestat.kafka.appfeedback.AppFeedbackMain.MODULE_KAFKA_APPFEEDBACK;
	public final static String MODULE_KAFKA_SUSFEEDBACK = com.lenovo.push.marketing.lestat.kafka.susfeedback.SUSFeedbackMain.MODULE_KAFKA_SUSFEEDBACK;
	public final static String MODULE_KAFKA_FEEDBACK = com.lenovo.push.marketing.lestat.kafka.feedback.FeedbackMain.MODULE_KAFKA_FEEDBACK;
	public final static String MODULE_KAFKA_LAG = com.lenovo.push.marketing.lestat.kafka.lag.LagCollectorMain.MODULE_KAFKA_LAG;
	
	public final static String MODULE_Test_01 = com.lenovo.push.marketing.lestat.kafka.writer.test.Data2KafkaInDevEnv.MODULE_NAME;
	public final static String MODULE_Test_02 = com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.AppData2KafkaInDevEnv.MODULE_NAME;

	@SuppressWarnings("serial")
	public final static List<String> MODULE_LIST = new ArrayList<String>() {
		{
			add(MODULE_KAFKA);
			add(MODULE_MAPRED);
			add(MODULE_DB);
			add(MODULE_LOGSTASH);
			add(MODULE_ENGINE);
			add(MODULE_KAFKA_APPFEEDBACK);
			add(MODULE_KAFKA_SUSFEEDBACK);
			add(MODULE_KAFKA_FEEDBACK);
			add(MODULE_KAFKA_LAG);
			
			add(MODULE_Test_01);
			add(MODULE_Test_02);
		}
	};

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.core.App -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> <module> ...");
			return;
		}

		String module = args[0];
		System.out.println("module : " + module);
		if (!MODULE_LIST.contains(args[0])) {
			System.out.println("unknown module");
			return;
		}

		String lestatHome = System.getProperty("lestat.home");
		System.out.println("lestatHome : " + lestatHome);

		if (MODULE_KAFKA.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.App.main(args);
		}
		
		if (MODULE_MAPRED.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.mr.App.main(args);
		}
		
		if (MODULE_DB.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.db.App.main(args);
		}
		
		if (MODULE_LOGSTASH.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.logstash.App.main(args);
		}
		
		if (MODULE_ENGINE.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.engine.App.main(args);
		}
		
		if (MODULE_KAFKA_APPFEEDBACK.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.appfeedback.AppFeedbackMain.main(args);
		}
		
		if (MODULE_KAFKA_SUSFEEDBACK.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.susfeedback.SUSFeedbackMain.main(args);
		}
		
		if (MODULE_KAFKA_FEEDBACK.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.feedback.FeedbackMain.main(args);
		}
		
		if (MODULE_KAFKA_LAG.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.lag.LagCollectorMain.main(args);
		}
		
		if (MODULE_Test_01.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.writer.test.Data2KafkaInDevEnv.main(args);
		}
		
		if (MODULE_Test_02.equals(module.toLowerCase())) {
			com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.AppData2KafkaInDevEnv.main(args);
		}
	}
}
