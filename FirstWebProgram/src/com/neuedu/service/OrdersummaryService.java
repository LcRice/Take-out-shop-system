package com.neuedu.service;

import java.util.Date;

public interface OrdersummaryService {

	// ��������
	boolean insertDate(int userid, Date date);

	// ����order
	int getMaxOrderid();

}
