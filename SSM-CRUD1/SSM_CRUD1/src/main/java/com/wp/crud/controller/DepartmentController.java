package com.wp.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.crud.bean.Department;
import com.wp.crud.bean.Msg;
import com.wp.crud.service.DepartmentService;

/**
 * 处理和部门有关的请求的控制器
 * @responseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
	这个注解表示该方法的返回结果直接写入HTTP response body中，一般在异步获取数据时使用。
	在使用@RequestMapping后，返回值通常解析为跳转路径。加上@responsebody后，返回结果直接写入HTTP response body中，不会被解析为跳转路径。比如异步请求，
	希望响应的结果是json数据，那么加上@responsebody后，就会直接返回json数据
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;

	//使用 @ResponseBody 注解由Srping完成对象——协议的转换
	@RequestMapping(value="/depts")
	@ResponseBody
	public Msg getDepts() {
		List<Department> depts = departmentService.getDepts();
		//向请求的客户端返回自己封装后的json格式的数据
		return Msg.success().add("depts", depts);
	}
}
