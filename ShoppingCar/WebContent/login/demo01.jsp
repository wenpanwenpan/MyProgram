<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ļ��ϴ�����</title>
</head>
<body>
<%
	request.setCharacterEncoding("GBK");
%>
	<form action = "demo01_do.jsp" method = "post" enctype = "multipart/form-data">
		������<input type = "text" name = "name"><br>
		��Ƭ��<input type = "file" name = "pic"><br>
		<input type = "submit" value = "�ϴ�">
		<input type = "reset" value = "ȡ��">
	</form>

</body>
</html>