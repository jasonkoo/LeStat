package com.lenovo.push.marketing.lestat.logstash;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;
import com.lenovo.push.marketing.lestat.logstash.task.CPTask;


/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class);
	public static final String MODULE_LOGSTASH = "logstash";
	
    public static void main( String[] args )
    {
    	String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();

		File log4jConfigFile = new File(confDir, MODULE_LOGSTASH + com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
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
		File applicationContextFile = new File(confDir, MODULE_LOGSTASH + com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("mapred App.main : spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("mapred App.main : fail to load spring config file !");
			return;
		}
		
		// 初始化spring
		ApplicationContext context;
		if (Env.isLinux()) {
			context = ConfigUtil.configSpring(com.lenovo.push.marketing.lestat.common.param.Param.PATH_SEPARATOR + applicationContextFile.getAbsolutePath());
		} else {
			context = ConfigUtil.configSpring(applicationContextFile.getAbsolutePath());
		}
		logger.info("initialize spring ok!");
		
		CPTask cptask = (CPTask) context.getBean("cptask");
		
		if (args.length > 1) {
			for (int i = 1; i < args.length; i++) {
				String dateStr = args[i];
				logger.info("input date time: " + dateStr);
				cptask.copyFiles(dateStr);
			}			
		}
    }
}
