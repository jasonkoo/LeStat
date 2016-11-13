package com.lenovo.push.marketing.lestat.db;

import java.io.File;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lenovo.push.marketing.lestat.common.param.Param;
import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;

/**
 * Hello world!
 *
 */
public class App 
{	
	private static Logger logger = Logger.getLogger(App.class);
	
	public static final String MODULE_DB = "db";
	
    public static void main( String[] args )
    {
    	String confDir = com.lenovo.push.marketing.lestat.common.param.Param
				.getLestatConfDir();

		File log4jConfigFile = new File(
				confDir,
				MODULE_DB
						+ com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
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
		File applicationContextFile = new File(
				confDir,
				MODULE_DB
						+ com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("db App.main : spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("db App.main : fail to load spring config file !");
			return;
		}
		
		ApplicationContext ac = null;
		// 初始化spring
		if (Env.isLinux()) {
			ac = ConfigUtil.configSpring(Param.PATH_SEPARATOR + applicationContextFile.getAbsolutePath());
		} else {
			ac = ConfigUtil.configSpring(applicationContextFile.getAbsolutePath());
		}
		logger.info("initialize spring ok!");		
	
		//AppFeedbackService as = (AppFeedbackService)ac.getBean("impalaAppFeedbackService");
		//as.test();
		//for (int j = 0; j < 12; j++) {
			/*DisturbanceService ds = (DisturbanceService)ac.getBean("disturbanceService");
			String thedate="20140820";
			List<DisturbanceResult> dRs = ds.getDisturbanceResultsByDate(thedate);
			for (int i = 0; i < dRs.size(); i++) {
				DisturbanceResult dr = dRs.get(i);
				System.out.println(dr.getHitCount() + ":" + dr.getNumUsers());
			}*/
			//MyThread myThread = new MyThread(ac);
			//myThread.start();
		//}
		
		/*DeviceService ds = (DeviceService)ac.getBean("deviceService");
		String st="1409500800";
		DeviceResult dr = ds.getActiveuv(st);
		System.out.println(dr.getUv());	*/
		//AdTaskService adTaskService = (AdTaskService) ac.getBean("mysql0AdTaskService");
		//adTaskService.getActiveAdTaskList("20140809");
		
    }
    /*public static class MyThread extends Thread {
    	private ApplicationContext ac;
    	public MyThread(ApplicationContext ac){
    		this.ac = ac;
    	}
    	@Override
    	public void run() {    		
    		super.run();
    		DisturbanceService ds = (DisturbanceService)ac.getBean("disturbanceService");
			String thedate="20140820";
			List<DisturbanceResult> dRs = ds.getDisturbanceResultsByDate(thedate);
			for (int i = 0; i < dRs.size(); i++) {
				DisturbanceResult dr = dRs.get(i);
				System.out.println(dr.getHitCount() + ":" + dr.getNumUsers());
			}
    	}
    }*/
}
