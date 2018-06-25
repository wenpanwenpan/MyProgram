package org.wp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.wp.dao.IAdminDAO;
import org.wp.vo.Admin;

public class AdminDAOImpl implements IAdminDAO {
	private Connection conn;
	private  PreparedStatement pstmt;
	public AdminDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean findLogin(Admin vo) throws Exception {		//检查是否登陆
		boolean flag = false;
		String sql = "select count(adminid) from admin where adminid = ? and password = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getAdminid());
		this.pstmt.setString(2, vo.getPassword());
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			if(rs.getInt(1) > 0) {
				flag = true;
			}
		}
		return flag;
	}
	
	@Override
	public boolean doCreate(Admin vo) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public boolean doRemove(String id) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public Admin findById(String id) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public List<Admin> findAll(String keyword) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public List<Admin> findAll(String keyword, int intcurrentPage, int lineSize) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public long getAllCount(String keyword) throws Exception {
		throw new Exception("此方法未实现");
	}

	

}
