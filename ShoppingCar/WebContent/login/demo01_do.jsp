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
<title>文件上传测试</title>
</head>
<body>
<%request.setCharacterEncoding("GBK"); %>
<%
	SmartUpload smart = new SmartUpload();			//实例化SmartUpload上传组件
	smart.initialize(pageContext);					//初始化上传操作
	smart.upload();									//上传准备
%>
<%
	String fileName = null;
	String filePath = null;
	if(smart.getFiles().getSize() > 0){		//有文件上传
		IPTimeStamp its = new IPTimeStamp("192.168.1.1");
		fileName = its.getIPTimeRand() + "." + smart.getFiles().getFile(0).getFileExt();		//拼凑文件名称
		
		filePath = this.getServletContext().getRealPath("/") + "productimg" + 
		File.separator + fileName;		//设置上传的文件路径
		
		if(smart.getFiles().getSize() > 0){
			//System.out.println(fileName);
			smart.getFiles().getFile(0).saveAs(filePath);		//文件上传到指定的文件夹中
		}
	}

%>


<img alt="测试图片" src="../productimg/${fileName} }">

</body>
</html>