package com.zheng.service.impl;

import java.util.UUID;

import com.zheng.domain.ServerNode;
import com.zheng.service.HashFunction;

public class MessageDigestConsistent extends AbstractConsistent {
	public MessageDigestConsistent(int virtualNodeNums){
		super(new MessageDigestHashFunction(MessageDigestHashFunction.ALGORITHM_MD5), virtualNodeNums);
	}
	public MessageDigestConsistent(HashFunction hashFun, int virtualNodeNums) {
		this(virtualNodeNums);
	}

	@Override
	public void addServerNode(ServerNode node) {
		/*
		 * 这里将messagedigest返回的16字节转换成4个Long数，生成对应的四个虚拟节点
		 */
		try {
			String virtualName = node.getName();
			byte[] bytes = null;
			long num = -1L;
			for (int i = 0; i < virtualNodeNums / 4; i++) { // 一次性生成4个虚拟节点
				virtualName += UUID.randomUUID();
				bytes = ((MessageDigestHashFunction)hashFun).getEncrptyBytes(virtualName);
				// 生成4个虚拟节点

				for (int h = 0; h < 4; h++) {
					num = (long) (bytes[3 + 4 * h] << 24) | (long) (bytes[2 + 4 * h] << 16)
							| (long) (bytes[1 + 4 * h] << 8) | (long) (bytes[4 * h] & 0xff);

					nodes.put(num, node);
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
