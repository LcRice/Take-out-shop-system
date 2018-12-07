package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Courier;
import com.neuedu.entity.Orderdetail;

public interface CourierDAO {
	// 配送员注册
	boolean insertCourier(Courier courier);

	// 根据couriernumber查找配送员
	Courier checkCouriernumber(String couriernumber);
	
	// 登录
	Courier findCourier(String couriernumber,String courierpassword);

	// 分页查询配送员
	List<Courier> findCourierList(int currentPage, int pageSize);

	int findTotalCount();

	// 更新配送状态
	boolean updateCourierstatus(String courierstatus, int courierid);

	// 更新配送员工资
	boolean updateCourieramount(int courierid);
	
	//更新好评数
	boolean updateCourierWellReceived(int courierid);
	
	//更新评论数
	boolean updateCouriercomment(int courierid);
	
	//根据id查询配送员
	Courier findCourier(int courierid);
	
	//分页查看配送员配送记录
	List<Orderdetail> findCourierOrderlist(int currentPage, int pageSize, int courierid);
	
	int findTotalCount(int courierid);
	
	//查找好评率最高的配送员
	Courier findMAXCourier();
}
