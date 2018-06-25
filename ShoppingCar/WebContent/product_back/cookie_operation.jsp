<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title></title>
</head>
<body>
<center>
<%
request.setCharacterEncoding("GBK");
%>
	<hr>
	<%
	if(session.getAttribute("id") == null){
		System.out.println();
	%>
	<%!
	boolean flag = false;
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1522:ORCL";
	private static final String DBUSER = "scott";
	private static final String DBPASSWORD = "tiger";
	%>
	<%
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	%>
	<%
	String mid = null;	//取得提交过来的登录信息
	String password = null;
	
	Cookie c[] = request.getCookies();
	for(int x = 0;x < c.length;x++){
		if("mid".equals(c[x].getName())){
			mid = c[x].getValue();
		}
		if("password".equals(c[x].getName())){
			password = c[x].getValue();	
		}
	}

	String sql = "select name from member where mid = ? and password = ?";
	
	%>
	<%
 	Class.forName(DBDRIVER);
	conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, mid);		//设置查询参数
	pstmt.setString(2, password);
	rs = pstmt.executeQuery();
	if(rs.next()){
		//如果用户登录成功了则进行保存cookie
		session.setAttribute("id", mid);
		Cookie c1 = new Cookie("mid",mid);
		Cookie c2 = new Cookie("password",password);
		int saveTime = Integer.parseInt(request.getParameter("savetime"));
		c1.setMaxAge(saveTime);
		c2.setMaxAge(saveTime);		//设置cookie的保存值
		response.addCookie(c1);		//添加cookie
		response.addCookie(c2);
		
		String name = rs.getString(1);	//取得姓名
		session.setAttribute("id", mid);	//保存mid
		session.setAttribute("name", name);
		flag = true;
	}
	conn.close(); 
	%>
<%
	}
%>


	
</center>

</body>
</html>