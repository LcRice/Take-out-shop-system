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
		
		CouriercommentService couriercommentService = new CouriercommentServiceImpl();
		CourierService courierService = new CourierServiceImpl();
		
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
					out.println("<script>alert('评论添加成功');location='CourierCommentQueryServlet?currentPage=1&userorderid=" + userorderid + "'</script>");
				}else{
					System.out.println("配送员好评更新失败");
				}
			}else{
				System.out.println("没达到3分好评要求");
				out.println("<script>alert('评论添加成功');location='CourierCommentQueryServlet?currentPage=1&userorderid=" + userorderid + "'</script>");
			}
		}else {
			out.println("<script>alert('评论添加失败');history.back()</script>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
