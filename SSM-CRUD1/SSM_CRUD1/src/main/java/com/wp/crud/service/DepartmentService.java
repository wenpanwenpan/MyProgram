package com.wp.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wp.crud.bean.Department;
import com.wp.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;
	
	//返回所有的部门信息
	public List<Department> getDepts() {
		return departmentMapper.selectByExample(null);
	}

}
