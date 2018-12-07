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
import com.neuedu.entity.Courierrest;
import com.neuedu.service.CourierService;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.OrderdetailService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.OrderdetailServiceImpl;

public class CourierFinishServlet extends HttpServlet {
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

		// �����ַ�����
		request.setCharacterEncoding(charSet);
		
		Courier courier = (Courier) session.getAttribute("courier");
		
		CourierrestService courierrestService = new CourierrestServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		OrderdetailService orderdetailService = new OrderdetailServiceImpl();
		
		Courierrest courierrest = courierrestService.findCourierrest(courier.getCourierid());
		
		if(courierrest != null){
			if(courierrestService.updateStatus(courierrest.getCourierrestid()) && courierService.updateCourierstatus("����", courier.getCourierid()) && orderdetailService.updateOrderstatus(courierrest.getUserorderid(), 2)){
				out.println("<script>alert('�������!');location='courier_index.jsp';</script>");
			}else{
				out.println("<script>alert('����ʧ��!');location='courier_index.jsp';</script>");
			}
		}else{
			out.println("<script>alert('���޶���!');location='courier_index.jsp';</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
