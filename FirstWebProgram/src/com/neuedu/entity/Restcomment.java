package com.neuedu.entity;

import java.util.Date;

public class Restcomment {
	private int restcommentid;
	private int userid;
	private int restid;
	private String restcommenttitle;
	private String restcommentcontext;
	private Date restcommentpubtime;
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getRestcommentid() {
		return restcommentid;
	}
	public void setRestcommentid(int restcommentid) {
		this.restcommentid = restcommentid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getRestid() {
		return restid;
	}
	public void setRestid(int restid) {
		this.restid = restid;
	}
	public String getRestcommenttitle() {
		return restcommenttitle;
	}
	public void setRestcommenttitle(String restcommenttitle) {
		this.restcommenttitle = restcommenttitle;
	}
	public String getRestcommentcontext() {
		return restcommentcontext;
	}
	public void setRestcommentcontext(String restcommentcontext) {
		this.restcommentcontext = restcommentcontext;
	}
	public Date getRestcommentpubtime() {
		return restcommentpubtime;
	}
	public void setRestcommentpubtime(Date restcommentpubtime) {
		this.restcommentpubtime = restcommentpubtime;
	}
	
	
}
