package com.lenovo.push.marketing.lestat.kafka.writer.pmservice;

import kafka.producer.DefaultPartitioner;
import kafka.utils.VerifiableProperties;

import org.apache.log4j.Logger;

public class LogPartitioner extends DefaultPartitioner {
	
	public LogPartitioner(VerifiableProperties props) {
		super(props);
	}


	private static final Logger logger = Logger.getLogger(LogPartitioner.class);

	private int partition = -1;
	

	@Override
	public int partition(Object key, int numPartitions) {
		// int partition = 0;
		// partition = Integer.parseInt((String) key) % numPartitions;
		partition = (partition + 1) % numPartitions;
		logger.debug("partition=" + partition + ", key=" + key + ", numPartitions=" + numPartitions);
		return partition;
	}

}