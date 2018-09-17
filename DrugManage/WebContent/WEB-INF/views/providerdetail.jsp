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
	
	<form:form action="" method="post" modelAttribute="provider">
		<table border="1" cellpadding="10" cellspacing="0" bgcolor="#F2F2F2">
			<tr style="text-align: center;" bgcolor="#4EEE94">
				<td colspan="2" style="font-size: 5">供应商详细信息</td>
			</tr>
			<tr>
				<td>供应商编号：</td>
				<td>${provider.pid}</td>
			</tr>
			<tr>
				<td>供应商名称：</td>
				<td>${provider.pname}</td>
			</tr>
			<tr>
				<td>供货商地址：</td>
				<td>${provider.paddress}</td>
			</tr>
			<tr>
				<td>供货商电话：</td>
				<td>${provider.ptel}</td>
			</tr>
			<tr>
				<td>供货商邮编：</td>
				<td>${provider.pemail}</td>
			</tr>
			<tr>
				<td>企业类型：</td>
				<td>${provider.pgrade}</td>
			</tr>	
		
		</table>
	</form:form>
	
</center>	
</body>
</html>