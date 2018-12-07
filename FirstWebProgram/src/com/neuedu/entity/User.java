package com.neuedu.entity;

public class User {
	private int userid;
	private String superadminnumber;
	private String username;
	private String usernumber;
	private String userpassword;
	private String userphoto;
	private String usercardnumber;
	private String usercardpassword;
	private int useramount;
	private int useraddress;
	
	public String getUserphoto() {
		return userphoto;
	}
	public void setUserphoto(String userphoto) {
		this.userphoto = userphoto;
	}
	public String getSuperadminnumber() {
		return superadminnumber;
	}
	public void setSuperadminnumber(String superadminnumber) {
		this.superadminnumber = superadminnumber;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsernumber() {
		return usernumber;
	}
	public void setUsernumber(String usernumber) {
		this.usernumber = usernumber;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUsercardnumber() {
		return usercardnumber;
	}
	public void setUsercardnumber(String usercardnumber) {
		this.usercardnumber = usercardnumber;
	}
	public String getUsercardpassword() {
		return usercardpassword;
	}
	public void setUsercardpassword(String usercardpassword) {
		this.usercardpassword = usercardpassword;
	}
	public int getUseramount() {
		return useramount;
	}
	public void setUseramount(int useramount) {
		this.useramount = useramount;
	}
	public int getUseraddress() {
		return useraddress;
	}
	public void setUseraddress(int useraddress) {
		this.useraddress = useraddress;
	}
	
	public User() {
		super();
	}
	public User(String superadminnumber, String username, String usernumber, String userpassword, String userphoto,
			String usercardnumber, String usercardpassword, int useramount, int useraddress) {
		super();
		this.superadminnumber = superadminnumber;
		this.username = username;
		this.usernumber = usernumber;
		this.userpassword = userpassword;
		this.userphoto = userphoto;
		this.usercardnumber = usercardnumber;
		this.usercardpassword = usercardpassword;
		this.useramount = useramount;
		this.useraddress = useraddress;
	}
	
	
	
	
}
