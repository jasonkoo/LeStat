package com.lenovo.push.marketing.lestat.db.redis.util;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPool;

public class ShardedJedisPoolBuilder extends JedisPoolConfig{
	private String jedisShards;

	public String getJedisShards() {
		return jedisShards;
	}

	public void setJedisShards(String jedisShards) {
		this.jedisShards = jedisShards;
	}
	
	public ShardedJedisPool build(){
		if(jedisShards==null){
			throw new IllegalArgumentException("please set 'jedisShards' field before build");
		}
		String[] jedisConnStrArray = jedisShards.split(",");
		List<JedisShardInfo> shardInfoList = new ArrayList<JedisShardInfo>();
		for(String jedisConnStr:jedisConnStrArray){
			jedisConnStr = jedisConnStr.trim();
			int port = 6379;
			String host = jedisConnStr;
			int portPos = jedisConnStr.lastIndexOf(':');
			if(portPos>1){
				host = jedisConnStr.substring(0,portPos);
				port = Integer.parseInt(jedisConnStr.substring(portPos+1));
			}
			shardInfoList.add(new JedisShardInfo(host, port));
		}
		return new ShardedJedisPool(this, shardInfoList);
	}
}
