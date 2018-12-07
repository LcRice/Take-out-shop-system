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
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		// 获取session对象
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

		// 从配置文件中读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		// 从配置文件中读取文件类型
		String fileType = this.getServletContext().getInitParameter("fileType");

		// 设置字符编码
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
