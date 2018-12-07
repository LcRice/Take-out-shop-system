package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.vo.GreensPage;

public interface GreensService {

	// 上架菜
	boolean addGreens(Greens greens, int restid);

	// 根据菜名查询菜
	boolean checkGreensname(String greensname, int restid);

	Greens findGreens(String greensname, int restid);

	// 根据菜编号查询菜
	Greens findGreensByGreensid(int greensid);

	// 更新菜的价格，库存，照片
	boolean updateGreens(Greens greens, Greens newgreens);

	// 更新菜的全部
	boolean updateGreensAll(Greens oldgreens, Greens newgreens);

	// 根据菜编号删除菜
	boolean deleteGreensByGreensid(int greensid);

	// 批量删除
	boolean deleteGreensBatch(String[] greensids);

	// 餐厅分页查询菜
	GreensPage getGreensPage(int currentPage, int pageSize, int restid);

	// 获取最大菜编号
	int getMaxGreensid();

	// 用户分页查询菜
	GreensPage getGreensPage(int currentPage, int pageSize, String greensname);

	// 更新库存
	boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar);

	// 更新评价数
	boolean updateGreencommentcount(int greensid);
}
