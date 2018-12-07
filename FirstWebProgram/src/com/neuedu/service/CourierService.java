package com.neuedu.service;

import com.neuedu.entity.Courier;
import com.neuedu.vo.CourierPage;
import com.neuedu.vo.OrderPage;

public interface CourierService {
	// ����Աע��
	boolean addCourier(Courier courier);

	// ����couriernumber��������Ա
	boolean checkCouriernumber(String couriernumber);

	// ��¼
	Courier findCourier(String couriernumber, String courierpassword);

	// ������ҳ��ѯ���Ա
	CourierPage getCourierPage(int currentPage, int pageSize);

	// ��������״̬
	boolean updateCourierstatus(String courierstatus, int courierid);

	// ��������Ա����
	boolean updateCourieramount(int courierid);

	// ���º�����
	boolean updateCourierWellReceived(int courierid);

	// ����������
	boolean updateCouriercomment(int courierid);

	// ����id��ѯ����Ա
	Courier findCourier(int courierid);

	// ����Ա���ͼ�¼
	OrderPage findOrderlist(int currentPage, int pageSize, int courierid);

	// ���Һ�������ߵ�����Ա
	Courier findMAXCourier();
}
