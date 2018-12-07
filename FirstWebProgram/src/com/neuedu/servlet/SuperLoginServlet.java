package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Superadmin;
import com.neuedu.service.SuperadminService;
import com.neuedu.service.Impl.SuperadminServiceImpl;
import com.neuedu.util.CookieUtil;

public class SuperLoginServlet extends HttpServlet {
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

		String supernumber = request.getParameter("supernumber");
		
		String superpassword = request.getParameter("superpassword");

		// ���ҵ������
		SuperadminService superadminService = new SuperadminServiceImpl();

		// ����ҵ�񷽷�
		Superadmin superadmin = superadminService.superLogin(supernumber, superpassword);

		if (superadmin != null) { // ��¼�ɹ�

			// ��session���Է�Χ�б����û�����
			session.setAttribute("superadmin", superadmin);

			// ��session���Է�Χ�л�ȡҪ���ص�URL,Ĭ�Ϸ�����ҳ
			String prevURL = "super_index.jsp";

			// �ض���url
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + prevURL));
		} else {
			out.println("<script>alert('�û����������������������');history.back()</script>"); // ���˵�ǰһҳ��֮ǰ�����ݿ��Ա���
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
