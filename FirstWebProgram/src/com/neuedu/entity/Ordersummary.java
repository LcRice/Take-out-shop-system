package com.neuedu.entity;

import java.util.Date;
import java.util.List;

public class Ordersummary {
	private int orderid;
	private int userid;
	private Date ordertime;
	private User user;
	private List<User> userslist;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserslist() {
		return userslist;
	}
	public void setUserslist(List<User> userslist) {
		this.userslist = userslist;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	
}
