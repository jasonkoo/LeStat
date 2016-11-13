package com.lenovo.push.marketing.lestat.db.impala.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.impala.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.impala.dao.AppFeedbackNewDao;
import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;

@Service("impalaAppFeedbackService")
public class AppFeedbackService {

	@Autowired
	private AppFeedbackDao impalaAppFeedbackDao;
	
	@Autowired
	private AppFeedbackNewDao impalaAppFeedbackNewDao;
	
	
	
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate) {
		return impalaAppFeedbackDao.getAppFeedbackStat(thedate);		
	}

	public List<AdTaskStatResult> getAppFeedbackStatNew(String thedate) {
		List<AdTaskStatResult> statResults = null;
		try {
			statResults =  impalaAppFeedbackNewDao.getAppFeedbackStat(thedate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statResults;
	}
	
	public void test() {
		String thedate = "20141029";
		List<AdTaskStatResult> statResults = this.getAppFeedbackStatNew(thedate);
		for (AdTaskStatResult statResult : statResults) {
			System.out.println(statResult.getSid() + "\t" + statResult.getAdid() + "\t" + statResult.getEventtype() + "\t" + statResult.getCnt());
		}
	}
}
