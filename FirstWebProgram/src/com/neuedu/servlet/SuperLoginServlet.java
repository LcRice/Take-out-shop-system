package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.neuedu.entity.Superadmin;
import com.neuedu.service.SuperadminService;
import com.neuedu.service.Impl.SuperadminServiceImpl;
import com.neuedu.util.CookieUtil;

public class SuperLoginServlet extends HttpServlet {
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

		String supernumber = request.getParameter("supernumber");
		
		String superpassword = request.getParameter("superpassword");

		// 组合业务层对象
		SuperadminService superadminService = new SuperadminServiceImpl();

		// 调用业务方法
		Superadmin superadmin = superadminService.superLogin(supernumber, superpassword);

		if (superadmin != null) { // 登录成功

			// 在session属性范围中保存用户对象
			session.setAttribute("superadmin", superadmin);

			// 从session属性范围中获取要返回的URL,默认返回主页
			String prevURL = "super_index.jsp";

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
