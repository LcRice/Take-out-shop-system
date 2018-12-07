package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.ShoppingcarDAO;
import com.neuedu.dao.Impl.ShoppingcarDAOImpl;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;
import com.neuedu.service.ShoppingcarService;
import com.neuedu.vo.ShoppingcarPage;

public class ShoppingcarServiceImpl implements ShoppingcarService {

	private ShoppingcarDAO shoppingcarDAO = new ShoppingcarDAOImpl();

	public boolean insertShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		return shoppingcarDAO.insertShopping(user, greens);
	}

	public boolean checkShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		if (shoppingcarDAO.checkShopping(user, greens) != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean updateShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		return shoppingcarDAO.updateShopping(user, greens);
	}

	public boolean deleteShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		return shoppingcarDAO.deleteShopping(user, greens);
	}

	public ShoppingcarPage findShoppingList(int currentPage, int pageSize, int userid) {
		// TODO Auto-generated method stub
		ShoppingcarPage shoppingcarPage = new ShoppingcarPage();
		shoppingcarPage.setPageSize(pageSize);

		shoppingcarPage.setCurrentPage(currentPage);

		int totalCount = shoppingcarDAO.findTotalCount(userid);
		shoppingcarPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		shoppingcarPage.setTotalPage(totalPage);

		List<Shoppingcar> Shoppingcarlist = shoppingcarDAO.findShoppingList(currentPage, pageSize, userid);

		shoppingcarPage.setDataList(Shoppingcarlist);

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

		shoppingcarPage.setBegin(begin);
		shoppingcarPage.setEnd(end);

		return shoppingcarPage;
	}

	public Shoppingcar findShopping(User user, Greens greens) {
		// TODO Auto-generated method stub
		return shoppingcarDAO.checkShopping(user, greens);
	}

}
