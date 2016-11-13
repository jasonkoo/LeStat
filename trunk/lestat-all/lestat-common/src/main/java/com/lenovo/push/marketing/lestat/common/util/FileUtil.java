package com.lenovo.push.marketing.lestat.common.util;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.lenovo.push.marketing.lestat.common.param.Param;

public class FileUtil {

	private final static String PATTERN_1 = "yyyyMMdd-HHmmss";

	public static String getHdfsFilename(String lestatHdfsHome, String theDate,
			int readerIndex, Date theLastHitTime) throws UnknownHostException {
		// long now = System.currentTimeMillis();
		Date now = new Date(System.currentTimeMillis());
		String nowStr = DateUtil.dateToString(now, PATTERN_1);
		// String theDate = DateUtil.dateToString(new Date(now),
		// DateUtil.DATE_PATTERN);
		String hostname = NetworkUtil.getHostname();
		String hitTime = DateUtil.dateToString(theLastHitTime, PATTERN_1);
		return lestatHdfsHome + Param.PATH_SEPARATOR
				+ Param.KAFKA_HDFS_FOLDER_NAME + Param.PATH_SEPARATOR + theDate
				+ Param.PATH_SEPARATOR + nowStr + Param.HYPHEN + hostname
				+ Param.HYPHEN + readerIndex + Param.HYPHEN + hitTime
				+ Param.DATA_FILE_EXT;

	}
	
	public static byte[] readFile(String filename) throws IOException{
		return FileUtils.readFileToByteArray(new File(filename));
	}
}
