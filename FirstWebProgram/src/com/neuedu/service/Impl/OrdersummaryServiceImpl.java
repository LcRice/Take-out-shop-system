package com.neuedu.service.Impl;

import java.util.Date;

import com.neuedu.dao.OrdersummaryDAO;
import com.neuedu.dao.Impl.OrdersummaryDAOImpl;
import com.neuedu.service.OrdersummaryService;

public class OrdersummaryServiceImpl implements OrdersummaryService {

	private OrdersummaryDAO ordersummaryDAO = new OrdersummaryDAOImpl();
	
	public boolean insertDate(int userid, Date date) {
		// TODO Auto-generated method stub
		return ordersummaryDAO.insertDate(userid,date);
	}
	
	public int getMaxOrderid() {
		// TODO Auto-generated method stub
		return ordersummaryDAO.getMaxOrderid();
	}

}
