package com.lenovo.push.marketing.lestat.mr.disturbance;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.lenovo.lps.push.marketing.drill.common.vo.HitDataEntry;


public class DisturbanceMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	private static Logger logger = Logger.getLogger(DisturbanceMapper.class);
	private IntWritable one = new IntWritable(1);
	private IntWritable zero = new IntWritable(0);
	private String pid;
	private String result;
	
	public void map(LongWritable offset, Text line, Context context) 
		throws IOException, InterruptedException {			
			try {
				HitDataEntry hde = HitDataEntry.parseLine(line.toString());
				if (hde != null) {
					pid = hde.getPid();
					result = hde.getResult();
					
					if (result.equals("0")) {
						context.write(new Text(pid), one);
					} else {
						context.write(new Text(pid), zero);
					}
				}
			} catch (Exception e) {
				logger.error("Parse Line Exception! Line Content: " + line.toString());
			}			
     }
}