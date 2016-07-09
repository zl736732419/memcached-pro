package com.zheng.service;

import com.zheng.domain.ServerNode;

public interface MemcachedService {
	public ServerNode lookUp(String key);
	
	public void addServerNode(ServerNode node);
	
	public void deleteServerNode(String serverName);
}
