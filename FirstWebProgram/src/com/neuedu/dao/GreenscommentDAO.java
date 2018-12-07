package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greencomment;

public interface GreenscommentDAO {
	// 插入数据
	boolean insertGreencomment(Greencomment greenscomment);
	
	//分页查看评论
	List<Greencomment> findGreenscommentList(int currentPage, int pageSize,int greensid);
	
	int findTotalCount(int greensid);
}
