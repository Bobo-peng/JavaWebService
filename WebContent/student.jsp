<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8" import="com.service.entity.*,java.util.*"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>学生信息</title>
</head>
<body>
<style type="text/css">
table.dataintable {
	margin-top:15px;
	border-collapse:collapse;
	border:1px solid #aaa;
	width:100%;
	}

table.dataintable th {
	vertical-align:baseline;
	padding:5px 15px 5px 6px;
	background-color:#3F3F3F;
	border:1px solid #3F3F3F;
	text-align:left;
	color:#fff;
	}

table.dataintable td {
	vertical-align:text-top;
	padding:6px 15px 6px 6px;
	border:1px solid #aaa;
	}

table.dataintable tr:nth-child(odd) {
	background-color:#F5F5F5;
}

table.dataintable tr:nth-child(even) {
	background-color:#fff;
}
</style>
<form action ="StudentServlet" method="get">
输入姓名：<input type="text" name="name"/>
<input type="submit" value="查询" />
</form>
&nbsp;
&nbsp;</div>
<table class="dataintable">
<tr>
  <th>姓名</th>
  <th>语文</th>
  <th>数学</th>
  <th>英语</th>
</tr>

<c:forEach items="${list}" var="stu">  
    <tr>  
        <td><b>${stu.getName()}</b></td>  	
		<td>${stu.getChinese()}</td>  
        <td>${stu.getMath()}</td>   
    	<td>${stu.getEnglish()}</td>  
    </tr>  
</c:forEach> 
</body>
</html>