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

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;
import com.neuedu.service.GreensService;
import com.neuedu.service.RestService;
import com.neuedu.service.ShoppingcarService;
import com.neuedu.service.SuperadminService;
import com.neuedu.service.UserService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.service.Impl.RestServiceImpl;
import com.neuedu.service.Impl.ShoppingcarServiceImpl;
import com.neuedu.service.Impl.SuperadminServiceImpl;
import com.neuedu.service.Impl.UserServiceImpl;

public class PayCheckServlet extends HttpServlet {
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

		GreensService greensService = new GreensServiceImpl();
		RestService restService = new RestServiceImpl();
		UserService userService = new UserServiceImpl();
		SuperadminService superadminService = new SuperadminServiceImpl();
		ShoppingcarService shoppingcarService = new ShoppingcarServiceImpl();

		User user = (User) session.getAttribute("user");
		int price = (Integer) session.getAttribute("totalprice");
		List<Greens> greenslist = (List<Greens>) session.getAttribute("ordergreenslist");
		List<Shoppingcar> shoppingcarslist = (List<Shoppingcar>) session.getAttribute("ordershoppingcarslist");
		
		String usercardnumber = user.getUsercardnumber();
		String usercardpassword = user.getUsercardpassword();

		String cardnumber = request.getParameter("usercardnumber");
		String cardpassword = request.getParameter("usercardpassword");

		if (usercardnumber.equals(cardnumber) && usercardpassword.equals(cardpassword)) {
			if (userService.updateUser(user, price)) {
				for (int i = 0; i < greenslist.size(); i++) {
					if (superadminService.updateSuperamount(greenslist.get(i))
							&& restService.updateRestamount(greenslist.get(i)) && greensService.updateGreensnumber(greenslist.get(i), shoppingcarslist.get(i))) {
						if (!shoppingcarService.deleteShopping(user, greenslist.get(i))) {
							out.print("<script>alert('购买失败！');history.back();</script>");
						}
					}
				}
				out.print("<script>alert('购买成功！');location='user_index.jsp'</script>");
			}
		} else {
			out.print("<script>alert('输入卡号密码不正确！');history.back();</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
