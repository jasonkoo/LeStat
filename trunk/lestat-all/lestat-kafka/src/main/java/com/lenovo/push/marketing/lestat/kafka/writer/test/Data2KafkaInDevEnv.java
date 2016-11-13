package com.lenovo.push.marketing.lestat.kafka.writer.test;

import java.io.File;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.kafka.App;

public class Data2KafkaInDevEnv {

	private static Logger logger = Logger.getLogger(App.class);

	public final static String MODULE_NAME = "test01";

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.kafka.writer.test.Data2KafkaInDevEnv -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> test01");
			return;
		}

		String confDir = com.lenovo.push.marketing.lestat.common.param.Param
				.getLestatConfDir();
		File log4jConfigFile = new File(
				confDir,
				MODULE_NAME
						+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		System.out.println("log4j_path : " + log4jConfigFile.getAbsolutePath());

		if (!log4jConfigFile.exists()) {
			System.out
					.println("Starter.main : log4j config file does not exist !");
			return;
		}
		if (!log4jConfigFile.canRead()) {
			System.out
					.println("Starter.main : fail to load log4j config file !");
			return;
		}

		// 初始化log4j
		ConfigUtil.configLog4j(log4jConfigFile.getPath());
		logger.info("config log4j: done");

		KafkaWriter writer = new KafkaWriter();
		writer.start();

		// 主程序循环
		while (true) {
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				logger.error("while ture: " + e.getMessage());
			}
		}
	}

}
