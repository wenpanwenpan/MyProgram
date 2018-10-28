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

<!-- Ա���޸�ģ̬�� -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">Ա���޸�</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="empName_update_input" class="col-sm-3 control-label">Ա��������</label>
				    <div class="col-sm-9">
				      <p class="form-control-static" id="empName_update_static"></p>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email_update_input" class="col-sm-3 control-label">Ա�����䣺</label>
				    <div class="col-sm-9">
				      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@qq.com">
				      <span class="help-block"></span>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="gender_add_input" class="col-sm-3 control-label">Ա���Ա�</label>
				    <div class="col-sm-9">
				     	<label class="radio-inline">
						  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> ��
					  	</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" id="gender2_update_input" value="F"> Ů
						</label>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="dept_update_input" class="col-sm-3 control-label">�������ƣ�</label>
				    <div class="col-sm-4">
				      <!-- �����ύdId���� -->
				      <select class="form-control" name="dId">
						 
					  </select>
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
		        <button type="button" class="btn btn-primary" id="emp_update_btn">�޸�</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- Ա����ӵ�ģ̬�� -->
		<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">����Ա��</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="empName_add_input" class="col-sm-3 control-label">Ա��������</label>
				    <div class="col-sm-9">
				      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="���������������">
				      <span class="help-block"></span>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email_add_input" class="col-sm-3 control-label">Ա�����䣺</label>
				    <div class="col-sm-9">
				      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
				      <span class="help-block"></span>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="gender_add_input" class="col-sm-3 control-label">Ա���Ա�</label>
				    <div class="col-sm-9">
				     	<label class="radio-inline">
						  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> ��
					  	</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" id="gender2_add_input" value="F"> Ů
						</label>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="dept_add_input" class="col-sm-3 control-label">�������ƣ�</label>
				    <div class="col-sm-4">
				      <!-- �����ύdId���� -->
				      <select class="form-control" name="dId">
						 
					  </select>
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">�ر�</button>
		        <button type="button" class="btn btn-primary" id="emp_save_btn">����</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- ********************************************************************************************************** -->
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
				<button id="emp_add_modal_btn" class="btn btn-success" data-toggle="modal">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				����
				</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				ɾ��
				</button>
			</div>
		</div>
		
	
		<!-- ��ʾ������� -->
		<div class="row" style="background-color: #F2F2F2">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>Ա�����</th>
							<th>Ա������</th>
							<th>Ա���Ա�</th>
							<th>Ա������</th>
							<th>��������</th>
							<th>����</th>
						</tr>					
					</thead>
					<tbody></tbody>
							
				</table>
			</div>
		</div>
		
		
		<!-- ��ʾ��ҳ��Ϣ -->
		<div class="row">
			<!-- ��ҳ������Ϣ��������divˮƽ���֣���ռ6�� -->
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<!-- ��ҳ����Ϣ -->
			<div class="col-md-6" id="page_nav_area">
			
			</div>
		</div>
		
	<script type="text/javascript">
<!--*************************************************************************************************-->
	//�ܼ�¼���͵�ǰҳ��
	var totalRecord,currentPage;
	
	$(function(){
		//1.ȥ��ҳ  ҳ���һ�μ��ص�ʱ��Ĭ��ȥ��һҳ
		to_page(1);

		
		});
	
	//1.ҳ���������Ժ�ֱ��ȥ����һ��ajax����Ҫ����ҳ����
		function to_page(pn){
			//�����һ�ε�tbody�е�����
			$("#emps_table tbody").empty();
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn=" + pn,		//���������Ĳ������ڼ�ҳ��
				type:"GET",
				success:function(result){
					//console.log(result);
					//1.��������ʾԱ������
					build_emps_table(result);
					//2.��������ʾ��ҳ��Ϣ
					build_page_info(result);

					//3.��������ʾ��ҳ��
					build_page_nav(result);
						
					}
				});
			}

		//��������ʾԱ������
		function build_emps_table(result){
			//�õ����ص�Ա����Ϣ����
			var emps = result.extend.pageInfo.list;
			//����jQuery��each����ȡ��ÿһ��Ա������Ϣ
			$.each(emps, function(index,item) {
				//ÿһ������ռһ����Ԫ��
				var check_item = $("<td><input type='checkbox' class='check_item' /></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender == 'M'?"��":"Ů");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				var editBtn = $("<button></button>").addClass("btn btn-success btn btn btn-primary btn-xs edit_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("�༭").attr("edit-id",item.empId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn btn btn-primary btn-xs delete_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("ɾ��").attr("del-id",item.empId);

				//������button��ť׷�ӵ�һ����Ԫ����
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				//append����ִ����֮���Ƿ���ԭ����Ԫ�ء������еĵ�Ԫ��׷�ӵ�һ����
				$("<tr></tr>").append(check_item)
					.append(empIdTd)
					.append(empNameTd)
					.append(genderTd)
					.append(emailTd)
					.append(deptNameTd)
					.append(btnTd)
					.appendTo("#emps_table tbody");
			});
			
		}

		//��������ʾ��ҳ��Ϣ
		function build_page_info(result){
			//�����һ�εķ�ҳ��Ϣ
			$("#page_info_area").empty();
			$("#page_info_area").append("��ǰ��" + result.extend.pageInfo.pageNum + "ҳ, ")
			.append("��" + result.extend.pageInfo.pages + "ҳ, ")
			.append("��" + result.extend.pageInfo.total + "������");

			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}

		//��������ʾ��ҳ����Ϣ
		function build_page_nav(result){
			//�����һ�εķ�ҳ����Ϣ
			$("#page_nav_area").empty();
			var firstPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("��ҳ"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
			var nexPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));			
			var lastPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("βҳ"));
	
			//��û����һҳ��һҳʱ���ð�ť
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
				}else{
					//Ϊ��ҳ��ť�󶨵���¼�
					firstPageLi.click(function(){
						to_page(1);
						});
					prePageLi.click(function(){
						to_page(result.extend.pageInfo.pageNum - 1);
						});
					}
			if(result.extend.pageInfo.hasNextPage == false){
				nexPageLi.addClass("disabled");
				lastPageLi.addClass("disabled");
				}else{
					//Ϊ��ҳ��ť�󶨵���¼�
					nexPageLi.click(function(){
						to_page(result.extend.pageInfo.pageNum + 1);
						});
					lastPageLi.click(function(){
						to_page(result.extend.pageInfo.pages);
						});
					}
			
			//����һ��ul������ҳ����һҳ��ӵ�ul��
			var ul = $("<ul></ul>").addClass("pagination").append(firstPageLi)
			.append(prePageLi);
			
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").attr("href","#").append(item));
				if(result.extend.pageInfo.pageNum == item){
					numLi.addClass("active");
					} 
				//�󶨵���¼�
				numLi.click(function(){
					to_page(item);
					});
				ul.append(numLi);
			});

			//����һҳ��βҳ��ӵ�ul��
			ul.append(nexPageLi).append(lastPageLi);

			$("#page_nav_area").append(ul);

			
			
		}
		
/**===============================================================================*/

		function reset_form(ele){
			//���ñ�����
			$(ele)[0].reset();
			//��ձ���ʽ
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
			
			}
		//��������ť�󶨵���¼�����ģ̬��
		$("#emp_add_modal_btn").click(function(){
			//ÿ�ε��������ťʱ�������������
			reset_form("#empAddModal form");
			//�����������ajax���󣬻�ȡ�������б���еĲ�����Ϣ
			getDepts("#empAddModal select");
			//ѡ��Ҫ�򿪵�ģ̬�򣬲��ҵ���modal�����������������
			$("#empAddModal").modal({
				//��������ط�ʱ���ر�ģ̬��
				backdrop:"static",
				keyboard:"true"
				});
			});

		//����Ĳ���Ϊ��ʾ���ŵ������б��
		function getDepts(ele){
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				//����ɹ���Ļص�����
				success:function(result){
					//console.log(result);
					//��̨���ص����� �� {"code":100,"msg":"����ɹ���","extend":{"depts":[{"deptId":1,"deptName":"������"},{"deptId":2,"deptName":"���Բ�"}]}}
					//ÿ�μ��ز���ʱ���Ƚ������б����ԭ�����������
					$(ele).empty();
					var result1=result.extend.depts;
					$.each(result1,function(index,element){
						//�����ӷ������˷��ص�json��ʽ�����ݣ�ȡ��������Ϣ����ӵ��´�����  optionԪ����
						var optionEle=$("<option></option>").append(element.deptName).attr("value",element.deptId);
						//��optionԪ����ӵ�ģ̬���е������б����
						optionEle.appendTo($(ele));
						});
					}
				});
			}

		//�û�����У��
		function validate_add_form(){
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(empName)){
				//���û�����������ʽУ�鲻ͨ����������bootstrap������ʾ��Ϣ
				//show_validate_msg("#empName_add_input","error","�û�����ʽ����");
				return false;
				}else{
				show_validate_msg("#empName_add_input","success","");
					}
			
			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
			if(!regEmail.test(email)){
				show_validate_msg("#email_add_input","error","�����ʽ����");
				return false;
				}else{
					show_validate_msg("#email_add_input","success","");
					}
			
			return true;
			}

		//��֤�û��������䣬��ȡΪһ������
		function show_validate_msg(ele,status,msg){
			//�������ǰԪ���е�һЩ����,�������ǰԪ�ص�У��״̬
			$(ele).parent().removeClass("has-error has-success");
			$(ele).next("span").text("");
			
			if(status == "error"){
				$(ele).parent().addClass("has-error");
				$(ele).next("span").text(msg);
				}else if(status == "success"){
					$(ele).parent().addClass("has-success");
					$(ele).next("span").text(msg);
					}
			}
		
		//������Ա��ģ̬���еı��水ť��ӵ���¼�
		$("#emp_save_btn").click(function(){

			//��֤����������ݸ�ʽ����д��ȷ����У�鲻ͨ������ֱ�ӷ���false�������������ajax������
			/* if(!validate_add_form()){
				return false;
				} */

			//У���û����Ƿ���ã��������ã���ֱ�ӷ���false�����������ajax������
			if($(this).attr("ajax-va") == "error"){
				return false;
				}
			
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				//ͨ��ȡ��ģ̬���еı���Ȼ��������л�������ȡ���е�ֵ����ӵ����������
				data:$("#empAddModal form").serialize(),
				success:function(result){
					//��̨��֤������̨���ص�״̬����100 ����ʾ��̨��JSR303У��ɹ�����ر�ģ̬��
					if(result.code == 100){
						//1.���سɹ��Ժ�Ҫ�ر�ģ̬��,ֱ�ӵ���jQuery��modal��������hide������������ģ̬��
						$("#empAddModal").modal("hide");
						//2.���سɹ��Ժ�Ҫ�Զ���ת�����һҳ����������ʾ
						to_page(totalRecord);
						}else{
						//��ʾʧ����Ϣ�����Ұ�ʧ�ܵ�ԭ����ӵ���Ӧ����������
						if(undefined != result.extend.errorFields.email){
							//��ʾ������Ϣ����
							show_validate_msg("#email_add_input","error","�����ʽ����");
							}
						if(undefined != result.extend.errorFields.empName){
							//��ʾԱ����������
							show_validate_msg("#empName_add_input","error","�û�����ʽ����ȷ��ӦΪ6-16λ��ĸ��2-5λ���ģ�");
							}
						//console.log(result);
							}
					
					
					}
				});
			});

		//������Ա��ʱ��Ա�����������ʧȥ�����ʱ����ajax����
		$("#empName_add_input").change(function(){
			var empName = $("#empName_add_input").val();
			$.ajax({
				url:"${APP_PATH}/checkuser",
				type:"POST",
				data:"empName="+empName,
				success:function(result){
					//ͨ���������˷��ص�״̬�룬���ж��û����Ƿ����
					if(result.code == 100){
						//�����水ť���һ�����ԣ�����������ʱ���ж�����������ж��û����Ƿ���ã��Ӷ������Ƿ�Ҫ�ύ
						$("#emp_save_btn").attr("ajax-va","success");
						show_validate_msg("#empName_add_input","success",result.extend.va_msg);
						}else{
							$("#emp_save_btn").attr("ajax-va","error");
							show_validate_msg("#empName_add_input","error",result.extend.va_msg);
							}
					}
				
				});
			});

		/**
			1.�����ǰ�ť����֮ǰ�Ͱ���click�����԰󶨲���
			2.�����ڴ�����ť��ʱ����¼���  ��.���԰󶨵��.live()
			3.jquery�°�û��live�¼����������ǿ���ʹ��on����

			���ÿһ�� �༭ ��ť��ʱ��򿪱༭ģ̬��
		*/		
		$(document).on("click",".edit_btn",function(){
			//�����һ�εĴ�����ʾ��Ϣ
			$("#email_update_input").parent().removeClass("has-error has-success");
			$("#email_update_input").next("span").text("");
			//1.���Ա����Ϣ��������Ա����Ϣ
			//2.���������Ϣ�������Բ�����Ϣ
			getDepts("#empUpdateModal select");

			//����ajax�����ѯԱ������,��ȡҪ�޸ĵ�Ա��id����ȡ��Ա����Ϣ
			var empId = $(this).attr("edit-id");
			getEmp(empId);

			//�ڵ���޸İ�ťʱ���ѵ�ǰԱ����id���浽�޸İ�ť�����Ե��У��Ա����޸�ʱ���̨���Ͳ���
			$("#emp_update_btn").attr("edit-id",empId);
			
			$("#empUpdateModal").modal({
				//��������ط�ʱ���ر�ģ̬��
				backdrop:"static",
				keyboard:"true"
				});
			});

		//����ajax���󣬻�����ݽ��б�����
		function getEmp(empId){
			$.ajax({
				url:"${APP_PATH}/emp/"+empId,
				type:"GET",
				success:function(result){
					//console.log(result);
					var empEle = result.extend.emp;
					//���ú�̨��ѯ����Ա����Ϣ�������ݣ����б����ݻ���
					$("#empName_update_static").text(empEle.empName);
					$("#email_update_input").val(empEle.email);
					$("#empUpdateModal input[name=gender]").val([empEle.gender]);
					$("#empUpdateModal select").val([empEle.dId]);
					}
					

				});
			}

		//������°�ť������Ա����Ϣ
		$("#emp_update_btn").click(function(){
			//����ǰ�Ƚ�������ĸ�ʽУ�飬У��ͨ�����ٷ�ajax�����޸����ݣ����򲻷�ajax����
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
			if(!regEmail.test(email)){
				show_validate_msg("#email_update_input","error","�����ʽ����");
				return false;
				}else{
					show_validate_msg("#email_update_input","success","");
					}
			//�����ʽУ��ͨ������ʼ�����������ajax����
			$.ajax({
				/**
				1.����ʹ�����·����l��PUT����
				type:"POST",
				data:$("#empUpdateModal form").serialize()+"&_method=PUT",
				ʹ��web.xml�ļ��е�HiddenHttpMethodFilter���������ã���POST����ת����PUT����
				Ҳ����ʹ�����з�����Ajaxֱ�ӷ���PUT����
					��web.xml�����ļ�������HttpPutFormContentFilter��������springmvc���������Լ���д��
					getParameter()��������request��������ȡֵ�������ύ�ı����ݾ����Զ��ķ�װ��employee��������
				*/
				url:"${APP_PATH}/emp/" + $(this).attr("edit-id"),
				type:"PUT",
				//���޸ı���������ݴ�����̨�Ľ��շ����У������޸�Ա����Ϣ
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
				//1.�ر�ģ̬��
				$("#empUpdateModal").modal("hide");
				//2.���ص��޸�ǰ��ҳ��
				to_page(currentPage);
					
				}
				
				})
			});


		//����on����Ϊ���е�class=del_btn��ɾ����ť�󶨵���¼�
		$(document).on("click",".delete_btn",function(){
			var empId = $(this).attr("del-id");
			var empName = $(this).parents("tr").find("td:eq(2)").text();

			if(confirm("ȷ��ɾ��Ա����" +empName+"����")){
				//���������е��ȷ�ϣ�����ajax����ɾ������
				$.ajax({
					url:"${APP_PATH}/emp/"+empId,
					type:"DELETE",
					success:function(result){
						//alert(result.msg);
						//�ص�ɾ��Ա��ʱ��ҳ��
						to_page(currentPage);
						}

					});
				
				}
		});

		//���ȫѡȫ��ѡ����
		$("#check_all").click(function(){
			//ʹ��attr��ȡchecked����ʱ����undefined
			//������Щԭ�����ԣ�attr��ȡ�����Զ������ԣ���������ʹ��prop���޸ĺͶ�ȡdomԭ�����Ե�ֵ
			//��check_item��ѡ��״̬���ú�check_allһ��
			$(".check_item").prop("checked",$(this).prop("checked"));
			});

		//Ϊcheck_item�󶨵���¼������ڸ��¼�����Ա����Ϣ����ǰ�󶨣�����ֻ����on()������
		$(document).on("click",".check_item",function(){
			//alert($(".check_item:checked").length);
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag)
			});


		//�������ɾ������
		$("#emp_delete_all_btn").click(function(){
			var emp_Names = "";
			var emp_ids = "";
			$.each($(".check_item:checked"),function(){
				emp_Names += $(this).parents("tr").find("td:eq(2)").text() + ",";
				emp_ids += $(this).parents("tr").find("td:eq(1)").text() + "-";
				});
			emp_Names = emp_Names.substring(0, emp_Names.length-1);
			emp_ids = emp_ids.substring(0,emp_ids.length-1);

			if(confirm("ȷ��ɾ��Ա����"+emp_Names+"����")){
				//���ȷ������ajax����
				$.ajax({
					url:"${APP_PATH}/emp/"+emp_ids,
					type:"DELETE",
					success:function(result){
						alert(result.msg);
						to_page(currentPage);
						}
					});
				
				}
				

			});
		
		
	</script>
	

	
	</div>


</body>
</html>