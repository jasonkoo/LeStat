package com.lenovo.push.marketing.lestat.db.mysql2.mapper;

import java.util.List;
import java.util.Map;

import com.lenovo.push.marketing.lestat.db.mysql2.entity.TaskInfoEntity;

public interface TaskInfoMapper extends BaseMapper<TaskInfoEntity> {
	public List<TaskInfoEntity> getTaskInfoList();
	public TaskInfoEntity getTaskInfoByFeedbackId(Map<String, Object> params);
}
