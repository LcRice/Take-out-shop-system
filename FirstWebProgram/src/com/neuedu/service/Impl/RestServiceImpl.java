package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.RestDAO;
import com.neuedu.dao.Impl.RestDAOImpl;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.RestService;
import com.neuedu.vo.OrderPage;
import com.neuedu.vo.RestPage;

public class RestServiceImpl implements RestService {

	RestDAO restDAO = new RestDAOImpl();
	public boolean addRest(Restaurant rest) {
		// TODO Auto-generated method stub
		return restDAO.insertRest(rest);
	}

	public boolean checkRestnumber(String restnumber) {
		// TODO Auto-generated method stub
		if(restDAO.checkRestnumber(restnumber) != null){
			return false;
		}else{
			return true;
		}
	}

	public Restaurant login(String restnumber, String restpassword) {
		// TODO Auto-generated method stub
		return restDAO.findRest(restnumber, restpassword);
	}

	public boolean updateRest(Restaurant oldrest, Restaurant newrest) {
		// TODO Auto-generated method stub
		return restDAO.updateRest(oldrest, newrest);
	}

	public boolean updateRestamount(Greens greens) {
		// TODO Auto-generated method stub
		return restDAO.updateRestamount(greens);
	}

	public OrderPage getOrdersPage(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		OrderPage orderPage = new OrderPage();
		orderPage.setPageSize(pageSize);

		orderPage.setCurrentPage(currentPage);

		int totalCount = restDAO.findTotalCount(restid);
		orderPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		orderPage.setTotalPage(totalPage);

		List<Orderdetail> orderlist = restDAO.findOrderList(currentPage, pageSize, restid);

		orderPage.setDataList(orderlist);

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

	public boolean updateRestcommentcount(int restid) {
		// TODO Auto-generated method stub
		return restDAO.updateRestcommentcount(restid);
	}

	public RestPage getRestPage(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		RestPage restPage = new RestPage();
		restPage.setPageSize(pageSize);

		restPage.setCurrentPage(currentPage);

		int totalCount = restDAO.findTotalCount();
		restPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		restPage.setTotalPage(totalPage);

		List<Restaurant> restlist = restDAO.findRestList(currentPage, pageSize);

		restPage.setDataList(restlist);

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

		restPage.setBegin(begin);
		restPage.setEnd(end);

		return restPage;
	}

	public Restaurant findRest(int restid) {
		// TODO Auto-generated method stub
		return restDAO.findRest(restid);
	}

}
