package com.neuedu.dao;

import java.util.Date;

import com.neuedu.entity.Ordersummary;

public interface OrdersummaryDAO {

	//插入数据
	boolean insertDate(int userid, Date date);
	
	//查找order
	int getMaxOrderid();
	
}
