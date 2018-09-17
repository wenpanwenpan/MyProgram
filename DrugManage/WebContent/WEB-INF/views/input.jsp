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
<title>�޸�ҩƷ��Ϣҳ��</title>
</head>
<body>
<center>
<%-- 1.ΪʲôҪʹ��form��ǩ 
	���Ը���Ŀ�������ҳ�棬���ҿ��Ը��ӷ���Ľ��б�ֵ����
	2.ע�⣺
	�ڱ��п���ͨ��modelAttribute����ָ���󶨵�ģ������  
	��û��ָ�������ԣ���Ĭ�ϴ�request������ж�ȡ����Ϊ  command�ı���bean
	���������Ҳ�����ڣ���ᷢ���쳣
	Ҳ����˵�����ύ��ʱ���������Ŀ�귽����Ҫ��һ��bean����bean���б�������Ԫ�ض�Ӧ�����ԣ��Ҹ�bean������Ҫ�����modelAttribute���Զ�Ӧ������һ����
	������Ŀ�귽����û�У������������в��ҿ�����û��������һ��bean����Ϊspringmvc��Ϊ��һ����Ҫ
	���л��Եġ�
--%>
	<!-- ���б��ύ�����Ĵ�����Ϣ���� -->
	<div style="background: url('../../picture/bg4.jpg'); width: 480px; height: 380px;text-align: center;">
	<form:form action="${pageContext.request.contextPath}/drug" method="post" modelAttribute="drug">		

		<c:if test="${drug.did != null}">
			<!-- �����򣬱�����Թ�����did���Ա㵽ʱ���ύ�� -->
			<form:hidden path="did"/>
			<!-- ���ڽ�post����ת��ΪPUT���� -->
			<input type="hidden" name="_method" value="PUT">
		</c:if>
		<h2><font color="red">�޸�ҩƷ��Ϣ</font></h2>
		
		ҩƷ����:<form:input path="dname"/><form:errors path="dname"></form:errors>
		<br>
		
		������ַ:<form:input path="daddress"/>
		<br>
		
		<%-- 
			1.�������͵�ת��
			2.�������͸�ʽ��
			3.����У��
			1��.���У�飿ע�⣿
			��.ʹ��JSRR 303��֤��׼
			��.����hibernate validate ��֤��ܵ�jar��
			��.��springmvc�������ļ������<mvc:annotation-drivern>
			��.��Ҫ��bean����������Ӷ�Ӧ��ע��
			��.��Ŀ�귽��bean���͵�ǰ��(����ε�ǰ��)���@valid
			
			2��.��֤����ת����һ��ҳ�棿
			ע�⣺�ڴ���������У���Ҫ��֤��bean�ʹ����������BandingResult����������Ҫ���ţ�
			3).������Ϣ�����ʾ����ΰѴ�����Ϣ���й��ʻ���
		 --%>
		
		��������:<form:input path="ddate"/><form:errors path="ddate"></form:errors>
		<br>
		
		
		
		ҩƷ�ȼ�:<form:input path="dgrade"/>
		<br>
		
		������Ⱥ:<form:input path="dperson"/>
		<br>
		
		�������ң�<form:select path="provider.pid" items="${providers}"
		itemLabel="pname" itemValue="pid"></form:select>
		<br>
		
		
		ҩƷ����:<form:textarea path="dnote"/>
		<br>
		
		<input type="submit" value="ȷ���޸�"> 
		<input type="reset" value="ȡ���޸�">
	</form:form>
	</div>
</center>	
</body>
</html>