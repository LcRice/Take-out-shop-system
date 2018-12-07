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
import com.neuedu.service.GreensService;
import com.neuedu.service.Impl.GreensServiceImpl;

public class GreensFindServlet extends HttpServlet {
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

		// �������ļ��ж�ȡ�ļ�����
		String fileType = this.getServletContext().getInitParameter("fileType");

		// �����ַ�����
		request.setCharacterEncoding(charSet);
		
		GreensService greensService = new GreensServiceImpl();
		
		int greensid = 0;
		greensid = Integer.parseInt(request.getParameter("greensid"));
		System.out.println();
		if(greensid>0){
			Greens oneGreens = greensService.findGreensByGreensid(greensid);
			if(oneGreens != null){
				session.setAttribute("oneGreens", oneGreens);
				request.getRequestDispatcher("greens_update_rest.jsp").forward(request, response);
			}else{
				out.print("<script>alert('SQL Error');history.back();</script>");
			}
		}else{
			out.print("<script>alert('Request Error');history.back();</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
