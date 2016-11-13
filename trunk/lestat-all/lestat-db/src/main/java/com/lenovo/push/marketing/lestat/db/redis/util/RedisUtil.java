package com.lenovo.push.marketing.lestat.db.redis.util;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.lenovo.lps.push.marketing.common.vo.appinfo.AppFeedbackDataEntry;
import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.param.Param;
import com.lenovo.push.marketing.lestat.db.redis.entity.AppFeedback;
import com.lenovo.push.marketing.lestat.db.redis.entity.SUSFeedback;

public class RedisUtil {
	
	static Logger logger = Logger.getLogger(RedisUtil.class);
	
	public static AppFeedback getAppFeedback(BigCache bigCache, String sid,String adId,String date) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("empty sid: " + sid);
		}
		if (StringUtils.isEmpty(adId)) {
			throw new IllegalArgumentException("empty adId: " + adId);
		}
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		AppFeedback feedback = new AppFeedback();
		feedback.setSid(sid);
		feedback.setAdId(adId);
		feedback.setDate(date);
		for (String action : Param.ACTION_MAP.keySet()) {
			String key = BigCache.KEY_APP_FEEDBACK.getKey(new String[]{sid,adId,action,date});
			long value = 0;
			String valueStr = bigCache.get(key);
			if (valueStr!=null) {
				value = Long.parseLong(valueStr);
			}
			if (Param.PUSHED.equals(action)) {
				feedback.setPushed(value);
			}
			if (Param.ARRIVED.equals(action)) {
				feedback.setArrived(value);
			}
			if (Param.DISPLAYED.equals(action)) {
				feedback.setDisplayed(value);
			}
			if (Param.CLICKED.equals(action)) {
				feedback.setClicked(value);
			}
			
		}
		return feedback;
	}
	
	public static void incrAppFeedback(BigCache bigCache, AppFeedbackDataEntry feedback) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String sid = feedback.getSid();
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("empty sid: " + sid);
		}
		String adId = feedback.getAdId();
		if (StringUtils.isEmpty(adId)) {
			throw new IllegalArgumentException("empty adId: " + adId);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_APP_FEEDBACK.getKey(new String[]{sid,adId,action,date});
			bigCache.incr(key);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
	public static Long ttlAppFeedback(BigCache bigCache, AppFeedbackDataEntry feedback) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String sid = feedback.getSid();
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("empty sid: " + sid);
		}
		String adId = feedback.getAdId();
		if (StringUtils.isEmpty(adId)) {
			throw new IllegalArgumentException("empty adId: " + adId);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_APP_FEEDBACK.getKey(new String[]{sid,adId,action,date});
			return bigCache.ttl(key);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
	public static void expireAppFeedback(BigCache bigCache, AppFeedbackDataEntry feedback,int seconds) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String sid = feedback.getSid();
		if (StringUtils.isEmpty(sid)) {
			throw new IllegalArgumentException("empty sid: " + sid);
		}
		String adId = feedback.getAdId();
		if (StringUtils.isEmpty(adId)) {
			throw new IllegalArgumentException("empty adId: " + adId);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_APP_FEEDBACK.getKey(new String[]{sid,adId,action,date});
			bigCache.expire(key, seconds);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
	
	public static SUSFeedback getSUSFeedback(BigCache bigCache, String appKey,String version,String channel,String date) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (StringUtils.isEmpty(appKey)) {
			throw new IllegalArgumentException("empty appKey: " + appKey);
		}
		if (StringUtils.isEmpty(version)) {
			throw new IllegalArgumentException("empty version: " + version);
		}
		if (StringUtils.isEmpty(channel)) {
			throw new IllegalArgumentException("empty channel: " + channel);
		}
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		SUSFeedback feedback = new SUSFeedback();
		feedback.setAppKey(appKey);
		feedback.setVersion(version);
		feedback.setChannel(channel);
		feedback.setDate(date);
		for (String action : Param.SUS_ACTION_MAP.keySet()) {
			String key = BigCache.KEY_SUS_FEEDBACK.getKey(new String[]{appKey,version,channel,action,date});
			long value = 0;
			String valueStr = bigCache.get(key);
			if (valueStr!=null) {
				value = Long.parseLong(valueStr);
			}
			
			if (Param.NOTIFY_SHOW.equals(action)) {
				feedback.setNotifyShow(value);;
			}
			if (Param.NOTIFY_CLICK.equals(action)) {
				feedback.setNotifyClick(value);
			}
			if (Param.ALERT_SHOW.equals(action)) {
				feedback.setAlertShow(value);
			}
			if (Param.ALERT_CONFORM.equals(action)) {
				feedback.setAlertConform(value);
			}
			
			
			if (Param.ALERT_CANCLE.equals(action)) {
				feedback.setAlertCancle(value);
			}
			if (Param.DOWNLOAD_START.equals(action)) {
				feedback.setDownloadStart(value);
			}
			if (Param.DOWNLOAD_SUCCESS.equals(action)) {
				feedback.setDownloadSuccess(value);
			}
			if (Param.INSTALL_ALERT_SHOW.equals(action)) {
				feedback.setInstallAlertShow(value);
			}
			
			
			if (Param.INSTALL_ALERT_CONFORM.equals(action)) {
				feedback.setInstallAlertConform(value);
			}
			if (Param.INSTALL_ALERT_CANCLE.equals(action)) {
				feedback.setInstallAlertCancle(value);
			}
			if (Param.INSTALL_START.equals(action)) {
				feedback.setInstallStart(value);
			}
			if (Param.CHECK_UPGRADE.equals(action)) {
				feedback.setCheckUpgrade(value);
			}
			
			if (Param.ERROR.equals(action)) {
				feedback.setError(value);
			}
			logger.debug("key: " + key);
			
		}
		logger.debug("feedback: " + new Gson().toJson(feedback));
		return feedback;
	}
	
	public static void incrSUSFeedback(BigCache bigCache, com.lenovo.push.marketing.lestat.common.vo.SUSFeedback feedback) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String appKey = feedback.getAppKey();
		if (StringUtils.isEmpty(appKey)) {
			throw new IllegalArgumentException("empty appKey: " + appKey);
		}
		String version = feedback.getCurrentVercode();
		if (StringUtils.isEmpty(version)) {
			throw new IllegalArgumentException("empty version: " + version);
		}
		String channel = feedback.getChannelKey();
		if (StringUtils.isEmpty(channel)) {
			throw new IllegalArgumentException("empty channel: " + channel);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getSUSKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_SUS_FEEDBACK.getKey(new String[]{appKey,version,channel,action,date});
			bigCache.incr(key);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
	public static Long ttlSUSFeedback(BigCache bigCache, com.lenovo.push.marketing.lestat.common.vo.SUSFeedback feedback) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String appKey = feedback.getAppKey();
		if (StringUtils.isEmpty(appKey)) {
			throw new IllegalArgumentException("empty appKey: " + appKey);
		}
		String version = feedback.getCurrentVercode();
		if (StringUtils.isEmpty(version)) {
			throw new IllegalArgumentException("empty version: " + version);
		}
		String channel = feedback.getChannelKey();
		if (StringUtils.isEmpty(channel)) {
			throw new IllegalArgumentException("empty channel: " + channel);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getSUSKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_SUS_FEEDBACK.getKey(new String[]{appKey,version,channel,action,date});
			return bigCache.ttl(key);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
	public static void expireSUSFeedback(BigCache bigCache, com.lenovo.push.marketing.lestat.common.vo.SUSFeedback feedback,int seconds) throws IOException {
		if (bigCache == null) {
			throw new IllegalArgumentException("null bigCache");
		}
		if (feedback == null) {
			throw new IllegalArgumentException("null feedback");
		}
		String appKey = feedback.getAppKey();
		if (StringUtils.isEmpty(appKey)) {
			throw new IllegalArgumentException("empty appKey: " + appKey);
		}
		String version = feedback.getCurrentVercode();
		if (StringUtils.isEmpty(version)) {
			throw new IllegalArgumentException("empty version: " + version);
		}
		String channel = feedback.getChannelKey();
		if (StringUtils.isEmpty(channel)) {
			throw new IllegalArgumentException("empty channel: " + channel);
		}
		String eventName = feedback.getEventName();
		if (StringUtils.isEmpty(eventName)) {
			throw new IllegalArgumentException("empty eventName: " + eventName);
		}
		String date = DateUtil.dateToString(feedback.getLogTime(), DateUtil.DATE_PATTERN);
		if (StringUtils.isEmpty(date) || !DateUtil.getToday().equals(date)) {
			throw new IllegalArgumentException("illegal date: " + date);
		}
		String action = Param.getSUSKey(eventName);
		if (StringUtils.isNotEmpty(action)) {
			String key = BigCache.KEY_SUS_FEEDBACK.getKey(new String[]{appKey,version,channel,action,date});
			bigCache.expire(key, seconds);
		} else {
			throw new RuntimeException("illegal eventName: " + eventName);
		}
	}
	
}
