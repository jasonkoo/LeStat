package com.lenovo.push.marketing.lestat.db.redis.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.common.vo.SUSFeedback;
import com.lenovo.push.marketing.lestat.db.redis.util.BigCache;
import com.lenovo.push.marketing.lestat.db.redis.util.RedisUtil;


@Service("redisSUSFeedbackService")
public class SUSFeedbackService {
	
	@Autowired
	private BigCache bigCache;
	
	
	public void saveAppFeedback(SUSFeedback feedback) throws IOException {
		if (feedback != null) {
			//String sid = appFeedbackDataEntry.getSid();
			//String adid = appFeedbackDataEntry.getAdId();
			RedisUtil.incrSUSFeedback(bigCache, feedback);
			
			Long ttl = RedisUtil.ttlSUSFeedback(bigCache, feedback);
			if (ttl!=null && ttl.longValue()==-1) {
				//http://www.redis.cn/commands/ttl.html
				//当 key 存在但没有设置剩余生存时间时，返回 -1 。
				RedisUtil.expireSUSFeedback(bigCache, feedback, 60 * 60 * 24);
			}
			
		}
	}
	
}
