<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="resources/js/jquery-1.8.3.min.js"></script>
<script type="text/javascript">
	var s;
	function show(e){
		console.log($(e).parent().html());
		s=$("")
		s=1;
		
	}
	
	function hide(e){
		console.log(s);
	}
</script>
<title>测试</title>
</head>
<body>
	<table>
		<tr>
			<td>第一列</td>
			<td><a onmouseover="javascript:show(this);" onmouseout="javascript:hide(this);">第二列</a></td>
		</tr>
	</table>
</body>
</html>