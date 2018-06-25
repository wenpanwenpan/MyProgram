<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册页面</title>
<script>
	function changeColor(ele,color){
		ele.bgColor = color;
	}
</script>
<script language = "javascript" src = "<%=request.getContextPath() %>/js/validate_login.js"></script>
</head>
<body>
<%
request.setCharacterEncoding("GBK");
response.setHeader("Content-type", "text/html;charset=GBK");
%>
<center>
<form action = "${pageContext.request.contextPath }/Member_Servlet?status=register" method = "post" onsubmit="return validate(this)" onsubmit = "return validate(this)">
	<table border = "1" cellpadding = "5" cellspacing = "0" bgcolor = "F2F2F2" width = "100%">
		<tr>
			<td colspan = "3">
				<h1>新用户注册 </h1>
			</td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td width = "25%">登录ID：</td>
			<td width = "35%"><input type = "text" name = "mid" onBlur="validateMid(this.value)"/></td>
			<td width = "25%"><span id = "mid_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>登录密码：</td>
			<td><input type = "password" name = "password" id = "password" onBlur="validatePassword(this.value) "/></td>
			<td><span id = "password_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>确认密码：</td>
			<td><input type = "password" name = "conf" id = "conf" onBlur="validateConf(this.value) "/></td>
			<td><span id = "conf_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>注册姓名：</td>
			<td><input type = "text" name = "name" id = "name" onBlur="validateName(this.value) "/></td>
			<td><span id = "name_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>常住地址：</td>
			<td><input type = "text" name = "address" id = "address" onBlur="validateAddress(this.value) "/></td>
			<td><span id = "address_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>联系电话：</td>
			<td><input type = "text" name = "telephone" id = "telephone" onBlur="validateTelephone(this.value) "/></td>
			<td><span id = "telephone_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>本地邮编：</td>
			<td><input type = "text" name = "zipcode" id = "zipcode" onBlur="validateZipcode(this.value) "/></td>
			<td><span id = "zipcode_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr>
			<td  colspan = "3"><input type = "submit" value = "一键注册"></input><input type = "reset" value = "取消注册"></td>
		</tr>
		<tr>
			<td  colspan = "3">
				<h3><font color = "RED">${msg}</font></h3>
			</td>
		</tr>
		
	</table>

</form>
</center>
</body>
</html>