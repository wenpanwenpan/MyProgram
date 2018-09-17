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
	private DrugDAOImpl drugDAOImpl;		//װ���ʵ�ʲ�����
	
	@Autowired
	private ProviderDAOImpl providerDAOImpl;
	
	@Autowired
	private DrugOutDAOImpl drugOutDAOImpl;
	
	/**
	 * ��ÿһ������ִ��ǰ������ã�����������Ҫ��������Ӧ�޸Ĳ���
	 * */
	@ModelAttribute
	public void getDrug(@RequestParam(value="id",required=false) String id,Map<String,Object> map) throws SQLException {	//ÿ������ǰ��Ҫִ��
		if(id != null) {
			map.put("drug", drugDAOImpl.getDrugById(id));		//ȡ����Ӧid��ҩƷ������������
		}
	}
	
	/**
	 * �г����е�ҩƷ��Ϣ
	 * */
	@RequestMapping(value="/listdrugs",method=RequestMethod.GET)
	public String list(Map<String, Object> map) throws SQLException {
		map.put("drugs",drugDAOImpl.getAllDrug());		//�����ݷ���������
		return "listdrug";
	}
	
	/**
	 * �޸�ҩƷ��Ϣ׼��
	 * ����@PathVariable���ύ�����Ĳ���ӳ�䵽�����У��Ա�ʹ��
	 * Ȼ�󷵻ص��޸�ҩƷ��ҳ�棬��������������һ��Ҫ��һ��ҩƷ������Ϊ
	 * Ĭ����Ҫ���б����Ե�
	 * @throws SQLException 
	 * */
	@RequestMapping(value="/drug/{id}",method=RequestMethod.GET)
	public String input(@PathVariable("id") String id,Map<String,Object> map) throws SQLException {	
		map.put("drug", drugDAOImpl.getDrugById(id));  //��ѯ��ҩƷ��Ϣ������
		map.put("providers",providerDAOImpl.getAllProvider());
		return "input";
	}
	
	/**
	 * ȷ���޸��û���Ϣ,ʹ��PUT�ύ
	 * @throws SQLException 
	 * ʹ��springmvc���ݸ�ʽ��֤
	 * */
	@RequestMapping(value="/drug",method=RequestMethod.PUT)
	public String update( @Valid @ModelAttribute("drug") Drug drug) throws SQLException {	//��Ҫ�����ݿ���ȡҩƷ
		drugDAOImpl.doUpdate(drug);
		return "redirect:/listdrugs";
	}
	
	/**
	 * ���׼��
	 * */
	@RequestMapping(value="/adddrugpre",method=RequestMethod.GET)
	public String adddrug(Map<String,Object> map) throws SQLException {	
		map.put("drug", new Drug());  //��ѯ��ҩƷ��Ϣ������
		map.put("providers",providerDAOImpl.getAllProvider());
		return "adddrug";
	}
	
	/**
	 * ȷ�����,ʹ��POST�ύ
	 * */
	@RequestMapping(value="/adddrug",method=RequestMethod.POST)
	public String adddrug(@Valid Drug drug,BindingResult result,Map<String,Object> map) throws SQLException {	//����Ҫ�����ݿ���ȡҩƷ
		
		//����������ת��������
		if(result.getErrorCount() > 0) {
			System.out.println("�����ˣ�");
			for(FieldError error:result.getFieldErrors()) {
			System.out.println(error.getField() + ":" + error.getDefaultMessage());
			}
			map.put("providers", providerDAOImpl.getAllProvider());			//Ҫ���б��Ļ���
			return "adddrug";
		}
		drugDAOImpl.doAdd(drug);
		
		return "redirect:/listdrugs";
	}
	
	/**
	 * ��ʾҩƷ����ϸ��Ϣ:��Ϊ��    ҩƷ�嵥��ʾ    ��    �ڳ����嵥��ʾ
	 * */
	@RequestMapping(value="/drugdetail/{id}",method=RequestMethod.GET)
	public String drugDetail(@PathVariable("id") String id,Map<String,Object> map) throws SQLException {	
		if( drugDAOImpl.getDrugById(id) == null) {				//�����ڳ���ҩƷ�嵥�в鿴ҩƷ��ϸ��Ϣ
			map.put("drug", drugOutDAOImpl.getDrugById(id));    //��ѯ��ҩƷ��Ϣ������
		}else {
			map.put("drug", drugDAOImpl.getDrugById(id));       //��ѯ��ҩƷ��Ϣ������
		}
		return "drugdetail";
	}
	
	/**
	 * ��ҩƷ��Ϣ����
	 * */
	@RequestMapping(value="/drugout/{id}",method=RequestMethod.GET)
	public String doremove(@PathVariable("id") String id) throws SQLException {	
		drugDAOImpl.doRemove(id);						//ִ�г������
		return "redirect:/listdrugs";
	}
	
	/**
	 * ��ʱ�����׼��
	 * */
	@RequestMapping(value="/drugoutbydatepre")
	public String drugOutByDatePre() {
		return "drugoutbydate";
	}
	
	/**
	 * ���ؼ��ֲ�ѯҩƷ
	 * ����ѯ�Ĺؼ�֮Ϊ�գ���Ĭ��Ϊ��ѯȫ��
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
