
	<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath %>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="app/css/main.css">
<title>memcached求余算法与一致性hash算法缓存命中率比较</title>
</head>
<body>

	<div class="main"></div>

</body>

<script src="app/scripts/bower_components/jquery/dist/jquery.min.js"></script>
<script src="app/scripts/lib/echarts/echarts.min.js"></script>
<script src="app/scripts/main.js"></script>

</html>