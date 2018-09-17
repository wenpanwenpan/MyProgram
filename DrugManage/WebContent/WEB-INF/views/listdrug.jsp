<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>显示全部药品</title>
<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
<script type="text/javascript" >
	$(function(){
		$(".delete").click(function(){
			var href = $(this).attr("href");		//获取超链接的href
			$("form").attr("action",href).submit();	//设置表单的action，然后提交 将post请求转换为DELETE
			return false;
		})
	})
	
	function changeColor(ele,color){
		ele.bgColor = color;
	}
</script>
</head>
<body>
	<marquee>欢迎来到药品信息具体管理界面</marquee>
	<table border="1" cellpadding="10" cellspacing="0" width="1000px" align="center" style="text-align: center;">
		<tr  bgcolor="#F2E5F5">
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="listallprovider">查看所有供应商</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="showdrugout">查看出库药品</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="drugoutbydatepre">过期药品出库</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="#">查看登录信息</a></td>
			<td onMouseOver="changeColor(this,'#F5F6F6')" onMouseOut = "changeColor(this,'#F2E5F5')"><a href="adddrugpre">增加新的药品</a></td>
		</tr>
	</table>	
<center>

	<div style="background: url('picture/bg2.jpg'); width: 895px; height: 500px">
		<form action="querybykey" method="post">
		<h2>按关键字查询药品信息</h2>
		<input type="text" name="key" placeholder="输入查询关键字"></input>
		<input value="查询" type="submit">
		</form>
		<form action="" method="post">
			<input type="hidden" name="_method" value="DELETE"/>
		</form>
		
		<c:if test="${empty requestScope.drugs }">
			<h3><font color="red">没有任何药品信息！</font></h3>
		</c:if>
		
	<c:if test="${!empty requestScope.drugs }">
		<table border="1" cellpadding="10" cellspacing="0">
			<tr>
				<th>药品ID</th>
				<th>药品名称</th>
				<th>药品过期日期</th>
				<th>出厂地址</th>
				<th>药品提供商</th>
				<th>适用人群</th>
				<th colspan="2">操作</th>
			</tr>
		<c:forEach items="${requestScope.drugs}" var="drug">
			<tr>
				<td>${drug.did }</td>
				<td><a href="drugdetail/${drug.did}" target="_ablank">${drug.dname }</a></td>
				<td>${drug.ddate }</td>
				<td>${drug.daddress }</td>
				<td><a href="providerdetail/${drug.provider.pid}"   target="_ablank">${drug.provider.pname }</a></td>
				<td>${drug.dperson}</td>
				<td><a href="drug/${drug.did}">更改信息</a></td>
				<!-- 必须要将超链接转为DELETE请求 -->
				<td><a href="drugout/${drug.did}">出库</a></td>
			
			</tr>		
		</c:forEach>
	 
		</table>	
	</c:if>
	</div>
</center>
</body>
</html>