<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ʒ��Ϣ����</title>
<script>
	function changeColor(ele,color){
		ele.bgColor = color;
	}
	function PreView(imgFile){			/* ������ʾҪ�ϴ���ͼƬ */
		var img = document.getElementById("photo");
		img.src = imgFile.value;		
	}			
</script>
<script language = "javascript" type = "text/javascript"  src = "${pageContext.request.contextPath}/js/validate_product.js"></script>
</head>
<body>
<center>
<form action = "${pageContext.request.contextPath}/Shopping_Car_Back_Servlet?status=add_Product" method = "post" onsubmit="return validate(this)" enctype="multipart/form-data">
	<table border = "1" cellpadding = "5" cellspacing = "0" bgcolor = "F2F2F2" width = "100%">
		<tr>
			<td colspan = "4">
				<h1>��������Ʒ </h1>
			</td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')" >
			<td width = "20%">��Ʒ���ƣ�</td>
			<td width = "25%"><input type = "text" name = "name" id = "name" onblur="validateName(this)"/></td>
			<td width = "25%"><span id = "name_msg" ><font color = "RED">*</font></span></td>
			
			<td rowspan = "4">
			<div id = "imgPreview"
			style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale);width:220px;height:200px">
				<img alt="picture" name = "photo" id = "photo" src="${pageContext.request.contextPath}/productimg/nophoto.jpg">
			</div>
			</td>
			
		</tr>
		
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>��Ʒ�۸�</td>
			<td><input type = "text" name = "price" onblur="validatePrice(this)"/></td>
			<td><span id = "price_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>��Ʒ������</td>
			<td><input type = "text" name = "amount" onblur="validateAmount(this)"/></td>
			<td><span id = "amount_msg" ><font color = "RED">*</font></span></td>
		</tr>
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td>��ƷͼƬ��</td>
			<td><input type = "file" id = "photo"name = "photo" onchange="PreView(this);"/></td>
			<td><span id = "photo_msg" ><font color = "RED">*</font></span></td>
		</tr>		
		<tr onMouseOver="changeColor(this,'white')" onMouseOut="changeColor(this,'F2F2F2')">
			<td colspan = "5">��Ʒ��飺</td>
		</tr>
		<tr>
			<td colspan = "4">
			<div class = "editor">
				<textarea id = "note" name = "note" style="width:650px;height:200px;">	
				</textarea>
				</div></td>
		</tr>	
		<tr>
			<td  colspan = "4"><input type = "submit" value = "�ύ" ></input>
			<input type = "reset" value = "����"></td>
		</tr>
		<tr>
			<td  colspan = "4"><a href = "${pageContext.request.contextPath}/product_back/product_list.jsp">����ҳ��</a></td>
		</tr>	
	</table>
</form>
</center>
</body>
</html>