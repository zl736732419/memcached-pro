package com.zheng;

import java.util.ArrayList;
import java.util.List;

import com.zheng.domain.ServerNode;
import com.zheng.service.MemcachedService;
import com.zheng.service.impl.CRC32Consistent;

public class MemcachedConfig {
	private List<ServerNode> nodes = new ArrayList<>();
//	private MemcachedService exec = new Moder();
	private MemcachedService exec = new CRC32Consistent();

	public void prepareServers() {
		String ip = "localhost";
		
		ServerNode node = null;
		for(int i = 0; i < 5; i++) {
			node = new ServerNode(ip, 11211+i);
			nodes.add(node);
		}
		
		testAddServer();
	}

	private void testAddServer() {
		for (ServerNode server : nodes) {
			exec.addServerNode(server);
		}
	}

	public List<ServerNode> getNodes() {
		return nodes;
	}

	public void setNodes(List<ServerNode> nodes) {
		this.nodes = nodes;
	}

	public MemcachedService getExec() {
		return exec;
	}

	public void setExec(MemcachedService exec) {
		this.exec = exec;
	}

}
