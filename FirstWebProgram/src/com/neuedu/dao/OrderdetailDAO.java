package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Orderdetail;

public interface OrderdetailDAO {

	// 插入数据
	boolean insertDate(int greensid, int orderid, int count);

	// 根据id查找对象
	Orderdetail findOrderdetail(int userorderid);

	// 更新订单状态
	boolean updateOrderstatus(int userorderid, int orderstatus);

	// 分页查看未完成的订单
	List<Orderdetail> findOrderdetailListByUser(int currentPage, int pageSize, int userid);

	int findTotalCount(int userid);

	// 分页查看已完成的订单
	List<Orderdetail> findFinishedOrderListByUser(int currentPage, int pageSize, int userid);

	int findFinishedTotalCount(int userid);

	// 分页查看餐厅的订单
	List<Orderdetail> findOrderdetailListByRest(int currentPage, int pageSize, int restid);

	int findRestTotalCount(int restid);
	
	//根据餐厅查看外完成订单
	List<Orderdetail> findUnfinishedOrderListByRest(int restid);
	
}
