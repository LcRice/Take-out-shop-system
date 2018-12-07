package com.neuedu.entity;

import java.util.List;

public class Greens {
	private int greensid;
	private String greensname;
	private int greensprice;
	private String greensphoto;
	private int greensnumber;
	private int greenscommentcount;
	private Restaurant rest;
	private List<Restaurant> restslist;
	
	public List<Restaurant> getRestslist() {
		return restslist;
	}
	public void setRestslist(List<Restaurant> restslist) {
		this.restslist = restslist;
	}
	public String getGreensphoto() {
		return greensphoto;
	}
	public void setGreensphoto(String greensphoto) {
		this.greensphoto = greensphoto;
	}
	public int getGreensid() {
		return greensid;
	}
	public void setGreensid(int greensid) {
		this.greensid = greensid;
	}
	public String getGreensname() {
		return greensname;
	}
	public void setGreensname(String greensname) {
		this.greensname = greensname;
	}
	public int getGreensprice() {
		return greensprice;
	}
	public void setGreensprice(int greensprice) {
		this.greensprice = greensprice;
	}
	public int getGreensnumber() {
		return greensnumber;
	}
	public void setGreensnumber(int greensnumber) {
		this.greensnumber = greensnumber;
	}
	public int getGreenscommentcount() {
		return greenscommentcount;
	}
	public void setGreenscommentcount(int greenscommentcount) {
		this.greenscommentcount = greenscommentcount;
	}
	public Restaurant getRest() {
		return rest;
	}
	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	
	public Greens() {
		super();
	}
	public Greens(String greensname, int greensprice, String greensphoto, int greensnumber, int greenscommentcount,
			Restaurant rest) {
		super();
		this.greensname = greensname;
		this.greensprice = greensprice;
		this.greensphoto = greensphoto;
		this.greensnumber = greensnumber;
		this.greenscommentcount = greenscommentcount;
		this.rest = rest;
	}
	
	
	
}
