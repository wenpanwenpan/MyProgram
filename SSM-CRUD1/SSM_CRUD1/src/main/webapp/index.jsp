<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>员工信息界面</title>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!-- web路径：
不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
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

<!-- 员工修改模态框 -->
<div class="modal fade" id="empUpdateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">员工修改</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="empName_update_input" class="col-sm-3 control-label">员工姓名：</label>
				    <div class="col-sm-9">
				      <p class="form-control-static" id="empName_update_static"></p>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email_update_input" class="col-sm-3 control-label">员工邮箱：</label>
				    <div class="col-sm-9">
				      <input type="text" name="email" class="form-control" id="email_update_input" placeholder="email@qq.com">
				      <span class="help-block"></span>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="gender_add_input" class="col-sm-3 control-label">员工性别：</label>
				    <div class="col-sm-9">
				     	<label class="radio-inline">
						  <input type="radio" name="gender" id="gender1_update_input" value="M" checked="checked"> 男
					  	</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" id="gender2_update_input" value="F"> 女
						</label>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="dept_update_input" class="col-sm-3 control-label">部门名称：</label>
				    <div class="col-sm-4">
				      <!-- 部门提交dId即可 -->
				      <select class="form-control" name="dId">
						 
					  </select>
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" id="emp_update_btn">修改</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- 员工添加的模态框 -->
		<div class="modal fade" id="empAddModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		  <div class="modal-dialog" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
		        <h4 class="modal-title" id="myModalLabel">新增员工</h4>
		      </div>
		      <div class="modal-body">
		        <form class="form-horizontal">
				  <div class="form-group">
				    <label for="empName_add_input" class="col-sm-3 control-label">员工姓名：</label>
				    <div class="col-sm-9">
				      <input type="text" name="empName" class="form-control" id="empName_add_input" placeholder="请输入你的姓名！">
				      <span class="help-block"></span>
				    </div>
				  </div>
				  <div class="form-group">
				    <label for="email_add_input" class="col-sm-3 control-label">员工邮箱：</label>
				    <div class="col-sm-9">
				      <input type="text" name="email" class="form-control" id="email_add_input" placeholder="email@qq.com">
				      <span class="help-block"></span>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="gender_add_input" class="col-sm-3 control-label">员工性别：</label>
				    <div class="col-sm-9">
				     	<label class="radio-inline">
						  <input type="radio" name="gender" id="gender1_add_input" value="M" checked="checked"> 男
					  	</label>
						<label class="radio-inline">
						  <input type="radio" name="gender" id="gender2_add_input" value="F"> 女
						</label>
				    </div>
				  </div>
				   <div class="form-group">
				    <label for="dept_add_input" class="col-sm-3 control-label">部门名称：</label>
				    <div class="col-sm-4">
				      <!-- 部门提交dId即可 -->
				      <select class="form-control" name="dId">
						 
					  </select>
				    </div>
				  </div>
				</form>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		        <button type="button" class="btn btn-primary" id="emp_save_btn">保存</button>
		      </div>
		    </div>
		  </div>
		</div>

<!-- ********************************************************************************************************** -->
	<!-- 搭建显示页面 -->
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>员工信息</h1>
			</div>
		</div>
		
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-4 col-md-offset-10">
				<button id="emp_add_modal_btn" class="btn btn-success" data-toggle="modal">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
				新增
				</button>
				<button class="btn btn-danger" id="emp_delete_all_btn">
				<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
				删除
				</button>
			</div>
		</div>
		
	
		<!-- 显示表格数据 -->
		<div class="row" style="background-color: #F2F2F2">
			<div class="col-md-12">
				<table class="table table-hover" id="emps_table">
					<thead>
						<tr>
							<th>
								<input type="checkbox" id="check_all"/>
							</th>
							<th>员工编号</th>
							<th>员工姓名</th>
							<th>员工性别</th>
							<th>员工邮箱</th>
							<th>部门名称</th>
							<th>操作</th>
						</tr>					
					</thead>
					<tbody></tbody>
							
				</table>
			</div>
		</div>
		
		
		<!-- 显示分页信息 -->
		<div class="row">
			<!-- 分页文字信息，；两个div水平布局，各占6列 -->
			<div class="col-md-6" id="page_info_area">
				
			</div>
			<!-- 分页条信息 -->
			<div class="col-md-6" id="page_nav_area">
			
			</div>
		</div>
		
	<script type="text/javascript">
<!--*************************************************************************************************-->
	//总记录数和当前页面
	var totalRecord,currentPage;
	
	$(function(){
		//1.去首页  页面第一次加载的时候默认去第一页
		to_page(1);

		
		});
	
	//1.页面加载完成以后，直接去发送一个ajax请求，要到分页数据
		function to_page(pn){
			//清空上一次的tbody中的内容
			$("#emps_table tbody").empty();
			$.ajax({
				url:"${APP_PATH}/emps",
				data:"pn=" + pn,		//请求所带的参数（第几页）
				type:"GET",
				success:function(result){
					//console.log(result);
					//1.解析并显示员工数据
					build_emps_table(result);
					//2.解析并显示分页信息
					build_page_info(result);

					//3.解析并显示分页条
					build_page_nav(result);
						
					}
				});
			}

		//解析并显示员工数据
		function build_emps_table(result){
			//得到返回的员工信息集合
			var emps = result.extend.pageInfo.list;
			//利用jQuery的each方法取出每一条员工的信息
			$.each(emps, function(index,item) {
				//每一个属性占一个单元格
				var check_item = $("<td><input type='checkbox' class='check_item' /></td>");
				var empIdTd = $("<td></td>").append(item.empId);
				var empNameTd = $("<td></td>").append(item.empName);
				var genderTd = $("<td></td>").append(item.gender == 'M'?"男":"女");
				var emailTd = $("<td></td>").append(item.email);
				var deptNameTd = $("<td></td>").append(item.department.deptName);
				var editBtn = $("<button></button>").addClass("btn btn-success btn btn btn-primary btn-xs edit_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-pencil")).append("编辑").attr("edit-id",item.empId);
				var delBtn = $("<button></button>").addClass("btn btn-danger btn btn btn-primary btn-xs delete_btn")
				.append($("<span></span>").addClass("glyphicon glyphicon-trash")).append("删除").attr("del-id",item.empId);

				//将两个button按钮追加到一个单元格中
				var btnTd = $("<td></td>").append(editBtn).append(" ").append(delBtn);
				//append方法执行完之后还是返回原来的元素。将所有的单元格追加到一行中
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

		//解析并显示分页信息
		function build_page_info(result){
			//清空上一次的分页信息
			$("#page_info_area").empty();
			$("#page_info_area").append("当前第" + result.extend.pageInfo.pageNum + "页, ")
			.append("共" + result.extend.pageInfo.pages + "页, ")
			.append("共" + result.extend.pageInfo.total + "条数据");

			totalRecord = result.extend.pageInfo.total;
			currentPage = result.extend.pageInfo.pageNum;
		}

		//解析并显示分页条信息
		function build_page_nav(result){
			//清空上一次的分页条信息
			$("#page_nav_area").empty();
			var firstPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("首页"));
			var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;").attr("href","#"));
			var nexPageLi = $("<li></li>").append($("<a></a>").append("&raquo;").attr("href","#"));			
			var lastPageLi = $("<li></li>").append($("<a></a>").attr("href","#").append("尾页"));
	
			//当没有上一页下一页时禁用按钮
			if(result.extend.pageInfo.hasPreviousPage == false){
				firstPageLi.addClass("disabled");
				prePageLi.addClass("disabled");
				}else{
					//为分页按钮绑定点击事件
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
					//为分页按钮绑定点击事件
					nexPageLi.click(function(){
						to_page(result.extend.pageInfo.pageNum + 1);
						});
					lastPageLi.click(function(){
						to_page(result.extend.pageInfo.pages);
						});
					}
			
			//创建一个ul并把首页和上一页添加到ul中
			var ul = $("<ul></ul>").addClass("pagination").append(firstPageLi)
			.append(prePageLi);
			
			$.each(result.extend.pageInfo.navigatepageNums,function(index,item){
				var numLi = $("<li></li>").append($("<a></a>").attr("href","#").append(item));
				if(result.extend.pageInfo.pageNum == item){
					numLi.addClass("active");
					} 
				//绑定点击事件
				numLi.click(function(){
					to_page(item);
					});
				ul.append(numLi);
			});

			//将下一页和尾页添加到ul中
			ul.append(nexPageLi).append(lastPageLi);

			$("#page_nav_area").append(ul);

			
			
		}
		
/**===============================================================================*/

		function reset_form(ele){
			//重置表单数据
			$(ele)[0].reset();
			//清空表单样式
			$(ele).find("*").removeClass("has-error has-success");
			$(ele).find(".help-block").text("");
			
			}
		//给新增按钮绑定点击事件，打开模态框
		$("#emp_add_modal_btn").click(function(){
			//每次点击新增按钮时，先清除表单数据
			reset_form("#empAddModal form");
			//向服务器发送ajax请求，获取到下拉列表框中的部门信息
			getDepts("#empAddModal select");
			//选中要打开的模态框，并且调用modal方法，设置相关属性
			$("#empAddModal").modal({
				//点击其他地方时不关闭模态框
				backdrop:"static",
				keyboard:"true"
				});
			});

		//传入的参数为显示部门的下拉列表框
		function getDepts(ele){
			$.ajax({
				url:"${APP_PATH}/depts",
				type:"GET",
				//请求成功后的回调函数
				success:function(result){
					//console.log(result);
					//后台返回的数据 ： {"code":100,"msg":"处理成功！","extend":{"depts":[{"deptId":1,"deptName":"开发部"},{"deptId":2,"deptName":"测试部"}]}}
					//每次加载部门时，先将下拉列表框中原来的内容清空
					$(ele).empty();
					var result1=result.extend.depts;
					$.each(result1,function(index,element){
						//遍历从服务器端返回的json格式的数据，取出部门信息并添加到新创建的  option元素中
						var optionEle=$("<option></option>").append(element.deptName).attr("value",element.deptId);
						//把option元素添加到模态框中的下拉列表框中
						optionEle.appendTo($(ele));
						});
					}
				});
			}

		//用户输入校验
		function validate_add_form(){
			var empName = $("#empName_add_input").val();
			var regName = /(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})/;
			if(!regName.test(empName)){
				//若用户名的正则表达式校验不通过，则利用bootstrap给出提示信息
				//show_validate_msg("#empName_add_input","error","用户名格式错误！");
				return false;
				}else{
				show_validate_msg("#empName_add_input","success","");
					}
			
			var email = $("#email_add_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
			if(!regEmail.test(email)){
				show_validate_msg("#email_add_input","error","邮箱格式错误！");
				return false;
				}else{
					show_validate_msg("#email_add_input","success","");
					}
			
			return true;
			}

		//验证用户名和邮箱，抽取为一个方法
		function show_validate_msg(ele,status,msg){
			//先清空以前元素中的一些内容,即清除当前元素的校验状态
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
		
		//给新增员工模态框中的保存按钮添加点击事件
		$("#emp_save_btn").click(function(){

			//验证表单里面的数据格式都填写正确否，若校验不通过，则直接返回false，不进行下面的ajax请求了
			/* if(!validate_add_form()){
				return false;
				} */

			//校验用户名是否可用，若不可用，则直接返回false，不发下面的ajax请求了
			if($(this).attr("ajax-va") == "error"){
				return false;
				}
			
			$.ajax({
				url:"${APP_PATH}/emp",
				type:"POST",
				//通过取得模态框中的表单，然后调用序列化方法获取表单中的值，添加到请求参数中
				data:$("#empAddModal form").serialize(),
				success:function(result){
					//后台验证：若后台返回的状态码是100 ，表示后台的JSR303校验成功，则关闭模态框。
					if(result.code == 100){
						//1.返回成功以后要关闭模态框,直接调用jQuery的modal方法传入hide参数即可隐藏模态框
						$("#empAddModal").modal("hide");
						//2.返回成功以后要自动跳转到最后一页进行数据显示
						to_page(totalRecord);
						}else{
						//显示失败信息，并且把失败的原因添加到对应的输入框后面
						if(undefined != result.extend.errorFields.email){
							//显示邮箱信息错误
							show_validate_msg("#email_add_input","error","邮箱格式错误！");
							}
						if(undefined != result.extend.errorFields.empName){
							//显示员工姓名错误
							show_validate_msg("#empName_add_input","error","用户名格式不正确，应为6-16位字母或2-5位中文！");
							}
						//console.log(result);
							}
					
					
					}
				});
			});

		//当新增员工时，员工姓名输入框失去焦点的时候发送ajax请求
		$("#empName_add_input").change(function(){
			var empName = $("#empName_add_input").val();
			$.ajax({
				url:"${APP_PATH}/checkuser",
				type:"POST",
				data:"empName="+empName,
				success:function(result){
					//通过服务器端返回的状态码，来判断用户名是否可用
					if(result.code == 100){
						//给保存按钮添加一个属性，当点击保存的时候判断这个属性来判断用户名是否可用，从而决定是否要提交
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
			1.我们是按钮创建之前就绑定了click，所以绑定不上
			2.可以在创建按钮的时候绑定事件。  ②.可以绑定点击.live()
			3.jquery新版没有live事件，所以我们可以使用on代替

			点击每一个 编辑 按钮的时候打开编辑模态框
		*/		
		$(document).on("click",".edit_btn",function(){
			//清空上一次的错误提示信息
			$("#email_update_input").parent().removeClass("has-error has-success");
			$("#email_update_input").next("span").text("");
			//1.查出员工信息，并回显员工信息
			//2.查出部门信息，并回显部门信息
			getDepts("#empUpdateModal select");

			//发送ajax请求查询员工数据,获取要修改的员工id，并取得员工信息
			var empId = $(this).attr("edit-id");
			getEmp(empId);

			//在点击修改按钮时，把当前员工的id保存到修改按钮的属性当中，以便点击修改时向后台发送参数
			$("#emp_update_btn").attr("edit-id",empId);
			
			$("#empUpdateModal").modal({
				//点击其他地方时不关闭模态框
				backdrop:"static",
				keyboard:"true"
				});
			});

		//发送ajax请求，获得数据进行表单回显
		function getEmp(empId){
			$.ajax({
				url:"${APP_PATH}/emp/"+empId,
				type:"GET",
				success:function(result){
					//console.log(result);
					var empEle = result.extend.emp;
					//利用后台查询出的员工信息返回数据，进行表单数据回显
					$("#empName_update_static").text(empEle.empName);
					$("#email_update_input").val(empEle.email);
					$("#empUpdateModal input[name=gender]").val([empEle.gender]);
					$("#empUpdateModal select").val([empEle.dId]);
					}
					

				});
			}

		//点击更新按钮，更新员工信息
		$("#emp_update_btn").click(function(){
			//更新前先进行邮箱的格式校验，校验通过后再发ajax请求修改数据，否则不发ajax请求
			var email = $("#email_update_input").val();
			var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
			if(!regEmail.test(email)){
				show_validate_msg("#email_update_input","error","邮箱格式错误！");
				return false;
				}else{
					show_validate_msg("#email_update_input","success","");
					}
			//邮箱格式校验通过，开始向服务器发送ajax请求
			$.ajax({
				/**
				1.可以使用如下方法發送PUT请求：
				type:"POST",
				data:$("#empUpdateModal form").serialize()+"&_method=PUT",
				使用web.xml文件中的HiddenHttpMethodFilter过滤器配置，将POST请求转换成PUT请求
				也可以使用下列方法由Ajax直接发送PUT请求：
					在web.xml配置文件中配置HttpPutFormContentFilter过滤器，springmvc将调用它自己重写的
					getParameter()方法来从request属性域中取值。所以提交的表单数据就能自动的封装到employee对象中了
				*/
				url:"${APP_PATH}/emp/" + $(this).attr("edit-id"),
				type:"PUT",
				//将修改表单里面的数据传到后台的接收方法中，进行修改员工信息
				data:$("#empUpdateModal form").serialize(),
				success:function(result){
				//1.关闭模态框
				$("#empUpdateModal").modal("hide");
				//2.返回到修改前的页面
				to_page(currentPage);
					
				}
				
				})
			});


		//利用on方法为所有的class=del_btn的删除按钮绑定点击事件
		$(document).on("click",".delete_btn",function(){
			var empId = $(this).attr("del-id");
			var empName = $(this).parents("tr").find("td:eq(2)").text();

			if(confirm("确认删除员工【" +empName+"】吗？")){
				//弹出窗口中点击确认，发送ajax请求删除即可
				$.ajax({
					url:"${APP_PATH}/emp/"+empId,
					type:"DELETE",
					success:function(result){
						//alert(result.msg);
						//回到删除员工时的页面
						to_page(currentPage);
						}

					});
				
				}
		});

		//完成全选全不选功能
		$("#check_all").click(function(){
			//使用attr获取checked属性时，是undefined
			//我们这些原生属性；attr获取的是自定义属性，所以我们使用prop来修改和读取dom原生属性的值
			//将check_item的选择状态设置和check_all一致
			$(".check_item").prop("checked",$(this).prop("checked"));
			});

		//为check_item绑定点击事件，由于该事件是在员工信息加载前绑定，所以只能用on()方法绑定
		$(document).on("click",".check_item",function(){
			//alert($(".check_item:checked").length);
			var flag = $(".check_item:checked").length == $(".check_item").length;
			$("#check_all").prop("checked",flag)
			});


		//点击批量删除操作
		$("#emp_delete_all_btn").click(function(){
			var emp_Names = "";
			var emp_ids = "";
			$.each($(".check_item:checked"),function(){
				emp_Names += $(this).parents("tr").find("td:eq(2)").text() + ",";
				emp_ids += $(this).parents("tr").find("td:eq(1)").text() + "-";
				});
			emp_Names = emp_Names.substring(0, emp_Names.length-1);
			emp_ids = emp_ids.substring(0,emp_ids.length-1);

			if(confirm("确认删除员工【"+emp_Names+"】吗？")){
				//点击确定后发送ajax请求
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