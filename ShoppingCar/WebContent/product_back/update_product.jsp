<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ʒ��Ϣ�޸�</title>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
	function PreView(imgFile){			/* ������ʾҪ�ϴ���ͼƬ */
		//alert(document.getElementById("imgPreview"));	
		/* var obj = document.getElementById("imgPreview");
		obj.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale)";												
		obj.filters.item("DXImageTransform.Microsoft.AlphaImageLoader").src = imgFile.value; */
		
		var img = document.getElementById("photo");
		img.src = imgFile.value;		
	}														
</script>
<script language = "javascript" type = "text/javascript"  src = "${pageContext.request.contextPath}/js/validate_product.js"></script>
</head>
<body>

<form action = "${pageContext.request.contextPath }/Shopping_Car_Back_Servlet?status=update_Product" method = "post" onsubmit="return validate(this)" enctype="multipart/form-data">
<center>
	<h2>�޸���Ʒ��Ϣ��</h2>

<c:if test="${pro != null}" var = "demo">
	<table border = "1" width = "100%" cellpadding = "5" cellspacing = "0" bgcolor ="F2F2F2" >
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td colspan = "4"><font size = "5">�޸���Ʒ��Ϣ</font></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��Ʒ��ţ�</font></td>
			<td>${pro.pid}</td>
			<td><span id = "pid_msg" ><font color = "RED">*</font></span></td>
			
			<td rowspan = "5" width = "20%">	
				<div id = "imgPreview" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);width:220px;height:200px;">
				<img alt="picture" name = "photo" id = "photo" src="${pageContext.request.contextPath}/productimg/${pro.photo}">
				</div>
			</td>
			
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��Ʒ���ƣ�</font></td>
			<td><input name = "name" id = "name" value ="${pro.name }" onblur="validateName(this)"></td>
			<td><span id = "name_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��Ʒ�۸�</font></td>
			<td><input name = "price" id ="price" value = "${pro.price }" onblur="validatePrice(this)"></td>
			<td><span id = "price_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
			<td><font size = "2">��Ʒ������</font></td>
			<td><input name = "amount" id = "amount" value = "${pro.amount }" onblur="validateAmount(this)"></td>
			<td><span id = "amount_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>��ƷͼƬ��</td>
			<td><input type = "file" name = "photo"  onchange="PreView(this);"/></td>
			<td><span id = "photo_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td colspan = "4">��Ʒ��飺</td>
		</tr>
		<tr>
			<td colspan = "4">
			<div class = "editor">
				<textarea id = "note" name = "note" style="width:650px;height:200px;">
					${pro.note }
				</textarea>
				</div></td>
		</tr>
		<tr onMouseOver = "changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">			
			<td colspan = "4" ><input type ="submit" value = "ȷ���޸�" />
			<input type = "hidden" value = "${pro.pid }" name = "pid">
			<input type = "hidden" value = "<%=request.getParameter("cp")%>" name = "cp">
			<input type = "hidden" value = "<%=request.getParameter("ls")%>" name = "ls">
			<input type = "hidden" value = "<%=request.getParameter("pg")%>" name = "pg">
			<input type = "reset" value = "ȡ���޸�"></td>
			
		</tr>	
</table>
</c:if>

</center>
</form>
</body>
</html>