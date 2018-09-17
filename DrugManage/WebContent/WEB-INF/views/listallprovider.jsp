<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>显示全部供应商</title>
</head>
<body>
	<marquee>欢迎来到药品供应商信息具体管理界面</marquee>	
<center>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<div style="align-content: center;background-color:#F2F2F2;width: 895px;text-align: center;">

	
	<c:if test="${empty requestScope.providers }">
		没有任何供应商信息！
	</c:if>
	<c:if test="${!empty requestScope.providers }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>供应商编号</th>
				<th>供应商名称</th>
				<th>供应商地址</th>
				<th>供应商电话</th>
				<th>供应商邮箱</th>
				<th>供应商等级</th>
				<th colspan="2">操作</th>
			</tr>
		<c:forEach items="${requestScope.providers}" var="provider">
			<tr>
				<td>${provider.pid }</td>
				<td>${provider.pname}</td>
				<td>${provider.paddress }</td>
				<td>${provider.ptel }</td>
				<td>${provider.pemail }</td>
				<td>${provider.pgrade}</td>
				<td><a href="#">更改信息</a></td>
				<!-- 必须要将超链接转为DELETE请求 -->
				<td><a href="#">删除</a></td>
			</tr>		
		</c:forEach>
		</table>
		<a href="listdrugs">回首页！</a>	
	</c:if>
	
	</div>
</center>
	
</body>
</html>