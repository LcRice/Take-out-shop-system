package com.neuedu.service.Impl;

import com.neuedu.dao.CourierrestDAO;
import com.neuedu.dao.Impl.CourierrestDAOImpl;
import com.neuedu.entity.Courierrest;
import com.neuedu.service.CourierrestService;

public class CourierrestServiceImpl implements CourierrestService {

	private CourierrestDAO courierrestDAO = new CourierrestDAOImpl();
	public boolean insertCourierrest(int courierid, int restid, int userorderid) {
		// TODO Auto-generated method stub
		return courierrestDAO.insertCourierrest(courierid, restid, userorderid);
	}
	
	public Courierrest findCourierrest(int courierid) {
		// TODO Auto-generated method stub
		return courierrestDAO.findCourierrest(courierid);
	}

	public boolean updateStatus(int courierrestid) {
		// TODO Auto-generated method stub
		return courierrestDAO.updateStatus(courierrestid);
	}

	public Courierrest findCourierrestByUser(int userorderid) {
		// TODO Auto-generated method stub
		return courierrestDAO.findCourierrestByUser(userorderid);
	}

}
