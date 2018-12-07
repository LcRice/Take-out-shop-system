package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.CouriercommentDAO;
import com.neuedu.dao.Impl.CouriercommentDAOImpl;
import com.neuedu.entity.Couriercomment;
import com.neuedu.entity.Greens;
import com.neuedu.service.CouriercommentService;
import com.neuedu.vo.CouriercommentPage;

public class CouriercommentServiceImpl implements CouriercommentService {

	private CouriercommentDAO couriercommentDAO = new CouriercommentDAOImpl();
	public CouriercommentPage findCouriercommentPage(int currentPage, int pageSize, int courierid) {
		// TODO Auto-generated method stub
		CouriercommentPage couriercommentPage = new CouriercommentPage();
		
		couriercommentPage.setPageSize(pageSize);

		couriercommentPage.setCurrentPage(currentPage);

		int totalCount = couriercommentDAO.findTotalCount(courierid);
		couriercommentPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		couriercommentPage.setTotalPage(totalPage);

		List<Couriercomment> couriercommentlist = couriercommentDAO.findCourierCommentList(currentPage, pageSize, courierid);

		couriercommentPage.setDataList(couriercommentlist);

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

		couriercommentPage.setBegin(begin);
		couriercommentPage.setEnd(end);

		return couriercommentPage;
	}

	public boolean addCouriercomment(Couriercomment couriercomment) {
		// TODO Auto-generated method stub
		return couriercommentDAO.insertCouriercomment(couriercomment);
	}

}
