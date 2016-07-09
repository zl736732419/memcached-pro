package com.zheng.test;

import org.junit.Before;

import com.zheng.MemcachedConfig;

public abstract class BaseTest {
	protected MemcachedConfig config = null;
	
	@Before
	public void prepareServers() {
		config = new MemcachedConfig();
		config.prepareServers();
	}
	
}
