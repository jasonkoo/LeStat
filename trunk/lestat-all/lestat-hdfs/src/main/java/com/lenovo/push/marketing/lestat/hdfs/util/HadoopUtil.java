package com.lenovo.push.marketing.lestat.hdfs.util;

import java.io.File;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

public class HadoopUtil {

	private static Configuration grabConfiguration(String hadoopConfDir,
			Configuration conf) {
		boolean oldVersionHadoop = new File(hadoopConfDir, "hadoop-default.xml")
				.exists()
				|| new File(hadoopConfDir, "hadoop-site.xml").exists();
		if (oldVersionHadoop) {
			conf.addResource(new Path(hadoopConfDir, "hadoop-default.xml"));
			conf.addResource(new Path(hadoopConfDir, "hadoop-site.xml"));
		} else {
			conf.addResource(new Path(hadoopConfDir, "mapred-site.xml"));
			conf.addResource(new Path(hadoopConfDir, "hdfs-site.xml"));
			conf.addResource(new Path(hadoopConfDir, "core-site.xml"));
		}
		return conf;
	}

	public static Configuration getConf(String hadoopConfDir) {
		if (hadoopConfDir==null) {
			throw new RuntimeException("hadoopConfDir is null");
		}
		Configuration config = new Configuration();
		return grabConfiguration(hadoopConfDir, config);
	}
}
