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
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象
		PrintWriter out = response.getWriter();

		// 获取session对象
		HttpSession session = request.getSession();

		// 读取字符编码
		String charSet = this.getServletContext().getInitParameter("charSet");

		// 设置字符编码
		request.setCharacterEncoding(charSet);

		RestcommentService restcommentService = new RestcommentServiceImpl();
		RestService restService = new RestServiceImpl();

		// 从session属性范围中取出当前登录的用户
		User user = (User) session.getAttribute("user");

		int userid = user.getUserid();

		// 接收数据
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

			out.println("<script>alert('评论添加成功');location='RestCommentQueryServlet?currentPage=1&userorderid="
					+ userorderid + "'</script>");

		} else {
			out.println("<script>alert('评论添加失败');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
