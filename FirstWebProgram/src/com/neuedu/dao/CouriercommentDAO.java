package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Couriercomment;

public interface CouriercommentDAO {
	//根据id查询
	List<Couriercomment> findCourierCommentList(int currentPage, int pageSize,int courierid);
	
	int findTotalCount(int courierid);
	
	//插入
	boolean insertCouriercomment(Couriercomment couriercomment);
}
