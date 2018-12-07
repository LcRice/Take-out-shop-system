package com.neuedu.entity;

import java.util.List;

public class Orderdetail {
	private int userorderid;
	private int greensid;
	private int orderid;
	private int count; //¹ºÂòÊıÁ¿
	private int orderstatus;
	private Greens greens;
	private Ordersummary ordersummary;
	private List<Greens> greenslist;
	private List<Ordersummary> ordersummarieslist;
	
	public int getOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(int orderstatus) {
		this.orderstatus = orderstatus;
	}
	public Greens getGreens() {
		return greens;
	}
	public void setGreens(Greens greens) {
		this.greens = greens;
	}
	public Ordersummary getOrdersummary() {
		return ordersummary;
	}
	public void setOrdersummary(Ordersummary ordersummary) {
		this.ordersummary = ordersummary;
	}
	public List<Greens> getGreenslist() {
		return greenslist;
	}
	public void setGreenslist(List<Greens> greenslist) {
		this.greenslist = greenslist;
	}
	public List<Ordersummary> getOrdersummarieslist() {
		return ordersummarieslist;
	}
	public void setOrdersummarieslist(List<Ordersummary> ordersummarieslist) {
		this.ordersummarieslist = ordersummarieslist;
	}
	public int getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(int userorderid) {
		this.userorderid = userorderid;
	}
	public int getGreensid() {
		return greensid;
	}
	public void setGreensid(int greensid) {
		this.greensid = greensid;
	}
	public int getOrderid() {
		return orderid;
	}
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
