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
import com.neuedu.service.UserService;
import com.neuedu.service.Impl.UserServiceImpl;
import com.neuedu.util.CookieUtil;

public class UserLoginServlet extends HttpServlet {
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


		String usernumber = request.getParameter("usernumber");
		String userpassword = request.getParameter("userpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// ���ҵ������
		UserService useService = new UserServiceImpl();

		// ����ҵ�񷽷�
		User user = useService.login(usernumber, userpassword);

		if (user != null) { // ��¼�ɹ�

			// ��¼�ɹ�����ѡ�и�ѡ��ʱ������һ���־û�cookie

			// if(member!=null){ //ѡ�и�ѡ��
			if (autoLogin != null) { // ѡ�и�ѡ��
				// ����һ���־û�cookie
				CookieUtil.addCookie(response, "userInfo", usernumber + "#" + userpassword, 7);
			}

			// ��session���Է�Χ�б����û�����
			session.setAttribute("user", user);

			// ��application���Է�Χ�и��²�������������
			if (application.getAttribute("onlineCount") == null) { // ��һλ�ÿ�
				application.setAttribute("onlineCount", 1);
			} else {
				application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
			}

			// ��session���Է�Χ�л�ȡҪ���ص�URL,Ĭ�Ϸ�����ҳ
			String prevURL = "user_index.jsp";

			if (session.getAttribute("prevURL") != null) {
				prevURL = (String) session.getAttribute("prevURL");
			}

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
