package org.wp.factory;

import org.wp.server.IFrontServerDAO;
import org.wp.server.impl.FrontServerDAOImpl;

public class ServerFactory {			//服务工厂类
	public static IFrontServerDAO getIFrontServerDAOInstance() {
		return new FrontServerDAOImpl();			//返回服务的实际操作
	}
}
