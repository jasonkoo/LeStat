package com.lenovo.push.marketing.hive.task;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lenovo.push.marketing.hive.task.dbproxy.DbProxy;
import com.lenovo.push.marketing.hive.task.subtask.AppFeedbackTask;
import com.lenovo.push.marketing.lestat.common.param.Param;
import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;
import com.lenovo.push.marketing.lestat.common.util.StringUtil;

public class HiveTaskMain {
	
	private static Logger logger = Logger.getLogger(HiveTaskMain.class);

	public final static String MODULE_HIVE_TASK = "hivetask";
	
	public final static String OPTION_D = "d";
	public final static String OPTION_S = "s";
	public final static String OPTION_A = "a";

	
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out
					.println("Usage : java com.lenovo.push.marketing.lestat.kafka.App -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> hivetask");
			return;
		}
		
		
		List<String> adIdList = null;
		List<String> dateList = null;

		// 创建 Options 对象
		Options options = new Options();

		// 添加 -d 参数
		options.addOption("d", "dateList", true, "Lists of dates");
		options.addOption("s", "sidList", true, "Lists of sids");
		options.addOption("a", "adIdList", true, "Lists of adIds");
		

		CommandLineParser parser = new PosixParser();
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption(OPTION_D)) {
				dateList = getValidDateList(cmd.getOptionValue(OPTION_D), ",");
			}
			if (dateList==null || dateList.size()==0) {
				dateList = new ArrayList<String>(1);
				dateList.add(DateUtil.getYesterday());
			}
			if (cmd.hasOption(OPTION_A)) {
				adIdList = StringUtil.getNonEmptyList(cmd.getOptionValue(OPTION_A), ",");
			}
			
			System.out.println("dateList:" + dateList);
			System.out.println("adIdList:" + adIdList);
			
		} catch (ParseException e) {
			System.out.println("cannot parse the args: " + e.getMessage());
			System.exit(-1);
		}
		
		

		String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();
		File log4jConfigFile = new File(confDir, MODULE_HIVE_TASK
				+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		
		if (!log4jConfigFile.exists()) {
			log4jConfigFile = new File(confDir, "default"
					+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
		}
		
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
		File applicationContextFile = new File(confDir, MODULE_HIVE_TASK
				+ com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("fail to load spring config file !");
			return;
		}
		// applicationContextFile = null;

		// 初始化spring
		ApplicationContext applicationContext = null;
		try {
			if (Env.isLinux()) {
				applicationContext = ConfigUtil.configSpring(Param.PATH_SEPARATOR
						+ applicationContextFile.getPath());
			} else {
				applicationContext = ConfigUtil.configSpring(applicationContextFile.getPath());
			}
		} catch (Exception e) {
			logger.error("init spring config with errors: " + e.getMessage());
			System.out.println("init spring config with errors: " + e.getMessage());
			System.exit(-1);
		}
		logger.info("initialize spring ok!");
		
//		HitDataTransporter transporter = new HitDataTransporter();
//		try {
//			transporter.run();
//		} catch (IOException e) {
//			logger.error(e.getMessage());
//		}
		
		try {
			if (applicationContext != null) {
				AppFeedbackTask appFeedbackTask = new AppFeedbackTask(dateList, adIdList);
				DbProxy dbProxy = (DbProxy) applicationContext.getBean("dbProxy");
				appFeedbackTask.setDbProxy(dbProxy);
				appFeedbackTask.calculate();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

		// 主程序循环
//		while (true) {
//			try {
//				Thread.sleep(60 * 1000);
//			} catch (InterruptedException e) {
//				logger.error("while ture: " + e.getMessage());
//			}
//		}
	}

	private static List<String> getValidDateList(String strList,
			String separator) {
		List<String> list = StringUtil.getNonEmptyList(strList, separator);
		if (list!=null) {
			String todayStr = DateUtil.getToday();
			List<String> resultList = new ArrayList<String>();
			for (String dateStr : list) {
				String dStr;
				try {
					dStr = DateUtil.dateToString(DateUtil.stringToDate(dateStr, DateUtil.DATE_PATTERN), DateUtil.DATE_PATTERN);
					if (DateUtil.isEarlierThan(dStr, todayStr)){
						resultList.add(dStr);
					}
					
				} catch (java.text.ParseException e) {
					System.err.println("invalid date: " + dateStr);
				}				
			}
			if (resultList.size()>0) {
				return resultList;
			}
		}
		return null;
	}
	
}
