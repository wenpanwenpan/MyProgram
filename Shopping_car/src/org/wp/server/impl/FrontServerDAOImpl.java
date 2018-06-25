package org.wp.server.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.wp.dao.IMerberDAO;
import org.wp.dao.IProductDAO;
import org.wp.dao.impl.MerberDAOImpl;
import org.wp.dao.impl.ProductDAOimpl;
import org.wp.dbc.DatabaseConnection;
import org.wp.server.IFrontServerDAO;
import org.wp.vo.Product;

public class FrontServerDAOImpl implements IFrontServerDAO{		//������
	private DatabaseConnection dbc = null;
	public FrontServerDAOImpl() {
		this.dbc = new DatabaseConnection();		//ȡ�����ݿ������
	}
	@Override
	public Map<String, Object> findOrder(String id, Set<Integer> key) throws Exception {
		Map<String , Object> map = new HashMap<>();
		try {
			IMerberDAO memberdao = new MerberDAOImpl(this.dbc.getConnection());		//��¼�û�ʵ��������
			IProductDAO productdao = new ProductDAOimpl(this.dbc.getConnection());	//��Ʒ��ʵ��������
			map.put("member", memberdao.findById(id));								//��ͨ��ID���ҵ����û����浽map��
			map.put("product", productdao.findAll(key));							//���û�����ĵ�ȫ����Ʒ���浽map��
		}catch (Exception e) {
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return map;
	}
	@Override
	public Product findProduct(Integer id,boolean flag) throws Exception {		//flag�ɷ�ֹ����ˢ��
		Product pro = null;
		try {
			IProductDAO dao = new ProductDAOimpl(this.dbc.getConnection());
			pro = dao.findById(id);		//����ID���ҵ���Ʒ
			if(flag) {
				dao.doUpdateCount(id); 	//���µ����
			}
		}catch (Exception e) {
			throw e ;
		}finally {
			this.dbc.Close();
		}
		return pro;
	}

}
