package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Courierrest;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.RestcommentService;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.RestcommentServiceImpl;
import com.neuedu.vo.RestcommentPage;


public class RestCommentQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ��ȡsession����
		HttpSession session = request.getSession();

		// ��ȡapplication����
		ServletContext application = this.getServletContext();

		// �������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �����ַ�����
		request.setCharacterEncoding(charSet);

		// ���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// ��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		RestcommentService restcommentService = new RestcommentServiceImpl();

		int userorderid = Integer.parseInt(request.getParameter("userorderid"));

		request.setAttribute("userorderid", userorderid);

		CourierrestService courierrestService = new CourierrestServiceImpl();

		Courierrest courierrest = courierrestService.findCourierrestByUser(userorderid);

		int restid = courierrest.getRestid();

		RestcommentPage restcommentPage = restcommentService.findRestcommentPage(currentPage, pageSize, restid);

		request.setAttribute("restcommentPage", restcommentPage);

		request.getRequestDispatcher("restcomment_query_user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
