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
<title>�޸�ҩƷ��Ϣҳ��</title>
</head>
<body>
<center>
	<!-- ���б��ύ�����Ĵ�����Ϣ���� -->
	
	<form:form action="" method="post" modelAttribute="drug">
		<table border="1" cellpadding="10" cellspacing="0" bgcolor="#F2F2F2">
			<tr style="text-align: center;" bgcolor="#4EEE94">
				<td colspan="2" style="font-size: 5">ҩƷ��ϸ��Ϣ</td>
			</tr>
			<tr>
				<td>ҩƷ��ţ�</td>
				<td>${drug.did}</td>
			</tr>
			<tr>
				<td>ҩƷ���ƣ�</td>
				<td>${drug.dname}</td>
			</tr>
			<tr>
				<td>ҩƷ��ַ��</td>
				<td>${drug.daddress}</td>
			</tr>
			<tr>
				<td>�������ڣ�</td>
				<td>${drug.ddate}</td>
			</tr>
			<tr>
				<td>ҩƷ�ȼ���</td>
				<td>${drug.dgrade}</td>
			</tr>
			<tr>
				<td>ʹ����Ⱥ��</td>
				<td>${drug.dperson}</td>
			</tr>
			<tr>
				<td>ҩƷ������</td>
				<td>${drug.dnote}</td>
			</tr>		
		
		</table>
	</form:form>
	
</center>	
</body>
</html>