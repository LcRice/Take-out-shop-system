package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.vo.OrderPage;
import com.neuedu.vo.RestPage;

public interface RestService {
	// 商户注册
	boolean addRest(Restaurant rest);

	// 根据restnumber查找商户
	boolean checkRestnumber(String restnumber);

	// 根据用户名，密码来查找用户---登录,查看个人信息
	Restaurant login(String restnumber, String restpassword);

	// 更新商户
	boolean updateRest(Restaurant oldrest, Restaurant newrest);

	// 更新餐厅余额
	boolean updateRestamount(Greens greens);

	// 餐厅分页查询订单
	OrderPage getOrdersPage(int currentPage, int pageSize, int restid);

	// 更新评论数
	boolean updateRestcommentcount(int restid);

	// 分页显示餐厅
	RestPage getRestPage(int currentPage, int pageSize);

	// 根据id查找商户
	Restaurant findRest(int restid);
}
