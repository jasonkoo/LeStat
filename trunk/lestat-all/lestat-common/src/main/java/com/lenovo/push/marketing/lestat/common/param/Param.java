package com.lenovo.push.marketing.lestat.common.param;

import java.io.File;

public class Param {
	public final static String SPRING_SURFIX = "-applicationContext.xml";
	
	public final static String LOG4J_DEFAULT = "default";
	public final static String LOG4J_SURFIX = "-log4j.xml";

	public final static String HYPHEN = "-";
	public final static String DATA_FILE_EXT = ".data";
	public final static String PATH_SEPARATOR = "/";
	public final static String LINE_SEPARATOR = "\n";
	
	public final static String KAFKA_HDFS_FOLDER_NAME = "kafka";
	public final static String KAFKA_EVENT_BUS_PROPERTY_FILE_NAME = "eventbus.properties";

	private static String LESTAT_HOME;
	private final static String CONF_DIR_NAME = "conf";

	public static String getLestatConfDir() {
		return getLestatHomeDir() + File.separator + Param.CONF_DIR_NAME;
	}
	
	public static String getKafkaEventBusPropertyFilePath() {
		return getLestatConfDir() + File.separator + Param.KAFKA_EVENT_BUS_PROPERTY_FILE_NAME;
	}
	
	public static String getLestatHomeDir() {
		if (LESTAT_HOME == null) {
			LESTAT_HOME = System.getProperty("lestat.home");
		}
		if (LESTAT_HOME == null) {
			throw new RuntimeException("lestat.home is null");
		}
		return LESTAT_HOME;
	}
}
