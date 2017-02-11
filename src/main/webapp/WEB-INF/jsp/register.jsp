<%@ page language="java" import="java.util.*" pageEncoding="utf-8"  contentType="text/html; charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>用户注册</title>
  </head>
  <script type="text/javascript" src="resources/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
  		/*检验数据格式是否正确  */
		function doRegister(){
			var username=$("input[name='username']").val();
			var password=$("input[name='password']").val();
			var nickname=$("input[name='nickname']").val();
			var eg=/^\w{6,15}$/;
			var msg=$("#msgUsername");
			if(!eg.test(username)){
				msg.text("请输入正确邮箱格式的用户名！").css("color","red").css("font-size","12px");
				return false;
			}
			
			if(password.length<6){
				alert("请输入长度不小于6位的密码！");
				return false;
			}
			$.ajax({
				url:'<%=basePath%>user/ajax/register.do',
				type:'get',
				dataType:'json',
				data:{
					'username':username,
					'password':password,
					'nickname':nickname,
				},
				success:function(data){
					//判断是否注册成功
					if(data.success==true){
						console.info("注册成功");
						location.href="<%=basePath%>user/login";
					}else{
						alert("注册失败！请及时与管理员联系！");	
						console.info("注册失败");
					}
				},
				error:function(data){
					alert("error:"+data);	
				}
			});
			
		}  
  		
  		/*检验用户名是否可以被注册*/
  		function checkUsername() {
  			var username=$("input[name='username']").val();
  			var eg=/^\w{6,15}$/;
			var msg=$("#msgUsername");
			if(!eg.test(username)){
				msg.text("请输入正确邮箱格式的用户名！").css("color","red").css("font-size","12px");
				return false;
			}
			$.ajax({
				url:'<%=basePath%>user/ajax/checkUsername',
				type:'get',
				dataType:'json',
				data:{
					'username':username
				},
				success:function(data){
					//var jsonObj=$.parseJSON(data);
					msg.text(data.msg).css("font-size","12px");
				    if(data.isExist=="true"){
						msg.css("color","red");
					}else{
						msg.css("color","blue");
					}
				},
				error:function(data){
					alert("error"+data);	
				}
			});
		}
  		
  </script>
  <body>
    <center>
			<h2>用户注册</h2>
			<div>用户名：<input name="username" onblur="checkUsername();"/></div><font id="msgUsername"></font>
			<br/>
			<div>密&nbsp;码：<input name="password" /></div>
			<br/>
			<div>昵&nbsp;称：<input name="nickname" /></div>
			<br/>
			<div>已有帐号？点击<a href="user/login">登录</a></div>
			<br/>
			<div><input type="button" value="注册" onclick="doRegister();" />
				&nbsp;&nbsp;
			</div>
	</center>
  </body>
</html>
