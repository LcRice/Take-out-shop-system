package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.service.GreensService;
import com.neuedu.service.RestcommentService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.service.Impl.RestcommentServiceImpl;
import com.neuedu.vo.GreensPage;
import com.neuedu.vo.RestcommentPage;

public class GreensQueryByRestServlet extends HttpServlet {
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

		int restid = Integer.parseInt(request.getParameter("restid"));
		session.setAttribute("restgreensid", restid);
		
		// ���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// ���յ�ǰҳ��
		int currentPage1 = Integer.parseInt(request.getParameter("currentPage1"));

		// ��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		// ��ȡ��ҳ��С
		int pageSize1 = Integer.parseInt(this.getInitParameter("pageSize1"));

		GreensService greensService = new GreensServiceImpl();
		RestcommentService restcommentService = new RestcommentServiceImpl();

		GreensPage greensPage = greensService.getGreensPage(currentPage, pageSize, restid);

		request.setAttribute("greensPage", greensPage);

		RestcommentPage restcommentPage = restcommentService.findRestcommentPage(currentPage1, pageSize1, restid);

		request.setAttribute("restcommentPage", restcommentPage);
		
		request.getRequestDispatcher("restgreens_query_user.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
