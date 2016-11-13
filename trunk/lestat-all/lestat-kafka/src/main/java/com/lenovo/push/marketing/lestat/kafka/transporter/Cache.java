package com.lenovo.push.marketing.lestat.kafka.transporter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lenovo.lps.push.marketing.drill.common.vo.HitDataEntry;
import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.common.util.FileUtil;
import com.lenovo.push.marketing.lestat.hdfs.util.HdfsUtil;
import com.lenovo.push.marketing.lestat.kafka.param.Param;

public class Cache {

	private static Logger logger = Logger.getLogger(Cache.class);

	private Config config;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
    
	//private String hitTimeStr;
	
	//private List<String> list = new ArrayList<String>();
	private Map<String, List<String>> map = new HashMap<String, List<String>>();
	private long lastSavingTime = System.currentTimeMillis();
	private int listSize;
	//private String currentLogDate = null;
	//private boolean changedDateFlag = false;
	
	private long disregardedLogCount = 0;

	public int getListSize() {
		return listSize;
	}

	public void setListSize(int listSize) {
		this.listSize = listSize;
	}

	public void add(String str) throws ParseException, IOException {
		//list.add(str);
		HitDataEntry de = HitDataEntry.parseLine(str);
		if (de==null) {
			logger.warn("invalid entry: " + str);
			return;
		}
		Date hitTime = de.getHitTime();
		//this.hitTimeStr = DateUtil.dateToString(hitTime, DateUtil.DATE_TIME_PATTERN);
		if (DateUtil.isEarlierThan(hitTime, Param.getStartTime())) {
			if (disregardedLogCount<10) {
				logger.warn("too early message: " + str);	
			}
			disregardedLogCount++;
			if (disregardedLogCount > 10000000) {
				disregardedLogCount = 0;
			}
			return;
		}
		String hitTimeStr = DateUtil.dateToString(hitTime, "yyyyMMdd");
		if (StringUtils.isNotEmpty(hitTimeStr)) {
			List<String> list = map.get(hitTimeStr);
			if (list==null) {
				list = new ArrayList<String>();
				map.put(hitTimeStr, list);
			}
			list.add(str);
			
			long now = System.currentTimeMillis();
			if (list.size() > listSize || now - lastSavingTime > 5 * 60 * 1000) {
				save();
				lastSavingTime = now;
				map.clear();
			}
		} else {
			logger.warn("hitTimeStr is empty: " + hitTimeStr);
		}
			
		
		
	}

	private void save() throws IOException {
		ByteArrayInputStream is = null;
		try {
			// hdfs存数据
			String hadoopConfDir = config.getHadoopConfDir();
			for (String theDate : map.keySet()) {
				StringBuilder sb = new StringBuilder();
				List<String> list = map.get(theDate);
				for (String str : list) {
					sb.append(str
							+ com.lenovo.push.marketing.lestat.common.param.Param.LINE_SEPARATOR);
				}
				String content = sb.toString();
				is = new ByteArrayInputStream(content.getBytes("UTF-8"));

				String theLastRecord = list.get(list.size() - 1);
				Date theLastHitTime = HitDataEntry.parseLine(theLastRecord)
						.getHitTime();

				String path = FileUtil.getHdfsFilename(
						config.getLestatHdfsHome(), theDate,
						Param.getReaderIndex(), theLastHitTime);

				// logger.info("hit time: " + hitTimeStr);
				logger.info("saving data: " + path);
				// String blockSize = new Long(256*1024*1024).toString();
				String blockSize = null;
				HdfsUtil.write(hadoopConfDir, is, path, blockSize);
				logger.info("saved data: " + path);
			}
		} catch (IOException e) {
			throw e;
		} finally {
			if (is != null) {
				IOUtils.closeQuietly(is);
				is = null;
			}
		}
	}
}
