package com.lenovo.push.marketing.lestat.common.util;

import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ConfigUtil {
	public static void configLog4j(String log4jPath) {
		DOMConfigurator.configure(log4jPath);
	}
	public static ApplicationContext configSpring(String spring_path) {
		return new FileSystemXmlApplicationContext(spring_path);
	}
}
