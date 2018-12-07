package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Orderdetail;

public interface OrderdetailDAO {

	// ��������
	boolean insertDate(int greensid, int orderid, int count);

	// ����id���Ҷ���
	Orderdetail findOrderdetail(int userorderid);

	// ���¶���״̬
	boolean updateOrderstatus(int userorderid, int orderstatus);

	// ��ҳ�鿴δ��ɵĶ���
	List<Orderdetail> findOrderdetailListByUser(int currentPage, int pageSize, int userid);

	int findTotalCount(int userid);

	// ��ҳ�鿴����ɵĶ���
	List<Orderdetail> findFinishedOrderListByUser(int currentPage, int pageSize, int userid);

	int findFinishedTotalCount(int userid);

	// ��ҳ�鿴�����Ķ���
	List<Orderdetail> findOrderdetailListByRest(int currentPage, int pageSize, int restid);

	int findRestTotalCount(int restid);
	
	//���ݲ����鿴����ɶ���
	List<Orderdetail> findUnfinishedOrderListByRest(int restid);
	
}
