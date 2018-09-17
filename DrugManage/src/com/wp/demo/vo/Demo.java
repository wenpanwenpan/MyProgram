package com.wp.demo.vo;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wp.demo.dao.impl.DrugDAOImpl;

@Service
public class Demo {
	
	
	
	@Transactional
	public static void  demo() throws SQLException {
		
		System.out.println("==============>���Գ���ִ�п�ʼ");
		
		DrugDAOImpl drugDAOImpl = new DrugDAOImpl();
		
		drugDAOImpl.insertDemo();
		System.out.println("��һ��ִ�гɹ���");
		drugDAOImpl.insertDemo();
		
		System.out.println("==============>���Գ���ִ�н���");
		
	}
	
	public static void main(String[] args) throws SQLException {
		demo();
	}

}
