<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��ʾȫ��ҩƷ</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(function(){
		$(".delete").click(function(){
			var href = $(this).attr("href");		//��ȡ�����ӵ�href
			$("form").attr("action",href).submit();	//���ñ���action��Ȼ���ύ ��post����ת��ΪDELETE
			return false;
		})
	})
	
	function changeColor(ele,color){
		ele.bgColor = color;
	}
</script>
</head>
<body>
	<marquee>��ӭ����ҩƷ��Ϣ����������</marquee>
	<table border="1" cellpadding="10" cellspacing="0" width="1000px" align="center" style="text-align: center;">
		<tr  bgcolor="#F2E5F5">
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="listallprovider">�鿴���й�Ӧ��</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="showdrugout">�鿴����ҩƷ</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="drugoutbydatepre">����ҩƷ����</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="#">�鿴��¼��Ϣ</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="adddrugpre">�����µ�ҩƷ</a></td>
		</tr>
	</table>	
<center>

	<div style="background: url('picture/bg2.jpg'); width: 895px; height: 500px">
		<form action="querybykey" method="post">
		<h2>���ؼ��ֲ�ѯҩƷ��Ϣ</h2>
		<input type="text" name="key" placeholder="�����ѯ�ؼ���"></input>
		<input value="��ѯ" type="submit">
		</form>
		<form action="" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
		</form>
		
		<c:if test="${empty requestScope.drugs }">
			<h3><font color="red">û���κ�ҩƷ��Ϣ��</font></h3>
		</c:if>
		
	<c:if test="${!empty requestScope.drugs }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>ҩƷID</th>
				<th>ҩƷ����</th>
				<th>ҩƷ��������</th>
				<th>������ַ</th>
				<th>ҩƷ�ṩ��</th>
				<th>������Ⱥ</th>
				<th colspan="2">����</th>
			</tr>
		<c:forEach items="${requestScope.drugs}" var="drug">
			<tr>
				<td>${drug.did }</td>
				<td><a href="drugdetail/${drug.did}" target="_ablank">${drug.dname }</a></td>
				<td>${drug.ddate }</td>
				<td>${drug.daddress }</td>
				<td><a href="providerdetail/${drug.provider.pid}"   target="_ablank">${drug.provider.pname }</a></td>
				<td>${drug.dperson}</td>
				<td><a href="drug/${drug.did}">������Ϣ</a></td>
				<!-- ����Ҫ��������תΪDELETE���� -->
				<td><a href="drugout/${drug.did}">����</a></td>
			
			</tr>		
		</c:forEach>
	 
		</table>	
	</c:if>
	</div>
</center>
</body>
</html>