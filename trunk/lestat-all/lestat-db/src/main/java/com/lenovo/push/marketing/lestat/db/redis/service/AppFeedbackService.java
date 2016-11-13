package com.lenovo.push.marketing.lestat.db.redis.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.lps.push.marketing.common.vo.appinfo.AppFeedbackDataEntry;
import com.lenovo.push.marketing.lestat.db.redis.entity.AppFeedback;
import com.lenovo.push.marketing.lestat.db.redis.util.BigCache;
import com.lenovo.push.marketing.lestat.db.redis.util.RedisUtil;


@Service("redisAppFeedbackService")
public class AppFeedbackService {
	
	@Autowired
	private BigCache bigCache;
	
	
	public void saveAppFeedback(AppFeedbackDataEntry appFeedbackDataEntry) throws IOException {
		if (appFeedbackDataEntry != null) {
			//String sid = appFeedbackDataEntry.getSid();
			//String adid = appFeedbackDataEntry.getAdId();
			RedisUtil.incrAppFeedback(bigCache, appFeedbackDataEntry);
			
			Long ttl = RedisUtil.ttlAppFeedback(bigCache, appFeedbackDataEntry);
			if (ttl!=null && ttl.longValue()==-1) {
				//http://www.redis.cn/commands/ttl.html
				//当 key 存在但没有设置剩余生存时间时，返回 -1 。
				RedisUtil.expireAppFeedback(bigCache, appFeedbackDataEntry, 60 * 60 * 24);
			}
			
		}
	}
	
	public AppFeedback getAppFeedback(String sid, String adId, String date) throws IOException {
		return RedisUtil.getAppFeedback(bigCache, sid, adId, date);
	}
}
