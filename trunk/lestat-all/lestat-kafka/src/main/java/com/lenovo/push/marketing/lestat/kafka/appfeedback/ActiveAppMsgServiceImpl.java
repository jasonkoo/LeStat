package com.lenovo.push.marketing.lestat.kafka.appfeedback;

import java.util.Collection;

import org.springframework.stereotype.Service;

// 线程不安全实现
@Service("activeAppMsgServiceImpl")
public class ActiveAppMsgServiceImpl implements ActiveAppMsgService{	
	
	private Collection<String> activeAppMsgs;
	
	@Override
	public void setActiveAppMsgs(Collection<String> activeAppMsgs) {
		this.activeAppMsgs = activeAppMsgs;				
	}
	
	@Override
	public boolean isActive(String adid) {
		return activeAppMsgs!= null && activeAppMsgs.contains(adid);		
	}
}
