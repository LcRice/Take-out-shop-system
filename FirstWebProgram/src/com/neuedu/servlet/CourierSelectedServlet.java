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
		
		CourierrestService courierrestService = new CourierrestServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		OrderdetailService orderdetailService = new OrderdetailServiceImpl();
		
		int userorderid =(Integer) session.getAttribute("userorderid");
		
		int courierid = Integer.parseInt(request.getParameter("courierid"));
		
		Restaurant rest = (Restaurant) session.getAttribute("rest");
		
		int restid = rest.getRestid();
		//��������Ա��������
		if(courierrestService.insertCourierrest(courierid, restid, userorderid)){
			//����״̬---����״̬;����Ա״̬
			if(orderdetailService.updateOrderstatus(userorderid, 3) && courierService.updateCourierstatus("æµ", courierid)){
				out.println("<script>alert('���ͳɹ�!');location='rest_index.jsp';</script>");
			}else{
				out.println("<script>alert('����ʧ��!');location='CourierQueryServlet';</script>");
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
