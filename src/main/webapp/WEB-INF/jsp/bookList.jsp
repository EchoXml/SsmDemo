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
<title>图书列表</title>
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
	function doAppoint(bookId){
		console.log(bookId);
		var url="<%=basePath%>book/ajax/"+bookId+"/appoint.do";
		$.ajax({
			url:url,
			type:'post',
			data:{
			},
			dataType:'json',
			success:function(data){
				console.log(JSON.stringify(data));
				alert(data.data.stateInfo);
				window.location.reload();
			},
			error:function(data){
				console.log(JSON.stringify(data));
			}
		}) 
	}
	
	function doDel(bookId){
		console.log(bookId);
		var url="<%=basePath%>book/ajax/"+bookId+"/del.do";
		$.ajax({
			url:url,
			type:'post',
			data:{
			},
			dataType:'json',
			success:function(data){
				console.log(JSON.stringify(data));
				alert(data.data.states);
				window.location.reload();
			},
			error:function(data){
				console.log(JSON.stringify(data));
			}
		}) 
	}
	//查看借书记录的元素
	var addE=null;
	function showBookRec(bookId,e) {
		var row=$(e).parent().parent();
		var str="<tr><td colspan='2'>学生编号</td><td colspan='2'>借阅日期</td></tr>";
		$.ajax({
			url:'<%=basePath%>appointment/ajax/getAppointsById',
			data:{
				"bookId":bookId,
			},
			type:"post",
			dataType:"json",
			//async: false,
			success:function(data){
				//console.log(JSON.stringify(data));
				if (data.data!=null) {
					$.each(data.data,function(name,v) {
						//console.log(v.studentId+"  "+v.appointTime);
						str=str+"<tr><td colspan='2'>"+v.studentId+"</td><td colspan='2'>"+getMyDate(v.appointTime)+"</td></tr>"
						});
					addE=$(str);
					row.after(addE);
				}else{
					str=str+"<tr><td colspan='4'>无借阅数据！</td></tr>"
					row.after(addE);
				}
				
				
			},
			error:function(data){
				console.log(JSON.stringify(data));
			}
		});
		
	}
	
	function hideBookRec(bookId,e) {
		
		if(addE!=null){
			addE.remove();
		}
		
	}
	
	//获得年月日      得到日期oTime  
    function getMyDate(str){  
        var oDate = new Date(str),  
        oYear = oDate.getFullYear(),  
        oMonth = oDate.getMonth()+1,  
        oDay = oDate.getDate(),  
        oHour = oDate.getHours(),  
        oMin = oDate.getMinutes(),  
        oSen = oDate.getSeconds(),  
        oTime = oYear +'-'+ getzf(oMonth) +'-'+ getzf(oDay) +' '+ getzf(oHour) +':'+ getzf(oMin) +':'+getzf(oSen);//最后拼接时间  
        return oTime;  
    };  
    //补0操作  
    function getzf(num){  
        if(parseInt(num) < 10){  
            num = '0'+num;  
        }  
        return num;  
    }  
</script>
</head>
<body>
	<center>
	<h5 >欢迎您，${loginUser.nickname }&nbsp;<a href="<%=basePath%>user/logout.do">注销</a></h5>
		<table border="1" cellpadding="10" cellspacing="0">
			<caption>图书库存查询</caption>
			<thead>
				<tr bgcolor="gray">
					<td width="100">图书编号</td>
					<td width="100">图书名称</td>
					<td width="200">图书库存</td>
					<td width="200">操作</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${books }" var="b" >
					<tr>
						<td>${b.bookId }</td>
						<td>${b.name }</td>
						<td>${b.number }</td>
						<td><a href="javascript:doAppoint(${b.bookId });">借阅</a>&nbsp;<a href="javascript:doDel(${b.bookId });">删除</a>
						&nbsp;<a onmouseover="javascript:showBookRec(${b.bookId },this);" onmouseout="javascript:hideBookRec(${b.bookId },this);">查看借阅记录</a>
						&nbsp;<a >查看自己借阅记录</a></td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="4"><a href="book/addBook">新增图书馆藏</a></td>
				</tr>
			</tfoot>
		</table>
	</center>
</body>
</html>