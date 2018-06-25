<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>我的购物车</title>
</head>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
	function gocheck(){
		window.location = "ProductFrontServlet?status=order_cars";
	}
</script>
<body>

<center>
	<h1>购物列表</h1>
<form action = "${pageContext.request.contextPath }/ProductFrontServlet?status=update_car" method = "post">
<table border = "1" width = "100%" bgcolor = "F2F2F2" cellpadding = "5" cellspacing="0">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>编号</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>名称</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>价格</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>数量</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>简介</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>购买数量</td>
	</tr>
<c:if test="${all != null }" var = "test" scope="request">
<c:forEach items="${all }" var="pro">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td>${pro.pid}</td>
		<td>${pro.name}</td>
		<td>${pro.price}</td>
		<td>${pro.amount}</td>
		<td>${pro.note}</td>	
		<!-- 显示购买数量 -->
		<td><input type = "text" maxlength = "3" size = "3" name = "${pro.pid}" value = "${cars[pro.pid]}"></td>
	</tr>
</c:forEach>
</c:if>
<c:if test="${all.size()==0 }" var = "test"  scope = "request">
<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" colspan = "6"><span class = "STYLE10"></span>暂时没有购买任何商品</td>
</tr>
</c:if>
<c:if test="${all.size()!=0 }"  var = "test" scope = "request">
<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" colspan = "6"><span class = "STYLE10">
		<input type = "submit" value = "提交修改"> 
		<input type = "button" value = "去结算" onclick = "gocheck()"> </span> </td>
</tr> 
</c:if>

</table>
</form>
</center>
</body>
</html>