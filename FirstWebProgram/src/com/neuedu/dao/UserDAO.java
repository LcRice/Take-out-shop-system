package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.User;

public interface UserDAO {
	// 用户注册
	boolean insertUser(User user);

	// 根据usernumber查找用户---注册
	User checkUsernumber(String usernumber);

	// 根据用户名，密码来查找用户---登录
	User findUser(String usernumber, String userpassword);

	// 更新用户信息
	boolean updateUser(User olduser, User newuser);

	// 付款成功后更新
	boolean updateUser(User user, int price);

	// 分页查看所有用户
	List<User> findUserList(int currentPage, int pageSize);

	int findTotalCount();

	// 根据Userid查找用户
	User findUser(int userid);

	// 分页查看用户所有订单
	List<Orderdetail> findOrderList(int currentPage, int pageSize, int userid);

	int findTotalCount(int userid);
}
