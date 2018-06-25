<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix="c"  uri = "http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>结算页面</title>
<style type="text/css">
<!--
.STYLE6(font-size:12px)
.STYLE10(font-size:14px; font-weight:bold;)
-->
</style>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
	function update(){
		window.location = "ProductFrontServlet?status=member_updatepre";
	}
</script>
</head>
<body>
<center>
	<h1>结算清单</h1>

<c:if test="${member != null && product != null}" scope = "request" var = "test">
	<table border = "1" width = "100%" cellpadding = "5" cellspacing = "0" bgcolor ="F2F2F2" >
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "3"><font size = "5">用户信息</font> <input type = "button" value = "修改用户信息" onclick = "update()"></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">用户编号：</font></td>
			<td>${member.mid}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">真实姓名：</font></td>
			<td>${member.name}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">联系电话：</font></td>
			<td>${member.telephone}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">用户地址：</font></td>
			<td>${member.address}</td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">邮政编码：</font></td>
			<td>${member.zipcode}</td>
		</tr>	

</table>

<table border = "1" width = "100%" bgcolor = "F2F2F2" cellpadding = "5" cellspacing="0">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td colspan="6"><span class = "STYLE10"><font size = "5">购买的商品</font></span></td>
		
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>编号</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>名称</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>价格</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>数量</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>简介</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>购买数量</td>
	</tr>

<c:forEach items="${product}" var="product">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" >${product.pid}</td>
		<td align = "center" valign = "middle" >${product.name}</td>
		<td align = "center" valign = "middle" >${product.price}</td>
		<td align = "center" valign = "middle" >${product.amount}</td>
		<td align = "center" valign = "middle" >${product.note}</td>		
		<td align = "center" valign = "middle" >${cars[product.pid]}</td>		
	</tr>
</c:forEach>

	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" colspan="6"><span class = "STYLE10"></span>总价钱：${sum} 元</td>
	</tr>

</table>
</c:if>
</center>


</body>
</html>