package com.neuedu.service;

import com.neuedu.entity.User;
import com.neuedu.vo.OrderPage;
import com.neuedu.vo.UserPage;

public interface UserService {
	// 用户注册
	boolean addUser(User user);

	// 根据usernumber查找用户---注册
	boolean checkUsernumber(String usernumber);

	// 根据用户名，密码来查找用户---登录
	User login(String usernumber, String userpassword);

	// 更新用户信息
	boolean updateUser(User olduser, User newuser);

	// 付款成功后更新
	boolean updateUser(User user, int price);

	// 分页查找用户
	UserPage getUserPage(int currentPage, int pageSize);

	// 根据Userid查找用户
	User findUser(int userid);
	
	OrderPage findOrderList(int currentPage, int pageSize, int userid);
}
