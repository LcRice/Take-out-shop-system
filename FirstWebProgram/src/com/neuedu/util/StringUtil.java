package com.neuedu.util;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletContext;

//字符串的工具类
public class StringUtil {

	public static void main(String[] args) {
		
		//System.out.println(convertFilename("d:/image/aa.jpg"));
		
		System.out.println(convertDatetime("2018-09-14 10:10:10", "yyyy年MM月dd日 HH时mm分ss秒"));
		
	}
	
	//将给定文件的主文件名改为yyyyMMddHHmmssSSSxxx的形式，其中xxx是100到999之间的随机数，文件的扩展名不变
	public static String convertFilename(String filename){
		
		//取出扩展名
		String extension = filename.substring(filename.lastIndexOf(".") + 1);
		
		//生成当前时间对应的字符串 yyyyMMddHHmmssSSS
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		
		String now = df.format(new Date());
		
		//生成100-999的随机数  0-899
		Random random = new Random();
		int num = random.nextInt(900) + 100;
		
		return now + num + "." + extension;
	}
	
	private static final String DB_PATTERN = "yyyy-MM-dd HH:mm:ss";
	
	//将数据库格式(yyyy-MM-dd HH:mm:ss)的日期时间字符串(datetime)转换为指定格式(pattern)的字符串
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
			return "刚刚";
		}else if(seconds <= 60 * 60){
			return seconds / 60 + "分钟前";
		}else if(seconds <= 60 * 60 * 3){
			return seconds / 60 / 60 + "小时前";
		}else if(seconds <= 60 * 60 * 24){
			return "今天 " + convertDatetime(datetime, "HH时mm分");
		}else{
			return convertDatetime(datetime, "MM月dd日 HH时mm分");
		}		
	}
	
	//将给定的时间 02:00:00加一天  
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
			
	//将数据库中的用户名username中出现关键字keyword的地方加红处理,否则直接返回
	public static String convertKeyword(String username, String keyword){

        if(!"".equals(keyword)){
	       return username.replace(keyword, "<font color='red'><b>" + keyword + "</b></font>");    
        }else{
        	return username;
        }
	}
	
	//获取附件对应的图标文件名
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
	
	//获取附件文件的大小
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
