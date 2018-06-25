package org.wp.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1522:ORCL";
	private static final String DBUSER = "scott";
	private static final String DBPASSWORD = "tiger";
	private  Connection conn ;
	private static int count = 0;
	
	public DatabaseConnection() {			//构造函数进行数据库连接
		System.out.println("************* count = " + this.count++);
		try {
			Class.forName(DBDRIVER);
			this.conn = DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {		//返回连接对象
		return conn;
	}
	
	public void Close() {					//关闭数据库
		if(this.conn != null) {
			try {
				this.conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


}
