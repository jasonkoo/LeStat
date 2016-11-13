package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DisturbancePercentMapper extends Mapper<LongWritable, Text, IntWritable, DoubleWritable>{
	
	private int total = 0;
	@Override
	protected void setup(Mapper<LongWritable, Text, IntWritable, DoubleWritable>.Context context)
			throws IOException, InterruptedException {
		Configuration conf = context.getConfiguration();
		total = conf.getInt("total", -1);	
	}
	public void map(LongWritable offset, Text line, Context context) 
		throws IOException, InterruptedException {
		String lineContent = line.toString();
		int disturbance = Integer.parseInt(lineContent.split("\t")[0]);
		int sum = Integer.parseInt(lineContent.split("\t")[1]);
		context.write(new IntWritable(disturbance), new DoubleWritable((double)sum / total));
     }
}