package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Greens;
import com.neuedu.entity.User;
import com.neuedu.service.GreensService;
import com.neuedu.service.ShoppingcarService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.service.Impl.ShoppingcarServiceImpl;

public class ShoppingDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ɾ�����ﳵ
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
		
		GreensService greensService = new GreensServiceImpl();
		ShoppingcarService shoppingcarService = new ShoppingcarServiceImpl();
		
		int greensid = Integer.parseInt(request.getParameter("greensid"));
		Greens greens = greensService.findGreensByGreensid(greensid);
		
		User user = (User) session.getAttribute("user");
		
		if(shoppingcarService.deleteShopping(user, greens)){
			out.println("<script>alert('ɾ���ɹ�');location='shoppingcar_query_user.jsp'</script>");
		} else {
			out.println("<script>alert('ɾ��ʧ��');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
