package com.wp.demo.handler;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wp.demo.dao.impl.DrugDAOImpl;
import com.wp.demo.dao.impl.ProviderDAOImpl;

@Controller
public class ProviderHandler {
	
	@Autowired
	private DrugDAOImpl drugDAOImpl;		//װ���ʵ�ʲ�����
	
	@Autowired
	private ProviderDAOImpl providerDAOImpl;
	
	/**
	 * @throws SQLException 
	 * �鿴�ṩ�̵���ϸ��Ϣ
	 * */
	@RequestMapping(value="/providerdetail/{pid}",method=RequestMethod.GET)
	public String providerDetail(@PathVariable("pid") String pid,Map<String,Object> map) throws SQLException {
		map.put("provider", providerDAOImpl.getProviderById(pid));
		return "providerdetail";
	}
	
	/**
	 * �г�ȫ���Ĺ�Ӧ��
	 * @throws SQLException 
	 * */
	@RequestMapping(value="listallprovider",method=RequestMethod.GET)
	public String listAllProvider(Map<String,Object> map) throws SQLException {
		map.put("providers", providerDAOImpl.getAllProvider());  			//ȡ�����е��ṩ����Ϣ
		return "listallprovider";
	}
	
	
	

}
