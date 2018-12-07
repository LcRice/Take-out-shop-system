package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.RestcommentDAO;
import com.neuedu.dao.Impl.RestcommentDAOImpl;
import com.neuedu.entity.Greencomment;
import com.neuedu.entity.Restcomment;
import com.neuedu.service.RestcommentService;
import com.neuedu.vo.RestcommentPage;

public class RestcommentServiceImpl implements RestcommentService {

	private RestcommentDAO restcommentDAO = new RestcommentDAOImpl();
	public boolean insertRestcomment(Restcomment restcomment) {
		// TODO Auto-generated method stub
		return restcommentDAO.insertRestcomment(restcomment);
	}

	public RestcommentPage findRestcommentPage(int currentPage, int pageSize, int restid) {
		// TODO Auto-generated method stub
		RestcommentPage restcommentPage = new RestcommentPage();
		
		restcommentPage.setPageSize(pageSize);

		restcommentPage.setCurrentPage(currentPage);

		int totalCount = restcommentDAO.findTotalCount(restid);
		restcommentPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		restcommentPage.setTotalPage(totalPage);

		List<Restcomment> restcommentlist = restcommentDAO.findRestcommentList(currentPage, pageSize, restid);
		
		restcommentPage.setDataList(restcommentlist);

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

		restcommentPage.setBegin(begin);
		restcommentPage.setEnd(end);

		return restcommentPage;
	}

}
