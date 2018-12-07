package com.neuedu.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.GreensService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.vo.GreensPage;

public class GreensQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();
		
		// 接收当前页码
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// 读取分页大小
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));
	
		GreensService greensService = new GreensServiceImpl();
		
		Restaurant rest = (Restaurant) session.getAttribute("rest");
	
		int restid = rest.getRestid();
		
		GreensPage greensPage = greensService.getGreensPage(currentPage, pageSize, restid);
		
		request.setAttribute("greensPage", greensPage);

		request.getRequestDispatcher("greens_query_rest.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
