package com.lenovo.push.marketing.lestat.kafka.appfeedback;

import java.util.Collection;


public interface ActiveAppMsgService {	
	
	public void setActiveAppMsgs(Collection<String> activeAppMsgs);	
	public boolean isActive(String adid);	
	
}
