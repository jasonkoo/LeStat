package com.lenovo.push.marketing.lestat.kafka.writer.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.lenovo.lps.push.marketing.drill.common.vo.HitDataEntry;
import com.lenovo.push.marketing.lestat.kafka.writer.ledrillinternal.DataGenerator;
import com.lenovo.push.marketing.lestat.kafka.writer.pmservice.LogTransporterImpl;

public class KafkaWriter implements Runnable {

	private static Logger logger = Logger.getLogger(KafkaWriter.class);
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
				//if (c % 3 == 0) {
					HitDataEntry hit = DataGenerator.getHitDataEntry();
					logTransporter.transportHitresult(hit);
				//}
			/*	if (c % 30 == 0) {
					FeedbackDataEntry feedback = DataGenerator
							.getFeedbackDataEntry();
					logTransporter.transportFeedback(feedback);
				}
				if (c % 100 == 0) {
					ErrorDataEntry error = DataGenerator.getErrorDataEntry();
					logTransporter.transportFeedbackError(error);
				}*/

				Thread.sleep(1 * 10);

			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

}
