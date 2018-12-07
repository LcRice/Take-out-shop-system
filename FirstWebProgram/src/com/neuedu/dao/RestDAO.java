package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;

public interface RestDAO {
	// 商户注册
	boolean insertRest(Restaurant rest);

	// 根据restnumber查找商户
	Restaurant checkRestnumber(String restnumber);
	
	//根据用户名，密码来查找用户---登录
	Restaurant findRest(String restnumber,String restpassword);
	
	//修改餐厅信息
	boolean updateRest(Restaurant oldrest,Restaurant newrest);
	
	//更新餐厅余额
	boolean updateRestamount(Greens greens);
	
	//查看订单
	List<Orderdetail> findOrderList(int currentPage, int pageSize,int restid);
	
	int findTotalCount(int restid);
	
	// 更新评论数
	boolean updateRestcommentcount(int restid);
	
	// 分页显示餐厅
	List<Restaurant> findRestList(int currentPage, int pageSize);
	
	int findTotalCount();
	
	//根据id查找商户
	Restaurant findRest(int restid);
}
