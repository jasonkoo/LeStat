package com.lenovo.push.marketing.lestat.db.redis.util;

import java.nio.charset.Charset;

public class CacheKey {
	
	private static final Charset UTF8 = Charset.forName("UTF-8");
	
	private String prfix;
	private int expireSends = -1;
	public CacheKey(String prfix, int expireSends) {
		this.prfix = prfix;
		this.expireSends = expireSends;
	}
	
	public CacheKey(String prfix) {
		super();
		this.prfix = prfix;
	}

	public void setPrfix(String prfix) {
		this.prfix = prfix;
	}
	public int getExpireSends() {
		return expireSends;
	}

	public void setExpireSends(int expireSends) {
		this.expireSends = expireSends;
	}

	public String getKey(String ... keyArgs){
		String key = prfix;
		if(keyArgs!=null && keyArgs.length>0){
			for(int i=0;i<keyArgs.length;i++){
				if(i==0){
					key += keyArgs[i];
				}else{
					key += ("."  + keyArgs[i]);
				}
			}
		}
		return key;
	}
	public byte[] getBinaryKey(String ... keyArgs){
		return getKey(keyArgs).getBytes(UTF8);
	}
	
}
