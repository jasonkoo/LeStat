package com.lenovo.push.marketing.lestat.logstash.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FileUtil {
	
	public static boolean exists(String path) {
		File file = new File(path);
		return file.exists();
	}
	
	public static FileInputStream getInputStream(String path) {
		File file = new File(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fis;
	}	
	
	public static void closeInputStream(InputStream is) {
		if (is != null) {
			IOUtils.closeQuietly(is);
		}		
	}
	
	public static String[] matchingFiles(String dirPath, String startswith) {
		File dir = new File(dirPath);
		FileChooser fc = new FileChooser(startswith);
		String[] matchings = dir.list(fc);
		return matchings;
	}
	
	
	
	public static void main(String[] args) {
		String dirPath = "C:\\Users\\gulei2\\Desktop\\20140610\\test";
		String startswith = "tfile-PUSH-016-2014-06-19-16-2";
		String[] matchings = matchingFiles(dirPath, startswith);
		for (String name: matchings) {
			System.out.println(name);
		}
		
	}
}
