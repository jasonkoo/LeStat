package com.lenovo.push.marketing.lestat.kafka.transporter;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.kafka.param.Param;
import com.lenovo.push.marketing.lestat.kafka.reader.KafkaDataReader;

public class HitDataTransporter  implements Runnable{  

	private static Logger logger = Logger.getLogger(HitDataTransporter.class);

	// @Resource
	private Config config;

	private Cache cache;
	
	private KafkaDataReader reader;
	
    private ExecutorService threadPool;

	public Cache getCache() {
		return cache;
	}

	public void setCache(Cache cache) {
		this.cache = cache;
	}

	private void transport(KafkaDataReader reader) {
		while (true) {
			try {
				List<Object> list = reader.read();
				if (list != null) {
					for (Object o : list) {
						if (o != null) {
							String str = (String) o;
							if (StringUtils.isNotEmpty(str)) {
								cache.add(str);
							}
						}
					}
				}
			} catch (Exception e) {
				logger.error("transport: " + e.getMessage());
				logger.error("transport: " + e);
				e.printStackTrace();
			}
		}

	}
	
	public void init() {
		try {
			reader = new KafkaDataReader();
			reader.init(config, Param.getReaderIndex(), Param.getReaderCount());
			
			threadPool = Executors.newFixedThreadPool(1);
			threadPool.execute(this);
		} catch (Exception e) {
			logger.error("init: " + e.getMessage());
		}
	}

	@Override
	public void run() {
		try {
			transport(reader);
		} catch (Exception e) {
			logger.error("run: " + e.getMessage());
		}
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}
}
