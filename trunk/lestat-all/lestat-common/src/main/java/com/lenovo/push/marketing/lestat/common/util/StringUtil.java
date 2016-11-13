package com.lenovo.push.marketing.lestat.common.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class StringUtil {

	public static List<String> getNonEmptyList(String strList,
			String separatorChars) {
		if (strList != null) {
			String d = StringUtils.isEmpty(separatorChars) ? ","
					: separatorChars;
			String[] sArray = StringUtils.split(strList, d);
			if (sArray != null && sArray.length > 0) {
				List<String> list = new ArrayList<String>();
				for (int i = 0; i < sArray.length; i++) {
					String e = sArray[i];
					if (StringUtils.isNotEmpty(e)) {
						list.add(e);
					}
				}
				if (list.size() > 0) {
					return list;
				}
			}
		}
		return null;
	}
}
