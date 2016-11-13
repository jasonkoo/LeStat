package com.lenovo.push.marketing.lestat.db.mysql2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lenovo.push.marketing.lestat.db.mysql2.dao.TaskInfoDao;
import com.lenovo.push.marketing.lestat.db.mysql2.entity.TaskInfoEntity;

@Service("mysql2TaskInfoService")
public class TaskInfoService {
	
	@Autowired
	private TaskInfoDao mysql2TaskInfoDao;
	
	public List<TaskInfoEntity> getTaskInfoList() {
		return mysql2TaskInfoDao.getTaskInfoList();
	}
	
	public TaskInfoEntity getTaskInfoByFeedbackId(String feedbackId) {
		return mysql2TaskInfoDao.getTaskInfoByFeedbackId(feedbackId);
	}

}
