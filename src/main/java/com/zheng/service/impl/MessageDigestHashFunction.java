package com.zheng.service.impl;

import java.nio.charset.Charset;
import java.security.MessageDigest;

import com.zheng.service.HashFunction;

public class MessageDigestHashFunction implements HashFunction {

	public static final String ALGORITHM_MD5 = "MD5";
	public static final String ALGORITHM_SHA = "SHA";

	private String algorithm;

	public MessageDigestHashFunction(String algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public long hash(String key) {
		long num = -1L;
		try {
			byte[] bytes = getEncrptyBytes(key);
			num = bytes[3] << 24 | ((long) bytes[2] << 16) | ((long) bytes[1] << 8) | (long) (bytes[0] & 0xff);
			// 选择前4个字节生成2^32数字
		} catch (Exception e) {
			e.printStackTrace();
		}

		return num;
	}

	public byte[] getEncrptyBytes(String key) throws Exception {
		MessageDigest md = MessageDigest.getInstance(algorithm);

		md.update(key.getBytes(Charset.forName("UTF-8")));
		byte[] bytes = md.digest();
		return bytes;
	}

	public String getAlgorithm() {
		return algorithm;
	}

	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

}
