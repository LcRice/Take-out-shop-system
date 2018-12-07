package com.neuedu.service;

import com.neuedu.entity.Greencomment;
import com.neuedu.vo.GreencommentPage;

public interface GreencommentService {
	// 插入数据
	boolean insertGreencomment(Greencomment greenscomment);
	
	//分页
	GreencommentPage findGreencommentPage(int currentPage, int pageSize, int greensid);
}
