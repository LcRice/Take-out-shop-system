package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.CourierService;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.OrderdetailService;
import com.neuedu.service.OrdersummaryService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.OrderdetailServiceImpl;
import com.neuedu.service.Impl.OrdersummaryServiceImpl;

public class CourierSelectedServlet extends HttpServlet {
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
		
		CourierrestService courierrestService = new CourierrestServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		OrderdetailService orderdetailService = new OrderdetailServiceImpl();
		
		int userorderid =(Integer) session.getAttribute("userorderid");
		
		int courierid = Integer.parseInt(request.getParameter("courierid"));
		
		Restaurant rest = (Restaurant) session.getAttribute("rest");
		
		int restid = rest.getRestid();
		//插入外卖员餐厅数据
		if(courierrestService.insertCourierrest(courierid, restid, userorderid)){
			//更新状态---订单状态;配送员状态
			if(orderdetailService.updateOrderstatus(userorderid, 3) && courierService.updateCourierstatus("忙碌", courierid)){
				out.println("<script>alert('配送成功!');location='rest_index.jsp';</script>");
			}else{
				out.println("<script>alert('配送失败!');location='CourierQueryServlet';</script>");
			}
		}else{
			out.println("<script>alert('Insert Error!');location='CourierQueryServlet';</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
