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
 * ����DAO��Ĺ���
 * �Ƽ�spring��Ŀ�Ϳ���ʹ��spring�ĵ�Ԫ���ԣ������Զ�ע��������Ҫ�����
 * ����springtestģ��  ʹ��spring�Դ��ĵ�Ԫ����
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
	 * �������ݿ��Ƿ���ͨ���Ƿ�����Զ�ע��
	 * */
	@Test
	public void testCRUD() {
		//����SpringIOC����
		/*ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		//�������л�ȡmapper
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);*/
		
		System.out.println(departmentMapper);
		
		//1�����뼸������
		/*departmentMapper.insertSelective(new Department(null,"������"));
		departmentMapper.insertSelective(new Department(null,"���Բ�"));*/
		
		//2.����Ա�����ݣ�����Ա������
		/*employeeMapper.insertSelective(new Employee(null,"wenpan","M","wenpan@qq.com",1));
		employeeMapper.insertSelective(new Employee(null,"zhangsan","F","zhangsan@qq.com",1));*/
		
		//3.����������Ա��������������ִ����������sqlsession
		/*for() {
			employeeMapper.insertSelective(new Employee(null,.));
		}*/
		//ִ����������Ա������
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i = 0;i < 1000;i++) {
			String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
			mapper.insertSelective(new Employee(null, uuid, "M", uuid + "@qq.com", 1));
			
		}
		
		
		
		
	}

}
