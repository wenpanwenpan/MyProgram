package com.wp.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wp.crud.bean.Department;
import com.wp.crud.bean.Msg;
import com.wp.crud.service.DepartmentService;

/**
 * ����Ͳ����йص�����Ŀ�����
 * @responseBodyע��������ǽ�controller�ķ������صĶ���ͨ���ʵ���ת����ת��Ϊָ���ĸ�ʽ֮��д�뵽response�����body����ͨ����������JSON���ݻ�����XML���ݡ�
	���ע���ʾ�÷����ķ��ؽ��ֱ��д��HTTP response body�У�һ�����첽��ȡ����ʱʹ�á�
	��ʹ��@RequestMapping�󣬷���ֵͨ������Ϊ��ת·��������@responsebody�󣬷��ؽ��ֱ��д��HTTP response body�У����ᱻ����Ϊ��ת·���������첽����
	ϣ����Ӧ�Ľ����json���ݣ���ô����@responsebody�󣬾ͻ�ֱ�ӷ���json����
 * @author Administrator
 *
 */
@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;

	//ʹ�� @ResponseBody ע����Srping��ɶ��󡪡�Э���ת��
	@RequestMapping(value="/depts")
	@ResponseBody
	public Msg getDepts() {
		List<Department> depts = departmentService.getDepts();
		//������Ŀͻ��˷����Լ���װ���json��ʽ������
		return Msg.success().add("depts", depts);
	}
}
