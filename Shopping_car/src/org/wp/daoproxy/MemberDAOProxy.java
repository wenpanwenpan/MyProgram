package org.wp.daoproxy;

import java.util.List;
import org.wp.dao.IMerberDAO;
import org.wp.dao.impl.MerberDAOImpl;
import org.wp.dbc.DatabaseConnection;
import org.wp.vo.Member;


public class MemberDAOProxy implements IMerberDAO{
	private DatabaseConnection dbc = null;
	private IMerberDAO dao;			//实际操作类
	
	public MemberDAOProxy() {
		this.dbc = new DatabaseConnection();
		this.dao = new MerberDAOImpl(this.dbc.getConnection()); 		//实际操作类
	}
	
	@Override
	public boolean findLogin(Member vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.findLogin(vo);
			if(flag) {
				this.dao.doUpdateLastdate(vo.getMid()); 		//更新最后一次的登录时间
			}
		}catch(Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public void doUpdateLastdate(String id) throws Exception {
		try {
			this.dao.doUpdateLastdate(id);;
		}catch(Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}	
	}
	@Override
	public boolean doCreate(Member vo) throws Exception {
		boolean flag = false;
		try {
			if(this.dao.findById(vo.getMid()) == null) {		//若该用户名没有被注册过才能注册
				flag = this.dao.doCreate(vo);
			}
		}catch(Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public Member findById(String id) throws Exception {
		Member member = null;
		try {
			member = this.dao.findById(id);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return member;
	}
	
	@Override
	public boolean doUpdate(Member vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdate(vo);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public boolean doRemove(String id) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doRemove(id);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public List<Member> findAll(String keyword) throws Exception {
		List<Member> all = null;
		try {
			all = this.dao.findAll(keyword);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return all;
	}

	@Override
	public List<Member> findAll(String keyword, int intcurrentPage, int lineSize) throws Exception {
		List<Member> all = null;
		try {
			all = this.dao.findAll(keyword, intcurrentPage, lineSize);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return all;
	}

	@Override
	public long getAllCount(String keyword) throws Exception {
		long count = 0;
		try {
			count = this.dao.getAllCount(keyword);
		}catch(Exception e){
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return count;
	}



}
