<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Insert title here</title>
</head>
<body>

<%
	int currentPage = 1;//Ϊ��ǰ���ڵ�ҳ��Ĭ���ڵ�һҳ
	int lineSize = 5;	//	ÿҳ��ʾ������
	long allRecorders = 0; //	��ʾȫ���ļ�¼��
	long pageSize = 1; 		//��ʾȫ����ҳ����βҳ��
	int lsData[] = {1,3,5,7,9,10,15,20,30,50};
	String keyword = request.getParameter("kw"); //���ܲ�ѯ�ؼ���
	String url = request.getParameter("url");
%>
<%
	try{
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
	}catch(Exception e){}
	try{
		lineSize = Integer.parseInt(request.getParameter("lineSize"));
	}catch(Exception e){}
	try{
		allRecorders = Long.parseLong(request.getParameter("allRecorders"));
		//System.out.println("allreco=========");
	}catch(Exception e){}
	
	if(keyword == null){
		keyword = "";
	}
%>
<%
	pageSize = (allRecorders + lineSize -1)/lineSize;
	if(pageSize == 0){
		pageSize = 1 ;
	}
%>
<script language = "javascript">
	function go(num){
		//alert("--------------");
		document.getElementById("cp").value = num;
		document.spform.submit();
	}
</script>

<form action = "<%=url%>" method = "post" name = "spform">
	�����ѯ�ؼ��֣�<input type = "text" name = "kw" value = "<%=keyword%>" > <input type = "submit" value = "��ѯ"><br>
	<input type = "button" value = "��ҳ" onclick = "go(1)" <%=currentPage == 1? "DISABLED":"" %>>
	<input type = "button" value = "��һҳ" onclick = "go(<%=currentPage - 1%>)" <%=currentPage==1?"DISABLED":""%>>
	<input type = "button" value = "��һҳ" onclick = "go(<%=currentPage + 1%>)" <%=currentPage==pageSize?"DISABLED":""%>>
	<input type = "button" value = "βҳ" onclick = "go(<%=pageSize%>)" <%=currentPage == pageSize?"DISABLED":"" %>>
	��ת����<select name = "selcp" onchange="go(this.value)">
	<%
		for(int x = 1;x <= pageSize;x++){
	%>
		<option value = "<%=x%>"<%=x==currentPage?"selected":"" %>><%=x%></option>
	<%
		}
	%>
	</select>ҳ
	ÿҳ��ʾ<select name = "ls" onchange = "go(1)">
	<%
		for(int x = 0;x < lsData.length;x++){		
	%>
		<option value = "<%=lsData[x]%>" <%=lsData[x]==lineSize?"selected":"" %>><%=lsData[x]%></option>
	<%
		}
	%>
	</select>��
	<input type = "hidden" name = "cp" value = "1" id = "cp">
</form>

</body>
</html>