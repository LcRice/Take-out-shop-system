package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.OrderdetailDAO;
import com.neuedu.dao.Impl.OrderdetailDAOImpl;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.service.OrderdetailService;
import com.neuedu.vo.OrderPage;

public class OrderdetailServiceImpl implements OrderdetailService {

	private OrderdetailDAO orderdetailDAO = new OrderdetailDAOImpl();
	public boolean insertDate(int greensid, int orderid, int count) {
		// TODO Auto-generated method stub
		if(orderdetailDAO.insertDate(greensid, orderid, count)){
			return false;
		}else{
			return true;
		}
	}
	
	public Orderdetail findOrderdetail(int userorderid) {
		// TODO Auto-generated method stub
		return orderdetailDAO.findOrderdetail(userorderid);
	}
	
	public boolean updateOrderstatus(int userorderid, int orderstatus) {
		// TODO Auto-generated method stub
		return orderdetailDAO.updateOrderstatus(userorderid, orderstatus);
	}

	public OrderPage findOrderPageByUser(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = orderdetailDAO.findTotalCount(userid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderdetaillist = orderdetailDAO.findOrderdetailListByUser(currentPage, pageSize, userid);

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

	public OrderPage findFinishedOrderByUser(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = orderdetailDAO.findFinishedTotalCount(userid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderdetaillist = orderdetailDAO.findFinishedOrderListByUser(currentPage, pageSize, userid);

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

	public OrderPage findFinishedOrderByRest(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = orderdetailDAO.findRestTotalCount(restid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderdetaillist = orderdetailDAO.findOrderdetailListByRest(currentPage, pageSize, restid);

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

	public List<Orderdetail> findUnfinishedOrderByRest(int restid) {
		// TODO Auto-generated method stub
		return orderdetailDAO.findUnfinishedOrderListByRest(restid);
	}

}
