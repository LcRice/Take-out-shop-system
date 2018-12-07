package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Restaurant;
import com.neuedu.service.GreensService;
import com.neuedu.service.RestService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.service.Impl.RestServiceImpl;
import com.neuedu.vo.GreensPage;
import com.neuedu.vo.RestPage;

public class RestQueryByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡsession����---��Ҫsession����ʱ�Ӵ˾�
		HttpSession session = request.getSession();

		// ���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// ��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		RestService restService = new RestServiceImpl();

		RestPage restPage = restService.getRestPage(currentPage, pageSize);

		
		request.setAttribute("restPage", restPage);

		request.getRequestDispatcher("rest_query_user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
