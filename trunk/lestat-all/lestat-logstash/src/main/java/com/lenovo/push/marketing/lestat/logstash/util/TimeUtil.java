package com.lenovo.push.marketing.lestat.logstash.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TimeUtil {
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd-H";
	public final static String DATE_PATTERN = "yyyyMMdd";
	public final static String DATE_TIME_PATTER_TEST = "yyyy-MM-dd HH:mm:ss";
	
	public static Date getNHourBefore(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, 0 - n);
		return cal.getTime();
	}
	public static Date getOneHourBefore(Date date) {
		return getNHourBefore(date, 1);		
	}
	
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		} else {
			return null;
		}
	}
	
	public static Date StringToDate(String dateStr, String pattern) {
		Date date = null;
		if (dateStr != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				date = sdf.parse(dateStr);
			} catch (ParseException e) {				
				e.printStackTrace();
			}
			return date;
		} else {
			return null;
		}
	}
	public static void main(String[] args) {
		//Date date = new Date();
		//date = TimeUtil.getOneHourBefore(date);
		//System.out.println(TimeUtil.dateToString(date, TimeUtil.DATE_TIME_PATTERN));
		Date date = TimeUtil.StringToDate("2014-06-20 13:30:00", TimeUtil.DATE_TIME_PATTER_TEST);
		/*date  = TimeUtil.getOneHourBefore(date);*/
		String dateString = TimeUtil.dateToString(date, TimeUtil.DATE_TIME_PATTERN);
		System.out.println(dateString);
	}
}
