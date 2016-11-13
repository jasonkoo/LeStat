package com.lenovo.push.marketing.lestat.mr;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import com.lenovo.push.marketing.lestat.common.util.ConfigUtil;
import com.lenovo.push.marketing.lestat.common.util.Env;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.mr.param.Param;
import com.lenovo.push.marketing.lestat.mr.task.DBTask;
import com.lenovo.push.marketing.lestat.mr.task.MRTask;
import com.lenovo.push.marketing.lestat.mr.task.RRTask;

/**
 * Hello world!
 *
 */
public class App 
{
	private static Logger logger = Logger.getLogger(App.class);
	
	public static final String MODULE_MAPRED = "mapred";
	
    public static void main( String[] args )
    {
    	if (args.length < 2) {
    		System.out.println("must have 2 or more args!");
    		System.out.println("Usage: java -Dlestat.home=<lestat.home> -Dlogfile.name=<logfile.name> com.lenovo.push.marketing.lestat.mr.App mapred hit 20140925 ...");
    		return;
    	}
    	
		String confDir = com.lenovo.push.marketing.lestat.common.param.Param.getLestatConfDir();

		File log4jConfigFile = new File(confDir, MODULE_MAPRED + com.lenovo.push.marketing.lestat.common.param.Param.LOG4J_SURFIX);
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
		File applicationContextFile = new File(confDir, MODULE_MAPRED + com.lenovo.push.marketing.lestat.common.param.Param.SPRING_SURFIX);

		if (!applicationContextFile.exists()) {
			logger.info("mapred App.main : spring config file does not exist !");
			return;
		}
		if (!applicationContextFile.canRead()) {
			logger.info("mapred App.main : fail to load spring config file !");
			return;
		}
		
		// 初始化spring
		ApplicationContext context = null;
		if (Env.isLinux()) {
			context = ConfigUtil.configSpring(com.lenovo.push.marketing.lestat.common.param.Param.PATH_SEPARATOR + applicationContextFile.getAbsolutePath());
		} else {
			context = ConfigUtil.configSpring(applicationContextFile.getAbsolutePath());
		}
		logger.info("initialize spring ok!");
		
		// set data source: hit
		Param param = (Param)context.getBean("mapredParams");
		logger.info("data source: " + args[1]);
		param.setDataSource(args[1]);		
		
	    // 
		if (args.length > 2) {
			MRTask mrtask = (MRTask) context.getBean("mrtask");
			RRTask rrtask = (RRTask) context.getBean("rrtask");
			DBTask dbtask = (DBTask) context.getBean("dbtask");
			for(int i = 2; i < args.length; i++){
				param.setThedate(args[i]);
				mrtask.runMRJob();
				List<DisturbanceResult> list = rrtask.readResults();
				for (DisturbanceResult dr : list) {
					System.out.println(dr.getHitCount() + ":" + dr.getNumUsers());
				}
				//dbtask.insertResults(list);
			}
		}
    }
}
