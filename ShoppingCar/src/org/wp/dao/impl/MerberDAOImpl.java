package org.wp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.wp.dao.IMerberDAO;
import org.wp.vo.Member;


public class MerberDAOImpl implements IMerberDAO{
	private Connection conn;
	private PreparedStatement pstmt;
	public MerberDAOImpl() {}
	public MerberDAOImpl(Connection conn) {
		this.conn = conn;			//数据库连接
	}
	@Override
	public boolean findLogin(Member vo) throws Exception {
		boolean flag = false;
		String sql = "select name,address,telephone,zipcode,lastdate from member where mid = ? and password = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getMid());
		this.pstmt.setString(2, vo.getPassword());
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			flag = true;
		}
		return flag;
	}

	@Override
	public void doUpdateLastdate(String id) throws Exception {
		String sql = "update member set lastdate = ? where mid = ?";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
		this.pstmt.setString(2, id);
		this.pstmt.executeUpdate();	
	}
	@Override
	public boolean doCreate(Member vo) throws Exception {
		boolean flag = false;
		String sql = "insert into member(mid,password,name,address,telephone,zipcode,lastdate) values(?,?,?,?,?,?,?) ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getMid());
		this.pstmt.setString(2, vo.getPassword());
		this.pstmt.setString(3, vo.getName());
		this.pstmt.setString(4, vo.getAddress());
		this.pstmt.setString(5, vo.getTelephone());
		this.pstmt.setString(6, vo.getZipcode());
		this.pstmt.setDate(7,new java.sql.Date(vo.getLastdate().getTime()));		//将当前时间提交到数据库
		if(this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag;
	}
	@Override
	public Member findById(String id) throws Exception {
		Member member = null;
		String sql = "select mid,password,name,address,telephone,zipcode,lastdate from  member where mid = ? ";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setString(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			member = new Member();
			member.setMid(rs.getString(1));
			member.setPassword(rs.getString(2));
			member.setName(rs.getString(3));
			member.setAddress(rs.getString(4));
			member.setTelephone(rs.getString(5));
			member.setZipcode(rs.getString(6));
			member.setLastdate(rs.getDate(7));
		}	
		return member;
	}
	@Override
	public boolean doUpdate(Member vo) throws Exception {
		boolean flag = false;
		String sql = "update member set name = ?,address = ?,telephone = ?,zipcode = ? where mid = ?";
		this.pstmt = conn.prepareStatement(sql);
		this.pstmt.setString(1, vo.getName());
		this.pstmt.setString(2, vo.getAddress());
		this.pstmt.setString(3, vo.getTelephone());
		this.pstmt.setString(4, vo.getZipcode());
		this.pstmt.setString(5, vo.getMid());
		this.pstmt.executeUpdate();
		
		if(this.pstmt.executeUpdate() > 0) {
			flag = true;
		}
		return flag ;
	}

	@Override
	public boolean doRemove(String  id) throws Exception {
		throw new Exception("此方法未实现");
	}
	
	@Override
	public List<Member> findAll(String keyword) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public List<Member> findAll(String keyword, int intcurrentPage, int lineSize) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public long getAllCount(String keyword) throws Exception {
		throw new Exception("此方法未实现");
	}


}
