package com.neuedu.entity;

public class Restaurant {
	private int restid;
	private String superadminnumber;
	private String restname;
	private String restnumber;
	private String restpassword;
	private String restphoto;
	private String restcardnumber;
	private String restcardpassword;
	private int restamount;
	private int restaddress;
	private int restcommentcount;
	private Courier courier;
	
	public Courier getCourier() {
		return courier;
	}
	public void setCourier(Courier courier) {
		this.courier = courier;
	}
	public String getRestphoto() {
		return restphoto;
	}
	public void setRestphoto(String restphoto) {
		this.restphoto = restphoto;
	}
	public String getSuperadminnumber() {
		return superadminnumber;
	}
	public void setSuperadminnumber(String superadminnumber) {
		this.superadminnumber = superadminnumber;
	}
	public int getRestid() {
		return restid;
	}
	public void setRestid(int restid) {
		this.restid = restid;
	}
	public String getRestname() {
		return restname;
	}
	public void setRestname(String restname) {
		this.restname = restname;
	}
	public String getRestnumber() {
		return restnumber;
	}
	public void setRestnumber(String restnumber) {
		this.restnumber = restnumber;
	}
	public String getRestpassword() {
		return restpassword;
	}
	public void setRestpassword(String restpassword) {
		this.restpassword = restpassword;
	}
	public String getRestcardnumber() {
		return restcardnumber;
	}
	public void setRestcardnumber(String restcardnumber) {
		this.restcardnumber = restcardnumber;
	}
	public String getRestcardpassword() {
		return restcardpassword;
	}
	public void setRestcardpassword(String restcardpassword) {
		this.restcardpassword = restcardpassword;
	}
	public int getRestamount() {
		return restamount;
	}
	public void setRestamount(int restamount) {
		this.restamount = restamount;
	}
	public int getRestaddress() {
		return restaddress;
	}
	public void setRestaddress(int restaddress) {
		this.restaddress = restaddress;
	}
	public int getRestcommentcount() {
		return restcommentcount;
	}
	public void setRestcommentcount(int restcommentcount) {
		this.restcommentcount = restcommentcount;
	}
	
	public Restaurant() {
		super();
	}
	public Restaurant(String superadminnumber, String restname, String restnumber, String restpassword,
			String restphoto, String restcardnumber, String restcardpassword, int restamount, int restaddress,
			int restcommentcount) {
		super();
		this.superadminnumber = superadminnumber;
		this.restname = restname;
		this.restnumber = restnumber;
		this.restpassword = restpassword;
		this.restphoto = restphoto;
		this.restcardnumber = restcardnumber;
		this.restcardpassword = restcardpassword;
		this.restamount = restamount;
		this.restaddress = restaddress;
		this.restcommentcount = restcommentcount;
	}
	
	
}
