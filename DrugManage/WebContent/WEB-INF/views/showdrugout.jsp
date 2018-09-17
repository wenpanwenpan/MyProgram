<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>出库药品</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(function(){
		$(".delete").click(function(){
			var href = $(this).attr("href");		//获取超链接的href
			$("form").attr("action",href).submit();	//设置表单的action，然后提交 将get请求转换为post
			return false;
		})
	})
</script>
</head>
<body>
	<marquee style="color: red"><h2><font color="green">欢迎来到出库药品信息具体管理界面</font></h2></marquee>	
<center>
	<form action="" method="post">
		<input type="hidden" name="_method" value="DELETE">
	</form>
	
	<div style="align-content: center;background-color:#D6D5B7;width: 1000px;text-align: center;">
		<form action="doqueryDrugOutByKey" method="post">
		<h2>按关键字查询出库药品</h2>
		<input type="text" name="key" placeholder="输入查询关键字"></input>
		<input value="查询" type="submit">
		</form>
		
	<c:if test="${empty requestScope.drugs }">
		<font color="red">抱歉！没有任何已经出库的药品信息！</font>
	</c:if>
	
		<c:if test="${!empty requestScope.drugs }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>药品ID</th>
				<th>药品名称</th>
				<th>药品进购日期</th>
				<th>出厂地址</th>
				<th>药品提供商</th>
				<th>适用人群</th>
				<th>出库日期</th>
				<th colspan="2">操作</th>
			</tr>
		<c:forEach items="${requestScope.drugs}" var="drug">
			<tr>
				<td>${drug.did }</td>
				<td><a href="drugdetail/${drug.did}" target="_ablank">${drug.dname }</a></td>
				<td>${drug.ddate }</td>
				<td>${drug.daddress }</td>
				<td>不显示</td>
				<td>${drug.dperson}</td>
				<td>2018-06-25</td>
				<td><a href="drugRemoveCompelete/${drug.did}" class="delete">彻底删除</a></td>
				<td><a href="adddrugagin/${drug.did}" class="adddrug">重新入库</a></td>
			
			</tr>		
		</c:forEach>
		</table>
		<a href="listdrugs">回首页！</a>	
	</c:if>	
	</div>

</center>
	
</body>
</html>