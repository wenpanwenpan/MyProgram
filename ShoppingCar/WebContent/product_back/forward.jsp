<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>未登录跳转页面</title>
<script>
	alert("${msg}");
	window.location = "${pageContext.request.contextPath}/login/login.jsp";
</script>
</head>
<body>

</body>
</html>