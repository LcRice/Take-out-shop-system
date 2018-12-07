package com.neuedu.entity;

import java.util.Date;

public class Greencomment {
	private int greencommentid;
	private int userid;
	private int greensid;
	private String greencommenttitle;
	private String greencommentcontext;
	private Date greencommentpubtime;
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getGreencommentid() {
		return greencommentid;
	}
	public void setGreencommentid(int greencommentid) {
		this.greencommentid = greencommentid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getGreensid() {
		return greensid;
	}
	public void setGreensid(int greensid) {
		this.greensid = greensid;
	}
	public String getGreencommenttitle() {
		return greencommenttitle;
	}
	public void setGreencommenttitle(String greencommenttitle) {
		this.greencommenttitle = greencommenttitle;
	}
	public String getGreencommentcontext() {
		return greencommentcontext;
	}
	public void setGreencommentcontext(String greencommentcontext) {
		this.greencommentcontext = greencommentcontext;
	}
	public Date getGreencommentpubtime() {
		return greencommentpubtime;
	}
	public void setGreencommentpubtime(Date greencommentpubtime) {
		this.greencommentpubtime = greencommentpubtime;
	}
	
	
	
}
