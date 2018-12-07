package com.neuedu.entity;

public class Courier {
	private int courierid;
	private String superadminnumber;
	private String couriername;
	private String couriernumber;
	private String courierpassword;
	private String courierphoto;
	private int couriercamount;
	private String courierstatus;
	private int couriercommentcount;
	private int courierwellreceived;
	
	
	public int getCouriercamount() {
		return couriercamount;
	}
	public void setCouriercamount(int couriercamount) {
		this.couriercamount = couriercamount;
	}
	public String getSuperadminnumber() {
		return superadminnumber;
	}
	public void setSuperadminnumber(String superadminnumber) {
		this.superadminnumber = superadminnumber;
	}
	public String getCourierphoto() {
		return courierphoto;
	}
	public void setCourierphoto(String courierphoto) {
		this.courierphoto = courierphoto;
	}
	public int getCourierid() {
		return courierid;
	}
	public void setCourierid(int courierid) {
		this.courierid = courierid;
	}
	public String getCouriername() {
		return couriername;
	}
	public void setCouriername(String couriername) {
		this.couriername = couriername;
	}
	public String getCouriernumber() {
		return couriernumber;
	}
	public void setCouriernumber(String couriernumber) {
		this.couriernumber = couriernumber;
	}
	public String getCourierpassword() {
		return courierpassword;
	}
	public void setCourierpassword(String courierpassword) {
		this.courierpassword = courierpassword;
	}
	public String getCourierstatus() {
		return courierstatus;
	}
	public void setCourierstatus(String courierstatus) {
		this.courierstatus = courierstatus;
	}
	public int getCouriercommentcount() {
		return couriercommentcount;
	}
	public void setCouriercommentcount(int couriercommentcount) {
		this.couriercommentcount = couriercommentcount;
	}
	public int getCourierwellreceived() {
		return courierwellreceived;
	}
	public void setCourierwellreceived(int courierwellreceived) {
		this.courierwellreceived = courierwellreceived;
	}
	
	public Courier() {
		super();
	}
	public Courier(String superadminnumber, String couriername, String couriernumber, String courierpassword,
			String courierphoto, int couriercamount, String courierstatus, int couriercommentcount,
			int courierwellreceived) {
		super();
		this.superadminnumber = superadminnumber;
		this.couriername = couriername;
		this.couriernumber = couriernumber;
		this.courierpassword = courierpassword;
		this.courierphoto = courierphoto;
		this.couriercamount = couriercamount;
		this.courierstatus = courierstatus;
		this.couriercommentcount = couriercommentcount;
		this.courierwellreceived = courierwellreceived;
	}
	
	
	
}
