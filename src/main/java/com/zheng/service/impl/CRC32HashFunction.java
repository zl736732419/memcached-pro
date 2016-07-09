package com.zheng.service.impl;

import java.nio.charset.Charset;
import java.util.zip.CRC32;

import com.zheng.service.HashFunction;

public class CRC32HashFunction implements HashFunction {

	@Override
	public long hash(String key) {
		CRC32 crc32 = new CRC32();
		crc32.update(key.getBytes(Charset.forName("UTF-8")));
		return crc32.getValue();
	}

}
