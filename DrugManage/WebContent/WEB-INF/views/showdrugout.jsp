<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����ҩƷ</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(function(){
		$(".delete").click(function(){
			var href = $(this).attr("href");		//��ȡ�����ӵ�href
			$("form").attr("action",href).submit();	//���ñ���action��Ȼ���ύ ��get����ת��Ϊpost
			return false;
		})
	})
</script>
</head>
<body>
	<marquee style="color: red"><h2><font color="green">��ӭ��������ҩƷ��Ϣ����������</font></h2></marquee>	
<center>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	
	<div style="align-content: center;background-color:#D6D5B7;width: 1000px;text-align: center;">
		<form action="doqueryDrugOutByKey" method="post">
		<h2>���ؼ��ֲ�ѯ����ҩƷ</h2>
		<input type="text" name="key" placeholder="�����ѯ�ؼ���"></input>
		<input value="��ѯ" type="submit">
		</form>
		
	<c:if test="${empty requestScope.drugs }">
		<font color="red">��Ǹ��û���κ��Ѿ������ҩƷ��Ϣ��</font>
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
				<th>��������</th>
				<th colspan="2">����</th>
			</tr>
		<c:forEach items="${requestScope.drugs}" var="drug">
			<tr>
				<td>${drug.did }</td>
				<td><a href="drugdetail/${drug.did}" target="_ablank">${drug.dname }</a></td>
				<td>${drug.ddate }</td>
				<td>${drug.daddress }</td>
				<td>����ʾ</td>
				<td>${drug.dperson}</td>
				<td>2018-06-25</td>
				<td><a href="drugRemoveCompelete/${drug.did}" class="delete">����ɾ��</a></td>
				<td><a href="adddrugagin/${drug.did}" class="adddrug">�������</a></td>
			
			</tr>		
		</c:forEach>
		</table>
		<a href="listdrugs">����ҳ��</a>	
	</c:if>	
	</div>

</center>
	
</body>
</html>