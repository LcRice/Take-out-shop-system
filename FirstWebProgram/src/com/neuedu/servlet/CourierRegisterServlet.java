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
import com.neuedu.entity.Courier;
import com.neuedu.service.CourierService;
import com.neuedu.service.Impl.CourierServiceImpl;
import com.neuedu.util.StringUtil;

public class CourierRegisterServlet extends HttpServlet {
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
		
		String couriernumber = "";
		String courierpassword = "";
		String courierphoto = "";
		String couriername = "";
		int courieramount = 0;
		try {
			couriernumber = smartUpload.getRequest().getParameter("couriernumber");
			courierpassword = smartUpload.getRequest().getParameter("courierpassword");
			courierphoto = smartUpload.getRequest().getParameter("photo");
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�
			if ("-1.gif".equals(courierphoto)) {
				try {
					// ����4---��ȡ�ϴ��ļ�
					SmartFile smartFile = smartUpload.getFiles().getFile(0);
					// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
					String serverFilePath = request.getRealPath("/image/photo");

					String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
					courierphoto = serverFilename;

					// ����6---�����ϴ��ļ�
					smartFile.saveAs(serverFilePath + "/" + serverFilename);
				} catch (SecurityException e) {
					out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
				}
			}
			
			
			
			couriername = smartUpload.getRequest().getParameter("couriername");
			
			
			
		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='rest_register.jsp'>��������</a>");
		}

		CourierService courierService = new CourierServiceImpl();

		Courier courier = new Courier("admin",couriername,couriernumber,courierpassword,courierphoto,courieramount,"����",0,0);

		if (courierService.checkCouriernumber(couriernumber)) {
			if (courierService.addCourier(courier)) {
				session.setAttribute("courier", courier);

				response.sendRedirect("courier_register_result.jsp");
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
