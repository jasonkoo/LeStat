package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DisturbanceDistributionMapper extends Mapper<LongWritable, Text, IntWritable, IntWritable>{
	
	private IntWritable one = new IntWritable(1);	
	
	public void map(LongWritable offset, Text line, Context context) 
		throws IOException, InterruptedException {
		String lineContent = line.toString();
		int sum = Integer.parseInt(lineContent.split("\t")[1]);
		context.write(new IntWritable(sum), one);
     }
}