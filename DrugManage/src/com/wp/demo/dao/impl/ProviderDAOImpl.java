package com.wp.demo.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.stereotype.Repository;

import com.wp.demo.dao.database.JDBCUtils;
import com.wp.demo.vo.Provider;

@Repository
public class ProviderDAOImpl {
	
	public List<Provider> getAllProvider() throws SQLException{
		List<Provider> all = null;
		//pid,pname,paddress,ptel,pemail,pgrade
		String sql = "select pid,pname,paddress,ptel,pemail,pgrade from provider order by pid";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		all = runner.query(sql, new BeanListHandler<>(Provider.class));
		return all;
	}
	
	public Provider getProviderById(String id) throws SQLException {
		Provider provider = null;
		String sql = "select pid,pname,paddress,ptel,pemail,pgrade from provider where pid = ?";
		QueryRunner runner = new QueryRunner(JDBCUtils.getDataSource());
		provider = runner.query(sql, new BeanHandler<>(Provider.class),id);
		return provider;
	}

}
