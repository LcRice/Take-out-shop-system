package com.neuedu.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;

//�ַ����Ĺ�����
public class StringUtil {

	public static void main(String[] args) {
		
		//System.out.println(convertFilename("d:/image/aa.jpg"));
		
		System.out.println(convertDatetime("2018-09-14 10:10:10", "yyyy��MM��dd�� HHʱmm��ss��"));
		
	}
	
	//�������ļ������ļ�����ΪyyyyMMddHHmmssSSSxxx����ʽ������xxx��100��999֮�����������ļ�����չ������
	public static String convertFilename(String filename){
		
		//ȡ����չ��
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		
		//���ɵ�ǰʱ���Ӧ���ַ��� yyyyMMddHHmmssSSS
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String now = df.format(new Date());
		
		//����100-999�������  0-899
		Random random = new Random();
		int num = random.nextInt(900) + 100;
		
		return now + num + "." + extension;
	}
	
	private static final String DB_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	//�����ݿ��ʽ(yyyy-MM-dd HH:mm:ss)������ʱ���ַ���(datetime)ת��Ϊָ����ʽ(pattern)���ַ���
	public static String convertDatetime(String datetime, String pattern){
		
		DateFormat source = new SimpleDateFormat(DB_PATTERN);
		DateFormat dest = new SimpleDateFormat(pattern);
		
		try {
			
			//String -->  date
			Date d = source.parse(datetime);

			// date-->  String
			return dest.format(d);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return null; 
	}
	
	public static String convertDatetime(Date datetime, String pattern){
		
		DateFormat df = new SimpleDateFormat(pattern);
		
		return df.format(datetime);
	}
	
	public static String convertDatetime(Date datetime) {
		
		Date now = new Date();
		
		long seconds = (now.getTime() - datetime.getTime() ) / 1000;
		
		if(seconds <= 60){
			return "�ո�";
		}else if(seconds <= 60 * 60){
			return seconds / 60 + "����ǰ";
		}else if(seconds <= 60 * 60 * 3){
			return seconds / 60 / 60 + "Сʱǰ";
		}else if(seconds <= 60 * 60 * 24){
			return "���� " + convertDatetime(datetime, "HHʱmm��");
		}else{
			return convertDatetime(datetime, "MM��dd�� HHʱmm��");
		}		
	}
	
	//��������ʱ�� 02:00:00��һ��  
	public static Date convertDatetime(String time){
		
		Date now = new Date();
		
		Calendar c = Calendar.getInstance();
		
		c.setTime(now);
		
		c.add(Calendar.DAY_OF_MONTH, 1);
		
		Date tommorrow = c.getTime();
		
		String executeTime = StringUtil.convertDatetime(tommorrow, "yyyy-MM-dd") + " " + time;
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			return df.parse(executeTime);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
			
	//�����ݿ��е��û���username�г��ֹؼ���keyword�ĵط��Ӻ촦��,����ֱ�ӷ���
	public static String convertKeyword(String username, String keyword){

        if(!"".equals(keyword)){
	       return username.replace(keyword, "<font color='red'><b>" + keyword + "</b></font>");    
        }else{
        	return username;
        }
	}
	
	//��ȡ������Ӧ��ͼ���ļ���
	public static String getIconFilename(String filename, ServletContext servletContext){
		
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		
		String filepath = servletContext.getRealPath("icon");
		
		File file = new File(filepath + "/" + extension + ".gif");
		
		if(file.exists()){
			return extension + ".gif";
		}else{
			return "default.gif";
		}
		
	}
	
	//��ȡ�����ļ��Ĵ�С
	public static String getFilesize(String filename, ServletContext servletContext){
	
		String filepath = servletContext.getRealPath("/attachment");
		
		File file = new File(filepath + "/" + filename);
		
		long length = file.length();
		
		if(length > 1024*1024){
			
			if(length % (1024*1024) ==0){
				return length / 1024 / 1024 + "M";
			}else{
				return String.format("%.1f", 1.0f * length / 1024 / 1024) + "M";
			}
		}else if(length > 1024){
			
			if(length % 1024 ==0){
				return length / 1024 + "K";
			}else{
				return String.format("%.1f", 1.0f * length / 1024) + "K";
			}
		}else{
			return length + "B";
		}
		
	}
}
