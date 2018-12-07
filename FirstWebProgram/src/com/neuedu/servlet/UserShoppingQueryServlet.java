package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.User;
import com.neuedu.service.ShoppingcarService;
import com.neuedu.service.Impl.ShoppingcarServiceImpl;
import com.neuedu.vo.ShoppingcarPage;

public class UserShoppingQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//��ѯ���ﳵ
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

		ShoppingcarService shoppingcarService = new ShoppingcarServiceImpl();
		
		User user = (User) session.getAttribute("user");
		int userid = user.getUserid();
		
		ShoppingcarPage shoppingcarPage = shoppingcarService.findShoppingList(currentPage, pageSize, userid);
		
		request.setAttribute("shoppingcarPage", shoppingcarPage);
		
		request.getRequestDispatcher("shoppingcar_query_user.jsp").forward(request, response);

		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
