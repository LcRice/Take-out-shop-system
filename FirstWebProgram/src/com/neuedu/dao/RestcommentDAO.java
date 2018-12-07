package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Restcomment;

public interface RestcommentDAO {
	// ��������
	boolean insertRestcomment(Restcomment restcomment);
	
	// ��ҳ�鿴����
	List<Restcomment> findRestcommentList(int currentPage, int pageSize,int restid);
	
	int findTotalCount(int restid);
}
