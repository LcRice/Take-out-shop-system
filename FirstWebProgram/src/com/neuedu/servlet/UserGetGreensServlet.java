package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Courierrest;
import com.neuedu.service.CourierService;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.OrderdetailService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.OrderdetailServiceImpl;

public class UserGetGreensServlet extends HttpServlet {
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

		// 设置字符编码
		request.setCharacterEncoding(charSet);
		
		OrderdetailService orderdetailService = new OrderdetailServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		CourierrestService courierrestService = new CourierrestServiceImpl();
		
		int userorderid = Integer.parseInt(request.getParameter("userorderid"));
		
		Courierrest courierrest = courierrestService.findCourierrestByUser(userorderid);
		
		if(orderdetailService.updateOrderstatus(userorderid, 4)){
			if(courierService.updateCourieramount(courierrest.getCourierid())){
				out.print("<script>alert('收货成功');location='user_index.jsp';</script>");
			}else{
				out.print("<script>alert('courierrest Error');history.back();</script>");
			}
		}else{
			out.print("<script>alert('orderdetail Error');history.back();</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
