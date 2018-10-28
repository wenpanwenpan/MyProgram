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

	//保存新增的员工信息
	public void saveEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	/**
	 * 检验用户名是否在数据库中存在
	 * 
	 * @param empName
	 * @return true代表当前姓名在数据库中不存在，可用
	 */
	public boolean checkUser(String empName) {
		EmployeeExample example = new EmployeeExample();
		Criteria criteria = example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count = employeeMapper.countByExample(example);
		return count == 0;	
	}

	/**
	 * 通过ID查询出员工信息，用于员工信息修改页面的员工信息回显
	 * @param empId
	 * @return
	 */
	public Employee getEmp(Integer empId) {
		Employee employee = employeeMapper.selectByPrimaryKey(empId);
		return employee;
	}

	/**
	 * 通过主键，更新修改的员工信息
	 * @param employee
	 */
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
		
	}

	/**
	 * 根据员工ID删除一个员工信息
	 * @param empId
	 */
	public void deleteEmp(Integer empId) {
		employeeMapper.deleteByPrimaryKey(empId);
	}

	/**
	 * 批量删除方法：传入要进行批量删除的员工的id信息，进行批量删除
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
