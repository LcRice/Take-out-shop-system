package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.RestService;
import com.neuedu.service.Impl.RestServiceImpl;
import com.neuedu.util.StringUtil;

public class RestUpdateServlet extends HttpServlet {
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

		// 从配置文件中读取文件类型
		String fileType = this.getServletContext().getInitParameter("fileType");

		// 设置字符编码
		request.setCharacterEncoding(charSet);

		RestService restService = new RestServiceImpl();

		// 步骤1---创建上传组件---实例化SmartUpload对象
		SmartUpload smartUpload = new SmartUpload("utf-8");
		System.out.println("创建上传组件");
		try {
			// 步骤2---初始化上传组件---调用initialize()方法
			smartUpload.initialize(getServletConfig(), request, response);
			System.out.println("初始化上传组件");

			// 检查2---检查文件大小
			smartUpload.setMaxFileSize(10 * 1024 * 1024);
			System.out.println("检查文件大小");

			// 检查3---检查文件格式
			smartUpload.setAllowedFilesList(fileType);// 允许的文件类型
			System.out.println("检查文件格式");

			// smartUpload.setDeniedFilesList("html");//不允许的文件类型

			// 步骤3---上传文件到服务器的临时内存中---调用upload()方法
			smartUpload.upload();

		} catch (SmartUploadException e1) {
			e1.printStackTrace();
			out.print("<script>alert('文件上传失败');history.back();</script>");
		} catch (SecurityException e) {
			out.print("<script>alert('文件大小不能超过10M，文件类型必须为jpg,gif,png类型');history.back();</script>");
		}
		System.out.println("上传文件到服务器的临时内存中");

		// 接收数据

		String restname = "";
		String restphoto = "";
		String restnumber = "";
		String restpassword = "";
		String restcardnumber = "";
		String restcardpassword = "";
		int restaddress = 0;
		try {
			restname = smartUpload.getRequest().getParameter("restname");
			restphoto = smartUpload.getRequest().getParameter("file");
			restnumber = smartUpload.getRequest().getParameter("restnumber");
			restpassword = smartUpload.getRequest().getParameter("restpassword");
			restcardnumber = smartUpload.getRequest().getParameter("restcardnumber");
			restcardpassword = smartUpload.getRequest().getParameter("restcardpassword");
			restaddress = Integer.parseInt(smartUpload.getRequest().getParameter("restaddress"));
			// 判断photo是否为上传文件

			try {
				// 步骤4---提取上传文件
				SmartFile smartFile = smartUpload.getFiles().getFile(0);
				// 步骤5---准备上传文件的存储路径和文件名---保证文件名唯一
				String serverFilePath = request.getRealPath("/image/photo");

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
				restphoto = serverFilename;

				// 步骤6---保存上传文件
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SecurityException e) {
				out.print("<script>alert('文件大小不能超过100k，文件类型必须为jpg类型');history.back();</script>");
			}

		} catch (Exception e) {
			out.print("填寫为空，请<a href='rest_update.jsp'>重新输入</a>");
		}

		Random random = new Random();
		int restamount = random.nextInt(100);
		
		Restaurant oldrest = (Restaurant) session.getAttribute("rest");
		
		Restaurant newrest = new Restaurant("admin",restname,restnumber,restpassword,restphoto,restcardnumber,restcardpassword,restamount,restaddress,oldrest.getRestcommentcount());
		
		if(restService.updateRest(oldrest, newrest)){
			session.removeAttribute("rest");
			session.setAttribute("rest", newrest);
			out.println("<script>alert('用户更新成功');location='rest_query.jsp'</script>");
		} else {
			out.println("<script>alert('用户更新失败，请重新输入');history.back()</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
