<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����Ա��¼</title>
</head>
<body>
	<center>
	<h1>��̨��¼����</h1>
	<hr>
	<form action = "${pageContext.request.contextPath}/AdminLoginServlet" method = "post">
		�û�&nbsp;ID��<input type = "text" name = "mid"><br>
		��&nbsp;&nbsp;&nbsp;&nbsp;��:<input type = "password" name = "password"><br>
		�������룺<select name = "savetime">
			<option value = <%=0 %>>������</option>
			<option value = <%=3600*24 %>>����һ��</option>
			<option value = <%=3600*24*30 %>>����һ��</option>
			<option value = <%=3600*24*30*365 %>>����һ��</option>
		</select><br>
		<input type = "submit" value = "��¼">
		<input type = "reset" value ="����"> <br>
		
		
		<%		//�����½�ĳ�����Ϣ
			List<String> all = (List<String>)request.getAttribute("errors");
			if(all != null){
				Iterator<String> iter = all.iterator();	
			while(iter.hasNext()){
		%>
				<li><%=iter.next() %></li>
		<%
		}
}
		%>
	</form>
	</center>	
</body>
</html>