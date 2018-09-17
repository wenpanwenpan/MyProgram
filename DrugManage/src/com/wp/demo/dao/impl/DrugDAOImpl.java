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
	 * 取得所有药品信息
	 * */
	public List<Drug> getAllDrug() throws SQLException{
		List<Drug> all = null;
		all = new ArrayList<Drug>();
		Drug drug = null;
		//BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
		//QueryRunner runner = new QueryRunner(com.wp.demo.dao.database.JDBCUtils.getDataSource());		//创建一个queryRuner
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drug order by did";
		
		Connection conn = JDBCUtils.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = null;
		rs = pstmt.executeQuery();		//执行查询
		
		while(rs.next()) {
			drug = new Drug();
			drug.setDid(rs.getString("did"));
			drug.setDname(rs.getString("dname"));
			drug.setDdate(rs.getString("ddate"));
			drug.setDaddress(rs.getString("daddress"));
			drug.setProvider(providerDAOImpl.getProviderById(rs.getString("pid")));		//设置提供商
			drug.setDgrade(rs.getString("dgrade"));
			drug.setDperson(rs.getString("dperson"));
			drug.setDnote(rs.getString("dnote"));

			all.add(drug);		//加入集合
		}

		//all = runner.query(sql, new BeanListHandler<>(Drug.class));						//执行查询操作，并且将查询结果保存到list集合
		JDBCUtils.close(conn);			//关闭数据库连接
		return all;
	}
	
	/**
	 * 通过did取得药品
	 * */
	public Drug getDrugById(String id) throws SQLException {
		Drug drug = null;
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drug where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());		//不带事务回滚
		drug = runner.query(sql, new BeanHandler<>(Drug.class),id);
		return drug;
	}
	
	/**
	 * 修改药品信息
	 * */
	public boolean doUpdate(Drug drug) throws SQLException {					//修改药品操作
		boolean flag = false;
		String sql = "update drug set dname=?,ddate=?,daddress=?,pid=?,dgrade=?,dperson=?,dnote=? where did=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		drug.setProvider(providerDAOImpl.getProviderById(drug.getProvider().getPid()));			//设置提供商对象信息
		Object[] params = {drug.getDname(),drug.getDdate(),drug.getDaddress(),drug.getProvider().getPid(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote(),drug.getDid()}; 
		if(runner.update(sql, params) > 0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 添加新药品入库
	 * */
	public boolean doAdd(Drug drug) throws SQLException {					//修改药品操作
		boolean flag = false;
		String sql = "insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		drug.setProvider(providerDAOImpl.getProviderById(drug.getProvider().getPid()));	//设置提供商对象信息
		
		Object[] params = {drug.getDid(),drug.getDname(),drug.getDdate(),drug.getDaddress(),drug.getProvider().getPid(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote()}; 
		if(runner.update(sql, params) > 0) {								//执行插入
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 将过期药品出库
	 * 将表drug中的信息删除，同时要将删除的信息存入出库信息表
	 * */
	public boolean doRemove(String id) throws SQLException {	 			//执行删除操作
		boolean flag = false;
		String sql = "delete drug where did = ?";
		String sql2="insert into drugout(did,dname,ddate,daddress,dgrade,dperson,dnote) values(?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		Drug drug = getDrugById(id);										//先取得药品
		//System.out.println("---->drug:  " + drug);
		Object[] params = {drug.getDid(),drug.getDname(),drug.getDdate(),drug.getDaddress(),
				drug.getDgrade(),drug.getDperson(),drug.getDnote()}; 
		
		if(runner.update(sql,id) > 0 && runner.update(sql2,params) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 根据过期时间删除过期药品
	 * */
	public boolean doRemoveByDate(String date) throws SQLException {	 			//执行删除操作
		boolean flag = false;
		String sql = "delete drug where ddate < ?";				
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());	
		if(runner.update(sql,date) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 通过过期时间取得药品
	 * @throws SQLException 
	 * */
	public List<Drug> getDrugByDate(String date) throws SQLException{
		List<Drug> drugs = null;
		String sql = "select * from drug where ddate < ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		drugs = runner.query(sql, new BeanListHandler<Drug>(Drug.class), date);			//将过期药品查询出来然后封装成一个集合
		
		return drugs;
	}
	
	/**
	 * 通过关键字查询药品信息
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
	public void insertDemo() throws SQLException {			//模拟插入失败
		String sql = "insert into drug(did,dname,ddate,daddress,pid,dgrade,dperson,dnote) values(?,?,?,?,?,?,?,?)";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		Object[] params = {"006","wenpan","2018-11-11","四川成都","101","试验药品","特殊人群","一款良药啊"};
		
		runner.update(sql, params);
	}
	
	public void delete(String id) throws SQLException {				//模拟删除
		String sql = "delete drug where did=?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		
		runner.update(sql,id);
	}
	
	

}
