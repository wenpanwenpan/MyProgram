<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Ա����Ϣ����</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web·����
����/��ʼ�����·��������Դ���Ե�ǰ��Դ��·��Ϊ��׼���������׳����⡣
��/��ʼ�����·��������Դ���Է�������·��Ϊ��׼(http://localhost:3306)����Ҫ������Ŀ��
		http://localhost:3306/crud
 -->
<script type="text/javascript"
	src="${APP_PATH }/static/js/jquery-1.12.4.min.js"></script>
<link
	href="${APP_PATH }/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="${APP_PATH }/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body>

	<!-- ���ʾҳ�� -->
	<div class="container">
		<!-- ���� -->
		<div class="row">
			<div class="col-md-12">
				<h1>Ա����Ϣ</h1>
			</div>
		</div>
		
		<!-- ��ť -->
		<div class="row">
			<div class="col-md-4 col-md-offset-10">
				<button class="btn btn-success">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				����
				</button>
				<button class="btn btn-danger">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				ɾ��
				</button>
			</div>
		</div>
		
		<!-- ��ʾ������� -->
		<div class="row" style="background-color: #F2F2F2">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>Ա�����</th>
						<th>Ա������</th>
						<th>Ա���Ա�</th>
						<th>Ա������</th>
						<th>��������</th>
						<th>����</th>
					</tr>
					<c:forEach items="${pageInfo.list }" var="emp">
						<tr>
							<th>${emp.empId }</th>
							<th>${emp.empName }</th>
							<th>${emp.gender == "M"?"��":"Ů" }</th>
							<th>${emp.email }</th>
							<th>${emp.department.deptName }</th>
							<th>
								<button class="btn btn-success btn btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
									�޸�
								</button>
								<button class="btn btn-danger btn btn btn-primary btn-xs">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
									ɾ��
								</button>
							</th>
						</tr>	
						
					</c:forEach>
							
				</table>
			</div>
		</div>
		
		
		<!-- ��ʾ��ҳ��Ϣ -->
		<div class="row">
			<!-- ��ҳ������Ϣ -->
			<div class="col-md-6">
				<h6>��ǰ�� ${pageInfo.pageNum } ҳ,�ܹ� ${pageInfo.pages} ҳ,�� ${pageInfo.total } ������</h>
			</div>
			
			<!-- ��ҳ����Ϣ -->
			<div class="col-md-6">
				<nav aria-label="Page navigation">
				  <ul class="pagination">
				    <li><a href="${APP_PATH }/emps?pn=1">��ҳ</a></li>
				    <c:if test="${pageInfo.hasPreviousPage }">
				    	<li>
				      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum -1}" aria-label="Previous">
				        <span aria-hidden="true">&laquo;</span>
				      </a>
				    </li>
				    </c:if>
				    <!-- ʹ��navigatepageNumsȡ��Ҫ��ʾ��ҳ�� -->
				    <c:forEach items="${pageInfo.navigatepageNums }" var="page_Num">
				    	<c:if test="${page_Num == pageInfo.pageNum }">
				    		<li class="active"><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
				    	</c:if>
				    	<c:if test="${page_Num != pageInfo.pageNum }">
				    		<li ><a href="${APP_PATH }/emps?pn=${page_Num }">${page_Num }</a></li>
				    	</c:if>
				    	
				    	
				    	
				    </c:forEach>

					<c:if test="${pageInfo.hasNextPage }">
						<li>
					      <a href="${APP_PATH }/emps?pn=${pageInfo.pageNum + 1}" aria-label="Next">
					        <span aria-hidden="true">&raquo;</span>
					      </a>
				   	   </li>
					</c:if>
				    <li><a href="${APP_PATH }/emps?pn=${pageInfo.pages}">βҳ</a></li>
				  </ul>
				</nav>
			</div>
			
		
		</div>
	
	
	
	
	
	
	
	
	
	
	</div>
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


</body>
</html>