package com.lenovo.push.marketing.lestat.db.param;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.lenovo.push.marketing.lestat.common.vo.SUSFeedback;

public class Param {

	public final static String PUSHED = "pushed";
	public final static String ARRIVED = "arrived";
	public final static String DISPLAYED = "displayed";
	public final static String CLICKED = "clicked";
	
	public final static String DIM_DEVICE_MODEL = "dm";
	public final static String DIM_APP_VERSION = "av";
	
	// 用户hive的原始报文中:陈朝，军爷已经确认
	// 服务器提供：消息已经投放到消息系统中（目前只有流式投放有）
	public final static String ORIGINAL_PUSHED = "push";
	// 服务器提供：消息已经投放到消息系统中（目前只有流式投放有）
	public final static String ORIGINAL_ARRIVED = "arrive";
	// 终端上报
	public final static String ORIGINAL_DISPLAYED = "display";
	// 终端上报
	public final static String ORIGINAL_CLICKED = "click";
	
	//click|display|install|engineUpgrade|clike2end|display2end
	

	@SuppressWarnings("serial")
	public final static Map<String, String> ACTION_MAP = new HashMap<String, String>() {
		{
			put(PUSHED, ORIGINAL_PUSHED);
			put(ARRIVED, ORIGINAL_ARRIVED);
			put(DISPLAYED, ORIGINAL_DISPLAYED);
			put(CLICKED, ORIGINAL_CLICKED);

		}
	};
	
	public static String getKey(String value) {
		String key = null;
		if (StringUtils.isNotEmpty(value)) {
			for (String key1 : ACTION_MAP.keySet()) {
				if (value.equals(ACTION_MAP.get(key1))) {
					key = key1;
					break;
				}
			}
		}
		return key;
	}
	
	/*
	notify_show     通知栏更新通知已显示
	notify_click       通知栏更新通知已点击
	alert_show       显示提示升级对话框
	alert_conform 用户点击确认升级按钮
	alert_cancle     用户点击取消升级按钮
	download_start        开始下载
	download_success   下载完成
	install_alert_show   显示安装提示对话框
	install_alert_conform      用户确认安装
	install_alert_cancle 用户取消安装
	install_start      开始安装
	check_ upgrade        服务端收到检测升级请求
	error  错误
	*/
	

	public final static String ORIGINAL_NOTIFY_SHOW = SUSFeedback.EVENTNAME_NOTIFY_SHOW;
	public final static String ORIGINAL_NOTIFY_CLICK = SUSFeedback.EVENTNAME_NOTIFY_CLICK;
	public final static String ORIGINAL_ALERT_SHOW = SUSFeedback.EVENTNAME_ALERT_SHOW;
	public final static String ORIGINAL_ALERT_CONFORM = SUSFeedback.EVENTNAME_ALERT_CONFORM;
	public final static String ORIGINAL_ALERT_CANCLE = SUSFeedback.EVENTNAME_ALERT_CANCLE;
	public final static String ORIGINAL_DOWNLOAD_START = SUSFeedback.EVENTNAME_DOWNLOAD_START;
	public final static String ORIGINAL_DOWNLOAD_SUCCESS = SUSFeedback.EVENTNAME_DOWNLOAD_SUCCESS;
	public final static String ORIGINAL_INSTALL_ALERT_SHOW = SUSFeedback.EVENTNAME_INSTALL_ALERT_SHOW;
	public final static String ORIGINAL_INSTALL_ALERT_CONFORM = SUSFeedback.EVENTNAME_INSTALL_ALERT_CONFORM;
	public final static String ORIGINAL_INSTALL_ALERT_CANCLE = SUSFeedback.EVENTNAME_INSTALL_ALERT_CANCLE;
	public final static String ORIGINAL_INSTALL_START = SUSFeedback.EVENTNAME_INSTALL_START;
	public final static String ORIGINAL_CHECK_UPGRADE = SUSFeedback.EVENTNAME_CHECK_UPGRADE;
	public final static String ORIGINAL_ERROR = SUSFeedback.EVENTNAME_ERROR;
	
	
	public final static String NOTIFY_SHOW = "notifyShow";
	public final static String NOTIFY_CLICK = "notifyClick";
	public final static String ALERT_SHOW = "alertShow";
	public final static String ALERT_CONFORM = "alertConform";
	public final static String ALERT_CANCLE = "alertCancle";
	public final static String DOWNLOAD_START = "downloadStart";
	public final static String DOWNLOAD_SUCCESS = "downloadSuccess";
	public final static String INSTALL_ALERT_SHOW = "installAlertShow";
	public final static String INSTALL_ALERT_CONFORM = "installAlertConform";
	public final static String INSTALL_ALERT_CANCLE = "installAlertCancle";
	public final static String INSTALL_START = "installStart";
	public final static String CHECK_UPGRADE = "checkUpgrade";
	public final static String ERROR = "error";
	
	@SuppressWarnings("serial")
	public final static Map<String, String> SUS_ACTION_MAP = new HashMap<String, String>() {
		{
			put(NOTIFY_SHOW, ORIGINAL_NOTIFY_SHOW);
			put(NOTIFY_CLICK, ORIGINAL_NOTIFY_CLICK);
			put(ALERT_SHOW, ORIGINAL_ALERT_SHOW);
			put(ALERT_CONFORM, ORIGINAL_ALERT_CONFORM);
			
			put(ALERT_CANCLE, ORIGINAL_ALERT_CANCLE);
			put(DOWNLOAD_START, ORIGINAL_DOWNLOAD_START);
			put(DOWNLOAD_SUCCESS, ORIGINAL_DOWNLOAD_SUCCESS);
			put(INSTALL_ALERT_SHOW, ORIGINAL_INSTALL_ALERT_SHOW);
			
			put(INSTALL_ALERT_CONFORM, ORIGINAL_INSTALL_ALERT_CONFORM);
			put(INSTALL_ALERT_CANCLE, ORIGINAL_INSTALL_ALERT_CANCLE);
			put(INSTALL_START, ORIGINAL_INSTALL_START);
			put(CHECK_UPGRADE, ORIGINAL_CHECK_UPGRADE);
			
			put(ERROR, ORIGINAL_ERROR);

		}
	};
	
	public static String getSUSKey(String value) {
		String key = null;
		if (StringUtils.isNotEmpty(value)) {
			for (String key1 : SUS_ACTION_MAP.keySet()) {
				if (value.equals(SUS_ACTION_MAP.get(key1))) {
					key = key1;
					break;
				}
			}
		}
		return key;
	}
	
}
