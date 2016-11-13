package com.lenovo.push.marketing.hive.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class ExtractHour extends UDF {
	private static String SRC_PATTERN="MMM d, yyyy h:mm:ss a";
	private static String DST_PATTERN="HH";
	
	
	public Text evaluate(final Text s) {
		if (s == null) {
			return null;
		}
		String hour = null;
		try {
			Date date = new SimpleDateFormat(SRC_PATTERN, Locale.ENGLISH).parse(s.toString());
			hour = new SimpleDateFormat(DST_PATTERN).format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Text(hour);
	}
	
	public static void main(String[] args) throws ParseException {
		String dateString = "Jun 17, 2015 12:32:04 AM";
		ExtractHour eh = new ExtractHour();
		Text reText = eh.evaluate(new Text(dateString));
		System.out.println(reText.toString());
	}
}
