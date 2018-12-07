package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.UserDAO;
import com.neuedu.dao.Impl.UserDAOImpl;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.vo.OrderPage;
import com.neuedu.vo.UserPage;

public class UserServiceImpl implements UserService {

	private UserDAO userDAO = new UserDAOImpl();
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(user);
	}
	public boolean checkUsernumber(String usernumber) {
		// TODO Auto-generated method stub
		if(userDAO.checkUsernumber(usernumber)!=null){
			return false;
		}else{
			return true;
		}
	}
	public User login(String usernumber, String userpassword) {
		// TODO Auto-generated method stub
		return userDAO.findUser(usernumber, userpassword);
	}
	public boolean updateUser(User olduser, User newuser) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(olduser, newuser);
	}
	public boolean updateUser(User user, int price) {
		// TODO Auto-generated method stub
		return userDAO.updateUser(user, price);
	}
	public UserPage getUserPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		UserPage userPage = new UserPage();
		
		userPage.setPageSize(pageSize);

		userPage.setCurrentPage(currentPage);

		int totalCount = userDAO.findTotalCount();
		userPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		userPage.setTotalPage(totalPage);

		List<User> userlist = userDAO.findUserList(currentPage, pageSize);

		userPage.setDataList(userlist);

		int pageNumer = 5;

		// 计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;

		// 调整开始页码
		if (begin < 1) {
			begin = 1;
			end = pageNumer;
		}

		// 调整结束页码
		if (end > totalPage) {
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}

		// 调整页码
		if (totalPage < pageNumer) {
			begin = 1;
			end = totalPage;
		}

		userPage.setBegin(begin);
		userPage.setEnd(end);

		return userPage;
	}
	public User findUser(int userid) {
		// TODO Auto-generated method stub
		return userDAO.findUser(userid);
	}
	public OrderPage findOrderList(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = userDAO.findTotalCount(userid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage == 0){
			totalPage = 1;
		}
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderlist = userDAO.findOrderList(currentPage, pageSize, userid);

		orderPage.setDataList(orderlist);

		int pageNumer = 5;

		// 计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;

		// 调整开始页码
		if (begin < 1) {
			begin = 1;
			end = pageNumer;
		}

		// 调整结束页码
		if (end > totalPage) {
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}

		// 调整页码
		if (totalPage < pageNumer) {
			begin = 1;
			end = totalPage;
		}

		orderPage.setBegin(begin);
		orderPage.setEnd(end);

		return orderPage;
	}

}
