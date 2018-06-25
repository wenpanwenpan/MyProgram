<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<script language = "javascript">
	function logout(){
		if(confirm("您确定要退出吗？"));
		top.location = "AdminLogoutServlet";		//将退出程序提交到servlet上去
		return false;
	}
	
</script>
</head>
<body>
	<center>
	<h1>登录成功！</h1>
	<jsp:include page="cookie_operation.jsp"></jsp:include>
	<h2>欢迎<font color = "red"><%=session.getAttribute("mid") %></font>光临！</h2>
	<h2><a href = "product_back/product_list.jsp"><font color = "red">去购物首页！</font></a></h2>	
	<input type = "button" onclick = "logout()" value = "退出">
	</center>
</body>
</html>