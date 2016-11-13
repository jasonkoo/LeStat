package com.lenovo.push.marketing.lestat.hdfs.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class OldHdfsUtil {

	public static void main(String[] args) throws Exception {

		String hadoopConfDir = "D:\\test\\hadoof\\conf";
		byte[] bytes = readHDFSFile(hadoopConfDir, "/test/test.txt");
		System.out.println("a=" + new String(bytes));
	}

	/*
	 * upload the local file to the hds notice that the path is full like
	 * /tmp/test.c
	 */
	public static void uploadLocalFile2HDFS(String s, String d)
			throws IOException {
		Configuration config = new Configuration();
		FileSystem hdfs = FileSystem.get(config);

		Path src = new Path(s);
		Path dst = new Path(d);

		hdfs.copyFromLocalFile(src, dst);

		hdfs.close();
	}

	/*
	 * create a new file in the hdfs. notice that the toCreateFilePath is the
	 * full path and write the content to the hdfs file.
	 */
	public static void createNewHDFSFile(String hadoopConfDir,
			String toCreateFilePath, String content) throws IOException {
		Configuration conf = HadoopUtil.getConf(hadoopConfDir);
		FileSystem hdfs = FileSystem.get(conf);

		FSDataOutputStream os = hdfs.create(new Path(toCreateFilePath));

		os.write(content.getBytes("UTF-8"));
		os.close();

		hdfs.close();
	}

	/*
	 * delete the hdfs file notice that the dst is the full path name
	 */
	public static boolean deleteHDFSFile(String dst) throws IOException {
		Configuration config = new Configuration();
		FileSystem hdfs = FileSystem.get(config);

		Path path = new Path(dst);
		boolean isDeleted = hdfs.delete(path, false);

		hdfs.close();

		return isDeleted;
	}

	/*
	 * read the hdfs file content notice that the dst is the full path name
	 */
	public static byte[] readHDFSFile(String hadoopConfDir, String dst)
			throws Exception {
		// Configuration conf = new Configuration();
		Configuration conf = HadoopUtil.getConf(hadoopConfDir);
		FileSystem fs = FileSystem.get(conf);

		// check if the file exists
		Path path = new Path(dst);
		if (fs.exists(path)) {
			FSDataInputStream is = fs.open(path);
			// get the file info to create the buffer
			FileStatus stat = fs.getFileStatus(path);

			// create the buffer
			byte[] buffer = new byte[Integer.parseInt(String.valueOf(stat
					.getLen()))];
			is.readFully(0, buffer);

			is.close();
			fs.close();

			return buffer;
		} else {
			throw new Exception("the file is not found .");
		}
	}

	/*
	 * make a new dir in the hdfs
	 * 
	 * the dir may like '/tmp/testdir'
	 */
	public static void mkdir(String dir) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		fs.mkdirs(new Path(dir));

		fs.close();
	}

	/*
	 * delete a dir in the hdfs
	 * 
	 * dir may like '/tmp/testdir'
	 */
	public static void deleteDir(String dir) throws IOException {
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(conf);

		fs.delete(new Path(dir), true);

		fs.close();
	}

	// public static void listAll(String dir) throws IOException
	// {
	// Configuration conf = new Configuration();
	// FileSystem fs = FileSystem.get(conf);
	//
	// FileStatus[] stats = fs.listStatus(new Path(dir));
	//
	// for(int i = 0; i < stats.length; ++i)
	// {
	// if (stats[i].isFile())
	// {
	// // regular file
	// System.out.println(stats[i].getPath().toString());
	// }
	// else if (stats[i].isDirectory())
	// {
	// // dir
	// System.out.println(stats[i].getPath().toString());
	// }
	// else if(stats[i].isSymlink())
	// {
	// // is s symlink in linux
	// System.out.println(stats[i].getPath().toString());
	// }
	//
	// }
	// fs.close();
	// }

}
