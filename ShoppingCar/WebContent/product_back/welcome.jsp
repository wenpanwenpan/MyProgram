<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
<script language = "javascript">
	function logout(){
		if(confirm("��ȷ��Ҫ�˳���"));
		top.location = "AdminLogoutServlet";		//���˳������ύ��servlet��ȥ
		return false;
	}
	
</script>
</head>
<body>
	<center>
	<h1>��¼�ɹ���</h1>
	<jsp:include page="cookie_operation.jsp"></jsp:include>
	<h2>��ӭ<font color = "red"><%=session.getAttribute("mid") %></font>���٣�</h2>
	<h2><a href = "product_back/product_list.jsp"><font color = "red">ȥ������ҳ��</font></a></h2>	
	<input type = "button" onclick = "logout()" value = "�˳�">
	</center>
</body>
</html>