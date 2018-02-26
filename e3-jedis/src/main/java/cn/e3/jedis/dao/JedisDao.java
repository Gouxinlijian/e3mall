package cn.e3.jedis.dao;

public interface JedisDao {
	
	//set
	public String set(String key,String value);
	//get
	public String get(String key);
	//hset
	public Long hset(String key,String filed,String value);
	//hget
	public String hget(String key,String filed);
	//hdel
	public Long hdel(String key,String filed);
	//expire
	public Long expire(String key,int seconds);
	//ttL
	public Long ttl(String key);

}
