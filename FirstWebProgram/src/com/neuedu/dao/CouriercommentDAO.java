package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Couriercomment;

public interface CouriercommentDAO {
	//����id��ѯ
	List<Couriercomment> findCourierCommentList(int currentPage, int pageSize,int courierid);
	
	int findTotalCount(int courierid);
	
	//����
	boolean insertCouriercomment(Couriercomment couriercomment);
}
