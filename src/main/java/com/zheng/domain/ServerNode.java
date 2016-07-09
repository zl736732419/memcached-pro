package com.zheng.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 服务器实体
 * 
 * @author zhenglian
 *
 */
public class ServerNode {
	private String ip;
	private int port;

	public ServerNode() {
	}

	public ServerNode(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		StringBuilder builder = new StringBuilder();
		builder.append(ip).append(":").append(port);
		return builder.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		if (obj == this) {
			return true;
		}

		if (!(obj instanceof ServerNode)) {
			return false;
		}

		ServerNode other = (ServerNode) obj;

		return new EqualsBuilder().append(this.getIp(), other.getIp()).append(this.getPort(), other.getPort()).build();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("ip", this.ip).append("port", port).build();
	}

}
