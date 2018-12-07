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
import com.neuedu.util.CookieUtil;

public class RestLoginServlet extends HttpServlet {
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


		String restnumber = request.getParameter("restnumber");
		String restpassword = request.getParameter("restpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// ���ҵ������
		RestService restService = new RestServiceImpl();

		// ����ҵ�񷽷�
		Restaurant rest = restService.login(restnumber, restpassword);

		if (rest != null) { // ��¼�ɹ�

			// ��¼�ɹ�����ѡ�и�ѡ��ʱ������һ���־û�cookie

			// if(member!=null){ //ѡ�и�ѡ��
			if (autoLogin != null) { // ѡ�и�ѡ��
				// ����һ���־û�cookie
				CookieUtil.addCookie(response, "restInfo", restnumber + "#" + restpassword, 7);
			}

			// ��session���Է�Χ�б����û�����
			session.setAttribute("rest", rest);

			// ��application���Է�Χ�и��²�������������
			if (application.getAttribute("onlineCount") == null) { // ��һλ�ÿ�
				application.setAttribute("onlineCount", 1);
			} else {
				application.setAttribute("onlineCount", (Integer) application.getAttribute("onlineCount") + 1);
			}

			// ��session���Է�Χ�л�ȡҪ���ص�URL,Ĭ�Ϸ�����ҳ
			String prevURL = "rest_index.jsp";

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
