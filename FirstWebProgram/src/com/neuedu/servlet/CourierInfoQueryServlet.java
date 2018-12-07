package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Courier;
import com.neuedu.entity.User;
import com.neuedu.service.CourierService;
import com.neuedu.service.UserService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.UserServiceImpl;

public class CourierInfoQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������---��Ҫ�������ʱ�Ӵ˾�
		PrintWriter out = response.getWriter();

		// ��ȡsession����---��Ҫsession����ʱ�Ӵ˾�
		HttpSession session = request.getSession();

		// ��ȡapplication����
		ServletContext application = this.getServletContext();

		int courierid = Integer.parseInt(request.getParameter("courierid"));

		session.setAttribute("courieridsuper", courierid);

		CourierService courierService = new CourierServiceImpl();
		
		Courier courier = courierService.findCourier(courierid);

		session.setAttribute("couriersuper", courier);

		request.getRequestDispatcher("courierinfo_query_super.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
