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
	private DrugOutDAOImpl drugOutDAOImpl;		//װ���ʵ�ʲ�����
	
	@Autowired
	private DrugDAOImpl drugDAOImpl;
	
	/**
	 * �г����еĳ���ҩƷ��Ϣ
	 * */
	@RequestMapping(value="/showdrugout",method=RequestMethod.GET)
	public String list(Map<String, Object> map) throws SQLException {
		map.put("drugs",drugOutDAOImpl.getAllDrug());		//�����ݷ���������
		return "showdrugout";
	}
	
	/**
	 * �������ҩƷ�ٴ����
	 * @throws SQLException 
	 * �ӳ�����в��ҵ���Ʒ��Ȼ�����������⣬����Ҫ�ڳ������ɾ��������Ϣ
	 * ��������Ĭ����101ҩ��
	 * ��������д���
	 * */
	@RequestMapping(value="adddrugagin/{did}",method=RequestMethod.GET)
	public String adddrugagin(@PathVariable("did") String did) throws SQLException {
		Drug drug = null;
		drug = drugOutDAOImpl.getDrugById(did);				//�ӳ���ҩƷ�в��ҵ�������Ϣ
		drug.setProvider(new Provider());
		drug.getProvider().setPid("101");        			//��������Ĭ����101ҩ��
		drugDAOImpl.doAdd(drug);							//�������
		drugOutDAOImpl.doRemove(did);						//�ڳ�����н���ɾ������
		
		return "redirect:/showdrugout";
	}
	
	/**
	 * �����ڽ�����ҩƷ����
	 * ��Ҫ���н�Ҫɾ����ҩƷ���⵽�������
	 * Ȼ����дӿ�����ɾ�����˴�Ӧ��������ʽ������
	 * �����ļ��������������������������ע�⣬Ȼ������Ҫʹ������ķ���������ʹ������
	 * @throws SQLException 
	 * */
	@Transactional
	@RequestMapping(value="/drugOutByDate",method=RequestMethod.POST)
	public String drugOutByDate(@RequestParam(value="date",required=true) String date) throws SQLException {		//��Ҫ�����������
		
		drugOutDAOImpl.addDrugByDate(date);					//��ӵ������
		drugDAOImpl.doRemoveByDate(date);					//�ӿ�����ɾ��
		
		return "redirect:/listdrugs";
	}
	
	/**
	 * �ӳ�����г���ɾ��
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/drugRemoveCompelete/{id}",method=RequestMethod.DELETE)
	public String drugRemoveCompelete(@PathVariable("id") String id) throws SQLException {
		drugOutDAOImpl.doRemove(id);
		return "redirect:/showdrugout";
	}
	
	
	/**
	 * ͨ���ؼ��ֲ�ѯ�����ҩƷ��Ϣ
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
