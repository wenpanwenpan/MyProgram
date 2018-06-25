package org.wp.factory;

import org.wp.dao.IAdminDAO;
import org.wp.dao.IMerberDAO;
import org.wp.dao.IProductDAO;
import org.wp.daoproxy.AdminDAOProxy;
import org.wp.daoproxy.MemberDAOProxy;
import org.wp.daoproxy.ProductDAOProxy;

public class DAOFactory {		//工厂类
	
	public static IMerberDAO getMemberDAOInstance() {
		return new MemberDAOProxy();
	}
	public static IProductDAO getIProductDAOInstance() {
		return new ProductDAOProxy();
	}
	public static IAdminDAO getIAdminDAOInstance() {		//登陆实例
		return new AdminDAOProxy();
	}
	

}
