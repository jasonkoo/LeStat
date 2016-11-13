package com.lenovo.push.marketing.hive.udf;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class Sha1 extends UDF {
	public Text evaluate(final Text s) {
		if (s == null) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("SHA1");
			md.update(s.toString().getBytes());
			byte[] hash = md.digest();
			Base64 encoder = new Base64();
			return new Text(encoder.encodeToString(hash).replaceAll("[\r\n]", ""));
		} catch (NoSuchAlgorithmException nsae) {
			throw new IllegalArgumentException("SHA1 is not setup");
		}
	}
}
