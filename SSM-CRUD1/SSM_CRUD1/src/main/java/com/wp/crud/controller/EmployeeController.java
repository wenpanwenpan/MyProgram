package com.wp.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.crud.bean.Employee;
import com.wp.crud.bean.Msg;
import com.wp.crud.service.EmployeeService;

/**
 * ����Ա����CRUD����
 * */

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	/**
	 * ��json�ĸ�ʽ��ǰ̨���󷵻����ݣ������Ϳ���ʵ�ֿ�ƽ̨�ͣ���������������󣬰�׿����iOS
	 * ��Ա���ķ�ҳ��ѯ�����������һ������ajax�ķ���
	 * @param pn
	 * @param model
	 * @return
	 */
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1") Integer pn) {
		//�ⲻ��һ����ҳ��ѯ��
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ�������·���,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn,5);
		//startPage��������������ѯ����һ����ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
				
		//ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ����������,����������ʾ��ҳ��eg:5  ��ʾ������ʾ5ҳ
		PageInfo<Employee> page = new PageInfo<>(emps,5);
		
		//�����Լ���װ�Ķ����success���������ҵ���add���������������Ա����Ϣ��ӵ�Msg����ļ����С�ֱ�ӷ��ض��������
		return Msg.success().add("pageInfo", page);
		
	}
	
	/**
	 * ��ѯ��ȫ��Ա������Ϣ��
	 * ���Ǵ�ͳ�ķ��ط��������ص�����ֻ����������ܽ�������׿��iOS�޷�����
	 * 
	 * */
	//@RequestMapping("/emps")
	public String getEmps(@RequestParam(value="pn",defaultValue="1") Integer pn,
			Model model) {
		//�ⲻ��һ����ҳ��ѯ��
		//����PageHelper��ҳ���
		//�ڲ�ѯ֮ǰֻ��Ҫ�������·���,����ҳ�룬�Լ�ÿҳ�Ĵ�С
		PageHelper.startPage(pn,5);
		//startPage��������������ѯ����һ����ҳ��ѯ
		List<Employee> emps = employeeService.getAll();
		
		//ʹ��pageInfo��װ��ѯ��Ľ����ֻ��Ҫ��pageInfo����ҳ�������
		//��װ����ϸ�ķ�ҳ��Ϣ���������ǲ�ѯ����������,����������ʾ��ҳ��eg:5  ��ʾ������ʾ5ҳ
		PageInfo<Employee> page = new PageInfo<>(emps,5);
		
		//ʹ��model����ѯ������Ա�����浽request��������
		model.addAttribute("pageInfo",page);
		
		return "list";
	}
	
	/**
	 * ��������Ա��
	 * BindingResult ��������֤���صĴ��󼯺�
	 */
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	@ResponseBody
	public Msg saveEmp(@Valid Employee employee,BindingResult result) {
		//У��ʧ��Ӧ�÷���ʧ�ܣ���ģ̬������ʾУ��ʧ�ܵĴ�����Ϣ
		if(result.hasErrors()) {
			Map<String,Object> map = new HashMap<>();
			List<FieldError> errors = result.getFieldErrors();
			for(FieldError fieldError:errors) {
				//System.out.println("�����ֶε����֣�" + fieldError.getField());
				//System.out.println("������Ϣ��" + fieldError.getDefaultMessage());
				//�����е�У�������Ϣ��������map������
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("errorFields", map);
		}else {
			employeeService.saveEmp(employee);
		}
		
		return Msg.success();
	}
	
	/**
	 * �����������Ա��ʱ��Ա�����������ʧȥ����󷢳�ajax������֤���û����Ƿ��Ѿ���ռ��
	 * ʹ��@RequestParam ע�⣬��ȷ�ĸ���springmvc����Ҫ��empName�������
	 * ���ǵ�ַ�����������ύ��ʽһ��Ҫ��ΪPOST����Ȼ����ֺ�̨���յ�����Ϊ����
	 * @param empName
	 * @return
	 */
	@RequestMapping(value="/checkuser",method=RequestMethod.POST)
	@ResponseBody
	public Msg checkUser(@RequestParam("empName") String empName) {
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})";
		if(!empName.matches(regex)) {
			//System.out.println("Ա��������" + empName);
			return Msg.fail().add("va_msg", "�û�����ʽ����ȷ��ӦΪ6-16λ��ĸ��-�����ֻ�2-5λ���ģ�");
		}
		
		if(employeeService.checkUser(empName)) {
			return Msg.success().add("va_msg", "�û������ã�");
		}else {
			return Msg.fail().add("va_msg", "�û����ѱ�ռ�ã�");
		}
		
	}
	
	/**
	 * @PathVariable ע���ʾ��·����ȡ��empId
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("empId") Integer empId) {
		Employee employee = employeeService.getEmp(empId);
		//System.out.println("employee:" + employee);
		return Msg.success().add("emp", employee);
	}
	
	/**
	 * �����޸ĵ�Ա����Ϣ
	 * 
	 * ���ֱ�ӷ���ajax=PUT��ʽ������
	 * ��װ������
	 * Employee 
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * ���⣺
	 * �������������ݣ�
	 * ����Employee�����װ���ϣ�
	 * update tbl_emp  where emp_id = 1014;
	 * 
	 * ԭ��
	 * Tomcat��
	 * 		1�����������е����ݣ���װһ��map��
	 * 		2��request.getParameter("empName")�ͻ�����map��ȡֵ��
	 * 		3��SpringMVC��װPOJO�����ʱ��
	 * 				���POJO��ÿ�����Ե�ֵ��request.getParamter("email");
	 * AJAX����PUT����������Ѫ����
	 * 		PUT�����������е����ݣ�request.getParameter("empName")�ò���
	 * 		Tomcatһ����PUT�����װ�������е�����Ϊmap��ֻ��POST��ʽ������ŷ�װ������Ϊmap
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * ���������
	 * ����Ҫ��֧��ֱ�ӷ���PUT֮�������Ҫ��װ�������е�����
	 * 1��������HttpPutFormContentFilter��
	 * 2���������ã����������е����ݽ�����װ��һ��map��
	 * 3��request�����°�װ��request.getParameter()����д���ͻ���Լ���װ��map��ȡ����
	 * Ա�����·���
	 * @param employee
	 * @return
	 */
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	@ResponseBody
	public Msg saveEmp(Employee employee,HttpServletRequest request) {
		employeeService.updateEmp(employee);
		//System.out.println("�������е�ֵ��"+request.getParameter("gender"));
		//System.out.println("��̨���յ���employee��" + employee);
		return Msg.success();
	}
	
	
	/**
	 * ����ɾ��-����ɾ������һ
	 * ����Ա��id������ɾ��Ա����Ϣ
	 * @PathVariableע�⣺ָ���뷽���Ĳ����Ǵ�·����ȡ�õ�
	 * @param empId
	 * @return
	 */
	@RequestMapping(value="/emp/{empIds}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg deleteEmp(@PathVariable("empIds")String empIds) {
		if(empIds.contains("-")) {
			//����ɾ��Ա����Ϣ
			ArrayList<Integer> list = new ArrayList<>();
			String emp_ids[] = empIds.split("-");
			
			//ȡ�����е�Ա��ID���ҷŵ�list������
			for(String empid:emp_ids) {
				list.add(Integer.parseInt(empid));
			}
			
			//����Ա��ID��list����
			employeeService.deleteEmpBatch(list);
		}else {
			Integer empId = Integer.parseInt(empIds);
			employeeService.deleteEmp(empId);
		}
		return Msg.success();
	}
	
	
	
	
	
	
	
	
	
	
	
}
