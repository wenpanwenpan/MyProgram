package com.wp.demo.vo;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.demo.dao.impl.DrugDAOImpl;

@Service
public class Demo {
	
	
	
	@Transactional
	public static void  demo() throws SQLException {
		
		System.out.println("==============>测试程序执行开始");
		
		DrugDAOImpl drugDAOImpl = new DrugDAOImpl();
		
		drugDAOImpl.insertDemo();
		System.out.println("第一句执行成功！");
		drugDAOImpl.insertDemo();
		
		System.out.println("==============>测试程序执行结束");
		
	}
	
	public static void main(String[] args) throws SQLException {
		demo();
	}

}
