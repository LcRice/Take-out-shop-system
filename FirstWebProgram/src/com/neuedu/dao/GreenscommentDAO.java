package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greencomment;

public interface GreenscommentDAO {
	// ��������
	boolean insertGreencomment(Greencomment greenscomment);
	
	//��ҳ�鿴����
	List<Greencomment> findGreenscommentList(int currentPage, int pageSize,int greensid);
	
	int findTotalCount(int greensid);
}
