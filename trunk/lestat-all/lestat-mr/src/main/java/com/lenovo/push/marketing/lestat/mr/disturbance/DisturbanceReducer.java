package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


public class DisturbanceReducer extends Reducer<Text, IntWritable, Text, IntWritable> {	
	
	public void reduce(Text pid, Iterable<IntWritable> counts, Context context) 
			throws IOException, InterruptedException {
		
		int sum = 0;
		for (IntWritable count : counts) {
			sum += count.get();
		}	
		
		context.write(pid, new IntWritable(sum));
	}
}