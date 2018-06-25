package org.wp.dbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;


import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	 private static DataSource dataSource=null;  		//����Դ
	    static{  
	        dataSource = new ComboPooledDataSource("helloC3P0");  		//ͨ�������ļ�ȡ�����ݿ����ӳ�
	    }  
	      
	    /** 
	     * ��ȡ���ݿ����� 
	     * @return 
	     */  
	    public static Connection getConnection(){  
	        Connection conn=null;  
	        try {  
	             conn=dataSource.getConnection();  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	        return conn;  
	    }  
	  
	      
	    /** 
	     * �ر����ݿ����� 
	     * @param conn 
	     */  
	    public static void closeConn(Connection conn) {			//��������
	    	 try {  
		            if(conn!=null){  
		                conn.close();  
		            }  
		        } catch (SQLException e) {  
		            e.printStackTrace();  
		        }  
	    }
	    public static void closeConn(ResultSet rs,PreparedStatement pstmt,Connection conn){  
	       try {
			if(rs != null) {
				rs.close();
			}
	       } catch (Exception e) {
			e.printStackTrace();
	    }
	       
	       try {
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	       
	       try {  
	            if(conn!=null){  
	                conn.close();  
	            }  
	        } catch (SQLException e) {  
	            e.printStackTrace();  
	        }  
	    }  
	    
	    
}
