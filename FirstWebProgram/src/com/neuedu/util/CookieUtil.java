package com.neuedu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	//根据名称查找cookiede值
	public static String findCookie(HttpServletRequest request, String name){
		
		//获取全部的cookie
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			
			for(Cookie cookie : cookies){
				if(name.equals(cookie.getName())){
					return cookie.getValue();
				}
			}
			
			return null;
			
		}else{
			return null;
		}
	}
	
	//添加持久化cookie
	public static void addCookie(HttpServletResponse response, String name, String value, int days){
		
		//创建cookie
		Cookie cookie = new Cookie(name, value);

		//MaxAge最大存活时间
		cookie.setMaxAge(days * 24 * 60 * 60);
		
		//发送cookie
		response.addCookie(cookie);
		
	}
	
	//删除cookie
	public static void deleteCookie(HttpServletResponse response, String name){
		
		//创建cookie
		Cookie cookie = new Cookie(name, "");

		//MaxAge最大存活时间
		cookie.setMaxAge(0);
		
		//发送cookie
		response.addCookie(cookie);
		
	}
	
}