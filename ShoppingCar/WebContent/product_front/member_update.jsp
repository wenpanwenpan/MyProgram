<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@page import="org.wp.vo.*,org.wp.factory.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�û���Ϣ�޸�</title>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
	function back(){			//ȡ���޸�
		window.location = "${pageContext.request.contextPath }/ProductFrontServlet?status=order_cars";
	}
</script>
</head>
<body>
<form action = "${pageContext.request.contextPath}/ProductFrontServlet?status=member_update" method = "post">
<center>
	<h2>�޸��û���Ϣ��</h2>

<%
	pageContext.setAttribute("member", request.getAttribute("member"));		//��ʵ��δ���Ҳ���Բ�Ҫ
%>
	<table border = "1" width = "100%" cellpadding = "5" cellspacing = "0" bgcolor ="F2F2F2" >
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "3"><font size = "5">�޸��û���Ϣ</font></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�û���ţ�</font></td>
			<td>${member.mid}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��ʵ������</font></td>
			<td><input name = "name" value = "${member.name}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��ϵ�绰��</font></td>
			<td><input name = "telphone" value = "${member.telephone}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�û���ַ��</font></td>
			<td><input name = "address" value = "${member.address}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�������룺</font></td>
			<td> <input name = "zipcode" value = "${member.zipcode}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "2" >
			<input type = "submit" value = "ȷ���޸�">
			<input type = "reset" value = "ȡ���޸�" onclick="back()"></td>
		</tr>	
</table>	
</center>
</form>
</body>
</html>