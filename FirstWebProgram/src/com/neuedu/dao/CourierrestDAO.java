package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Courierrest;

public interface CourierrestDAO {
	//��������
	boolean insertCourierrest(int courierid, int restid, int userorderid);
	
	//����id���Ҷ���
	Courierrest findCourierrest(int courierid);
	
	//����״̬
	boolean updateStatus(int courierrestid);
	
	//����״̬���Ҷ���
	Courierrest findCourierrestByUser(int userorderid);
	
}
