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

public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

		UserService userService = new UserServiceImpl();

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

		// ��������

		String username = "";
		String userphoto = "";
		String usernumber = "";
		String userpassword = "";
		String usercardnumber = "";
		String usercardpassword = "";
		int useraddress = 0;
		try {
			username = smartUpload.getRequest().getParameter("username");
			userphoto = smartUpload.getRequest().getParameter("file");
			usernumber = smartUpload.getRequest().getParameter("usernumber");
			userpassword = smartUpload.getRequest().getParameter("userpassword");
			usercardnumber = smartUpload.getRequest().getParameter("usercardnumber");
			usercardpassword = smartUpload.getRequest().getParameter("usercardpassword");
			useraddress = Integer.parseInt(smartUpload.getRequest().getParameter("useraddress"));
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�

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

		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='user_update.jsp'>��������</a>");
		}

		Random random = new Random();
		int useramount = random.nextInt(100);
		
		User olduser = (User) session.getAttribute("user");
		
		User newuser = new User("admin",username,usernumber,userpassword,userphoto,usercardnumber,usercardpassword,useramount,useraddress);
		
		if(userService.updateUser(olduser, newuser)){
			session.removeAttribute("user");
			session.setAttribute("user", newuser);
			out.println("<script>alert('�û����³ɹ�');location='user_query.jsp'</script>");
		} else {
			out.println("<script>alert('�û�����ʧ�ܣ�����������');history.back()</script>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
