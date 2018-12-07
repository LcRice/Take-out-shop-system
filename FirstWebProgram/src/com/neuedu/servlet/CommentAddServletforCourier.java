package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Couriercomment;
import com.neuedu.entity.Courierrest;
import com.neuedu.entity.User;
import com.neuedu.service.CourierService;
import com.neuedu.service.CouriercommentService;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.service.Impl.CouriercommentServiceImpl;
import com.neuedu.service.Impl.CourierrestServiceImpl;


public class CommentAddServletforCourier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ���������������
		response.setContentType("text/html;charset=utf-8");

		// ��ȡout�������
		PrintWriter out = response.getWriter();

		// ��ȡsession����
		HttpSession session = request.getSession();

		// ��ȡ�ַ�����
		String charSet = this.getServletContext().getInitParameter("charSet");

		// �����ַ�����
		request.setCharacterEncoding(charSet);
		
		CouriercommentService couriercommentService = new CouriercommentServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		
		// ��session���Է�Χ��ȡ����ǰ��¼���û�
		User user = (User) session.getAttribute("user");
		
		int userid = user.getUserid();

		// ��������
		String title = request.getParameter("title");
		
		String content = request.getParameter("content");
		
		Date date = new Date();

		int score = Integer.parseInt(request.getParameter("score"));

		int userorderid = Integer.parseInt(request.getParameter("userorderid"));
		
		CourierrestService courierrestService = new CourierrestServiceImpl();
		
		Courierrest courierrest = courierrestService.findCourierrestByUser(userorderid);
		
		int courierid = courierrest.getCourierid();
		
		Couriercomment couriercomment = new Couriercomment();
		couriercomment.setUserid(userid);
		couriercomment.setCourierid(courierid);
		couriercomment.setCouriercommenttitle(title);
		couriercomment.setCouriercommentcontext(content);
		couriercomment.setCouriercommentpubtime(date);
		couriercomment.setScore(score);

		if(couriercommentService.addCouriercomment(couriercomment) && courierService.updateCouriercomment(courierid)){
			if(score>3){
				if(courierService.updateCourierWellReceived(courierid)){
					out.println("<script>alert('������ӳɹ�');location='CourierCommentQueryServlet?currentPage=1&userorderid=" + userorderid + "'</script>");
				}else{
					System.out.println("����Ա��������ʧ��");
				}
			}else{
				System.out.println("û�ﵽ3�ֺ���Ҫ��");
				out.println("<script>alert('������ӳɹ�');location='CourierCommentQueryServlet?currentPage=1&userorderid=" + userorderid + "'</script>");
			}
		}else {
			out.println("<script>alert('�������ʧ��');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
