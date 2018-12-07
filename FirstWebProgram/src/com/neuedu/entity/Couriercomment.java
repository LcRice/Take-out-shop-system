package com.neuedu.entity;

import java.util.Date;

public class Couriercomment {
	private int couriercommentid;
	private int userid;
	private int courierid;
	private String couriercommenttitle;
	private String couriercommentcontext;
	private Date couriercommentpubtime;
	private int score;
	
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getCouriercommentid() {
		return couriercommentid;
	}
	public void setCouriercommentid(int couriercommentid) {
		this.couriercommentid = couriercommentid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getCourierid() {
		return courierid;
	}
	public void setCourierid(int courierid) {
		this.courierid = courierid;
	}
	public String getCouriercommenttitle() {
		return couriercommenttitle;
	}
	public void setCouriercommenttitle(String couriercommenttitle) {
		this.couriercommenttitle = couriercommenttitle;
	}
	public String getCouriercommentcontext() {
		return couriercommentcontext;
	}
	public void setCouriercommentcontext(String couriercommentcontext) {
		this.couriercommentcontext = couriercommentcontext;
	}
	public Date getCouriercommentpubtime() {
		return couriercommentpubtime;
	}
	public void setCouriercommentpubtime(Date couriercommentpubtime) {
		this.couriercommentpubtime = couriercommentpubtime;
	}
	
}
