package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;
import com.neuedu.vo.ShoppingcarPage;

public interface ShoppingcarService {
	// 向购物车插入数据
	boolean insertShopping(User user, Greens greens);

	// 查看购物车是否含有数据
	boolean checkShopping(User user, Greens greens);

	// 更新购物车
	boolean updateShopping(User user, Greens greens);

	// 删除数据
	boolean deleteShopping(User user, Greens greens);

	// 用户分页查询购物车
	ShoppingcarPage findShoppingList(int currentPage, int pageSize, int userid);
	
	// 根据用户和菜查找购物车
	Shoppingcar findShopping(User user, Greens greens);
}
