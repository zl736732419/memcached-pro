package com.zheng.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zheng.MemcachedConfig;
import com.zheng.MemcachedUtils;
import com.zheng.domain.ServerNode;

public class MemcachedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MemcachedConfig config = new MemcachedConfig();
		config.prepareServers();
		
		Map<String, Map<String, String>> stats = null;
		int totalGets = 0;
		int totalHits = 0;
		for(ServerNode node : config.getNodes()) {
			stats = MemcachedUtils.getMemCachedClient(node).stats();
			totalGets += Integer.parseInt(stats.get(node.getName()).get("cmd_get").trim());
			totalHits += Integer.parseInt(stats.get(node.getName()).get("get_hits").trim());
		}
		
		float rate = 1f;
		if(totalGets != 0 && totalHits != 0) {
			rate = totalHits / (float)totalGets;
		}
		
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(rate+"");
		response.getWriter().close();
	}
	
}
