<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>修改药品信息页面</title>
</head>
<body>
<center>
	<!-- 进行表单提交出错后的错误消息回显 -->
	
	<form:form action="" method="post" modelAttribute="drug">
		<table border="1" cellpadding="10" cellspacing="0" bgcolor="#F2F2F2">
			<tr style="text-align: center;" bgcolor="#4EEE94">
				<td colspan="2" style="font-size: 5">药品详细信息</td>
			</tr>
			<tr>
				<td>药品编号：</td>
				<td>${drug.did}</td>
			</tr>
			<tr>
				<td>药品名称：</td>
				<td>${drug.dname}</td>
			</tr>
			<tr>
				<td>药品地址：</td>
				<td>${drug.daddress}</td>
			</tr>
			<tr>
				<td>出厂日期：</td>
				<td>${drug.ddate}</td>
			</tr>
			<tr>
				<td>药品等级：</td>
				<td>${drug.dgrade}</td>
			</tr>
			<tr>
				<td>使用人群：</td>
				<td>${drug.dperson}</td>
			</tr>
			<tr>
				<td>药品描述：</td>
				<td>${drug.dnote}</td>
			</tr>		
		
		</table>
	</form:form>
	
</center>	
</body>
</html>