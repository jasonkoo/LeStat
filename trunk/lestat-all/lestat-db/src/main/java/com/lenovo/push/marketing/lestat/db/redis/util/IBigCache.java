package com.lenovo.push.marketing.lestat.db.redis.util;

import redis.clients.jedis.ShardedJedisPool;

public interface IBigCache {

	final CacheKey KEY_APP_FEEDBACK = new CacheKey("S.APP.FEEDBACK.");
	final CacheKey KEY_SUS_FEEDBACK = new CacheKey("S.SUS.FEEDBACK.");

	public ShardedJedisPool getShardedJedisPool();

	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool);
}