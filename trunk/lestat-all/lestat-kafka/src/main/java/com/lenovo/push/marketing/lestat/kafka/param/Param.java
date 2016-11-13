package com.lenovo.push.marketing.lestat.kafka.param;

import java.util.Date;

public class Param {
	
	private static int READER_INDEX = -1;
	private static int READER_COUNT = -1;
	private static Date START_TIME = new Date();
	
	public static int getReaderIndex(){
		if (READER_INDEX==-1){
			throw new RuntimeException("READER_INDEX is -1");
		}
		return READER_INDEX;
	}
	
	public static int getReaderCount(){
		if (READER_COUNT==-1){
			throw new RuntimeException("READER_COUNT is -1");
		}
		return READER_COUNT;
	}
	
	public static void setReaderIndex(int ri){
		READER_INDEX = ri;
	}
	
	public static void setReaderCount(int rc){
		READER_COUNT = rc;
	}

	public static Date getStartTime() {
		return START_TIME;
	}

	public static void setStartTime(Date startTime) {
		START_TIME = startTime;
	}
	
	

}
