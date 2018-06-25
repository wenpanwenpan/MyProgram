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

public class FrontServerDAOImpl implements IFrontServerDAO{		//服务类
	private Connection dbc = null;
	public FrontServerDAOImpl() {
		this.dbc = JDBCUtils.getConnection();		//取得数据库的连接
	}
	@Override
	public Map<String, Object> findOrder(String id, Set<Integer> key) throws Exception {
		Map<String , Object> map = new HashMap<>();
		try {
			IMerberDAO memberdao = new MerberDAOImpl(this.dbc);		//登录用户实例操作类
			IProductDAO productdao = new ProductDAOimpl(this.dbc);	//商品的实例操作类
			map.put("member", memberdao.findById(id));								//将通过ID查找到的用户保存到map中
			map.put("product", productdao.findAll(key));							//将用户购买的的全部商品保存到map中
		}catch (Exception e) {
			throw e ;
		}finally {
			JDBCUtils.closeConn(dbc);
		}
		return map;
	}
	@Override
	public Product findProduct(Integer id,boolean flag) throws Exception {		//flag可防止恶意刷新
		Product pro = null;
		try {
			IProductDAO dao = new ProductDAOimpl(this.dbc);
			pro = dao.findById(id);		//根据ID查找到商品
			if(flag) {
				dao.doUpdateCount(id); 	//更新点击量
			}
		}catch (Exception e) {
			throw e ;
		}finally {
			JDBCUtils.closeConn(dbc);
		}
		return pro;
	}

}
