package com.wp.demo.handler;

import java.sql.SQLException;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wp.demo.dao.impl.DrugDAOImpl;
import com.wp.demo.dao.impl.DrugOutDAOImpl;
import com.wp.demo.dao.impl.ProviderDAOImpl;
import com.wp.demo.vo.Drug;

@Controller
public class DrugHandler {
	
	@Autowired
	private DrugDAOImpl drugDAOImpl;		//装配进实际操作类
	
	@Autowired
	private ProviderDAOImpl providerDAOImpl;
	
	@Autowired
	private DrugOutDAOImpl drugOutDAOImpl;
	
	/**
	 * 在每一个方法执行前都会调用，本程序中主要是用来对应修改操作
	 * */
	@ModelAttribute
	public void getDrug(@RequestParam(value="id",required=false) String id,Map<String,Object> map) throws SQLException {	//每个方法前都要执行
		if(id != null) {
			map.put("drug", drugDAOImpl.getDrugById(id));		//取出对应id的药品。放入属性域
		}
	}
	
	/**
	 * 列出所有的药品信息
	 * */
	@RequestMapping(value="/listdrugs",method=RequestMethod.GET)
	public String list(Map<String, Object> map) throws SQLException {
		map.put("drugs",drugDAOImpl.getAllDrug());		//将数据放入属性域
		return "listdrug";
	}
	
	/**
	 * 修改药品信息准备
	 * 利用@PathVariable将提交过来的参数映射到方法中，以便使用
	 * 然后返回到修改药品的页面，但是在属性域中一定要有一个药品对象，因为
	 * 默认是要进行表单回显的
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/drug/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") String id,Map<String,Object> map) throws SQLException {	
		map.put("drug", drugDAOImpl.getDrugById(id));  //查询出药品信息并保存
		map.put("providers",providerDAOImpl.getAllProvider());
		return "input";
	}
	
	/**
	 * 确认修改用户信息,使用PUT提交
	 * @throws SQLException 
	 * 使用springmvc数据格式验证
	 * */
	@RequestMapping(value="/drug",method=RequestMethod.PUT)
	public String update( @Valid @ModelAttribute("drug") Drug drug) throws SQLException {	//需要从数据库中取药品
		drugDAOImpl.doUpdate(drug);
		return "redirect:/listdrugs";
	}
	
	/**
	 * 添加准备
	 * */
	@RequestMapping(value="/adddrugpre",method=RequestMethod.GET)
	public String adddrug(Map<String,Object> map) throws SQLException {	
		map.put("drug", new Drug());  //查询出药品信息并保存
		map.put("providers",providerDAOImpl.getAllProvider());
		return "adddrug";
	}
	
	/**
	 * 确认添加,使用POST提交
	 * */
	@RequestMapping(value="/adddrug",method=RequestMethod.POST)
	public String adddrug(@Valid Drug drug,BindingResult result,Map<String,Object> map) throws SQLException {	//不需要从数据库中取药品
		
		//若数据类型转换出错了
		if(result.getErrorCount() > 0) {
			System.out.println("出错了！");
			for(FieldError error:result.getFieldErrors()) {
			System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			map.put("providers", providerDAOImpl.getAllProvider());			//要进行表单的回显
			return "adddrug";
		}
		drugDAOImpl.doAdd(drug);
		
		return "redirect:/listdrugs";
	}
	
	/**
	 * 显示药品的详细信息:分为在    药品清单显示    和    在出库清单显示
	 * */
	@RequestMapping(value="/drugdetail/{id}",method=RequestMethod.GET)
	public String drugDetail(@PathVariable("id") String id,Map<String,Object> map) throws SQLException {	
		if( drugDAOImpl.getDrugById(id) == null) {				//若是在出库药品清单中查看药品详细信息
			map.put("drug", drugOutDAOImpl.getDrugById(id));    //查询出药品信息并保存
		}else {
			map.put("drug", drugDAOImpl.getDrugById(id));       //查询出药品信息并保存
		}
		return "drugdetail";
	}
	
	/**
	 * 将药品信息出库
	 * */
	@RequestMapping(value="/drugout/{id}",method=RequestMethod.GET)
	public String doremove(@PathVariable("id") String id) throws SQLException {	
		drugDAOImpl.doRemove(id);						//执行出库操作
		return "redirect:/listdrugs";
	}
	
	/**
	 * 按时间出库准备
	 * */
	@RequestMapping(value="/drugoutbydatepre")
	public String drugOutByDatePre() {
		return "drugoutbydate";
	}
	
	/**
	 * 按关键字查询药品
	 * 若查询的关键之为空，则默认为查询全部
	 * @throws SQLException 
	 * */
	@RequestMapping(value="querybykey")
	public String queryByKey(@RequestParam(value="key",required=false) String key,Map<String, Object> map) throws SQLException {
		if(key.equals("") || key == null) {
			map.put("drugs",drugDAOImpl.getAllDrug());
		}else {
			map.put("drugs", drugDAOImpl.doqueryByKey(key));
		}
		
		return "listdrug";
	}

}
