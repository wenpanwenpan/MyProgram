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
<title>新增药品信息页面</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
	$(function(){	
		$("#ddate").blur(function(){
			var value = $(this).val();
			value = value.trim(); 
			
			var reg = /^\d{4}\-\d{2}\-\d{2}$/;
			if(!reg.test(value)){
				$("#msg").text("格式错误");
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
	<!-- 进行表单提交出错后的错误消息回显 -->
	<div style="background: url('picture/bg1.jpg'); width: 895px; height: 400px">
	<h2>新增药品信息</h2>
	<form:form action="${pageContext.request.contextPath}/adddrug" method="post" modelAttribute="drug">
			<!-- path属性对应HTML表单标签的name属性值 -->
			药品编号:<form:input path="did"/><form:errors path="did"></form:errors>
			<br>

		药品名称:<form:input path="dname"/><form:errors path="dname"></form:errors>
		<br>
		
		生产地址:<form:input path="daddress"/><form:errors path="daddress"></form:errors>
		<br>
		
		过期日期:<form:input path="ddate" id="ddate" placeholder="日期格式：yyyy-MM-dd" /><span id="msg"></span><form:errors path="ddate"></form:errors>
		<br>
		
		药品等级:<form:input path="dgrade"/><form:errors path="dgrade"></form:errors>
		<br>
		
		适用人群:<form:input path="dperson"/><form:errors path="dperson"></form:errors>
		<br>
		
		生产厂家：<form:select path="provider.pid" items="${providers}"
		itemLabel="pname" itemValue="pid"></form:select>
		<br>
		
		药品描述:<form:textarea path="dnote"/>
		<br>
		
		<input type="submit" value="确认添加"> 
		<input type="reset" value="取消添加">
	</form:form>
	</div>
</center>	
</body>
</html>