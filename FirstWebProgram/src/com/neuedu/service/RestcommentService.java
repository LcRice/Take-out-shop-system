package com.neuedu.service;

import com.neuedu.entity.Restcomment;
import com.neuedu.vo.RestcommentPage;

public interface RestcommentService {
	// 插入数据
	boolean insertRestcomment(Restcomment restcomment);
	
	// 分页查看评论
	RestcommentPage findRestcommentPage(int currentPage, int pageSize, int restid);
}
