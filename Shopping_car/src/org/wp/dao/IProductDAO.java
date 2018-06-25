package org.wp.dao;

import java.util.List;
import java.util.Set;

import org.wp.vo.Product;

public interface IProductDAO extends IDAO<Product, Integer> {
	public List<Product> findAll(Set<Integer> key) throws Exception;
	public void doUpdateCount(Integer id) throws Exception;

}
