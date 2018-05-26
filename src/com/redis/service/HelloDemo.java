package com.redis.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * redis的连接
 * @author quanwenchao
 * 2018-5-25 14:20:06
 */
public class HelloDemo {

	private static final String ip = "127.0.0.1";
	private static final int port = 6381;
	private static final String pwd = "Cqmyg12321";

	/**
	 * 单实例的测试
	 */
	public static void connect() {
		// 设置ip、port
		Jedis jedis = new Jedis(ip, port); // ip、port
		// 设置密码
		jedis.auth(pwd);

		System.out.println(jedis.ping());
		
		// 设置数据
		jedis.set("addr", "shenzhen1");
		
		// 获取数据
		String addr = jedis.get("addr");
		System.out.println(addr);
		
		// 释放资源
		jedis.close();

	}
	
	
	/**
	 * 连接池方式连接
	 */
	public static void connectPool() {
		
		// 获得连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxTotal(30); // 设置最大连接数
		config.setMaxIdle(10); // 设置最大空闲连接数

		// 创建Jedis的连接池
		JedisPool jedisPool = new JedisPool(config, ip, port, 0, pwd);

		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource(); // 通过连接池获得连接
			jedis.set("date", "saturday"); // 设置数据
			String date = jedis.get("date"); // 获取数据

			System.out.println("date: " + date);
			System.out.println(jedis.keys("*"));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}




	}
	
	public static void main(String[] args) {
		
		 //connect();

		connectPool();
		
		
	}
}
