<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>管理员登录</title>
</head>
<body>
	<center>
	<h1>后台登录程序</h1>
	<hr>
	<form action = "${pageContext.request.contextPath}/AdminLoginServlet" method = "post">
		用户&nbsp;ID：<input type = "text" name = "mid"><br>
		密&nbsp;&nbsp;&nbsp;&nbsp;码:<input type = "password" name = "password"><br>
		保存密码：<select name = "savetime">
			<option value = <%=0 %>>不保存</option>
			<option value = <%=3600*24 %>>保存一天</option>
			<option value = <%=3600*24*30 %>>保存一月</option>
			<option value = <%=3600*24*30*365 %>>保存一年</option>
		</select><br>
		<input type = "submit" value = "登录">
		<input type = "reset" value ="重置"> <br>
		
		
		<%		//输出登陆的出错信息
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