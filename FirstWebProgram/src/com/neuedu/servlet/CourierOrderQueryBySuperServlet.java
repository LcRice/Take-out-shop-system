package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.service.CourierService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.vo.OrderPage;

public class CourierOrderQueryBySuperServlet extends HttpServlet {
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

		int courierid = (Integer) session.getAttribute("courieridsuper");

		// ���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// ��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		CourierService courierService = new CourierServiceImpl();

		OrderPage orderPage = courierService.findOrderlist(currentPage, pageSize, courierid);

		request.setAttribute("orderPage", orderPage);

		request.getRequestDispatcher("courierorder_query_super.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
