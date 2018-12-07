package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Courier;
import com.neuedu.entity.Orderdetail;

public interface CourierDAO {
	// ����Աע��
	boolean insertCourier(Courier courier);

	// ����couriernumber��������Ա
	Courier checkCouriernumber(String couriernumber);
	
	// ��¼
	Courier findCourier(String couriernumber,String courierpassword);

	// ��ҳ��ѯ����Ա
	List<Courier> findCourierList(int currentPage, int pageSize);

	int findTotalCount();

	// ��������״̬
	boolean updateCourierstatus(String courierstatus, int courierid);

	// ��������Ա����
	boolean updateCourieramount(int courierid);
	
	//���º�����
	boolean updateCourierWellReceived(int courierid);
	
	//����������
	boolean updateCouriercomment(int courierid);
	
	//����id��ѯ����Ա
	Courier findCourier(int courierid);
	
	//��ҳ�鿴����Ա���ͼ�¼
	List<Orderdetail> findCourierOrderlist(int currentPage, int pageSize, int courierid);
	
	int findTotalCount(int courierid);
	
	//���Һ�������ߵ�����Ա
	Courier findMAXCourier();
}
