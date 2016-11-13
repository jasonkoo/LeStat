package com.lenovo.push.marketing.lestat.db.mdrill.param;

import java.util.HashMap;
import java.util.Map;

public class Param {
	public final static String COLUMNNAME_COL_0 = "col_0";
	public final static String COLUMNNAME_COL_1 = "col_1";
	public final static String COLUMNNAME_COL_2 = "col_2";
	public final static String COLUMNNAME_COL_3 = "col_3";
	public final static String COLUMNNAME_COL_4 = "col_4";
	public final static String COLUMNNAME_COL_5 = "col_5";
	public final static String COLUMNNAME_COL_6 = "col_6";
	public final static String COLUMNNAME_COL_7 = "col_7";
	
	
	public final static String ARRIVED = "arrived";
	public final static String DISPLAYED = "displayed";
	public final static String SYSMSGCLICKED = "sysmsgclicked";
	public final static String S2NDDISPLAYED = "s2nddisplayed";
	public final static String S2NDCLICKED = "s2ndclicked";
	public final static String DOWNLOADED = "downloaded";
	public final static String INSTALLED = "installed";
	public final static String ACTIVATED = "activated";
	
	@SuppressWarnings("serial")
	public final static Map<String, String> EVENT_COL_MAP = new HashMap<String, String>() {
		{
			put(ARRIVED, COLUMNNAME_COL_0);
			put(DISPLAYED, COLUMNNAME_COL_1);
			put(SYSMSGCLICKED, COLUMNNAME_COL_2);
			put(S2NDDISPLAYED, COLUMNNAME_COL_3);
			put(S2NDCLICKED, COLUMNNAME_COL_4);
			put(DOWNLOADED, COLUMNNAME_COL_5);
			put(INSTALLED, COLUMNNAME_COL_6);
			put(ACTIVATED, COLUMNNAME_COL_7);
		}
	};
}
