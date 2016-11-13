package com.lenovo.push.marketing.lestat.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class NetworkUtil {
	public static String getHostname() throws UnknownHostException {
		InetAddress ia = InetAddress.getLocalHost();
		return ia.getHostName();
	}
}
