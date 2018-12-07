package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;

public interface GreensDAO {
	//上架菜
	boolean insertGreens(Greens greens,int restid);
	
	//根据菜名查询菜
	Greens checkGreensname(String greensname,int restid);
	
	//根据菜编号查询
	Greens findGreensByGreensid(int greensid);
	
	//更新菜的库存，价格，照片---上架
	boolean updateGreens(Greens greens,Greens newgreens);

	//更新菜的信息---修改
	boolean updateGreensAll(Greens oldgreens,Greens newgreens);
	
	//删除菜
	boolean deleteGreensByGreensid(int greensid);
	
	//批量删除
	boolean deleteGreensBatch(String[] greensids);
	
	//餐厅分页查询所有菜
	List<Greens> findGreensList(int currentPage, int pageSize,int restid);

	int findTotalCount(int restid);
	
	//获取菜最大编号
	int findMaxGreensid();
	
	//用户分页查询所有菜
	List<Greens> findGreensList(int currentPage, int pageSize, String greensname);
	
	int findTotalCount(String greensname);
	
	//更新库存
	boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar);
	
	// 更新评价数
	boolean updateGreencommentcount(int greensid);
}
