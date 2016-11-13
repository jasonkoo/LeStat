package com.lenovo.push.marketing.lestat.mr.task;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;
import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.hdfs.util.HadoopUtil;
import com.lenovo.push.marketing.lestat.mr.disturbance.Driver;
import com.lenovo.push.marketing.lestat.mr.param.Param;

public class MRTask{
	private static Logger logger = Logger.getLogger(MRTask.class);
	
	private Driver driver;
	
	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	private Config config;
	private Param param;
	

	public void init() {		
		this.config = driver.getConfig();
		this.param = driver.getParam();
	}

	public void runMRJob() {
		String[] args = new String[2];
		args[0] = "-libjars";		
		args[1] = "";
		String jarStrings = param.getLibjars();
		String lestatHome = System.getProperty("lestat.home");
		String[] jars = jarStrings.split(",");
		for (String jar : jars) {
			args[1] += lestatHome + "/lib/" + jar + ",";
		}				
		logger.info("libjars: " + args[1]);
		
				
		Configuration conf = HadoopUtil.getConf(config.getHadoopConfDir());
		
		logger.info("Disturbance MapReduce Task Starts!");
		long start = System.currentTimeMillis();
		
		try {
			ToolRunner.run(conf, driver, args);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		long end = System.currentTimeMillis();
		logger.info("Disturbance MapReduce Task Ends!");
		logger.info("Disturbance MapReduce Task Takes: " + (end - start) / 1000 + " s");
		
	}
	
}
