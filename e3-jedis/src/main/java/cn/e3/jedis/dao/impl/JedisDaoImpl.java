package cn.e3.jedis.dao.impl;


import org.springframework.beans.factory.annotation.Autowired;

import cn.e3.jedis.dao.JedisDao;
import redis.clients.jedis.JedisCluster;

public class JedisDaoImpl implements JedisDao {
	
	//注入jedis集群对象
	@Autowired
	private JedisCluster jc;

	@Override
	public String set(String key, String value) {
		// TODO Auto-generated method stub
		String set = jc.set(key, value);
		return set;
	}

	@Override
	public String get(String key) {
		// TODO Auto-generated method stub
		String value = jc.get(key);
		return value;
	}

	@Override
	public Long hset(String key, String field, String value) {
		// TODO Auto-generated method stub
		Long hset = jc.hset(key, field, value);
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		// TODO Auto-generated method stub
		String value = jc.hget(key, field);
		return value;
	}

	@Override
	public Long hdel(String key, String field) {
		// TODO Auto-generated method stub
		Long hdel = jc.hdel(key, field);
		return hdel;
	}

	@Override
	public Long expire(String key, int seconds) {
		// TODO Auto-generated method stub
		Long expire = jc.expire(key, seconds);
		return expire;
	}

	@Override
	public Long ttl(String key) {
		// TODO Auto-generated method stub
		Long ttl = jc.ttl(key);
		return ttl;
	}

}
