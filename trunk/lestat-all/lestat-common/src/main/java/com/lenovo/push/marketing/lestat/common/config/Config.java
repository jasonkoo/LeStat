package com.lenovo.push.marketing.lestat.common.config;

public class Config {

	private String kafkaTopic;
	private String hadoopConfDir;
	private String lestatHdfsHome;
	private int partitionCount;	

	public String getHadoopConfDir() {
		return hadoopConfDir;
	}

	public void setHadoopConfDir(String hadoopConfDir) {
		this.hadoopConfDir = hadoopConfDir;
	}	

	public String getKafkaTopic() {
		return kafkaTopic;
	}

	public void setKafkaTopic(String kafkaTopic) {
		this.kafkaTopic = kafkaTopic;
	}

	public String getLestatHdfsHome() {
		return lestatHdfsHome;
	}

	public void setLestatHdfsHome(String lestatHdfsHome) {
		this.lestatHdfsHome = lestatHdfsHome;
	}

	public int getPartitionCount() {
		return partitionCount;
	}

	public void setPartitionCount(int partitionCount) {
		this.partitionCount = partitionCount;
	}
}
