package com.neuedu.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.service.GreensService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.vo.GreensPage;

public class GreensQueryByUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡsession����---��Ҫsession����ʱ�Ӵ˾�
		HttpSession session = request.getSession();

		// �������ļ��ж�ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �����ַ�����
		request.setCharacterEncoding(charSet);

		// ���յ�ǰҳ��
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));

		// ��ȡ��ҳ��С
		int pageSize = Integer.parseInt(this.getInitParameter("pageSize"));

		GreensService greensService = new GreensServiceImpl();

		String usergreensname = request.getParameter("findgreens");
		if (usergreensname == null) {
			usergreensname = "";
		}

		GreensPage usergreensPage = greensService.getGreensPage(currentPage, pageSize, usergreensname);

		request.setAttribute("usergreensPage", usergreensPage);

		request.setAttribute("usergreensname", usergreensname);

		request.getRequestDispatcher("greens_query_user.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
