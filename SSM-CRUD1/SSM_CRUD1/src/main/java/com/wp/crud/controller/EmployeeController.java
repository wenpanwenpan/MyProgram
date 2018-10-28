package com.wp.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.crud.bean.Employee;
import com.wp.crud.bean.Msg;
import com.wp.crud.service.EmployeeService;

/**
 * 处理员工的CRUD请求
 * */

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * 以json的格式向前台请求返回数据，这样就可以实现跨平台型，可以是浏览器请求，安卓请求，iOS
	 * 将员工的分页查询方法改造成了一个类似ajax的方法
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		//这不是一个分页查询；
		//引入PageHelper分页插件
		//在查询之前只需要调用如下方法,传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
				
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括我们查询出来的数据,传入连续显示的页数eg:5  表示连续显示5页
		PageInfo<Employee> page = new PageInfo<>(emps,5);
		
		//返回自己封装的对象的success方法，并且调用add方法，将查出来的员工信息添加到Msg对象的集合中。直接返回对象给请求方
		return Msg.success().add("pageInfo", page);
		
	}
	
	/**
	 * 查询出全部员工的信息。
	 * 这是传统的返回方法，返回的数据只有浏览器才能解析，安卓，iOS无法解析
	 * 
	 * */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,
			Model model) {
		//这不是一个分页查询；
		//引入PageHelper分页插件
		//在查询之前只需要调用如下方法,传入页码，以及每页的大小
		PageHelper.startPage(pn,5);
		//startPage后面紧跟的这个查询就是一个分页查询
		List<Employee> emps = employeeService.getAll();
		
		//使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
		//封装了详细的分页信息，包括我们查询出来的数据,传入连续显示的页数eg:5  表示连续显示5页
		PageInfo<Employee> page = new PageInfo<>(emps,5);
		
		//使用model将查询出来的员工保存到request属性域中
		model.addAttribute("pageInfo",page);
		
		return "list";
	}
	
	/**
	 * 保存新增员工
	 * BindingResult ：代表验证返回的错误集合
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		//校验失败应该返回失败，在模态框中显示校验失败的错误信息
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError:errors) {
				//System.out.println("错误字段的名字：" + fieldError.getField());
				//System.out.println("错误信息：" + fieldError.getDefaultMessage());
				//将所有的校验错误信息都保存在map集合中
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
		}
		
		return Msg.success();
	}
	
	/**
	 * 浏览器端新增员工时，员工姓名输入框失去焦点后发出ajax请求，验证该用户名是否已经被占用
	 * 使用@RequestParam 注解，明确的告诉springmvc必须要有empName这个参数
	 * 不是地址栏传参数，提交方式一定要改为POST，不然会出现后台接收的中文为乱码
	 * @param empName
	 * @return
	 */
	@RequestMapping(value="/checkuser",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkUser(@RequestParam("empName") String empName) {
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regex)) {
			//System.out.println("员工姓名：" + empName);
			return Msg.fail().add("va_msg", "用户名格式不正确，应为6-16位字母或-或数字或2-5位中文！");
		}
		
		if(employeeService.checkUser(empName)) {
			return Msg.success().add("va_msg", "用户名可用！");
		}else {
			return Msg.fail().add("va_msg", "用户名已被占用！");
		}
		
	}
	
	/**
	 * @PathVariable 注解表示从路径中取出empId
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("empId") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		//System.out.println("employee:" + employee);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * 更新修改的员工信息
	 * 
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee 
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * 问题：
	 * 请求体中有数据；
	 * 但是Employee对象封装不上；
	 * update tbl_emp  where emp_id = 1014;
	 * 
	 * 原因：
	 * Tomcat：
	 * 		1、将请求体中的数据，封装一个map。
	 * 		2、request.getParameter("empName")就会从这个map中取值。
	 * 		3、SpringMVC封装POJO对象的时候。
	 * 				会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
	 * 员工更新方法
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee,HttpServletRequest request) {
		employeeService.updateEmp(employee);
		//System.out.println("请求体中的值："+request.getParameter("gender"));
		//System.out.println("后台接收到的employee：" + employee);
		return Msg.success();
	}
	
	
	/**
	 * 批量删除-单个删除二合一
	 * 根据员工id来单个删除员工信息
	 * @PathVariable注解：指传入方法的参数是从路径中取得的
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{empIds}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("empIds")String empIds) {
		if(empIds.contains("-")) {
			//批量删除员工信息
			ArrayList<Integer> list = new ArrayList<>();
			String emp_ids[] = empIds.split("-");
			
			//取出所有的员工ID并且放到list集合中
			for(String empid:emp_ids) {
				list.add(Integer.parseInt(empid));
			}
			
			//传入员工ID的list集合
			employeeService.deleteEmpBatch(list);
		}else {
			Integer empId = Integer.parseInt(empIds);
			employeeService.deleteEmp(empId);
		}
		return Msg.success();
	}
	
	
	
	
	
	
	
	
	
	
	
}
