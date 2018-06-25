package org.wp.server.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.wp.dao.IMerberDAO;
import org.wp.dao.IProductDAO;
import org.wp.dao.impl.MerberDAOImpl;
import org.wp.dao.impl.ProductDAOimpl;
import org.wp.dbc.DatabaseConnection;
import org.wp.dbc.JDBCUtils;
import org.wp.server.IFrontServerDAO;
import org.wp.vo.Product;

public class FrontServerDAOImpl implements IFrontServerDAO{		//������
	private Connection dbc = null;
	public FrontServerDAOImpl() {
		this.dbc = JDBCUtils.getConnection();		//ȡ�����ݿ������
	}
	@Override
	public Map<String, Object> findOrder(String id, Set<Integer> key) throws Exception {
		Map<String , Object> map = new HashMap<>();
		try {
			IMerberDAO memberdao = new MerberDAOImpl(this.dbc);		//��¼�û�ʵ��������
			IProductDAO productdao = new ProductDAOimpl(this.dbc);	//��Ʒ��ʵ��������
			map.put("member", memberdao.findById(id));								//��ͨ��ID���ҵ����û����浽map��
			map.put("product", productdao.findAll(key));							//���û�����ĵ�ȫ����Ʒ���浽map��
		}catch (Exception e) {
			throw e ;
		}finally {
			JDBCUtils.closeConn(dbc);
		}
		return map;
	}
	@Override
	public Product findProduct(Integer id,boolean flag) throws Exception {		//flag�ɷ�ֹ����ˢ��
		Product pro = null;
		try {
			IProductDAO dao = new ProductDAOimpl(this.dbc);
			pro = dao.findById(id);		//����ID���ҵ���Ʒ
			if(flag) {
				dao.doUpdateCount(id); 	//���µ����
			}
		}catch (Exception e) {
			throw e ;
		}finally {
			JDBCUtils.closeConn(dbc);
		}
		return pro;
	}

}
