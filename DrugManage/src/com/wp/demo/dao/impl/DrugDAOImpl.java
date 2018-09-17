package com.wp.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class DrugDAOImpl {
	
	@Autowired
	private ProviderDAOImpl providerDAOImpl;
	
	/**
	 * ȡ������ҩƷ��Ϣ
	 * */
	public List<Drug> getAllDrug() throws SQLException{
		List<Drug> all = null;
		all = new ArrayList<Drug>();
		Drug drug = null;
		//BeanListHandler����������е�ÿһ�����ݶ���װ��һ����Ӧ��JavaBeanʵ���У���ŵ�List�
		//QueryRunner runner = new QueryRunner(com.wp.demo.dao.database.JDBCUtils.getDataSource());		//����һ��queryRuner
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drug order by did";
		
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = pstmt.executeQuery();		//ִ�в�ѯ
		
		while(rs.next()) {
			drug = new Drug();
			drug.setDid(rs.getString("did"));
			drug.setDname(rs.getString("dname"));
			drug.setDdate(rs.getString("ddate"));
			drug.setDaddress(rs.getString("daddress"));
			drug.setProvider(providerDAOImpl.getProviderById(rs.getString("pid")));		//�����ṩ��
			drug.setDgrade(rs.getString("dgrade"));
			drug.setDperson(rs.getString("dperson"));
			drug.setDnote(rs.getString("dnote"));

			all.add(drug);		//���뼯��
		}

		//all = runner.query(sql, new BeanListHandler<>(Drug.class));						//ִ�в�ѯ���������ҽ���ѯ������浽list����
		JDBCUtils.close(conn);			//�ر����ݿ�����
		return all;
	}
	
	/**
	 * ͨ��didȡ��ҩƷ
	 * */
	public Drug getDrugById(String id) throws SQLException {
		Drug drug = null;
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drug where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());		//��������ع�
		drug = runner.query(sql, new BeanHandler<>(Drug.class),id);
		return drug;
	}
	
	/**
	 * �޸�ҩƷ��Ϣ
	 * */
	public boolean doUpdate(Drug drug) throws SQLException {					//�޸�ҩƷ����
		boolean flag = false;
		String sql = "update drug set dname=?,ddate=?,daddress=?,pid=?,dgrade=?,dperson=?,dnote=? where did=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		drug.setProvider(providerDAOImpl.getProviderById(drug.getProvider().getPid()));			//�����ṩ�̶�����Ϣ
		Object[] params = {drug.getDname(),drug.getDdate(),drug.getDaddress(),drug.getProvider().getPid(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote(),drug.getDid()}; 
		if(runner.update(sql, params) > 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * �����ҩƷ���
	 * */
	public boolean doAdd(Drug drug) throws SQLException {					//�޸�ҩƷ����
		boolean flag = false;
		String sql = "insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		drug.setProvider(providerDAOImpl.getProviderById(drug.getProvider().getPid()));	//�����ṩ�̶�����Ϣ
		
		Object[] params = {drug.getDid(),drug.getDname(),drug.getDdate(),drug.getDaddress(),drug.getProvider().getPid(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote()}; 
		if(runner.update(sql, params) > 0) {								//ִ�в���
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ������ҩƷ����
	 * ����drug�е���Ϣɾ����ͬʱҪ��ɾ������Ϣ���������Ϣ��
	 * */
	public boolean doRemove(String id) throws SQLException {	 			//ִ��ɾ������
		boolean flag = false;
		String sql = "delete drug where did = ?";
		String sql2="insert into drugout(did,dname,ddate,daddress,dgrade,dperson,dnote) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		Drug drug = getDrugById(id);										//��ȡ��ҩƷ
		//System.out.println("---->drug:  " + drug);
		Object[] params = {drug.getDid(),drug.getDname(),drug.getDdate(),drug.getDaddress(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote()}; 
		
		if(runner.update(sql,id) > 0 && runner.update(sql2,params) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ���ݹ���ʱ��ɾ������ҩƷ
	 * */
	public boolean doRemoveByDate(String date) throws SQLException {	 			//ִ��ɾ������
		boolean flag = false;
		String sql = "delete drug where ddate < ?";				
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());	
		if(runner.update(sql,date) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * ͨ������ʱ��ȡ��ҩƷ
	 * @throws SQLException 
	 * */
	public List<Drug> getDrugByDate(String date) throws SQLException{
		List<Drug> drugs = null;
		String sql = "select * from drug where ddate < ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		drugs = runner.query(sql, new BeanListHandler<Drug>(Drug.class), date);			//������ҩƷ��ѯ����Ȼ���װ��һ������
		
		return drugs;
	}
	
	/**
	 * ͨ���ؼ��ֲ�ѯҩƷ��Ϣ
	 * @throws SQLException 
	 * */
	public List<Drug> doqueryByKey(String key) throws SQLException{
		List<Drug> all = null;
		String sql = "select * from drug where did like ? or dname like ? or dperson like ? or ddate like ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		Object[] params = {key,key,key,key};
		all = runner.query(sql, new BeanListHandler<Drug>(Drug.class), params);

		return all;
	}
//=========================================================================================================================	
	public void insertDemo() throws SQLException {			//ģ�����ʧ��
		String sql = "insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		Object[] params = {"006","wenpan","2018-11-11","�Ĵ��ɶ�","101","����ҩƷ","������Ⱥ","һ����ҩ��"};
		
		runner.update(sql, params);
	}
	
	public void delete(String id) throws SQLException {				//ģ��ɾ��
		String sql = "delete drug where did=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		runner.update(sql,id);
	}
	
	

}
