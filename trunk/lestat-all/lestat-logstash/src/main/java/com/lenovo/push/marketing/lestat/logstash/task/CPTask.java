package com.lenovo.push.marketing.lestat.logstash.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.hdfs.util.HdfsUtil;
import com.lenovo.push.marketing.lestat.logstash.param.Param;
import com.lenovo.push.marketing.lestat.logstash.util.FileUtil;
import com.lenovo.push.marketing.lestat.logstash.util.OSUtil;
import com.lenovo.push.marketing.lestat.logstash.util.TimeUtil;


public class CPTask {
	private static Logger logger = Logger.getLogger(CPTask.class);
	
	private Param param;
	private Config config;

	public Param getParam() {
		return param;
	}
	
	public void setParam(Param param) {
		this.param = param;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public void copyFiles(String dateStr) {
		
		String sourceDir = param.getSourceDir();
		String filePrefix = param.getFilePrefix();
		
		String hdfsHome = config.getLestatHdfsHome();
		String dstDir = param.getDstDir();		
		
		Date date;
		if (dateStr == null) {
			date = new Date();
		} else {
			// dateStr format:  "2014-06-19 18:30:00"
			date = TimeUtil.StringToDate(dateStr, TimeUtil.DATE_TIME_PATTER_TEST);
		}		
		date = TimeUtil.getOneHourBefore(date);
		String dateString = TimeUtil.dateToString(date, TimeUtil.DATE_PATTERN);
		String dateHourString = TimeUtil.dateToString(date, TimeUtil.DATE_TIME_PATTERN);
		
		String outDir = hdfsHome + "/" + dstDir + "/" + dateString;	
		
		logger.info("out dir: " + outDir);
		int numFilesCopied = 0;
		logger.info("Copy Task Starts!");
		long start = System.currentTimeMillis();
		for (int i = 0; i < 12; i++) {
			String startswith = filePrefix + "-"  + OSUtil.getHostName() + "-" + dateHourString + "-" + i + "-";
			String[] matchings = FileUtil.matchingFiles(sourceDir, startswith);
			for (String fileName : matchings) {
				String inFile = sourceDir + File.separator + fileName;
				String outFile = outDir + "/" + fileName;

				logger.info("input file path: " + inFile);
				logger.info("output file path: " + outFile);
				if (FileUtil.exists(inFile)) {
					logger.info("copying " + fileName);
					FileInputStream fis = FileUtil.getInputStream(inFile);
					String blockSize = new Long(256*1024*1024).toString();
					// String blockSize = null;
					try {
						HdfsUtil.write(config.getHadoopConfDir(), fis, outFile, blockSize);
					} catch (IOException e) {
						e.printStackTrace();
					}
					FileUtil.closeInputStream(fis);
					logger.info("copy ends");
					numFilesCopied++;
				} else {
					logger.info(fileName + " not exist!");
				}
			}
		}
		long end = System.currentTimeMillis();
		logger.info("Copy Task Ends!");
		logger.info("Number of files copied for " + dateHourString + ": " + numFilesCopied);
		logger.info("Copy Task Takes: " +  (end - start) / 1000 + " s");
	}
}
