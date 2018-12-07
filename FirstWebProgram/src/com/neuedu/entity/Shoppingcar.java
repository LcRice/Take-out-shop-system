package com.neuedu.entity;

public class Shoppingcar {
	private int shoppingcarid;
	private int userid;
	private int greensid;
	private int count;
	private Greens greens;
	private User user;
	private Restaurant rest;
	
	public Restaurant getRest() {
		return rest;
	}
	public void setRest(Restaurant rest) {
		this.rest = rest;
	}
	public Greens getGreens() {
		return greens;
	}
	public void setGreens(Greens greens) {
		this.greens = greens;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getShoppingcarid() {
		return shoppingcarid;
	}
	public void setShoppingcarid(int shoppingcarid) {
		this.shoppingcarid = shoppingcarid;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	public Shoppingcar() {
		super();
	}
	public Shoppingcar(int userid, int greensid, int count) {
		super();
		this.userid = userid;
		this.greensid = greensid;
		this.count = count;
	}
	public Shoppingcar(int count, Greens greens, User user, Restaurant rest) {
		super();
		this.count = count;
		this.greens = greens;
		this.user = user;
		this.rest = rest;
	}
	
	
	
}
