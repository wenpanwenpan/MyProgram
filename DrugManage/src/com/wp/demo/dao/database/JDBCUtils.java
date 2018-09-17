package com.wp.demo.dao.database;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	
	public static DataSource dataSource = null;
	
	static {
		dataSource = new ComboPooledDataSource("helloC3P0");		//从配置文件中获取数据源
	}
	
	public static DataSource getDataSource() {
		return dataSource;			//返回数据源
	}
	
	public static Connection getConnection() {		//返回数据库连接
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void close(Connection conn) {		//关闭数据库连接
		if(conn != null) {
			try {
				conn.close();
				} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*public static void main(String[] args) throws SQLException {
		System.out.println(dataSource.getConnection());
		
	}*/
	
}
