package org.wp.server;

import java.util.Map;
import java.util.Set;

import org.wp.vo.Product;

public interface IFrontServerDAO {
	public Map<String, Object> findOrder(String id,Set<Integer> key) 
			throws Exception;
	public Product findProduct(Integer id,boolean falg)throws Exception;		//用于增加点击量

}
