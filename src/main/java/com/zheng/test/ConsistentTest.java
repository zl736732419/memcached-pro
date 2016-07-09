package com.zheng.test;

import org.junit.Test;

import com.zheng.domain.ServerNode;
import com.zheng.service.impl.AbstractConsistent;
import com.zheng.service.impl.CRC32Consistent;
import com.zheng.service.impl.MessageDigestConsistent;

public class ConsistentTest {

	@Test
	public void test() {
		AbstractConsistent service = new MessageDigestConsistent(100);
		ServerNode node = new ServerNode("localhost", 11211);
		service.addServerNode(node);
		node = new ServerNode("localhost", 11212);
		service.addServerNode(node);
		node = new ServerNode("localhost", 11213);
		service.addServerNode(node);
		System.out.println("节点列表如下：");
		System.out.println(service.getNodes());

		String key = "name";
		long num = service.getHashFun().hash(key);
		ServerNode server = service.lookUp(key);
		System.out.println(key + "hash值为：" + num + ",应该落在服务器：" + server.getName());

	}
}
