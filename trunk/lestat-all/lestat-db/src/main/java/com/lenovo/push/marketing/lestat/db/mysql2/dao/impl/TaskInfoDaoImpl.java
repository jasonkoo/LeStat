package com.lenovo.push.marketing.lestat.db.mysql2.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.mysql2.dao.TaskInfoDao;
import com.lenovo.push.marketing.lestat.db.mysql2.entity.TaskInfoEntity;
import com.lenovo.push.marketing.lestat.db.mysql2.mapper.TaskInfoMapper;

@Repository("mysql2TaskInfoDao")
public class TaskInfoDaoImpl extends BaseDaoImpl<TaskInfoEntity, TaskInfoMapper> implements TaskInfoDao {

	public TaskInfoDaoImpl() {
		setMapperClass(TaskInfoMapper.class);
	}
	@Override
	public List<TaskInfoEntity> getTaskInfoList() {
		return this.getMapper().getTaskInfoList();
	}

	@Override
	public TaskInfoEntity getTaskInfoByFeedbackId(String feedbackId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("feedbackId", feedbackId);	
		return this.getMapper().getTaskInfoByFeedbackId(params);
	}

}
