package com.wp.demo.dao.impl;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wp.demo.dao.database.JDBCUtils;
import com.wp.demo.vo.Drug;

@Repository
public class DrugOutDAOImpl {
	
	@Autowired
	private ProviderDAOImpl providerDAOImpl;
	
	/**
	 * ȡ�����г���ҩƷ��Ϣ
	 * */
	public List<Drug> getAllDrug() throws SQLException{
		List<Drug> all = null;
		//BeanListHandler����������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List�
		QueryRunner runner = new QueryRunner(com.wp.demo.dao.database.JDBCUtils.getDataSource());		//����һ��queryRuner
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drugout order by did";
		
		all = runner.query(sql, new BeanListHandler<>(Drug.class));						//ִ�в�ѯ���������ҽ���ѯ������浽list����
		
		return all;
	}
	
	/**
	 * ͨ��didȡ��ҩƷ
	 * */
	public Drug getDrugById(String id) throws SQLException {
		Drug drug = null;
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drugout where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());		//��������ع�
		drug = runner.query(sql, new BeanHandler<>(Drug.class),id);
		return drug;
	}
	
	
	/**
	 * �ӳ��������������Ҫ��������е�������Ϣ����ɾ��
	 * */
	public boolean doRemove(String id) throws SQLException {	 			//ִ��ɾ������
		boolean flag = false;
		String sql = "delete drugout where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		if(runner.update(sql,id) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ������ҩƷ�������
	 * @throws SQLException 
	 * */
	public boolean addDrugByDate(String date) throws SQLException {
		boolean flag = false;
		String sql = "insert into drugout select * from drug where ddate < ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		if(runner.update(sql, date) > 0) {
			flag = true;
		}	
		return flag;
	}
	
	/**
	 * ͨ���ؼ��ֲ�ѯҩƷ��Ϣ
	 * @throws SQLException 
	 * */
	public List<Drug> doqueryByKey(String key) throws SQLException{
		List<Drug> all = null;
		String sql = "select * from drugout where did like ? or dname like ? or dperson like ? or ddate like ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {key,key,key,key};
		all = runner.query(sql, new BeanListHandler<Drug>(Drug.class), params);

		return all;
	}
	
	

}
