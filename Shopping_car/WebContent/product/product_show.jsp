<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ʒ�б�</title>
</head>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
</script>
<body>
<center>
<%
	pageContext.setAttribute("pro", request.getAttribute("pro"));		//����ȥ��
%>
<table border = "1" width = "100%" bgcolor = "F2F2F2" cellpadding = "5" cellspacing="0">	
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" rowspan = "7" width="20%"><span class = "STYLE10"></span>
			<img alt="picture" name = "photo" id = "photo" src="${pageContext.request.contextPath}/productimg/${pro.photo}">
		</td>			
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" ><span class = "STYLE10"></span>����</td>	
		<td align = "center" valign = "middle" ><span class = "STYLE10">${pro.name}
		<a href = "${pageContext.request.contextPath }/ProductBackServlet?pid=${pro.pid}&status=add_cars" target="_ablank">���ӵ����ﳵ</a></span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" ><span class = "STYLE10"></span>�۸�</td>	
		<td align = "center" valign = "middle"><span class = "STYLE10">${pro.price}</span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" ><span class = "STYLE10"></span>����</td>
		<td align = "center" valign = "middle" ><span class = "STYLE10">${pro.amount}</span></td>			
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" ><span class = "STYLE10"></span>������</td>	
		<td align = "center" valign = "middle"><span class = "STYLE10">${pro.count}</span></td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle" colspan = "2"><span class = "STYLE10"></span>���</td>
	</tr>
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td colspan = "2"><span class = "STYLE10">${pro.note}</span></td>
	</tr>
</table>
</center>
</body>
</html>