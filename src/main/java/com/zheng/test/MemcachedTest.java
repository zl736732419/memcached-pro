package com.zheng.test;

import java.util.Map;

import org.junit.Test;

import com.whalin.MemCached.MemCachedClient;
import com.zheng.MemcachedUtils;
import com.zheng.domain.ServerNode;
import com.zheng.service.HashFunction;
import com.zheng.service.impl.CRC32HashFunction;

public class MemcachedTest extends BaseTest {

	/**
	 * 测试连接服务器并填入数据,这里只是测试是否连接成功并能成功插入数据到缓存服务器
	 */
	@Deprecated
	public void initData() {
		MemCachedClient client = null;
		for(int i = 0; i < config.getNodes().size(); i++) {
			client = MemcachedUtils.getMemCachedClient(config.getNodes().get(i));
			for(int j = 0; j < 100; j++) {
				client.add("key" + j, "value" + j);
			}
		}
	}
	
	
	/**
	 * 将3000个值均分到各个服务器中
	 * 
	 */
	@Test
	public void initEachServerData() {
		String key = null;
		String value = null;
		
		ServerNode server = null;
		for(int i = 0; i < 3000; i++) {
			key = "key" + i;
			value = "value" + i;
			server = config.getExec().lookUp(key);
			MemcachedUtils.getMemCachedClient(server).add(key, value);
		}
		
		System.out.println("添加值完毕!");
		
	}
	
	@Test
	public void testStats() {
		System.out.println("各服务器状态为:");
		for(ServerNode node : config.getNodes()) {
			Map<String, Map<String, String>> map = MemcachedUtils.getMemCachedClient(node).stats();
			System.out.println(map);
		}
	}
	
	
	@Test
	public void deleteGet() {
		config.getExec().deleteServerNode(config.getNodes().get(2).getName());
		String key = null;
		ServerNode node = null;
		Object value = null;
		for(int k = 0; k < 2; k++) { //一共取两次
			for(int i = 0; i < 3000; i++) {
				key = "key" + i;
				node = config.getExec().lookUp(key);
				value = MemcachedUtils.getMemCachedClient(node).get(key);
				if(null == value) {
					MemcachedUtils.getMemCachedClient(node).add(key, "value" + i);
				}
			}
			
		}
		
	}
	
	@Test
	public void test(){
		HashFunction hashFun = new CRC32HashFunction();
		System.out.println(hashFun.hash("key0"));
		System.out.println(hashFun.hash("key0"));
	}
	
}
