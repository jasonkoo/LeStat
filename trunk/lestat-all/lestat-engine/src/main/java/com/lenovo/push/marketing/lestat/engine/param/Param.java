package com.lenovo.push.marketing.lestat.engine.param;

import java.util.ArrayList;
import java.util.List;

public class Param {

	public final static String SID = "sid";
	public final static String AD_ID = "adId";
	public final static String THEDATE = "thedate";
	public final static String START_DATE = "sd";
	public final static String END_DATE = "ed";
	public final static String DIM = "dim";
	public final static String COL = "col";

	public final static String DIM_DEVICE_MODEL = "dm";
	public final static String DIM_APP_VERSION = "av";

	@SuppressWarnings("serial")
	public final static List<String> DIM_LIST = new ArrayList<String>() {
		{
			add(DIM_DEVICE_MODEL);
			add(DIM_APP_VERSION);

		}
	};
	
	public final static String PACKAGE_ID = "pkgid";
	public final static String VERTION = "ver";
	public final static String CHANNEL = "cha";
	public final static String APP_KEY = "ak";
	
	public final static int SUS_MAX_DATE_DIFF = 30;
	
	public final static String PACKAGE_NAME_LIST = "pnl";
	public final static int FEEDBACK_MAX_DATE_DIFF = 7;
}
