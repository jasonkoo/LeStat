package com.lenovo.push.marketing.lestat.kafka.writer.ledrillinternal;

import java.util.Date;

import com.lenovo.lps.push.marketing.drill.common.util.NumberUtil;
import com.lenovo.lps.push.marketing.drill.common.vo.ErrorDataEntry;
import com.lenovo.lps.push.marketing.drill.common.vo.FeedbackDataEntry;
import com.lenovo.lps.push.marketing.drill.common.vo.HitDataEntry;
import com.lenovo.lps.push.marketing.drill.common.vo.UserDataEntry;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppDataEntry;
import com.lenovo.push.marketing.lestat.kafka.writer.test.appinfo.vo.AppFeedbackDataEntry;

public class DataGenerator {

	private static long c = 0;
	
	public static FeedbackDataEntry getFeedbackDataEntry() {
		FeedbackDataEntry de = new FeedbackDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);

		de.setAdId("adId_" + timeStr);
		de.setPid("pid_" + timeStr);
		de.setEventType(Integer.toString(getEventType()));
		de.setAcId("acId_" + timeStr);
		de.setAdType("adType_" + timeStr);
		de.setLogTime(now);
		
		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel("deviceModel_" + timeStr);
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);

		de.setArrivalTime(now);
		return de;
	}
	
	public static AppFeedbackDataEntry getAppFeedbackDataEntry() {
		AppFeedbackDataEntry de = new AppFeedbackDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);
		de.setAdId("adId_" + String.format("%08d", new Long((nowl % 100))));
		de.setPid("pid_" + timeStr);
		de.setEventType(Integer.toString(getEventType()));
		de.setAcId("acId_" + timeStr);
		de.setAdType("adType_" + timeStr);
		de.setLogTime(now);
		
		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel("deviceModel_" + timeStr);
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);
		
		de.setArrivalTime(now);

		de.setSid(new Long(10020 + (nowl % 3)).toString());
		if (c % 5 !=0) {
			de.setSuccess(true);
		}else {
			de.setSuccess(false);
			de.setErrCode("ErrCode_" + timeStr);
		}
		de.setPackName("packName_" + timeStr);
		de.setCurrVer("currVer_" + timeStr);
		de.setTargetVer("targetVer_" + timeStr);
		de.setValue("value_" + timeStr);
		
		c++;
		return de;
	}

	private static int getEventType() {
		return NumberUtil.getRandomInt(FeedbackDataEntry.COUNTS_ARRAY_LENGTH);
	}

	public static HitDataEntry getHitDataEntry() {
		HitDataEntry de = new HitDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);

		de.setDate(now);
		de.setAdId("adId_" + timeStr);
		de.setPid("pid_" + timeStr);
		de.setResult("result_" + timeStr);
		de.setHitTime(now);

		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel("deviceModel_" + timeStr);
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);
		
		de.setArrivalTime(now);
		return de;
	}
	
	public static AppDataEntry getAppDataEntry() {
		AppDataEntry de = new AppDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);

		de.setDate(now);
//		de.setAdId("adId_" + timeStr);
//		de.setPid("pid_" + timeStr);
//		de.setResult("result_" + timeStr);
		de.setLogTime(now);

		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel(new Long(nowl % 13).toString());
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);
		
		de.setArrivalTime(now);
		
		de.setSid(new Long(10020 + (nowl % 2)).toString());
		de.setAppPkgName("appPkgName_" + timeStr);
		de.setAppVerCode("appVerCode_" + timeStr);
		de.setAppVerName("1.0." + new Long(nowl % 9).toString());
		de.setIntegratedMode("integratedMode_" + timeStr);
		de.setEngineWorkMode("engineWorkMode_" + timeStr);
		return de;
	}

	public static UserDataEntry getUserDataEntry() {
		UserDataEntry de = new UserDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);

		de.setPid("pid_" + timeStr);
		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel("deviceModel_" + timeStr);
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);

		de.setArrivalTime(now);
		return de;
	}
	
	public static ErrorDataEntry getErrorDataEntry() {
		ErrorDataEntry de = new ErrorDataEntry();
		Date now = new Date();
		long nowl = now.getTime();
		String timeStr = Long.toString(nowl);

		de.setDate(now);
		de.setAdId("adId_" + timeStr);
		de.setPid("pid_" + timeStr);
		de.setResult("result_" + timeStr);
		de.setType("type_" + timeStr);
		de.setPackageName("packageName_" + timeStr);
		de.setTargetVersion("targetVersion_" + timeStr);
		de.setLogTime(now);

		de.setAccessNum(nowl);
		de.setApn("apn_" + timeStr);
		de.setCellId("cellId_" + timeStr);
		de.setChannelName("channelName_" + timeStr);
		de.setChargeStatus("chargeStatus_" + timeStr);
		de.setCityName("cityName_" + timeStr);
		de.setCountryCode("countryCode_" + timeStr);
		de.setCreateDate(now);
		de.setCustVersion("custVersion_" + timeStr);
		de.setDeviceIMSI("deviceIMSI_" + timeStr);
		de.setDeviceModel("deviceModel_" + timeStr);
		de.setDeviceId("deviceId_" + timeStr);
		de.setDeviceIdType("deviceIdType_" + timeStr);
		de.setIp("ip_" + timeStr);
		de.setLatitude("latitude_" + timeStr);
		de.setLocId("locId_" + timeStr);
		de.setLongitude("longitude_" + timeStr);
		de.setModifyDate(now);
		de.setNetaccessType("netaccessType_" + timeStr);
		de.setOperationType("operationType_" + timeStr);
		de.setOperatorCode("operatorCode_" + timeStr);
		de.setOsVersion("osVersion_" + timeStr);
		de.setPePkgName("pePkgName_" + timeStr);
		de.setPeVerCode("peVerCode_" + timeStr);
		de.setPeVersion("peVersion_" + timeStr);
		de.setPePollVersion("pePollVersion_" + timeStr);
		de.setSysId("sysId_" + timeStr);
		
		de.setArrivalTime(now);
		return de;
	}

}
