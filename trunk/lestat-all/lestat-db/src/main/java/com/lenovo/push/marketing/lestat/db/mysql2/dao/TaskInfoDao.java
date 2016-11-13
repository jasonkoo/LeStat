package com.lenovo.push.marketing.lestat.db.mysql2.dao;

import java.util.List;

import com.lenovo.push.marketing.lestat.db.mysql2.entity.TaskInfoEntity;
import com.lenovo.push.marketing.lestat.db.mysql2.mapper.TaskInfoMapper;

public interface TaskInfoDao extends BaseDao<TaskInfoEntity, TaskInfoMapper> {
	public List<TaskInfoEntity> getTaskInfoList();
	public TaskInfoEntity getTaskInfoByFeedbackId(String feedbackId);
}
