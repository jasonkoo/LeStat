package com.lenovo.push.marketing.hive.udf;

import org.apache.hadoop.io.Text;

import junit.framework.TestCase;

public class Sha1Test extends TestCase {

	public void testEvaluate() {
		//fail("Not yet implemented");
		Sha1 sha1 = new Sha1();
		//System.out.println(sha1.evaluate(new Text("IdeaTabA1006")));
		//System.out.println("aaa");
		assertEquals("ARyUXzDOLLr8RS85hA8CVpMznEI=", sha1.evaluate(new Text("1111")).toString());
		assertEquals("CMgx8vQZ1CtngFgv3XjTP+u1mbY=", sha1.evaluate(new Text("Lenovo A850t")).toString());
	}

}
