package org.wp.daoproxy;

import java.util.List;

import org.wp.dao.IAdminDAO;
import org.wp.dao.impl.AdminDAOImpl;
import org.wp.dbc.DatabaseConnection;
import org.wp.vo.Admin;

public class AdminDAOProxy implements IAdminDAO {
	private DatabaseConnection dbc = null;
	private AdminDAOImpl dao = null;
	private long count ;
	public AdminDAOProxy() {
		this.dbc = new DatabaseConnection();
		this.dao = new AdminDAOImpl(this.dbc.getConnection());
	}
	@Override
	public boolean doCreate(Admin vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doCreate(vo);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public boolean doUpdate(Admin vo) throws Exception {
		boolean flag = false;
		try {
			flag = this.dao.doUpdate(vo);
		}catch (Exception e) {
			throw e;
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
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

	@Override
	public Admin findById(String id) throws Exception {
		Admin admin = null;
		try {
			admin = this.dao.findById(id);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return admin;
	}

	@Override
	public List<Admin> findAll(String keyword) throws Exception {
		List<Admin> all = null;
		try {
			all = this.dao.findAll(keyword);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return all;
	}

	@Override
	public List<Admin> findAll(String keyword, int intcurrentPage, int lineSize) throws Exception {
		List<Admin> all = null;
		try {
			all = this.dao.findAll(keyword, intcurrentPage, lineSize);
			this.count = this.dao.getAllCount(keyword);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return all;
	}

	@Override
	public long getAllCount(String keyword) throws Exception {
		return this.count;
	}

	@Override
	public boolean findLogin(Admin vo) throws Exception {
		boolean flag = false ;
		try {
			flag = this.dao.findLogin(vo);
		}catch (Exception e) {
			throw e;
		}finally {
			this.dbc.Close();
		}
		return flag;
	}

}
