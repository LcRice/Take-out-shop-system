package com.neuedu.service;

import com.neuedu.entity.Greencomment;
import com.neuedu.vo.GreencommentPage;

public interface GreencommentService {
	// ��������
	boolean insertGreencomment(Greencomment greenscomment);
	
	//��ҳ
	GreencommentPage findGreencommentPage(int currentPage, int pageSize, int greensid);
}
