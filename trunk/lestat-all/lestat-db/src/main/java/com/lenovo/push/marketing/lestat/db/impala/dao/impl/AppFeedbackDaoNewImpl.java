package com.lenovo.push.marketing.lestat.db.impala.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.impala.dao.AppFeedbackNewDao;
import com.lenovo.push.marketing.lestat.db.impala.entity.AdTaskStatResult;

@Repository("impalaAppFeedbackNewDao")
public class AppFeedbackDaoNewImpl implements AppFeedbackNewDao{
	private static Logger logger = Logger.getLogger(AppFeedbackDaoNewImpl.class);
	
	@Autowired
	private DataSource impalaDataSource;
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rset = null;
	
	public List<AdTaskStatResult> getAppFeedbackStat(String thedate) throws SQLException {
		
		try {
			logger.info("Getting connection from pool.");
			conn = impalaDataSource.getConnection();
			logger.info("Preparing statement.");			
		
			String sql = "select sid, adid, eventtype, count(distinct deviceid) as cnt from appfeedback where thedate=? group by sid, adid, eventtype order by sid, eventtype";
			logger.info("SQL before setting parameters: " + sql);
			stmt = conn.prepareStatement(sql);
			int index = 1;
			logger.info("Setting parameter " + index + ": thedate => " + thedate);
			stmt.setString(index++, thedate);
			
			logger.info("Executing statement.");			
			rset = stmt.executeQuery();
			
			// Assembly Results
			List<AdTaskStatResult> statResults = new ArrayList<AdTaskStatResult>();
			while (rset.next()) {
				AdTaskStatResult adTaskStatResult = new AdTaskStatResult();
				adTaskStatResult.setSid(rset.getString(1));
				adTaskStatResult.setAdid(rset.getString(2));
				adTaskStatResult.setEventtype(rset.getString(3));
				adTaskStatResult.setCnt(rset.getLong(4));
				statResults.add(adTaskStatResult);	
			}
			return statResults;
		} catch (SQLException e) {
			throw e;
		} finally {
			try {
				if (rset != null) {	rset.close();}
				if (stmt != null) { stmt.close();}
				if (conn != null) { conn.close();}
			} catch (SQLException e) {					
				throw e;
			}
		}		
	}
}
