package com.lenovo.push.marketing.lestat.db.mysql0.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.common.util.DateUtil;
import com.lenovo.push.marketing.lestat.db.mysql0.dao.AppMsgDao;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;

@Service("mysql0AppMsgService")
public class AppMsgService {
	
	@Autowired
	private AppMsgDao mysql0AppMsgDao;
	
	public List<AppMsgEntity> getActiveAppMsgList(String date, List<String> adIdList)  {
		String lowerEndDate = date;
		//String upperEndDate = DateUtil.getNAfterDate(date, 1);
		return mysql0AppMsgDao.getActiveAppMsgList(lowerEndDate, adIdList);
	}
	
	public List<AppMsgEntity> getExpiredAppMsgList(String date, List<String> adIdList) throws ParseException {
		String lowerEndDate = date;
		String upperEndDate = DateUtil.getNAfterDate(date, 1);
		return mysql0AppMsgDao.getExpiredAppMsgList(lowerEndDate, upperEndDate, adIdList);
	}
}
