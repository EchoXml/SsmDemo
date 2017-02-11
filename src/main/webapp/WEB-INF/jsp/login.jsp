<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script type="text/javascript" src="resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	function doLogin(){
		var username=$("input[name='username']").val();
		var password=$("input[name='password']").val();
		var msg=$("#msg");
		if (username==""||password=="") {
			msg.text("用户名与密码不能为空！");
			return false;
		}
		$.ajax({
			url:'<%=basePath%>user/ajax/login.do',
			type:'post',
			dataType:'text',
			data:{
				'username':username,
				'password':password,
			},
			success:function(data){
				//var jsonObj=$.parseJSON(data);
				msg.css("font-size","12px");
			    if(data=="true"){
					location.href="<%=basePath%>book/list";
				}else{
					msg.text("用户名与密码不匹配").css("color","red");
				}
			},
			error:function(data){
				alert("error"+data);	
			}
		});
	}
</script>
<title>用户登录</title>
</head>
<body>
	<center>
			<h2>用户登录</h2>
			<div>用户名：<input name="username" /></div>
			<br/>
			<div>密&nbsp;码：<input name="password" /></div>
			<br/>
			<div>没有帐号？点击<a href="user/register">注册</a></div>
			<font id="msg"></font>
			<br/>
			<div>
				<input type="button" value="登录" onclick="doLogin();"/>
			</div>
	</center>
</body>
</html>
