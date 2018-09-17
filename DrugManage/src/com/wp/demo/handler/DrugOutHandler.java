package com.wp.demo.handler;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.demo.dao.impl.DrugDAOImpl;
import com.wp.demo.dao.impl.DrugOutDAOImpl;
import com.wp.demo.vo.Drug;
import com.wp.demo.vo.Provider;

@Controller
public class DrugOutHandler {
	
	@Autowired
	private DrugOutDAOImpl drugOutDAOImpl;		//装配进实际操作类
	
	@Autowired
	private DrugDAOImpl drugDAOImpl;
	
	/**
	 * 列出所有的出库药品信息
	 * */
	@RequestMapping(value="/showdrugout",method=RequestMethod.GET)
	public String list(Map<String, Object> map) throws SQLException {
		map.put("drugs",drugOutDAOImpl.getAllDrug());		//将数据放入属性域
		return "showdrugout";
	}
	
	/**
	 * 将出库的药品再次入库
	 * @throws SQLException 
	 * 从出库表中查找到商品，然后进行重新入库，并且要在出库表中删除该条信息
	 * 重新入库后默认是101药厂
	 * 用事务进行处理
	 * */
	@RequestMapping(value="adddrugagin/{did}",method=RequestMethod.GET)
	public String adddrugagin(@PathVariable("did") String did) throws SQLException {
		Drug drug = null;
		drug = drugOutDAOImpl.getDrugById(did);				//从出库药品中查找到具体信息
		drug.setProvider(new Provider());
		drug.getProvider().setPid("101");        			//重新入库后默认是101药厂
		drugDAOImpl.doAdd(drug);							//重新入库
		drugOutDAOImpl.doRemove(did);						//在出库表中进行删除操作
		
		return "redirect:/showdrugout";
	}
	
	/**
	 * 按日期将过期药品出库
	 * 先要进行将要删除的药品出库到出库表中
	 * 然后进行从库存表中删除，此处应该用声明式事务处理
	 * 配置文件中配置事务管理器，启用事务注解，然后在需要使用事务的方法上声明使用事务
	 * @throws SQLException 
	 * */
	@Transactional
	@RequestMapping(value="/drugOutByDate",method=RequestMethod.POST)
	public String drugOutByDate(@RequestParam(value="date",required=true) String date) throws SQLException {		//需要传入请求参数
		
		drugOutDAOImpl.addDrugByDate(date);					//添加到出库表
		drugDAOImpl.doRemoveByDate(date);					//从库存表中删除
		
		return "redirect:/listdrugs";
	}
	
	/**
	 * 从出库表中彻底删除
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/drugRemoveCompelete/{id}",method=RequestMethod.DELETE)
	public String drugRemoveCompelete(@PathVariable("id") String id) throws SQLException {
		drugOutDAOImpl.doRemove(id);
		return "redirect:/showdrugout";
	}
	
	
	/**
	 * 通过关键字查询出库的药品信息
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/doqueryDrugOutByKey",method=RequestMethod.POST)
	public String doqueryByKey(@RequestParam(value="key",required=false) String key,Map<String , Object> map) throws SQLException {
		drugOutDAOImpl.doqueryByKey(key);
		if(key.equals("") || key == null) {
			map.put("drugs",drugOutDAOImpl.getAllDrug());
		}else {
			map.put("drugs", drugOutDAOImpl.doqueryByKey(key));
		}
		return "showdrugout";
	}
	
	

}
