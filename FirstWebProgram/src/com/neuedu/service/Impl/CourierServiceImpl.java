package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.CourierDAO;
import com.neuedu.dao.Impl.CourierDAOImpl;
import com.neuedu.entity.Courier;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.service.CourierService;
import com.neuedu.vo.CourierPage;
import com.neuedu.vo.GreensPage;
import com.neuedu.vo.OrderPage;

public class CourierServiceImpl implements CourierService {

	private CourierDAO courierDAO = new CourierDAOImpl();
	public boolean addCourier(Courier courier) {
		// TODO Auto-generated method stub
		return courierDAO.insertCourier(courier);
	}

	public boolean checkCouriernumber(String couriernumber) {
		// TODO Auto-generated method stub
		if(courierDAO.checkCouriernumber(couriernumber) != null){
			return false;
		}else{
			return true;
		}
	}

	public CourierPage getCourierPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		CourierPage courierPage = new CourierPage();
		courierPage.setPageSize(pageSize);

		courierPage.setCurrentPage(currentPage);

		int totalCount = courierDAO.findTotalCount();
		courierPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		courierPage.setTotalPage(totalPage);

		List<Courier> courierlist = courierDAO.findCourierList(currentPage, pageSize);

		courierPage.setDataList(courierlist);

		int pageNumer = 5;

		// 计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;

		// 调整开始页码
		if (begin < 1) {
			begin = 1;
			end = pageNumer;
		}

		// 调整结束页码
		if (end > totalPage) {
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}

		// 调整页码
		if (totalPage < pageNumer) {
			begin = 1;
			end = totalPage;
		}

		courierPage.setBegin(begin);
		courierPage.setEnd(end);

		return courierPage;
	}

	public boolean updateCourierstatus(String courierstatus,int courierid) {
		// TODO Auto-generated method stub
		return courierDAO.updateCourierstatus(courierstatus,courierid);
	}

	public boolean updateCourieramount(int courierid) {
		// TODO Auto-generated method stub
		return courierDAO.updateCourieramount(courierid);
	}

	public Courier findCourier(String couriernumber, String courierpassword) {
		// TODO Auto-generated method stub
		return courierDAO.findCourier(couriernumber, courierpassword);
	}

	public boolean updateCourierWellReceived(int courierid) {
		// TODO Auto-generated method stub
		return courierDAO.updateCourierWellReceived(courierid);
	}

	public boolean updateCouriercomment(int courierid) {
		// TODO Auto-generated method stub
		return courierDAO.updateCouriercomment(courierid);
	}

	public Courier findCourier(int courierid) {
		// TODO Auto-generated method stub
		return courierDAO.findCourier(courierid);
	}

	public OrderPage findOrderlist(int currentPage, int pageSize, int courierid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = courierDAO.findTotalCount(courierid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderdetaillist = courierDAO.findCourierOrderlist(currentPage, pageSize, courierid);

		orderPage.setDataList(orderdetaillist);

		int pageNumer = 5;

		// 计算页码
		int begin = currentPage - pageNumer / 2;
		int end = currentPage + pageNumer / 2;

		// 调整开始页码
		if (begin < 1) {
			begin = 1;
			end = pageNumer;
		}

		// 调整结束页码
		if (end > totalPage) {
			end = totalPage;
			begin = totalPage - pageNumer + 1;
		}

		// 调整页码
		if (totalPage < pageNumer) {
			begin = 1;
			end = totalPage;
		}

		orderPage.setBegin(begin);
		orderPage.setEnd(end);

		return orderPage;
	}

	public Courier findMAXCourier() {
		// TODO Auto-generated method stub
		return courierDAO.findMAXCourier();
	}

}
