package com.neuedu.service;

import com.neuedu.entity.Courier;
import com.neuedu.vo.CourierPage;
import com.neuedu.vo.OrderPage;

public interface CourierService {
	// 配送员注册
	boolean addCourier(Courier courier);

	// 根据couriernumber查找配送员
	boolean checkCouriernumber(String couriernumber);

	// 登录
	Courier findCourier(String couriernumber, String courierpassword);

	// 餐厅分页查询快递员
	CourierPage getCourierPage(int currentPage, int pageSize);

	// 更新配送状态
	boolean updateCourierstatus(String courierstatus, int courierid);

	// 更新配送员工资
	boolean updateCourieramount(int courierid);

	// 更新好评数
	boolean updateCourierWellReceived(int courierid);

	// 更新评论数
	boolean updateCouriercomment(int courierid);

	// 根据id查询配送员
	Courier findCourier(int courierid);

	// 配送员配送记录
	OrderPage findOrderlist(int currentPage, int pageSize, int courierid);

	// 查找好评率最高的配送员
	Courier findMAXCourier();
}
