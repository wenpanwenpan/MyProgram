package org.wp.dao;

import org.wp.vo.Admin;

public interface IAdminDAO extends IDAO<Admin,String> {
	public boolean findLogin(Admin vo) throws Exception;

}
