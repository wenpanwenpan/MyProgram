<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>登录页面</title>
</head>
<body>
<center>
	<div style="align-content: center;background-color:#D6D5B7;width: 900px;text-align: center;width: 500px;height: 300px">
	<form action="listdrugs" method="get">
		<h3>用户登录</h3>
		<p>在这里你可以进行登 操作：</p>
		<p><font color="red">若登录成功，你可以进行相关的药品操作</font></p>
		<p><font color="red">若登录失败，请保证输入正确并重新登录</font></p>		
		
		用户名称：<input type="text" name="username"><br>
		用户密码：<input type="password" name="password"><br>
		
		<a href="${request.getContextPath}/DrugManage/listdrugs">登录</a>
		<a href="#">取消</a>
		
	</form>

	</div>
	
	
</center>
</body>
</html>