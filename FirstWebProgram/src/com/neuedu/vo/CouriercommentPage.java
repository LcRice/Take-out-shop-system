package com.neuedu.vo;

import com.neuedu.entity.Couriercomment;

public class CouriercommentPage extends BasePage<Couriercomment>{
	
	private Integer begin;
	private Integer end;
	
	public Integer getBegin() {
		return begin;
	}
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}
}
