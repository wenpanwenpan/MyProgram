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
<title>修改药品信息页面</title>
</head>
<body>
<center>
<%-- 1.为什么要使用form标签 
	可以更快的开发出表单页面，而且可以更加方便的进行表单值回显
	2.注意：
	在表单中可以通过modelAttribute属性指定绑定的模型属性  
	若没有指定该属性，则默认从request域对象中读取名字为  command的表单的bean
	如果该属性也不存在，则会发生异常
	也就是说当表单提交的时候，在请求的目标方法中要有一个bean，该bean具有表单中所有元素对应的属性，且该bean的名字要与表单中modelAttribute属性对应的名字一样。
	若请求目标方法中没有，则在属性域中查找看看有没有这样的一个bean。因为springmvc认为表单一定是要
	进行回显的。
--%>
	<!-- 进行表单提交出错后的错误消息回显 -->
	<div style="background: url('../../picture/bg4.jpg'); width: 480px; height: 380px;text-align: center;">
	<form:form action="${pageContext.request.contextPath}/drug" method="post" modelAttribute="drug">		

		<c:if test="${drug.did != null}">
			<!-- 隐藏域，保存回显过来的did，以便到时候提交表单 -->
			<form:hidden path="did"/>
			<!-- 用于将post请求转换为PUT请求 -->
			<input type="hidden" name="_method" value="PUT">
		</c:if>
		<h2><font color="red">修改药品信息</font></h2>
		
		药品名称:<form:input path="dname"/><form:errors path="dname"></form:errors>
		<br>
		
		生产地址:<form:input path="daddress"/>
		<br>
		
		<%-- 
			1.数据类型的转换
			2.数据类型格式化
			3.数据校验
			1）.如何校验？注解？
			①.使用JSRR 303验证标准
			②.加入hibernate validate 验证框架的jar包
			③.在springmvc的配置文件中添加<mvc:annotation-drivern>
			④.需要在bean的属性上添加对应的注解
			⑤.在目标方法bean类型的前面(即入参的前面)添加@valid
			
			2）.验证出错转向到哪一个页面？
			注意：在处理方法入参中，需要验证的bean和处理错误结果（BandingResult两个参数需要挨着）
			3).错误消息如何显示，如何把错误消息进行国际化？
		 --%>
		
		出厂日期:<form:input path="ddate"/><form:errors path="ddate"></form:errors>
		<br>
		
		
		
		药品等级:<form:input path="dgrade"/>
		<br>
		
		适用人群:<form:input path="dperson"/>
		<br>
		
		生产厂家：<form:select path="provider.pid" items="${providers}"
		itemLabel="pname" itemValue="pid"></form:select>
		<br>
		
		
		药品描述:<form:textarea path="dnote"/>
		<br>
		
		<input type="submit" value="确认修改"> 
		<input type="reset" value="取消修改">
	</form:form>
	</div>
</center>	
</body>
</html>