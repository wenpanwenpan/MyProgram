<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��ʾȫ����Ӧ��</title>
</head>
<body>
	<marquee>��ӭ����ҩƷ��Ӧ����Ϣ����������</marquee>	
<center>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE"/>
	</form>
	
	<div style="align-content: center;background-color:#F2F2F2;width: 895px;text-align: center;">

	
	<c:if test="${empty requestScope.providers }">
		û���κι�Ӧ����Ϣ��
	</c:if>
	<c:if test="${!empty requestScope.providers }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>��Ӧ�̱��</th>
				<th>��Ӧ������</th>
				<th>��Ӧ�̵�ַ</th>
				<th>��Ӧ�̵绰</th>
				<th>��Ӧ������</th>
				<th>��Ӧ�̵ȼ�</th>
				<th colspan="2">����</th>
			</tr>
		<c:forEach items="${requestScope.providers}" var="provider">
			<tr>
				<td>${provider.pid }</td>
				<td>${provider.pname}</td>
				<td>${provider.paddress }</td>
				<td>${provider.ptel }</td>
				<td>${provider.pemail }</td>
				<td>${provider.pgrade}</td>
				<td><a href="#">������Ϣ</a></td>
				<!-- ����Ҫ��������תΪDELETE���� -->
				<td><a href="#">ɾ��</a></td>
			</tr>		
		</c:forEach>
		</table>
		<a href="listdrugs">����ҳ��</a>	
	</c:if>
	
	</div>
</center>
	
</body>
</html>