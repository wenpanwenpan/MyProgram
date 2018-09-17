<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>����ҩƷ��Ϣҳ��</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){	
		$("#ddate").blur(function(){
			var value = $(this).val();
			value = value.trim(); 
			
			var reg = /^\d{4}\-\d{2}\-\d{2}$/;
			if(!reg.test(value)){
				$("#msg").text("��ʽ����");
				$("#ddate").focus();
				$("#ddate").select();
				}else{
					$("#msg").text("");
				}
			})
		})

</script>
</head>
<body>
<center>
	<!-- ���б��ύ�����Ĵ�����Ϣ���� -->
	<div style="background: url('picture/bg1.jpg'); width: 895px; height: 400px">
	<h2>����ҩƷ��Ϣ</h2>
	<form:form action="${pageContext.request.contextPath}/adddrug" method="post" modelAttribute="drug">
			<!-- path���Զ�ӦHTML����ǩ��name����ֵ -->
			ҩƷ���:<form:input path="did"/><form:errors path="did"></form:errors>
			<br>

		ҩƷ����:<form:input path="dname"/><form:errors path="dname"></form:errors>
		<br>
		
		������ַ:<form:input path="daddress"/><form:errors path="daddress"></form:errors>
		<br>
		
		��������:<form:input path="ddate" id="ddate" placeholder="���ڸ�ʽ��yyyy-MM-dd" /><span id="msg"></span><form:errors path="ddate"></form:errors>
		<br>
		
		ҩƷ�ȼ�:<form:input path="dgrade"/><form:errors path="dgrade"></form:errors>
		<br>
		
		������Ⱥ:<form:input path="dperson"/><form:errors path="dperson"></form:errors>
		<br>
		
		�������ң�<form:select path="provider.pid" items="${providers}"
		itemLabel="pname" itemValue="pid"></form:select>
		<br>
		
		ҩƷ����:<form:textarea path="dnote"/>
		<br>
		
		<input type="submit" value="ȷ�����"> 
		<input type="reset" value="ȡ�����">
	</form:form>
	</div>
</center>	
</body>
</html>