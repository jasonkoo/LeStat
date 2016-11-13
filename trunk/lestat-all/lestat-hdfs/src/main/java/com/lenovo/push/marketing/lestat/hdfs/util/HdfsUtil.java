package com.lenovo.push.marketing.lestat.hdfs.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

public class HdfsUtil {

	public static void main(String[] args) throws Exception {

		System.out.println("a");
		String hadoopConfDir = "D:\\test\\hadoof\\conf";
		// byte[] bytes = readHDFSFile(hadoopConfDir, "/test/test.txt");
		// System.out.println("a=" + new String(bytes));
		write(hadoopConfDir, new ByteArrayInputStream("afasf".getBytes()),
				"/test/test1.txt", new Long(128*1024*1024).toString());
		System.out.println("b");
	}

	public static void write(String hadoopConfDir, InputStream is, String path, String blockSize)
			throws IOException {
		FSDataOutputStream os = null;
		try {
			Configuration conf = HadoopUtil.getConf(hadoopConfDir);
			if (blockSize != null) {
				conf.set("dfs.block.size", blockSize);
			}
			FileSystem fs = FileSystem.get(conf);
			os = fs.create(new Path(path));
			IOUtils.copyBytes(is, os, 1024, true);
		} catch (IOException e) {
			throw e;
		} finally {
			if (os != null) {
				org.apache.commons.io.IOUtils.closeQuietly(os);
			}
		}
	}
	
	public static void writeInt(Configuration conf, String path, int data) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		FSDataOutputStream out = fs.create(new Path(path), false);
		out.writeInt(data);
		out.close();
	}
	
	public static void delDir(Configuration conf, String path) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(path), true);
	}
	
	public static boolean exists(Configuration conf, String path) throws IOException {
		FileSystem fs = FileSystem.get(conf);
		return fs.exists(new Path(path));
	}	
	
	public static int readTotal(Configuration conf, String path, int numReducers) throws IOException {
		int total = 0;
		FileSystem fs = FileSystem.get(conf);
		// int arrays are initialized to zero by default
		int[] flags = new int[numReducers];
		  
		for (FileStatus s : fs.listStatus(new Path(path))) {
			String name = s.getPath().getName();
			// ignore combiner output
			if (name.contains("_m_"))
				continue;
			// attempt_201405271217_0013_r_000000_0
			int index = Integer.valueOf(name.substring(28, 34));

			if (flags[index] == 0) {
				FSDataInputStream fin = fs.open(s.getPath());
				total += fin.readInt();
				fin.close();
				flags[index] = 1;
			}
		}
		
		return total;
	}
	
	public static List<String> readLines(Configuration conf, String path) throws IOException {
		ArrayList<String> list = new ArrayList<String>();
		FileSystem fs = FileSystem.get(conf);
		String line;
		for (FileStatus s : fs.listStatus(new Path(path))) {
			if (s.isFile()) {
				BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(s.getPath())));
				line = br.readLine();
				while (line != null) {
					list.add(line);
					line = br.readLine();
				}
				br.close();
			}
		}
		return list;
	}
	
	
}
