package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;

public interface ShoppingcarDAO {
	// 向购物车插入数据
	boolean insertShopping(User user, Greens greens);

	// 查看购物车是否含有数据
	Shoppingcar checkShopping(User user, Greens greens);
	
	// 更新购物车
	boolean updateShopping(User user, Greens greens);
	
	// 删除数据
	boolean deleteShopping(User user,Greens greens);
	
	// 根据userid查看购物车
	List<Shoppingcar> findShoppingList(int currentPage, int pageSize, int userid);
	
	int findTotalCount(int userid);
}
