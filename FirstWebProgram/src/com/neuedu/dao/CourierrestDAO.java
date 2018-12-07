package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Courierrest;

public interface CourierrestDAO {
	//插入数据
	boolean insertCourierrest(int courierid, int restid, int userorderid);
	
	//根据id查找对象
	Courierrest findCourierrest(int courierid);
	
	//更新状态
	boolean updateStatus(int courierrestid);
	
	//根据状态查找对象
	Courierrest findCourierrestByUser(int userorderid);
	
}
