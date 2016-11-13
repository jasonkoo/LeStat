package com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.kafka.writer.ledrillinternal.DataGenerator;
import com.lenovo.push.marketing.lestat.kafka.writer.pmservice.LogTransporterImpl;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppDataEntry;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppFeedbackDataEntry;

public class AppKafkaWriter implements Runnable {

	private static Logger logger = Logger.getLogger(AppKafkaWriter.class);
	private ExecutorService threadPool;

	private LogTransporterImpl logTransporter;

	public void start() {
		try {
			logTransporter = new LogTransporterImpl();
			threadPool = Executors.newFixedThreadPool(1);
			threadPool.execute(this);
		} catch (Exception e) {
			logger.error("init: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			long c = -1;
			while (true) {
				c++;
				logger.debug("c=" + c);
				if (c % 3 == 0) {
					AppDataEntry app = DataGenerator.getAppDataEntry();
					logTransporter.transportAppInfo(app);
				}
				if (c % 30 == 0) {
					AppFeedbackDataEntry feedback = DataGenerator
							.getAppFeedbackDataEntry();
					logTransporter.transportAppFeedback(feedback);
				}

				Thread.sleep(1 * 100);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
