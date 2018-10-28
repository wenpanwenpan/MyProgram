package com.wp.crud.bean;

import javax.validation.constraints.Pattern;

/**
 * 使用JSR303进行校验，为了有些用户绕过前端验证，直接提交输入信息。所以在提交到服务器时还要进行后端的验证
 * @Pattern 自定义的校验规则
 * @author Administrator
 *
 */
public class Employee {
    private Integer empId;

	@Pattern(regexp="(^[a-zA-Z0-9_-]{6,16}$)|(^[\\u2E80-\\u9FFF]{2,5})",
    		message="用户名格式不正确，应为6-16位字母或2-5位中文！")
    private String empName;

    private String gender;
    
    @Pattern(regexp="^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$",
    		message="邮箱格式错误！")
    private String email;

    private Integer dId;

    //希望查询员工的同时部门信息也是查询出来的
    private Department department;
    
    public Employee() {
		super();
	}

	public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.gender = gender;
		this.email = email;
		this.dId = dId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
    
    @Override
  	public String toString() {
  		return "Employee [empId=" + empId + ", empName=" + empName + ", gender=" + gender + ", email=" + email
  				+ ", dId=" + dId + ", department=" + department + "]";
  	}
}