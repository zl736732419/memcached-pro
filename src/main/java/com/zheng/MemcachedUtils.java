package com.zheng;

import java.util.HashMap;
import java.util.Map;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;
import com.zheng.domain.ServerNode;

public class MemcachedUtils {
	
	public static Map<String, MemCachedClient> map = new HashMap<>();
	
	/**
	 * 获取操作memcached接口
	 * @param node
	 * @return
	 */
	public static MemCachedClient getMemCachedClient(ServerNode node) {
		MemCachedClient client = map.get(node.getName());
		if(client == null) {
			client = connect(node);
			map.put(node.getName(), client);
		}
		
		return client;
	}
	
	/**
	 * 连接指定的memcached缓存服务器
	 * @param node
	 */
	private static MemCachedClient connect(ServerNode node) {
		String poolName = "poolName: " + node.getPort();
		SockIOPool pool = SockIOPool.getInstance(poolName);
		String servers[] = {node.getName()};
		Integer[] weights = {3};
		//设置服务器信息
		pool.setServers(servers);
		pool.setWeights(weights);
	
		//设置初始连接数，最小最大连接数，最大处理时间
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxBusyTime(6*60*60*1000);
		
		//设置主线程睡眠时间
		pool.setMaintSleep(30);
		
		pool.setNagle(false);
		pool.setSocketTO(30);
		pool.setSocketConnectTO(0);
		
		pool.initialize();
		
		MemCachedClient client = new MemCachedClient(poolName);
		
		return client;
	}
	
}
