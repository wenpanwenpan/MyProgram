package org.wp.vo;

import java.util.Date;

public class Emp {		//javabean
	private Integer empno;
	private String ename;
	private String job;
	private Date hiredate;
	private double sal ;
	private double comm;
	private Emp mgr;	//雇员领导
	private Dept dept;	//一个雇员属于一个部门
	
	public Dept getDept() {
		return dept;
	}
	public void setDept(Dept dept) {
		this.dept = dept;
	}
	public Emp getMgr() {
		return mgr;
	}
	public void setMgr(Emp mgr) {
		this.mgr = mgr;
	}
	public Integer getEmpno() {
		return empno;
	}
	public void setEmpno(Integer empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public double getSal() {
		return sal;
	}
	public void setSal(double sal) {
		this.sal = sal;
	}
	public double getComm() {
		return comm;
	}
	public void setComm(double comm) {
		this.comm = comm;
	} 

}
