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
	 * 取得所有出库药品信息
	 * */
	public List<Drug> getAllDrug() throws SQLException{
		List<Drug> all = null;
		//BeanListHandler：将结果集中的每一行数据都封装到一个对应的JavaBean实例中，存放到List里。
		QueryRunner runner = new QueryRunner(com.wp.demo.dao.database.JDBCUtils.getDataSource());		//创建一个queryRuner
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drugout order by did";
		
		all = runner.query(sql, new BeanListHandler<>(Drug.class));						//执行查询操作，并且将查询结果保存到list集合
		
		return all;
	}
	
	/**
	 * 通过did取得药品
	 * */
	public Drug getDrugById(String id) throws SQLException {
		Drug drug = null;
		String sql = "select did,dname,ddate,daddress,pid,dgrade,dperson,dnote from drugout where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());		//不带事务回滚
		drug = runner.query(sql, new BeanHandler<>(Drug.class),id);
		return drug;
	}
	
	
	/**
	 * 从出库表中重新入库后，要将出库表中的这条信息进行删除
	 * */
	public boolean doRemove(String id) throws SQLException {	 			//执行删除操作
		boolean flag = false;
		String sql = "delete drugout where did = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		if(runner.update(sql,id) > 0) {
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 将过期药品集体出库
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
	 * 通过关键字查询药品信息
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
