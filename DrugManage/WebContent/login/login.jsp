<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��¼ҳ��</title>
</head>
<body>
<center>
	<div style="align-content: center;background-color:#D6D5B7;width: 900px;text-align: center;width: 500px;height: 300px">
	<form action="listdrugs" method="get">
		<h3>�û���¼</h3>
		<p>����������Խ��е� ������</p>
		<p><font color="red">����¼�ɹ�������Խ�����ص�ҩƷ����</font></p>
		<p><font color="red">����¼ʧ�ܣ��뱣֤������ȷ�����µ�¼</font></p>		
		
		�û����ƣ�<input type="text" name="username"><br>
		�û����룺<input type="password" name="password"><br>
		
		<a href="${request.getContextPath}/DrugManage/listdrugs">��¼</a>
		<a href="#">ȡ��</a>
		
	</form>

	</div>
	
	
</center>
</body>
</html>