<%@page import="org.wp.dao.IProductDAO"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@page import="org.wp.vo.*,org.wp.factory.*"%>
<%@page import="java.util.*"%>
<%@taglib prefix = "c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>��Ʒ�б�</title>
</head>
<script language = "javascript">
	function changeColor(obj,color){
		obj.bgColor = color;
	}
	function update(){
		window.location = "${pageContext.request.contextPath }/Member_Servlet?status=member_updatepre";
	}
	function exit(){
		window.location = "${pageContext.request.contextPath }/AdminLogoutServlet";
	}
</script>
<body>
<%!
	public static final String URL = "product_list.jsp";
%>
<%
	int currentPage = 1;			//Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�һҳ
	int lineSize = 5;				//	ÿҳ��ʾ������
	long allRecorders = 0 ; 		//	��ʾȫ���ļ�¼��
	String keyword = request.getParameter("kw"); //���ղ�ѯ�ؼ���
%>
<%
	try{
		currentPage = Integer.parseInt(request.getParameter("cp"));			//ȡ�õ�ǰ��ҳ��
	}catch(Exception e){}
	try{
		lineSize = Integer.parseInt(request.getParameter("ls"));			//ȡ��ÿҳ��ʾ������
	}catch(Exception e){}
	try{
		allRecorders = Long.parseLong(request.getParameter("allRecorders"));		//ȡ��ȫ���Ĳ�ѯ��¼
	}catch(Exception e){}
	if(keyword == null){
		keyword = "";
	}	
%>
<%
	IProductDAO dao = DAOFactory.getIProductDAOInstance();					//��Ʒ������
	List<Product> all = dao.findAll(keyword, currentPage, lineSize);		//ȡ�����е���Ʒ��Ϣ
	pageContext.setAttribute("all", all);				//���浽page���Է�Χ��������foreach��ǩʹ��
	allRecorders = dao.getAllCount(keyword);								//��Ʒ�ļ�¼����
%>
<center>
	<h1>��Ʒ�б�</h1>
	<h2><a href = "${pageContext.request.contextPath}/Shopping_Car_Back_Servlet?status=add_Productpre" target = "_ablank">��������Ʒ</a></h2>
<!-- �����ҳ��������Ҵ�����ز��� -->
<jsp:include page = "split_page_plugin.jsp">
	<jsp:param name = "allRecorders" value = "<%=allRecorders %>"></jsp:param>
	<jsp:param name = "url" value = "<%=URL%>"></jsp:param>
	<jsp:param name = "currentPage" value = "<%=currentPage%>"></jsp:param>
	<jsp:param name = "lineSize" value = "<%=lineSize%>"></jsp:param>
</jsp:include>

<table border = "1" width = "100%" bgcolor = "F2F2F2" cellpadding = "5" cellspacing="0">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>���</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>����</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>�۸�</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>����</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>���</td>
		<td align = "center" valign = "middle"><span class = "STYLE10"></span>������</td>
		<td align = "center" valign = "middle" colspan = "2"><span class = "STYLE10"></span>����</td>
	</tr>
	
<c:forEach items="${all}" var="pro">
	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td>${pro.pid}</td>
		<td><a href = "${pageContext.request.contextPath}/Shopping_Car_Back_Servlet?pid=${pro.pid}&status=show">${pro.name}</a></td>
		<td>${pro.price}</td>
		<td>${pro.amount}</td>
		<td>${pro.note == null?" ":pro.note}</td>	
		<td>${pro.count}</td>
		<td><a href = "${pageContext.request.contextPath }/Shopping_Car_Back_Servlet?status=updatepre_Product&pid=${pro.pid}&pic=${pro.photo}" target="_ablank">�޸�</a></td>
		<td><a href = "${pageContext.request.contextPath}/Shopping_Car_Back_Servlet?pid=${pro.pid}&pic=${pro.photo}&status=delete_Product" target="_ablank">ɾ��</a></td>
	</tr>
</c:forEach>

	<tr onMouseOver="changeColor(this,'white')" onMouseOut = "changeColor(this,'F2F2F2')">
		<td colspan = "8">
		 <input type = "button" value = "�޸ĵ�¼��Ϣ" onclick = "update()">
		 <input type = "button" value = "�˳���¼����" onclick = "exit()">
		</td>	

	</tr>
</table>
</center>
</body>
</html>