package com.neuedu.service;

import com.neuedu.entity.Restcomment;
import com.neuedu.vo.RestcommentPage;

public interface RestcommentService {
	// ��������
	boolean insertRestcomment(Restcomment restcomment);
	
	// ��ҳ�鿴����
	RestcommentPage findRestcommentPage(int currentPage, int pageSize, int restid);
}
