package com.lenovo.push.marketing.lestat.db.redis.util;

import java.io.IOException;
import java.io.Serializable;

import org.apache.commons.lang.SerializationUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class BigCache implements IBigCache {

	private static final Logger LOG = Logger.getLogger(BigCache.class);
	
	private ShardedJedisPool shardedJedisPool;
	
	public Object loadObject(byte[] key) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			byte[] objBytes = jedis.get(key);
			if (objBytes != null) {
				return SerializationUtils.deserialize(objBytes);
			}
		} catch (Exception e) {
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
		return null;
	}
	
	public void saveObject(byte[] key,Serializable obj) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			byte[] objectBytes = null;
			if(obj==null){
				objectBytes = new byte[0];
			}else{
				objectBytes = SerializationUtils.serialize(obj);
			}
			jedis.set(key, objectBytes);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	public void incr(String key) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.incr(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	public Long ttl(String key) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			return jedis.ttl(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	public void expire(String key, int seconds) throws IOException{
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.expire(key, seconds);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	public String get(String key) throws IOException{
		String value = null;
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			value = jedis.get(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
			throw new IOException(e);
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
		return value;
	}




	
	public void remove(byte[] key){
		ShardedJedis jedis = shardedJedisPool.getResource();
		try {
			jedis.del(key);
		} catch (Exception e) {
			LOG.warn(e, e);
			if (null != jedis && e instanceof JedisConnectionException) {
				shardedJedisPool.returnBrokenResource(jedis);
				jedis = null;
			}
		} finally {
			if (null != jedis)
				shardedJedisPool.returnResource(jedis);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#getShardedJedisPool()
	 */
	@Override
	public ShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	/* (non-Javadoc)
	 * @see com.lenovo.lps.push.marketing.common.cache.IBigCache#setShardedJedisPool(redis.clients.jedis.ShardedJedisPool)
	 */
	@Override
	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}


	

	

}