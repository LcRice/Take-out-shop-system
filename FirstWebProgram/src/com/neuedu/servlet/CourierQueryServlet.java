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
import com.neuedu.vo.CourierPage;

public class CourierQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		// 获取session对象
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

		// 从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		// 设置字符编码
		request.setCharacterEncoding(charSet);

		// 接收当前页码
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		int userorderid = Integer.parseInt(request.getParameter("userorderid"));
		
		session.setAttribute("userorderid", userorderid);
		
		// 读取分页大小
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));
		
		CourierService courierService = new CourierServiceImpl();
		
		CourierPage courierPage = courierService.getCourierPage(currentPage, pageSize);
		
		request.setAttribute("courierPage", courierPage);

		request.getRequestDispatcher("courier_query_rest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
