package com.neuedu.service;

import com.neuedu.entity.Couriercomment;
import com.neuedu.vo.CouriercommentPage;

public interface CouriercommentService {
	CouriercommentPage findCouriercommentPage(int currentPage, int pageSize, int courierid);
	
	boolean addCouriercomment(Couriercomment couriercomment);
}
