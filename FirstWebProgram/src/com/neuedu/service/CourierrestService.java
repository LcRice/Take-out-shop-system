package com.neuedu.service;

import com.neuedu.entity.Courierrest;

public interface CourierrestService {
	// ��������
	boolean insertCourierrest(int courierid, int restid, int userorderid);

	// ����id���Ҷ���
	Courierrest findCourierrest(int courierid);

	// ����״̬
	boolean updateStatus(int courierrestid);

	// ����״̬���Ҷ���
	Courierrest findCourierrestByUser(int userorderid);
}
