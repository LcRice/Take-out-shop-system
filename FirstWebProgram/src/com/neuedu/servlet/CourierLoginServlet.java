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
import com.neuedu.service.CourierService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.util.CookieUtil;

public class CourierLoginServlet extends HttpServlet {
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

		String couriernumber = request.getParameter("couriernumber");
		String courierpassword = request.getParameter("courierpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// ���ҵ������
		CourierService courierService = new CourierServiceImpl();

		// ����ҵ�񷽷�
		Courier courier = courierService.findCourier(couriernumber, courierpassword);

		if (courier != null) { // ��¼�ɹ�

			// ��¼�ɹ�����ѡ�и�ѡ��ʱ������һ���־û�cookie

			// if(member!=null){ //ѡ�и�ѡ��
			if (autoLogin != null) { // ѡ�и�ѡ��
				// ����һ���־û�cookie
				CookieUtil.addCookie(response, "courierInfo", couriernumber + "#" + courierpassword, 7);
			}

			// ��session���Է�Χ�б����û�����
			session.setAttribute("courier", courier);

			// ��session���Է�Χ�л�ȡҪ���ص�URL,Ĭ�Ϸ�����ҳ
			String prevURL = "courier_index.jsp";

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
