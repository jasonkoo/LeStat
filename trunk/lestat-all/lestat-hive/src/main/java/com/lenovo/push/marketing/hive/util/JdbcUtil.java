package com.lenovo.push.marketing.hive.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {

	// private final static String driverName =
	// "org.apache.hadoop.hive.jdbc.HiveDriver";

	private final static String driverName = "org.apache.hive.jdbc.HiveDriver";

	public static void main(String[] args) throws SQLException {
		System.out.println("001");
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		// replace "hive" here with the name of the user the queries should run
		// as
		System.out.println("002");
		Connection con = DriverManager.getConnection(
				"jdbc:hive2://PUSH-009:10000/default", "hive", "");
		Statement stmt = con.createStatement();
		System.out.println("003");
		String tableName = "testHiveDriverTable";
		stmt.execute("drop table if exists " + tableName);
		System.out.println("004");
		stmt.execute("create table " + tableName + " (key int, value string)");
		// show tables
		System.out.println("005");
		String sql = "show tables '" + tableName + "'";
		System.out.println("Running: " + sql);
		ResultSet res = stmt.executeQuery(sql);
		System.out.println("006");
		if (res.next()) {
			System.out.println(res.getString(1));
		}
		System.out.println("007");
		// describe table
		sql = "describe " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1) + "\t" + res.getString(2));
		}

		System.out.println("008");
		// load data into table
		// NOTE: filepath has to be local to the hive server
		// NOTE: /tmp/a.txt is a ctrl-A separated file with two fields per line
		String filepath = "/tmp/a.txt";
		sql = "load data local inpath '" + filepath + "' into table "
				+ tableName;
		System.out.println("Running: " + sql);
		stmt.execute(sql);

		System.out.println("009");
		// select * query
		sql = "select * from " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(String.valueOf(res.getInt(1)) + "\t"
					+ res.getString(2));
		}

		System.out.println("010");
		// regular hive query
		sql = "select count(1) from " + tableName;
		
		//sql = "select * from " + tableName;
		System.out.println("Running: " + sql);
		res = stmt.executeQuery(sql);
		while (res.next()) {
			System.out.println(res.getString(1));
		}
		System.out.println("011");
	}
}
