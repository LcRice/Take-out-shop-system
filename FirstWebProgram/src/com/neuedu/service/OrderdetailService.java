package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Orderdetail;
import com.neuedu.vo.OrderPage;

public interface OrderdetailService {

	// ��������
	boolean insertDate(int greensid, int orderid, int count);

	// ����id���Ҷ���
	Orderdetail findOrderdetail(int userorderid);

	// ���¶���״̬
	boolean updateOrderstatus(int userorderid, int orderstatus);

	// ��ҳ��ѯδ��ɵĶ���
	OrderPage findOrderPageByUser(int currentPage, int pageSize, int userid);

	// ��ҳ��ѯ����ɵĶ���
	OrderPage findFinishedOrderByUser(int currentPage, int pageSize, int userid);

	// ��ҳ��ѯ�����Ķ���
	OrderPage findFinishedOrderByRest(int currentPage, int pageSize, int restid);
	
	// ��ҳ��ѯ�����Ķ���
	List<Orderdetail> findUnfinishedOrderByRest(int restid);
}
