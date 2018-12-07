package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Courierrest;
import com.neuedu.entity.Restcomment;
import com.neuedu.entity.User;
import com.neuedu.service.CourierrestService;
import com.neuedu.service.RestService;
import com.neuedu.service.RestcommentService;
import com.neuedu.service.Impl.CourierrestServiceImpl;
import com.neuedu.service.Impl.RestServiceImpl;
import com.neuedu.service.Impl.RestcommentServiceImpl;

public class CommentAddServletforRest extends HttpServlet {
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

		RestcommentService restcommentService = new RestcommentServiceImpl();
		RestService restService = new RestServiceImpl();

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

		int restid = courierrest.getRestid();

		Restcomment restcomment = new Restcomment();
		restcomment.setUserid(userid);
		restcomment.setRestid(restid);
		restcomment.setRestcommenttitle(title);
		restcomment.setRestcommentcontext(content);
		restcomment.setRestcommentpubtime(date);
		restcomment.setScore(score);

		if (restcommentService.insertRestcomment(restcomment) && restService.updateRestcommentcount(restid)) {

			out.println("<script>alert('������ӳɹ�');location='RestCommentQueryServlet?currentPage=1&userorderid="
					+ userorderid + "'</script>");

		} else {
			out.println("<script>alert('�������ʧ��');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
