package com.neuedu.service.Impl;

import java.util.List;

import com.neuedu.dao.GreenscommentDAO;
import com.neuedu.dao.Impl.GreenscommentDAOImpl;
import com.neuedu.entity.Couriercomment;
import com.neuedu.entity.Greencomment;
import com.neuedu.service.GreencommentService;
import com.neuedu.vo.GreencommentPage;

public class GreencommentServiceImpl implements GreencommentService {

	private GreenscommentDAO greenscommentDAO = new GreenscommentDAOImpl();
	public boolean insertGreencomment(Greencomment greenscomment) {
		// TODO Auto-generated method stub
		return greenscommentDAO.insertGreencomment(greenscomment);
	}

	public GreencommentPage findGreencommentPage(int currentPage, int pageSize, int greensid) {
		// TODO Auto-generated method stub
		GreencommentPage greencommentPage = new GreencommentPage();
		
		greencommentPage.setPageSize(pageSize);

		greencommentPage.setCurrentPage(currentPage);

		int totalCount = greenscommentDAO.findTotalCount(greensid);
		greencommentPage.setTotalCount(totalCount);

		int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
		if(totalPage==0){
			totalPage = 1;
		}
		greencommentPage.setTotalPage(totalPage);

		List<Greencomment> greencommentlist = greenscommentDAO.findGreenscommentList(currentPage, pageSize, greensid);
		
		greencommentPage.setDataList(greencommentlist);

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

		greencommentPage.setBegin(begin);
		greencommentPage.setEnd(end);

		return greencommentPage;
	}

}
