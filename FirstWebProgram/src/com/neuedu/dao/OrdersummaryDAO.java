package com.neuedu.dao;

import java.util.Date;

import com.neuedu.entity.Ordersummary;

public interface OrdersummaryDAO {

	//��������
	boolean insertDate(int userid, Date date);
	
	//����order
	int getMaxOrderid();
	
}
