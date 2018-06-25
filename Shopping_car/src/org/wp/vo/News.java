package org.wp.vo;

import java.io.Serializable;
import java.util.Date;

public class News implements Serializable {
	private Integer nid;
	private String title;
	private String author;
	private Date pubdate;
	private String content;
	private Integer lockflag;
	public Integer getNid() {
		return nid;
	}
	public void setNid(Integer nid) {
		this.nid = nid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getPubdate() {
		return pubdate;
	}
	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getLockflag() {
		return lockflag;
	}
	public void setLockflag(Integer lockflag) {
		this.lockflag = lockflag;
	}
	
	
}
