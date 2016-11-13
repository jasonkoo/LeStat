package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.lenovo.push.marketing.lestat.hdfs.util.HdfsUtil;


public class DisturbanceDistributionReducer extends Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {	
	
	private int total = 0;
	
	public void reduce(IntWritable disturbance, Iterable<IntWritable> counts, Context context) 
			throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable count : counts) {
			sum += count.get();
		}	
		total += sum;
		context.write(disturbance, new IntWritable(sum));
	}
	
	@Override
	protected void cleanup(Reducer<IntWritable, IntWritable, IntWritable, IntWritable>.Context context)
			throws IOException, InterruptedException {
			Configuration conf = context.getConfiguration();
			String taskId = conf.get("mapred.task.id");
			//String taskId = context.getTaskAttemptID().toString();
			String path = conf.get("totalPath");
			
			
			if (taskId != null && path != null) {
				// Only for reducer
				if (taskId.contains("_r_")) {
					// Write to a file the total count we've seen in this reducer.
					path = path + "/" + taskId;
					HdfsUtil.writeInt(context.getConfiguration(), path, total);					
				}
			}			
	}	
}