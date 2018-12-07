package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Greencomment;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.User;
import com.neuedu.service.GreencommentService;
import com.neuedu.service.GreensService;
import com.neuedu.service.OrderdetailService;
import com.neuedu.service.Impl.GreencommentServiceImpl;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.service.Impl.OrderdetailServiceImpl;

public class CommentAddServletforGreens extends HttpServlet {
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

		GreencommentService greencommentService = new GreencommentServiceImpl();
		GreensService greensService = new GreensServiceImpl();

		// 从session属性范围中取出当前登录的用户
		User user = (User) session.getAttribute("user");

		int userid = user.getUserid();

		// 接收数据
		String title = request.getParameter("title");

		String content = request.getParameter("content");

		Date date = new Date();

		int score = Integer.parseInt(request.getParameter("score"));

		int userorderid = Integer.parseInt(request.getParameter("userorderid"));

		OrderdetailService orderdetailService = new OrderdetailServiceImpl();

		Orderdetail orderdetail = orderdetailService.findOrderdetail(userorderid);

		int greensid = orderdetail.getGreensid();

		Greencomment greenscomment = new Greencomment();
		greenscomment.setUserid(userid);
		greenscomment.setGreensid(greensid);
		greenscomment.setGreencommenttitle(title);
		greenscomment.setGreencommentcontext(content);
		greenscomment.setGreencommentpubtime(date);
		greenscomment.setScore(score);

		if (greencommentService.insertGreencomment(greenscomment) && greensService.updateGreencommentcount(greensid)) {

			out.println("<script>alert('评论添加成功');location='GreensCommentQueryServlet?currentPage=1&userorderid="
					+ userorderid + "'</script>");

		} else
		{
			out.println("<script>alert('评论添加失败');history.back()</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
