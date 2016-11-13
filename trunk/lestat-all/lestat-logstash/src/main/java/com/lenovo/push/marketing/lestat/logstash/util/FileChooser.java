package com.lenovo.push.marketing.lestat.logstash.util;

import java.io.File;
import java.io.FilenameFilter;

public class FileChooser implements FilenameFilter{
	
	private String pattern;
	
	public FileChooser(String startswith) {
		pattern = startswith;
	}

	@Override
	public boolean accept(File dir, String name) {		
		return name.startsWith(pattern);
	}

}
