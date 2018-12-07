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
import com.neuedu.service.CourierService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.util.CookieUtil;

public class CourierLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置输出内容类型
		response.setContentType("text/html;charset=utf-8");

		// 获取out输出对象---需要输出内容时加此句
		PrintWriter out = response.getWriter();

		// 获取session对象---需要session对象时加此句
		HttpSession session = request.getSession();

		// 获取application对象
		ServletContext application = this.getServletContext();

		String couriernumber = request.getParameter("couriernumber");
		String courierpassword = request.getParameter("courierpassword");

		String member = request.getParameter("member");
		String autoLogin = request.getParameter("autoLogin");

		// 组合业务层对象
		CourierService courierService = new CourierServiceImpl();

		// 调用业务方法
		Courier courier = courierService.findCourier(couriernumber, courierpassword);

		if (courier != null) { // 登录成功

			// 登录成功并且选中复选框时，发送一个持久化cookie

			// if(member!=null){ //选中复选框
			if (autoLogin != null) { // 选中复选框
				// 发送一个持久化cookie
				CookieUtil.addCookie(response, "courierInfo", couriernumber + "#" + courierpassword, 7);
			}

			// 在session属性范围中保存用户对象
			session.setAttribute("courier", courier);

			// 从session属性范围中获取要返回的URL,默认返回主页
			String prevURL = "courier_index.jsp";

			// 重定向到url
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/" + prevURL));
		} else {
			out.println("<script>alert('用户名或密码错误，请重新输入');history.back()</script>"); // 回退到前一页，之前的数据可以保留
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
