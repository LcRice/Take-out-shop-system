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

	// 删除购物车
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

		// 设置字符编码
		request.setCharacterEncoding(charSet);
		
		GreensService greensService = new GreensServiceImpl();
		ShoppingcarService shoppingcarService = new ShoppingcarServiceImpl();
		
		int greensid = Integer.parseInt(request.getParameter("greensid"));
		Greens greens = greensService.findGreensByGreensid(greensid);
		
		User user = (User) session.getAttribute("user");
		
		if(shoppingcarService.deleteShopping(user, greens)){
			out.println("<script>alert('删除成功');location='shoppingcar_query_user.jsp'</script>");
		} else {
			out.println("<script>alert('删除失败');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
