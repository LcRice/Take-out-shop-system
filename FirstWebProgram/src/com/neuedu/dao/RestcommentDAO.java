package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Restcomment;

public interface RestcommentDAO {
	// 插入数据
	boolean insertRestcomment(Restcomment restcomment);
	
	// 分页查看评论
	List<Restcomment> findRestcommentList(int currentPage, int pageSize,int restid);
	
	int findTotalCount(int restid);
}
