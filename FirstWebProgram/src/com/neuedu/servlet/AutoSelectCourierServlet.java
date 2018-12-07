package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Courier;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.CourierService;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.OrderdetailService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.OrderdetailServiceImpl;

public class AutoSelectCourierServlet extends HttpServlet {
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
		
		Restaurant rest = (Restaurant) session.getAttribute("rest");
		int restid = rest.getRestid();
		
		List<Orderdetail> orderlist = orderdetailService.findUnfinishedOrderByRest(restid);
		
		if(orderlist.size() == 0){
			out.println("<script>alert('暂无订单!');location='rest_index.jsp';</script>");
		}
		
		
		for(Orderdetail o : orderlist){
			Courier courier = courierService.findMAXCourier();
			if(courierrestService.insertCourierrest(courier.getCourierid(), restid, o.getUserorderid())){
				if(orderdetailService.updateOrderstatus(o.getUserorderid(), 3) && courierService.updateCourierstatus("忙碌", courier.getCourierid())){
					out.println("<script>alert('配送成功!');location='rest_index.jsp';</script>");
				}else{
					out.println("<script>alert('配送失败!');location='rest_index.jsp';</script>");
				}
			}else{
				out.println("<script>alert('Insert Error!');location='rest_index.jsp';</script>");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
