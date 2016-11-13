package com.lenovo.push.marketing.lestat.logstash.util;

public class OSUtil {
	public static String getHostName() {
		if (System.getProperty("os.name").startsWith("Windows")) {
		    // Windows will always set the 'COMPUTERNAME' variable
		    return System.getenv("COMPUTERNAME");
		} else {
		    // If it is not Windows then it is most likely a Unix-like operating system
		    // such as Solaris, AIX, HP-UX, Linux or MacOS.

		    // Most modern shells (such as Bash or derivatives) sets the 
		    // HOSTNAME variable so lets try that first.
		    String hostname = System.getenv("HOSTNAME");
		    if (hostname != null) {
		       return hostname;
		    } else {
		    	return "PUSH-016";
		       // If the above returns null *and* the OS is Unix-like
		       // then you can try an exec() and read the output from the 
		       // 'hostname' command which exist on all types of Unix/Linux.

		       // If you are an OS other than Unix/Linux then you would have 
		       // to do something else. For example on OpenVMS you would find 
		       // it like this from the shell:  F$GETSYI("NODENAME") 
		       // which you would probably also have to find from within Java 
		       // via an exec() call.

		       // If you are on zOS then who knows ??

		       // etc, etc
		    }
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getHostName());
	}
}
