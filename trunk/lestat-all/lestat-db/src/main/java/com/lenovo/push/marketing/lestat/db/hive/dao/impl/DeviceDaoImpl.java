package com.lenovo.push.marketing.lestat.db.hive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.hive.dao.DeviceDao;
import com.lenovo.push.marketing.lestat.db.hive.entity.DeviceResult;


@Repository("deviceDao")
public class DeviceDaoImpl implements DeviceDao {
	private static Logger logger = Logger.getLogger(DeviceDaoImpl.class);
	@Autowired
	private DataSource hiveDataSource;
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rset = null;
	
	@Override
	public DeviceResult getActiveUv(String st) {
		DeviceResult dr = new DeviceResult();
		try {
				logger.info("Getting connection from pool.");
				conn = hiveDataSource.getConnection();
				logger.info("Preparing statement.");
				String sql = "select count(distinct deviceid) as uv from device where hittime>=?";
				stmt = conn.prepareStatement(sql);			
				stmt.setString(1, st);
				logger.info("Executing statement.");			
				rset = stmt.executeQuery();
				if (rset.next()) {
					dr.setUv(rset.getInt(1));
				}				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {			
				try {
					if (rset != null) {	rset.close();}
					if (stmt != null) { stmt.close();}
					if (conn != null) { conn.close();}
				} catch (SQLException e) {					
					e.printStackTrace();
				}
		}
		
		return dr;
		
	}

}
