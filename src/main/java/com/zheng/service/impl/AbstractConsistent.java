package com.zheng.service.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import com.zheng.domain.ServerNode;
import com.zheng.service.HashFunction;
import com.zheng.service.MemcachedService;

public abstract class AbstractConsistent implements MemcachedService {
	protected int virtualNodeNums = 100; // 每一个服务器对应的虚拟节点数
	protected HashFunction hashFun = new CRC32HashFunction();
	protected SortedMap<Long, ServerNode> nodes = new TreeMap<>();

	public AbstractConsistent(HashFunction hashFun, int virtualNodeNums) {
		this.hashFun = hashFun;
		this.virtualNodeNums = virtualNodeNums;
	}

	public int getVirtualNodeNums() {
		return virtualNodeNums;
	}

	public void setVirtualNodeNums(int virtualNodeNums) {
		this.virtualNodeNums = virtualNodeNums;
	}

	public SortedMap<Long, ServerNode> getNodes() {
		return nodes;
	}

	public void setNodes(SortedMap<Long, ServerNode> nodes) {
		this.nodes = nodes;
	}

	public HashFunction getHashFun() {
		return hashFun;
	}

	public void setHashFun(HashFunction hashFun) {
		this.hashFun = hashFun;
	}

	@Override
	public ServerNode lookUp(String key) {
		long num = hashFun.hash(key);
		ServerNode node = null;
		for (Map.Entry<Long, ServerNode> entry : nodes.entrySet()) {
			if (node == null) {
				node = entry.getValue();
			}

			if (num <= entry.getKey().longValue()) {
				node = entry.getValue();
				break;
			}
		}

		return node;
	}

	@Override
	public void deleteServerNode(String serverName) {
		Iterator<Map.Entry<Long, ServerNode>> iterator = nodes.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Long, ServerNode> entry = iterator.next();
			if (entry.getValue().getName().equals(serverName)) {
				iterator.remove();
			}
		}
	}
	
}
