package com.lenovo.push.marketing.lestat.kafka.susfeedback;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;

import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.common.param.Param;



public class KafkaDataReader {

	private long ttCnt = 0;
	private long readCnt = 0;
	private long lastLogPrintingTime = System.currentTimeMillis();

	//private static final int KAFKA_PARTITION_TOTAL = 24;
	private static Logger logger = Logger.getLogger(KafkaDataReader.class); 
	private ConsumerConfig config;
	private Config myConfig;
    private ConsumerConnector connector;  

    private BlockingQueue<String> messages = new LinkedBlockingQueue<String>(10000);
    //private BlockingQueue<String> messages = new LinkedBlockingQueue<String>();
    private ExecutorService threadPool;
    //private int partitonNum = 1;
    
    private int readerIndex;
	private int readerCount;
	
	private Boolean initFlag = true;
	
	//public final static int PARTITIONS = 2;
 
	public void init(Config config,int readerIndex,
			int readerCount) throws IOException {
		this.myConfig = config;
		logger.info("kafka reader readerIndex = " + readerIndex);
		logger.info("kafka reader readerCount = " + readerCount);
		this.readerIndex = readerIndex;
		this.readerCount = readerCount;
		myInit();
	}

	private synchronized void myInit() throws IOException {
		if (initFlag) {
			try {
				logger.info("kafka reader myInit");
				Properties properties = new Properties();
				String confDir = Param.getLestatConfDir();
				if (confDir!=null) {
					File propertiesFile = new File(confDir,"tj-consumer.properties");
					if (!propertiesFile.exists()) {
						throw new IOException("tj-consumer.properties does not exist!");
					}
					properties.load(new FileInputStream(propertiesFile));
				} else {
					throw new IOException("-DlestatConfDir is null!");
				}
				//properties.load(ClassLoader
				//		.getSystemResourceAsStream("consumer.properties"));
				String groupId = properties.getProperty("group.id");
				logger.info("groupId=" + groupId);
				String zookeeperConnect = properties.getProperty("zookeeper.connect");
				logger.info("zookeeper.connect=" + zookeeperConnect);

				this.config = new ConsumerConfig(properties);
				
				String topic = myConfig.getKafkaTopic();
				logger.info("topic=" + topic);

				this.connector = Consumer.createJavaConsumerConnector(config);
				Map<String, Integer> topics = new HashMap<String, Integer>();
				
				int partitionCount = myConfig.getPartitionCount();
				logger.info("partitionCount=" + partitionCount);
				topics.put(topic, partitionCount);
				Map<String, List<KafkaStream<byte[], byte[]>>> streamMap = connector
						.createMessageStreams(topics);
				List<KafkaStream<byte[], byte[]>> streamList = streamMap
						.get(topic);
				int size = streamList.size();
				logger.info("kafka streamList size = " + size);

				threadPool = Executors.newFixedThreadPool(size);
				for (KafkaStream<byte[], byte[]> consumerStream : streamList) {
					// System.out.println("partiton:"+partition.hashCode());
					// KafkaStream<byte[], byte[]> partition =
					// partitions.get(readerIndex % size);
					threadPool.execute(new MessageRunner(consumerStream));
					// KafkaStream<byte[], byte[]> partition =
					// partitions.get(readerIndex % size);
					// threadPool.execute(new MessageRunner(partition));
					// KafkaStream<byte[], byte[]> partition2 =
					// partitions.get((readerIndex+readerCount) % size);
					// threadPool.execute(new MessageRunner(partition2));
				}
				initFlag = false;
			} catch (Exception e) {
				initFlag = true;
				logger.error(
						"001 kafka reader cannot be initialized: "
								+ e.getMessage(), e);
				throw new IOException("kafka reader cannot be initialized");
			}
		}
	}

	public List<Object> read() throws IOException {
		myInit();
		readCnt++;
		printlog();
		
		try {
		List<Object> list = new ArrayList<Object>();
		int count = 0;
		while(count < 10000){
			String message = messages.poll();
			if(message != null){
				//LOG.info("poll message=" + message);
				list.add(message);
				count++;
				// for log only
				ttCnt++;
			}else{
				break;
			}			
		}
		//System.out.println("return list");
		return list;
		//return null;
		} catch (Exception e) {
				initFlag = true;
				logger.error(
						"002 kafka reader does not run any more: "
								+ e.getMessage(), e);
				throw new IOException("kafka reader does not run any more");
		}
	}

	private void printlog() {
//		if (readCnt == Long.MAX_VALUE) {
//			readCnt = 0;
//			LOG.info("kafka reader: readCnt reset");
//		}
//		if (ttCnt == Long.MAX_VALUE) {
//			ttCnt = 0;
//			LOG.info("kafka reader: ttCnt reset");
//		}
		long now = System.currentTimeMillis();
		if (now - this.lastLogPrintingTime > 10000) {
			this.lastLogPrintingTime = now;
			logger.info("kafka reader info: [" + "appfeedback-topic: readerIndex=" + readerIndex + ", readerCount=" +readerCount+", readCnt=" + readCnt + ", ttCnt=" +ttCnt + "]");
		}
		
	}

	public void close() throws IOException {
		connector.shutdown();  
	}

	
    public interface MessageExecutor {  
        
        public void execute(MessageAndMetadata<byte[],byte[]> item);  

    } 
    
    class MessageRunner implements Runnable{  
        private KafkaStream<byte[], byte[]> partition;  
          
        MessageRunner(KafkaStream<byte[], byte[]> partition) {  
            this.partition = partition;  
        }  
          
        public void run(){  
        	try {
            ConsumerIterator<byte[], byte[]> it = this.partition.iterator();  
            while(it.hasNext()){  
                                //connector.commitOffsets();手动提交offset,当autocommit.enable=false时使用  
                MessageAndMetadata<byte[],byte[]> item = it.next();  
                String message = new String(item.message());
               // TODO remove debugging logs
                logger.debug("add message=" + message);
                //messages.add(message);
                messages.put(message);
            }  
        	} catch (Exception e) {
					initFlag = true;
					logger.error(
							"003 kafka reader does not run any more: "
									+ e.getMessage(), e);
        	}
        }  
    } 
}


