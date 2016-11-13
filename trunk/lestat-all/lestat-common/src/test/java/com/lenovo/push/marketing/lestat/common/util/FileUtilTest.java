package com.lenovo.push.marketing.lestat.common.util;

import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Test;

public class FileUtilTest {

	@Test
	public void testGetHdfsFilename() throws UnknownHostException {
		//fail("Not yet implemented");
		String path = FileUtil.getHdfsFilename("/abc", "20140606", 2, new Date());
		System.out.println("path: " + path);
	}

}
