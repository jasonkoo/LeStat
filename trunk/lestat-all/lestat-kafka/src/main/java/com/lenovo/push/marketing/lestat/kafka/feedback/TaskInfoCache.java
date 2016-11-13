package com.lenovo.push.marketing.lestat.kafka.feedback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.lenovo.push.marketing.lestat.db.mysql2.entity.TaskInfoEntity;
import com.lenovo.push.marketing.lestat.db.mysql2.service.TaskInfoService;

public class TaskInfoCache {
	
	@Resource(name = "mysql2TaskInfoService")
	private TaskInfoService taskInfoService;
	
	// feedbackId-pkgName pairs
	private Map<String, String> kvPairs = new HashMap<String, String>();
	
	public void init() {
		List<TaskInfoEntity> taskInfoEntities = taskInfoService.getTaskInfoList();
		if (taskInfoEntities != null && taskInfoEntities.size() > 0) {
			for (TaskInfoEntity taskInfoEntity : taskInfoEntities) {
				kvPairs.put(taskInfoEntity.getFeedbackId(), taskInfoEntity.getPkgName() + "");
			}
		}
	}
	
	public boolean exist(String feedbackId) {
		return kvPairs.containsKey(feedbackId);
	}
	
	public void add(String feedbackId) {
		TaskInfoEntity taskInfoEntity = taskInfoService.getTaskInfoByFeedbackId(feedbackId);
		if (taskInfoEntity != null) {
			kvPairs.put(taskInfoEntity.getFeedbackId(), taskInfoEntity.getPkgName());
		}
	}
	
	public String getValue(String feedbackId) {
		return kvPairs.get(feedbackId);
	}
	
	public void print() {
		for (String key : kvPairs.keySet()) {
			System.out.println(key + "-" + kvPairs.get(key));
		}
	}
}
