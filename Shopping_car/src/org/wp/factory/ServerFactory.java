package org.wp.factory;

import org.wp.server.IFrontServerDAO;
import org.wp.server.impl.FrontServerDAOImpl;

public class ServerFactory {			//���񹤳���
	public static IFrontServerDAO getIFrontServerDAOInstance() {
		return new FrontServerDAOImpl();			//���ط����ʵ�ʲ���
	}
}
