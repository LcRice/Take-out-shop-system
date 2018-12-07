package com.neuedu.service;

import java.util.List;

import com.neuedu.entity.Orderdetail;
import com.neuedu.vo.OrderPage;

public interface OrderdetailService {

	// 插入数据
	boolean insertDate(int greensid, int orderid, int count);

	// 根据id查找对象
	Orderdetail findOrderdetail(int userorderid);

	// 更新订单状态
	boolean updateOrderstatus(int userorderid, int orderstatus);

	// 分页查询未完成的订单
	OrderPage findOrderPageByUser(int currentPage, int pageSize, int userid);

	// 分页查询已完成的订单
	OrderPage findFinishedOrderByUser(int currentPage, int pageSize, int userid);

	// 分页查询餐厅的订单
	OrderPage findFinishedOrderByRest(int currentPage, int pageSize, int restid);
	
	// 分页查询餐厅的订单
	List<Orderdetail> findUnfinishedOrderByRest(int restid);
}
