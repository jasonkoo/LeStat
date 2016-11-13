package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.util.Date;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;
import org.apache.log4j.Logger;

import com.lenovo.push.marketing.lestat.common.config.Config;
import com.lenovo.push.marketing.lestat.hdfs.util.HdfsUtil;
import com.lenovo.push.marketing.lestat.mr.param.Param;

public class Driver extends Configured implements Tool {
	
	private static Logger logger = Logger.getLogger(Driver.class);

	private Config config;
	private Param param;

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}	

	public Param getParam() {
		return param;
	}

	public void setParam(Param param) {
		this.param = param;
	}

	public int run(String[] args) throws Exception {

		String basePath = config.getLestatHdfsHome();
		String date = param.getThedate();
		int numRedStage1 = param.getNumRedStage1();
		int numRedStage2 = param.getNumRedStage2();
		int numRedStage3 = param.getNumRedStage3();
		
		logger.info("numRedStage1: " + numRedStage1);
		logger.info("numRedStage2: " + numRedStage2);
		logger.info("numRedStage3: " + numRedStage3);

		// Used to calculate number of reduce tasks to run in parallel
		// The right number of reduces seems to be
		// 0.95 or 1.75 * (nodes * mapred.tasktracker.tasks.maximum)
		// i.e. [0.95 * numSlaves * numCPUsPerSlave * numCoresPerCPU, 1.75 *
		// numSlaves * numCPUs * coresPerCPU]

		//int numReducers = (int) Math.round(0.95 * numSlaves * 1 * 4);		
		

		Configuration conf = getConf();

		String inputPath;
		String outputPath;
		Job job1 = new Job(conf);
		inputPath = basePath + "/" + param.getDataSource() + "/" + date;
		outputPath = basePath + "/mapred/" + date + "/" + param.getStage1Dir();		
		
		// If inputPath not exist, no need to compute
		if (! HdfsUtil.exists(conf, inputPath)) {
			return 2;
		}
		
		job1.setJobName("Disturbance stage-1 at " + new Date() +  ": " + inputPath);
		job1.setJarByClass(Driver.class);

		FileInputFormat.setInputPaths(job1, new Path(inputPath));
		FileOutputFormat.setOutputPath(job1, new Path(outputPath));

		job1.setMapperClass(DisturbanceMapper.class);
		job1.setReducerClass(DisturbanceReducer.class);
		job1.setCombinerClass(DisturbanceReducer.class);

		job1.setMapOutputKeyClass(Text.class);
		job1.setMapOutputValueClass(IntWritable.class);

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(IntWritable.class);

		job1.setNumReduceTasks(numRedStage1);

		// Delete the output directory if it exists already
		HdfsUtil.delDir(conf, outputPath);

		job1.waitForCompletion(true);	

		String totalPath = basePath + "/mapred/" + date + "/" + param.getStage2TotalDir();
		conf.set("totalPath", totalPath);
		Job job2 = new Job(conf);
		inputPath = basePath + "/mapred/" + date + "/" + param.getStage1Dir();
		outputPath = basePath + "/mapred/" + date + "/" + param.getStage2Dir();
		
		param.setResultPath(outputPath);
		param.setTotalPath(totalPath);
		
		job2.setJobName("Disturbance stage-2 at " +  new Date() + ": " + inputPath);
		job2.setJarByClass(Driver.class);

		FileInputFormat.setInputPaths(job2, new Path(inputPath));
		FileOutputFormat.setOutputPath(job2, new Path(outputPath));

		job2.setMapperClass(DisturbanceDistributionMapper.class);
		job2.setReducerClass(DisturbanceDistributionReducer.class);
		job2.setCombinerClass(DisturbanceDistributionReducer.class);

		job2.setMapOutputKeyClass(IntWritable.class);
		job2.setMapOutputValueClass(IntWritable.class);

		job2.setOutputKeyClass(IntWritable.class);
		job2.setOutputValueClass(IntWritable.class);		
		
		job2.setNumReduceTasks(numRedStage2);

		// Delete the output directory if it exists already
		HdfsUtil.delDir(conf, outputPath);
		HdfsUtil.delDir(conf, totalPath);

		job2.waitForCompletion(true);
		
//		int total = HdfsUtil.readTotal(conf, totalPath, numRedStage2);
//
//		conf.setInt("total", total);
//		Job job3 = new Job(conf);
//		inputPath = basePath + "/mapred/" + date + "/" + param.getStage2Dir();
//		outputPath = basePath + "/mapred/" + date + "/" + param.getStage3Dir();
//
//		job3.setJobName("Compute disturbance distribution: " + inputPath);
//		job3.setJarByClass(Driver.class);
//
//		FileInputFormat.setInputPaths(job3, new Path(inputPath));
//		FileOutputFormat.setOutputPath(job3, new Path(outputPath));
//
//		job3.setMapperClass(DisturbancePercentMapper.class);
//
//		job3.setMapOutputKeyClass(IntWritable.class);
//		job3.setMapOutputValueClass(DoubleWritable.class);
//
//		job3.setOutputKeyClass(IntWritable.class);
//		job3.setOutputValueClass(DoubleWritable.class);
//		
//		job3.setNumReduceTasks(numRedStage3);
//		// Delete the output directory if it exists already
//		HdfsUtil.delDir(conf, outputPath);
//
//		job3.waitForCompletion(true);

		return 0;
	}
	
	/*private void printJobCounters(Job job) throws IOException {
		Counters counters = job.getCounters();
		for (CounterGroup cg : counters) {
			//CounterGroup cg = counters.getGroup("Map-Reduce Framework");
			logger.info("* Counter Group: " + cg.getDisplayName() + " (" + cg.getName() + ")");
			for (Counter c : cg) {
				logger.info("  - " + c.getDisplayName() + ": " + c.getName() + ": "+c.getValue());
			}			
		}
		
	}*/
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new Driver(), args);
	}
}
