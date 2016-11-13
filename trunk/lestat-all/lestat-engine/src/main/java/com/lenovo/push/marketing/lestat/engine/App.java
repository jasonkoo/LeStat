package com.lenovo.push.marketing.lestat.engine;

import java.io.File;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.param.Param;
import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;

/**
 * Hello world!
 * 
 */
public class App {
	private static Logger logger = Logger.getLogger(App.class);

	public final static String MODULE_ENGINE = "engine";

	public static void main(String[] args) {
		if (args.length != 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.engine.App -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> engine");
			return;
		}
		
		
		

		String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();
		File log4jConfigFile = new File(confDir, MODULE_ENGINE
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
		

		// 初始化Spring的ApplicationContext.xml配置文件
		// logger.info("appContextPath = " + spring_path);
		File applicationContextFile = new File(confDir, MODULE_ENGINE
				+ com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("Kafka App.main : spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("Kafka App.main : fail to load spring config file !");
			return;
		}
		// applicationContextFile = null;

		// 初始化spring
		if (Env.isLinux()) {
			ConfigUtil.configSpring(Param.PATH_SEPARATOR + applicationContextFile.getPath());
		} else {
			ConfigUtil.configSpring(applicationContextFile.getPath());
		}
		logger.info("initialize spring ok!");
		
//		HitDataTransporter transporter = new HitDataTransporter();
//		try {
//			transporter.run();
//		} catch (IOException e) {
//			logger.error(e.getMessage());
//		}

		// 主程序循环
//		while (true) {
//			try {
//				Thread.sleep(60 * 1000);
//			} catch (InterruptedException e) {
//				logger.error("while ture: " + e.getMessage());
//			}
//		}
	}
}
