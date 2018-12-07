package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.GreensDAO;
import com.neuedu.dao.Impl.GreensDAOImpl;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.service.GreensService;
import com.neuedu.vo.GreensPage;

public class GreensServiceImpl implements GreensService {

	private GreensDAO greensDAO = new GreensDAOImpl();

	public boolean addGreens(Greens greens, int restid) {
		// TODO Auto-generated method stub
		return greensDAO.insertGreens(greens,restid);
	}

	public boolean checkGreensname(String greensname,int restid) {
		// TODO Auto-generated method stub
		if (greensDAO.checkGreensname(greensname,restid) != null) {
			return false;
		} else {
			return true;
		}
	}

	public Greens findGreens(String greensname,int restid) {
		return greensDAO.checkGreensname(greensname,restid);
	}

	public boolean updateGreens(Greens greens, Greens newgreens) {
		// TODO Auto-generated method stub
		return greensDAO.updateGreens(greens, newgreens);
	}

	public GreensPage getGreensPage(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		GreensPage greensPage = new GreensPage();
		greensPage.setPageSize(pageSize);

		greensPage.setCurrentPage(currentPage);

		int totalCount = greensDAO.findTotalCount(restid);
		greensPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		greensPage.setTotalPage(totalPage);

		List<Greens> greenslist = greensDAO.findGreensList(currentPage, pageSize, restid);

		greensPage.setDataList(greenslist);

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

		greensPage.setBegin(begin);
		greensPage.setEnd(end);

		return greensPage;
	}

	public int getMaxGreensid() {
		// TODO Auto-generated method stub
		return greensDAO.findMaxGreensid();
	}

	public Greens findGreensByGreensid(int greensid) {
		// TODO Auto-generated method stub
		return greensDAO.findGreensByGreensid(greensid);
	}

	public boolean updateGreensAll(Greens oldgreens, Greens newgreens) {
		// TODO Auto-generated method stub
		return greensDAO.updateGreensAll(oldgreens, newgreens);
	}

	public boolean deleteGreensByGreensid(int greensid) {
		// TODO Auto-generated method stub
		return greensDAO.deleteGreensByGreensid(greensid);
	}

	public boolean deleteGreensBatch(String[] greensids) {
		// TODO Auto-generated method stub
		return greensDAO.deleteGreensBatch(greensids);
	}

	public GreensPage getGreensPage(int currentPage, int pageSize, String greensname) {
		// TODO Auto-generated method stub
		GreensPage greensPage = new GreensPage();
		greensPage.setPageSize(pageSize);

		greensPage.setCurrentPage(currentPage);

		int totalCount = greensDAO.findTotalCount(greensname);
		greensPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		greensPage.setTotalPage(totalPage);

		List<Greens> greenslist = greensDAO.findGreensList(currentPage, pageSize, greensname);

		greensPage.setDataList(greenslist);

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

		greensPage.setBegin(begin);
		greensPage.setEnd(end);

		return greensPage;
	}

	public boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar) {
		// TODO Auto-generated method stub
		return greensDAO.updateGreensnumber(greens, shoppingcar);
	}

	public boolean updateGreencommentcount(int greensid) {
		// TODO Auto-generated method stub
		return greensDAO.updateGreencommentcount(greensid);
	}

}
