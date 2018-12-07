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
import com.neuedu.entity.User;
import com.neuedu.service.UserService;
import com.neuedu.service.Impl.UserServiceImpl;
import com.neuedu.util.StringUtil;

public class UserRegisterServlet extends HttpServlet {
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

		// �������ļ��ж�ȡ�ļ�����
		String fileType = this.getServletContext().getInitParameter("fileType");

		// �����ַ�����
		request.setCharacterEncoding(charSet);

		// ����1---�����ϴ����---ʵ����SmartUpload����
		SmartUpload smartUpload = new SmartUpload("utf-8");
		System.out.println("�����ϴ����");
		try {
			// ����2---��ʼ���ϴ����---����initialize()����
			smartUpload.initialize(getServletConfig(), request, response);
			System.out.println("��ʼ���ϴ����");

			// ���2---����ļ���С
			smartUpload.setMaxFileSize(10 * 1024 * 1024);
			System.out.println("����ļ���С");

			// ���3---����ļ���ʽ
			smartUpload.setAllowedFilesList(fileType);// ������ļ�����
			System.out.println("����ļ���ʽ");

			// smartUpload.setDeniedFilesList("html");//��������ļ�����

			// ����3---�ϴ��ļ�������������ʱ�ڴ���---����upload()����
			smartUpload.upload();

		} catch (SmartUploadException e1) {
			e1.printStackTrace();
			out.print("<script>alert('�ļ��ϴ�ʧ��');history.back();</script>");
		} catch (SecurityException e) {
			out.print("<script>alert('�ļ���С���ܳ���10M���ļ����ͱ���Ϊjpg,gif,png����');history.back();</script>");
		}
		System.out.println("�ϴ��ļ�������������ʱ�ڴ���");

		// ��֤��֤��
		String valCode = smartUpload.getRequest().getParameter("valCode");
		String valCodeSession = (String) session.getAttribute("valCodeInSession");

		if (!valCode.equalsIgnoreCase(valCodeSession)) {
			out.println("<script>alert('��֤�����벻��ȷ��������������');history.back();</script>");
			return;
		}

		// ��������
		
		String usernumber = "";
		String userpassword = "";
		String userphoto = "";
		String username = "";
		String usercardnumber = "";
		String usercardpassword = "";
		int useramount = 0;
		int useraddress = 0;
		try {
			usernumber = smartUpload.getRequest().getParameter("usernumber");
			userpassword = smartUpload.getRequest().getParameter("userpassword");
			userphoto = smartUpload.getRequest().getParameter("photo");
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�
			if ("-1.gif".equals(userphoto)) {
				try {
					// ����4---��ȡ�ϴ��ļ�
					SmartFile smartFile = smartUpload.getFiles().getFile(0);
					// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
					String serverFilePath = request.getRealPath("/image/photo");

					String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
					userphoto = serverFilename;

					// ����6---�����ϴ��ļ�
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SecurityException e) {
					out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
				}
			}
			
			Random random = new Random();
			
			username = smartUpload.getRequest().getParameter("username");
			usercardnumber = smartUpload.getRequest().getParameter("usercardnumber");
			usercardpassword = smartUpload.getRequest().getParameter("usercardpassword");
			useramount = random.nextInt(100);
			useraddress = Integer.parseInt(smartUpload.getRequest().getParameter("useraddress"));
			
			
		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='user_register.jsp'>��������</a>");
		}

		UserService userService = new UserServiceImpl();

		User user = new User("admin",username,usernumber,userpassword,userphoto,usercardnumber,usercardpassword,useramount,useraddress);

		if (userService.checkUsernumber(usernumber)) {
			if (userService.addUser(user)) {
				session.setAttribute("user", user);

				response.sendRedirect("user_register_result.jsp");
			}
		} else {
			out.println("<script> alert('�Ñ����Ѿ����ڣ�Ո����ע��');history.back();</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
