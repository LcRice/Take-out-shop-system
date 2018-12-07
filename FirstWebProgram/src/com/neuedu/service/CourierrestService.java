package com.neuedu.service;

import com.neuedu.entity.Courierrest;

public interface CourierrestService {
	// 插入数据
	boolean insertCourierrest(int courierid, int restid, int userorderid);

	// 根据id查找对象
	Courierrest findCourierrest(int courierid);

	// 更新状态
	boolean updateStatus(int courierrestid);

	// 根据状态查找对象
	Courierrest findCourierrestByUser(int userorderid);
}
