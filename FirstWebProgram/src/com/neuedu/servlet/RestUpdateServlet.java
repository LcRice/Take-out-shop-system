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

		RestService restService = new RestServiceImpl();

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
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�

			try {
				// ����4---��ȡ�ϴ��ļ�
				SmartFile smartFile = smartUpload.getFiles().getFile(0);
				// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
				String serverFilePath = request.getRealPath("/image/photo");

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
				restphoto = serverFilename;

				// ����6---�����ϴ��ļ�
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SecurityException e) {
				out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
			}

		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='rest_update.jsp'>��������</a>");
		}

		Random random = new Random();
		int restamount = random.nextInt(100);
		
		Restaurant oldrest = (Restaurant) session.getAttribute("rest");
		
		Restaurant newrest = new Restaurant("admin",restname,restnumber,restpassword,restphoto,restcardnumber,restcardpassword,restamount,restaddress,oldrest.getRestcommentcount());
		
		if(restService.updateRest(oldrest, newrest)){
			session.removeAttribute("rest");
			session.setAttribute("rest", newrest);
			out.println("<script>alert('�û����³ɹ�');location='rest_query.jsp'</script>");
		} else {
			out.println("<script>alert('�û�����ʧ�ܣ�����������');history.back()</script>");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
