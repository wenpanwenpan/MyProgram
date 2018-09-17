package com.wp.demo.vo;

public class Provider {
	private String pid;
	private String pname;
	private String paddress;
	private String ptel;
	private String pemail;
	private String pgrade;
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPaddress() {
		return paddress;
	}
	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}
	public String getPtel() {
		return ptel;
	}
	public void setPtel(String ptel) {
		this.ptel = ptel;
	}
	public String getPemail() {
		return pemail;
	}
	public void setPemail(String pemail) {
		this.pemail = pemail;
	}
	public String getPgrade() {
		return pgrade;
	}
	public void setPgrade(String pgrade) {
		this.pgrade = pgrade;
	}
	public Provider() {}
	public Provider(String pid, String pname, String paddress, String ptel, String pemail, String pgrade) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.paddress = paddress;
		this.ptel = ptel;
		this.pemail = pemail;
		this.pgrade = pgrade;
	}
	@Override
	public String toString() {
		return "Provider [pid=" + pid + ", pname=" + pname + ", paddress=" + paddress + ", ptel=" + ptel + ", pemail="
				+ pemail + ", pgrade=" + pgrade + "]";
	}

}
