package com.wp.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.crud.bean.Employee;
import com.wp.crud.bean.EmployeeExample;
import com.wp.crud.bean.EmployeeExample.Criteria;
import com.wp.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	public List<Employee> getAll() {
		List<Employee> emps = employeeMapper.selectByExampleWithDept(null);
		return emps;
	}

	//����������Ա����Ϣ
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * �����û����Ƿ������ݿ��д���
	 * 
	 * @param empName
	 * @return true����ǰ���������ݿ��в����ڣ�����
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;	
	}

	/**
	 * ͨ��ID��ѯ��Ա����Ϣ������Ա����Ϣ�޸�ҳ���Ա����Ϣ����
	 * @param empId
	 * @return
	 */
	public Employee getEmp(Integer empId) {
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	/**
	 * ͨ�������������޸ĵ�Ա����Ϣ
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	/**
	 * ����Ա��IDɾ��һ��Ա����Ϣ
	 * @param empId
	 */
	public void deleteEmp(Integer empId) {
		employeeMapper.deleteByPrimaryKey(empId);
	}

	/**
	 * ����ɾ������������Ҫ��������ɾ����Ա����id��Ϣ����������ɾ��
	 * @param empIds
	 */
	public void deleteEmpBatch(List<Integer> empIds) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		
		//delete from xxx where emp_id in(x,x,x,x);
		criteria.andEmpIdIn(empIds);
		employeeMapper.deleteByExample(example);
		
	}

}
