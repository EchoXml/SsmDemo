<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>用户登录</title>
</head>
<body>
	<center>
		登录成功！
	</center>
	<h2>欢迎你,${loginUser.username }</h2>
	session:${sessionScope.loginUser.username }<br/>
	request:${requestScope.loginUser.username }
</body>
</html>
