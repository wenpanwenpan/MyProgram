<%@page import="java.io.File"%>
<%@page import="org.wp.utils.IPTimeStamp"%>
<%@page import="org.wp.vo.Product"%>
<%@page import="org.wp.factory.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@page import = "org.lxh.smart.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>�ļ��ϴ�����</title>
</head>
<body>
<%request.setCharacterEncoding("GBK"); %>
<%
	SmartUpload smart = new SmartUpload();			//ʵ����SmartUpload�ϴ����
	smart.initialize(pageContext);					//��ʼ���ϴ�����
	smart.upload();									//�ϴ�׼��
%>
<%
	String fileName = null;
	String filePath = null;
	if(smart.getFiles().getSize() > 0){		//���ļ��ϴ�
		IPTimeStamp its = new IPTimeStamp("192.168.1.1");
		fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//ƴ���ļ�����
		
		filePath = this.getServletContext().getRealPath("/") + "productimg" + 
		File.separator + fileName;		//�����ϴ����ļ�·��
		
		if(smart.getFiles().getSize() > 0){
			//System.out.println(fileName);
			smart.getFiles().getFile(0).saveAs(filePath);		//�ļ��ϴ���ָ�����ļ�����
		}
	}

%>


<img alt="����ͼƬ" src="../productimg/${fileName} }">

</body>
</html>