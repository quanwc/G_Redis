package com.redis.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 数据类型操作：
 * 		String(字符串)、List(列表)、Keys
 * @author quanwenchao
 * 2018-5-25 17:25:04
 */
public class Demo01 {
	
	private static final String ip = "127.0.0.1";
    private static final int port = 6381;
    private static final String pwd = "Cqmyg12321";
	private static Jedis jedis = null;
	
	static {
		jedis = new Jedis(ip, port);
		jedis.auth(pwd);
	}
	
	
	/**
	 * String(字符串)
	 */
	public static void stringJedis() {
		// 设置 redis 字符串数据
        jedis.set("jedisKey", "https://redis.io/");
        System.out.println(jedis.get("jedisKey"));
	}
	
	/**
	 * List(列表) 
	 */
	public static void listJedis() {
		
		// 存储数据到列表中
		jedis.lpush("user-list", "zs");
		jedis.lpush("user-list", "li");
		jedis.lpush("user-list", "ww");
		jedis.lpush("user-list", "zl");
		
		// 获取存储的数据并输出
		List<String> list = jedis.lrange("user-list", 0, 110);
		for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
	}
	
	
	/**
	 * Keys 
	 */
	public static void keysJedis() {
		
		// 获取数据并输出
		Set<String> keys  = jedis.keys("*");
		Iterator<String> it=keys.iterator() ;
		 while(it.hasNext()){   
            String key = it.next();   
            System.out.println(key);   
        }
	}
	
	public static void main(String[] args) {
		
		// String类型
		 stringJedis();
		
		// List(列表) 
		// listJedis();
		
		// Keys
		// keysJedis();
//		JedisPoolConfig
		
	}
	
}
