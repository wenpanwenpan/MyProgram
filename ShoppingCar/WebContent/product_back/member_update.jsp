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
		window.location = "${pageContext.request.contextPath }/product_back/product_list.jsp";
	}
</script>
</head>
<body>

<form action = "${pageContext.request.contextPath}/Member_Servlet?status=member_update" method = "post">
<center>
	<h2>�޸��û���Ϣ��</h2>
	<table border = "1" width = "100%" cellpadding = "5" cellspacing = "0" bgcolor ="F2F2F2" >
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "3"><font size = "5">�޸��û���Ϣ</font></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�û���ţ�</font></td>
			<td>${mem.mid}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��ʵ������</font></td>
			<td><input name = "name" value = "${mem.name}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��ϵ�绰��</font></td>
			<td><input name = "telphone" value = "${mem.telephone}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�û���ַ��</font></td>
			<td><input name = "address" value = "${mem.address}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">�������룺</font></td>
			<td> <input name = "zipcode" value = "${mem.zipcode}"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "2" ><input type = "submit" value = "ȷ���޸�">
			<input type = "reset" value = "ȡ���޸�" onclick = "back()"></td>
		</tr>
	
</table>		
</center>
</form>
</body>
</html>