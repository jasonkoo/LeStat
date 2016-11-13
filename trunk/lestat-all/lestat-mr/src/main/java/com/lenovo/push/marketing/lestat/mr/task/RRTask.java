package com.lenovo.push.marketing.lestat.mr.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.DisturbanceResult;
import com.lenovo.push.marketing.lestat.hdfs.util.HadoopUtil;
import com.lenovo.push.marketing.lestat.hdfs.util.HdfsUtil;
import com.lenovo.push.marketing.lestat.mr.disturbance.Driver;
import com.lenovo.push.marketing.lestat.mr.param.Param;

public class RRTask{
	private static Logger logger = Logger.getLogger(RRTask.class);
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
	
	public List<DisturbanceResult> readResults() {
		logger.info("Read Results Task Starts!");
		long start = System.currentTimeMillis();
		Configuration conf = HadoopUtil.getConf(config.getHadoopConfDir());
		List<DisturbanceResult> resultList = null;
		try {
			List<String> lines = HdfsUtil.readLines(conf, param.getResultPath());
			//long total = HdfsUtil.readTotal(conf, param.getTotalPath(), param.getNumRedStage2());
			//logger.info("Total Number of users:" + total);
			
			Date now = new Date();
			resultList = new ArrayList<DisturbanceResult>();
 			for (String line : lines) {
				logger.info(line);
				String[] cols = line.split("\t");
				DisturbanceResult dr = new DisturbanceResult();
				dr.setThedate(param.getThedate());
				dr.setHitCount(Integer.valueOf(cols[0]));
				dr.setNumUsers(Integer.valueOf(cols[1]));
				//dr.setPercent( (double)Integer.valueOf(cols[1]) / total );
				dr.setTs(now);
				resultList.add(dr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		logger.info("Read Results Task Ends!");
		logger.info("Read Results Task Takes: " + (end - start) / 1000 + " s");
		return resultList;		
	}
}
