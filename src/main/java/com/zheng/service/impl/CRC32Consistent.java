package com.zheng.service.impl;

import com.zheng.domain.ServerNode;

/**
 *	crc32实现一致性hash算法 
 * @author zhenglian
 *
 */
public class CRC32Consistent extends AbstractConsistent {
	public CRC32Consistent() {
		this(100);
	}
	
	public CRC32Consistent(int virtualNodeNum) {
		super(new CRC32HashFunction(), virtualNodeNum);
	}
	
	@Override
	public void addServerNode(ServerNode node) {
		String virtualNodeName = null;
		long num = -1L;
		for(int i = 0; i < virtualNodeNums; i++) {
			virtualNodeName = node.getName() + "-" + (i+1);
			num = this.hashFun.hash(virtualNodeName);
			nodes.put(num, node);
		}
	}

}
