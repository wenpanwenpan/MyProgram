package com.wp.demo.vo;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Drug {
	
	@NotEmpty
	private String did;
	@NotEmpty
	private String dname;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String ddate;
	@NotEmpty
	private String daddress;
	private Provider provider;
	@NotEmpty
	private String dgrade;
	@NotEmpty
	private String dperson;
	private String dnote;
	
	public Drug() {}

	public Drug(String did, String dname, String ddate, String daddress, Provider provider, String dgrade,
			String dperson, String dnote) {
		super();
		this.did = did;
		this.dname = dname;
		this.ddate = ddate;
		this.daddress = daddress;
		this.provider = provider;
		this.dgrade = dgrade;
		this.dperson = dperson;
		this.dnote = dnote;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDdate() {
		return ddate;
	}

	public void setDdate(String ddate) {
		this.ddate = ddate;
	}

	public String getDaddress() {
		return daddress;
	}

	public void setDaddress(String daddress) {
		this.daddress = daddress;
	}

	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}

	public String getDgrade() {
		return dgrade;
	}

	public void setDgrade(String dgrade) {
		this.dgrade = dgrade;
	}

	public String getDperson() {
		return dperson;
	}

	public void setDperson(String dperson) {
		this.dperson = dperson;
	}

	public String getDnote() {
		return dnote;
	}

	public void setDnote(String dnote) {
		this.dnote = dnote;
	}
	

	@Override
	public String toString() {
		return "Drug [did=" + did + ", dname=" + dname + ", ddate=" + ddate + ", daddress=" + daddress + ", provider="
				+ provider + ", dgrade=" + dgrade + ", dperson=" + dperson + ", dnote=" + dnote + "]";
	}
	
	

}
