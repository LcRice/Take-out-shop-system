package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Restaurant;
import com.neuedu.service.RestService;
import com.neuedu.service.Impl.RestServiceImpl;

public class RestInfoQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();
		
		int restid = Integer.parseInt(request.getParameter("restid"));
		
		session.setAttribute("restidsuper", restid);
		
		RestService restService = new RestServiceImpl();
		
		Restaurant restaurant = restService.findRest(restid);
		
		session.setAttribute("restsuper", restaurant);
		
		request.getRequestDispatcher("restinfo_query_super.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
