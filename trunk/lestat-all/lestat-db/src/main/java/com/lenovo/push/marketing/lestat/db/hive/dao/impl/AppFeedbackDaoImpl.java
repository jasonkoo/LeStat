package com.lenovo.push.marketing.lestat.db.hive.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.lenovo.push.marketing.lestat.db.hive.dao.AppFeedbackDao;
import com.lenovo.push.marketing.lestat.db.hive.entity.AppMsgEventStatEntity;
import com.lenovo.push.marketing.lestat.db.mysql0.entity.AppMsgEntity;

@Repository("hiveAppFeedbackDao")
public class AppFeedbackDaoImpl implements AppFeedbackDao {
	
	private static Logger logger = Logger.getLogger(AppFeedbackDaoImpl.class);
	
	@Autowired
	private DataSource hiveDataSource;
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rset = null;
	
	@Override
	public List<AppMsgEventStatEntity> getAppMsgEventStatForActiveAppMsgs(List<AppMsgEntity> appMsgEntities, String startDate, String endDate) throws SQLException{
		try {
			logger.info("Getting connection from pool.");
			conn = hiveDataSource.getConnection();
			logger.info("Preparing statement.");
			
			// construct sql components
			List<String> conditionList = new ArrayList<String>();
			for (int i =0; i < appMsgEntities.size(); i++) {
				conditionList.add( "( sid = ? and adid = ?)" );
			}
			String tail = StringUtils.join(conditionList, " or ");
			
			String sql = "select sid, adid, eventtype, count(distinct deviceid) as distCount from appfeedback where thedate >= ? and thedate <= ? and (" + tail + ")" + "  group by sid, adid, eventtype";
			logger.info("HiveQL before setting parameters: " + sql);
			stmt = conn.prepareStatement(sql);
			int index = 1;
			logger.info("Setting parameter " + index + ": thedate >= " + startDate);
			stmt.setString(index++, startDate);
			logger.info("Setting parameter " + index + ": thedate >= " + endDate);
			stmt.setString(index++, endDate);
			
			for (AppMsgEntity ame : appMsgEntities) {
				logger.info("Setting parameter " + index + "," + (index + 1) + ": sid => " + ame.getAppSid() + ", adid => " + ame.getAppMsgid());
				stmt.setString(index++, ame.getAppSid());
				stmt.setString(index++, ame.getAppMsgid());
			}
			
			logger.info("Executing statement.");			
			rset = stmt.executeQuery();
			// Assembly Results
			List<AppMsgEventStatEntity> amesEntities = new ArrayList<AppMsgEventStatEntity>();
			while (rset.next()) {
				AppMsgEventStatEntity amesEntity = new AppMsgEventStatEntity();
				amesEntity.setSid(rset.getString(1));
				amesEntity.setAdid(rset.getString(2));
				amesEntity.setEventtype(rset.getString(3));
				amesEntity.setCnt(rset.getLong(4));
				amesEntities.add(amesEntity);			
			}		
			
			return amesEntities;
			
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

	@Override
	public List<AppMsgEventStatEntity> getAppMsgEventStatForExpiredAppMsg(String sid, String adId, String startDate, String endDate) throws SQLException{
		
		if (sid == null || adId == null) {
			return null;
		}
		
		try {
			logger.info("Getting connection from pool.");
			conn = hiveDataSource.getConnection();
			logger.info("Preparing statement.");
			String sql = "select eventtype, count(distinct deviceid) as distCount from appfeedback where sid = ? and adid = ? and thedate >= ? and thedate <= ? group by eventtype";
			logger.info("HiveQL before setting parameters: " + sql);
			stmt = conn.prepareStatement(sql);
			int index = 1;
			logger.info("Setting parameter " + index + ": sid => " + sid);
			stmt.setString(index++, sid);
			logger.info("Setting parameter " + index + ": adid => " + adId);
			stmt.setString(index++, adId);			
			logger.info("Setting parameter " + index + ": thedate >= " + startDate);
			stmt.setString(index++, startDate);
			logger.info("Setting parameter " + index + ": thedate >= " + endDate);
			stmt.setString(index++, endDate);
			logger.info("Executing statement.");		
			rset = stmt.executeQuery();
			// Assembly Results
			List<AppMsgEventStatEntity> amesEntities = new ArrayList<AppMsgEventStatEntity>();
			while (rset.next()) {
				AppMsgEventStatEntity ames = new AppMsgEventStatEntity();
				ames.setSid(sid);
				ames.setAdid(adId);
				ames.setEventtype(rset.getString(1));
				ames.setCnt(rset.getLong(2));
				amesEntities.add(ames);
			}
			return amesEntities;
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
