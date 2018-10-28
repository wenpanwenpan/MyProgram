package com.wp.crud.test;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wp.crud.bean.Department;
import com.wp.crud.bean.Employee;
import com.wp.crud.dao.DepartmentMapper;
import com.wp.crud.dao.EmployeeMapper;

/**
 * 测试DAO层的工作
 * 推荐spring项目就可以使用spring的单元测试，可以自动注入我们需要的组件
 * 导入springtest模块  使用spring自带的单元测试
 * 
 * */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class MapperTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	/**
	 * 测试数据库是否连通，是否可以自动注入
	 * */
	@Test
	public void testCRUD() {
		//创建SpringIOC容器
		/*ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//从容器中获取mapper
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);*/
		
		System.out.println(departmentMapper);
		
		//1、插入几个部门
		/*departmentMapper.insertSelective(new Department(null,"开发部"));
		departmentMapper.insertSelective(new Department(null,"测试部"));*/
		
		//2.生成员工数据，测试员工插入
		/*employeeMapper.insertSelective(new Employee(null,"wenpan","M","wenpan@qq.com",1));
		employeeMapper.insertSelective(new Employee(null,"zhangsan","F","zhangsan@qq.com",1));*/
		
		//3.批量插入多个员工；批量：可以执行批量操作sqlsession
		/*for() {
			employeeMapper.insertSelective(new Employee(null,.));
		}*/
		//执行批量插入员工操作
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i < 1000;i++) {
			String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
			mapper.insertSelective(new Employee(null, uuid, "M", uuid + "@qq.com", 1));
			
		}
		
		
		
		
	}

}
