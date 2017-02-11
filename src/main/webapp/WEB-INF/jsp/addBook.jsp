<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书馆藏新增</title>
<style>
	a{
		text-decoration: none;
		color:black;
	}
	
	a:hover{
		text-decoration:underline;
	}
</style>
 <script type="text/javascript" src="resources/js/jquery-1.8.3.min.js"></script>
  <script type="text/javascript">
  		function doAddBook(){
  			var name=$("input[name='name']").val();
  			var number=$("input[name='number']").val();
  			
  			if(name==""||number==""){
  				alert("数据不可为空！");
  				return false;
  			}
  			$.ajax({
  				url:'<%=basePath%>book/ajax/addBook.do',
  				data:{
  					'name':name,
  					'number':number,
  				},
  				dataType:"json",
  				type:'get',
  				success:function(data){
  					var jsonStr = JSON.stringify(data);
  					console.log(jsonStr);
  					location.href="<%=basePath%>"+data.data;
  					//json是对象，直接使用“.”连接符读取
  				
  				},
  				error:function(data){
  					console.info(data.success+"  "+data.error);
  					alert("失败！");
  				}
  			});
  			
  		}
  </script>
</head>
<body>
	<center>
	<h5 >欢迎您，${loginUser.nickname }&nbsp;<a href="<%=basePath%>user/logout.do">注销</a></h5>
		<table border="1" cellpadding="10" cellspacing="0">
			<caption>图书馆藏新增</caption>
			<tr>
				<td>图书名称：</td>
				<td><input name="name" /></td>
			</tr>
			<tr>
				<td>图书数量：</td>
				<td><input name="number" type="number" /></td>
			</tr>
		</table>
		<button onclick="doAddBook();">新增图书</button>
		<a href="javascript:history.go(-1);">返回</a>
	</center>
</body>
</html>