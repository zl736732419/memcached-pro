package com.zheng.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.zheng.domain.ServerNode;
import com.zheng.service.HashFunction;
import com.zheng.service.MemcachedService;

/**
 * 求余算法
 * @author zhenglian
 */
public class Moder implements MemcachedService {
	
	private List<ServerNode> nodes = new ArrayList<>();
	private HashFunction hashFun = new CRC32HashFunction();
	
	@Override
	public ServerNode lookUp(String key) {
		long num = hashFun.hash(key);
		int index = (int) (num % nodes.size());
		return nodes.get(index);
	}

	@Override
	public void addServerNode(ServerNode node) {
		if(nodes.contains(node)) {
			return;
		}
		
		nodes.add(node);
	}

	@Override
	public void deleteServerNode(String serverName) {
		for(ServerNode node : nodes) {
			if(node.getName().equals(serverName)) {
				nodes.remove(node);
				break;
			}
		}
	}

	
}
