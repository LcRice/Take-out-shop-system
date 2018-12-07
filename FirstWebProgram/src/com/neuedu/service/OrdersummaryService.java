package com.neuedu.service;

import java.util.Date;

public interface OrdersummaryService {

	// 插入数据
	boolean insertDate(int userid, Date date);

	// 查找order
	int getMaxOrderid();

}
