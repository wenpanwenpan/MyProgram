package org.wp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.wp.dao.IProductDAO;
import org.wp.vo.Product;

public class ProductDAOimpl implements IProductDAO {
	private Connection conn ;
	private PreparedStatement pstmt = null;
	public ProductDAOimpl(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public boolean doCreate(Product vo) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public boolean doUpdate(Product vo) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public boolean doRemove(Integer id) throws Exception {
		throw new Exception("此方法未实现");
	}

	@Override
	public Product findById(Integer id) throws Exception {
		Product pro = null;
		String sql = "select pid,name,note,price,amount,count,photo from product1 where pid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		ResultSet rs = this.pstmt.executeQuery();
		if(rs.next()) {
			pro = new Product();
			pro.setPid(rs.getInt(1));
			pro.setName(rs.getString(2));
			pro.setNote(rs.getString(3));
			pro.setPrice(rs.getDouble(4));
			pro.setAmount(rs.getInt(5));
			pro.setCount(rs.getInt(6));	
			pro.setPhoto(rs.getString(7));
		}
		return pro;
	}

	@Override
	public List<Product> findAll(String keyword) throws Exception {			//根据关键字查询商品
		throw new Exception("此方法未实现");
	}

	@Override
	public List<Product> findAll(String keyword, int currentPage, int lineSize) throws Exception {		//查找所有的商品信息，并分页
		List<Product> all = new ArrayList<Product>();		//取得所有商品信息
		String sql = "select * from (  " + 
				" select pid,name,note,price,amount,count,photo,ROWNUM rn  " + 
				" from product1 where ( name like ? or note like ? or price like ? or amount like ? ) " +  
				" and  ROWNUM <= ? order by pid ) temp " + 
				" where temp.rn >?";
		this.pstmt = this.conn.prepareStatement(sql);
		pstmt.setString(1, "%" + keyword + "%");
		pstmt.setString(2, "%" + keyword + "%");
		pstmt.setString(3, "%" + keyword + "%");
		pstmt.setString(4, "%" + keyword + "%");
		pstmt.setInt(5, currentPage * lineSize);
		pstmt.setInt(6,(currentPage -1 )*lineSize);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Product pro = new Product();
			pro.setPid(rs.getInt(1));
			pro.setName(rs.getString(2));
			pro.setNote(rs.getString(3));
			pro.setPrice(rs.getDouble(4));
			pro.setAmount(rs.getInt(5));
			pro.setCount(rs.getInt(6));
			pro.setPhoto(rs.getString(7));
			all.add(pro);
		}
		return all;
	}

	@Override
	public long getAllCount(String keyword) throws Exception {
		long count = 0;
		String sql ="select count(pid) from product1 " + 
				" where name like ? or note like ? or price like ? or amount like ?";
		this.pstmt = this.conn.prepareStatement(sql);				//??????????????????????????????????????????????????????
		this.pstmt.setString(1, "%" + keyword + "%");
		this.pstmt.setString(2, "%" + keyword + "%");
		this.pstmt.setString(3, "%" + keyword + "%");
		this.pstmt.setString(4, "%" + keyword + "%");
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			count = rs.getInt(1);
		}
		return count;
	}

	@Override
	public List<Product> findAll(Set<Integer> key) throws Exception {		//查找已经购买的商品
		List<Product> all = new ArrayList<Product>();
		StringBuffer sql = new StringBuffer();
		sql.append("select pid,name,note,price,amount,count,photo from product1 where pid in ( ");		
		int count = 0;	
		Iterator<Integer> iter = key.iterator();			//参数set集合
		while(iter.hasNext()){		//拼sql语句
			count++;
			sql.append(iter.next()) ;
			if(count <= key.size() - 1){
				sql.append(",");
			}
		}
		sql.append(")");
		this.pstmt = this.conn.prepareStatement(sql.toString());			
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()){
			Product pro = new Product();
			pro.setPid(rs.getInt(1));
			pro.setName(rs.getString(2));
			pro.setNote(rs.getString(3));
			pro.setPrice(rs.getDouble(4));
			pro.setAmount(rs.getInt(5));
			pro.setCount(rs.getInt(6));
			pro.setPhoto(rs.getString(7));
			all.add(pro);
		}
		return all;			//返回查找到的已购买的商品集合
	}

	@Override
	public void doUpdateCount(Integer id) throws Exception {					//更新点击量
		String sql = "update product1 set count = count + 1 where pid = ?";
		this.pstmt = this.conn.prepareStatement(sql);
		this.pstmt.setInt(1, id);
		this.pstmt.executeUpdate();

	}

}
