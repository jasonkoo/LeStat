package com.alimama.mdrill.jdbc;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;


public class MdrillStatement implements java.sql.Statement{
	private String strurl;
	  private int fetchSize = 50;

	public MdrillStatement(String strurl) {
		this.strurl = strurl;
	}
	/**
	   * We need to keep a reference to the result set to support the following:
	   * <code>
	   * statement.execute(String sql);
	   * statement.getResultSet();
	   * </code>.
	   */
	  private ResultSet resultSet = null;

	  /**
	   * The maximum number of rows this statement should return (0 => all rows).
	   */
	  private int maxRows = 0;

	  /**
	   * Add SQLWarnings to the warningChain if needed.
	   */
	  private SQLWarning warningChain = null;

	  /**
	   * Keep state so we can fail certain calls made after close().
	   */
	  private boolean isClosed = false;



	  public void addBatch(String sql) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void cancel() throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void clearBatch() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public void clearWarnings() throws SQLException {
	    warningChain = null;
	  }

	  public void close() throws SQLException {
	    this.strurl = null;
	    resultSet = null;
	    isClosed = true;
	  }


	  public boolean execute(String sql) throws SQLException {

		  String lowercase=sql.toLowerCase();
		    if(lowercase.indexOf("insert")>=0&&lowercase.indexOf("into")>0 && lowercase.indexOf("values")>0)
		    {
		    	InsertParser parser=new InsertParser();
		    	try {
		    		parser.parse(sql);
					HttpClient httpclient = new DefaultHttpClient();
					httpclient.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000*30);
					 httpclient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF8");
					HttpPost httppost = new HttpPost("http://" + this.strurl+ "/higo/insert.jsp");
					List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
					nameValuePairs.add(new BasicNameValuePair("project",parser.tablename));
					nameValuePairs.add(new BasicNameValuePair("json", parser.jsons));

					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs,"UTF-8"));

					HttpResponse response = httpclient.execute(httppost);

					InputStream is = response.getEntity().getContent();
					BufferedInputStream bis = new BufferedInputStream(is);
					ByteArrayBuffer baf = new ByteArrayBuffer(1024);

					int current = 0;
					while ((current = bis.read()) != -1) {
						baf.append((byte) current);
					}
							
		    	return true;
		    	}catch(Throwable e){
		    		throw new SQLException(e); 
		    	}
		    }
		  
	    ResultSet rs = executeQuery(sql);
	    return rs != null;
	  }

	  public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	
	  public boolean execute(String sql, int[] columnIndexes) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	

	  public boolean execute(String sql, String[] columnNames) throws SQLException {
	    throw new SQLException("Method not supported");
	  }



	  public int[] executeBatch() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	

	  public ResultSet executeQuery(String sql) throws SQLException {
	    if (isClosed) {
	      throw new SQLException("Can't execute after statement has been closed");
	    }
	    
	   
	    

	    SqlParser parse=new SqlParser();
	    Long total=0l;
	    
	    StringBuffer debug=new StringBuffer();
	   
	    List<List<Object>> results=new ArrayList<List<Object>>();
	     MdrillRequest request=new MdrillRequest(parse, this.strurl);
	    try {
	    	this.clearWarnings();
	      resultSet = null;
	      parse.parse(sql);
	      debug.append(parse.toString());
		    debug.append("\n");
	      total=request.request(results);
	      if(total<0)
	      {
	    	  throw new Exception("server exception");
	      }
	    } catch (Exception ex) {
	    	debug.append(request.content);
	      throw new SQLException(ex.toString()+"\n"+debug.toString(), "08S01");
	    }
	    resultSet = new MdrillQueryResultSet(parse,results,total);
	    resultSet.setFetchSize(fetchSize);
	    return resultSet;
	  }


	  public int executeUpdate(String sql) throws SQLException {
		  throw new SQLException("Method not supported");
	  }


	  public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int executeUpdate(String sql, String[] columnNames) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public Connection getConnection() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getFetchDirection() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getFetchSize() throws SQLException {
	    return fetchSize;
	  }

	  public ResultSet getGeneratedKeys() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getMaxFieldSize() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getMaxRows() throws SQLException {
	    return maxRows;
	  }

	  public boolean getMoreResults() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public boolean getMoreResults(int current) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getQueryTimeout() throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public ResultSet getResultSet() throws SQLException {
	    return resultSet;
	  }


	  public int getResultSetConcurrency() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getResultSetHoldability() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getResultSetType() throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public int getUpdateCount() throws SQLException {
	    return 0;
	  }

	  public SQLWarning getWarnings() throws SQLException {
	    return warningChain;
	  }


	  public boolean isClosed() throws SQLException {
	    return isClosed;
	  }


	  public boolean isPoolable() throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void setCursorName(String name) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void setEscapeProcessing(boolean enable) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void setFetchDirection(int direction) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	  public void setFetchSize(int rows) throws SQLException {
	    fetchSize = rows;
	  }


	  public void setMaxFieldSize(int max) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public void setMaxRows(int max) throws SQLException {
	    if (max < 0) {
	      throw new SQLException("max must be >= 0");
	    }
	    maxRows = max;
	  }

	  public void setPoolable(boolean poolable) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public void setQueryTimeout(int seconds) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public boolean isWrapperFor(Class<?> iface) throws SQLException {
	    throw new SQLException("Method not supported");
	  }

	  public <T> T unwrap(Class<T> iface) throws SQLException {
	    throw new SQLException("Method not supported");
	  }


	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
