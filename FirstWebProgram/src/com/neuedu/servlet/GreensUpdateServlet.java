package com.neuedu.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartFile;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.service.GreensService;
import com.neuedu.service.Impl.GreensServiceImpl;
import com.neuedu.util.StringUtil;

public class GreensUpdateServlet extends HttpServlet {
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

		GreensService greensService = new GreensServiceImpl();

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

		String greensname = "";
		int greensprice = 0;
		String greensphoto = "";
		int greensnumber = 0;
		int greenscommentcount = 0;
		try {
			greensname = smartUpload.getRequest().getParameter("greensname");
			greensprice = Integer.parseInt(smartUpload.getRequest().getParameter("greensprice"));
			greensphoto = smartUpload.getRequest().getParameter("file");
			greensnumber = Integer.parseInt(smartUpload.getRequest().getParameter("greensnumber"));
			// �ж�photo�Ƿ�Ϊ�ϴ��ļ�

			try {
				// ����4---��ȡ�ϴ��ļ�
				SmartFile smartFile = smartUpload.getFiles().getFile(0);
				// ����5---׼���ϴ��ļ��Ĵ洢·�����ļ���---��֤�ļ���Ψһ
				String serverFilePath = request.getRealPath("/image/photo");

				String serverFilename = StringUtil.convertFilename(smartFile.getFileName());
				greensphoto = serverFilename;

				// ����6---�����ϴ��ļ�
				smartFile.saveAs(serverFilePath + "/" + serverFilename);
			} catch (SecurityException e) {
				out.print("<script>alert('�ļ���С���ܳ���100k���ļ����ͱ���Ϊjpg����');history.back();</script>");
			}

		} catch (Exception e) {
			out.print("�Ϊ�գ���<a href='greens_add.jsp'>��������</a>");
		}

		Restaurant rest = (Restaurant) session.getAttribute("rest");
		int restid = rest.getRestid();

		Greens oneGreens = (Greens) session.getAttribute("oneGreens");
		int oldGreensid = oneGreens.getGreensid();

		Greens newgreens = new Greens(greensname, greensprice, greensphoto, greensnumber, greenscommentcount, rest);

		if (greensService.checkGreensname(greensname, restid)) {
			System.out.println("����Ϊδ�ϼܵĲ�");
			Greens oldgreens = greensService.findGreensByGreensid(oldGreensid);
			if (greensService.updateGreensAll(oldgreens, newgreens)) {
				out.println("<script>alert('�²˸��³ɹ�');location='GreensQueryServlet?currentPage=1'</script>");
			} else {
				out.println("<script>alert('�²˸���ʧ�ܣ�����������');history.back()</script>");
			}
		} else {
			System.out.println("����Ϊ���ϼܵĲ�");
			Greens oldgreens = greensService.findGreens(greensname, restid);
			if (oldGreensid == oldgreens.getGreensid()) {
				System.out.println("����Ϊ�Լ�");
				if (greensService.updateGreensAll(oldgreens, newgreens)) {
					out.println("<script>alert('�²˸��³ɹ�');location='GreensQueryServlet?currentPage=1'</script>");
				} else {
					out.println("<script>alert('�²˸���ʧ�ܣ�����������');history.back()</script>");
				}
			} else {
				System.out.println("����Ϊ����");
				if(greensService.updateGreensAll(oldgreens, newgreens) && greensService.deleteGreensByGreensid(oldGreensid)){
					out.println("<script>alert('�²˸��³ɹ�');location='GreensQueryServlet?currentPage=1'</script>");
				} else {
					out.println("<script>alert('�²˸���ʧ�ܣ�����������');history.back()</script>");
				}
			}

		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
