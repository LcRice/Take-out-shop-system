package com.neuedu.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

	//�������Ʋ���cookiedeֵ
	public static String findCookie(HttpServletRequest request, String name){
		
		//��ȡȫ����cookie
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
	
	//��ӳ־û�cookie
	public static void addCookie(HttpServletResponse response, String name, String value, int days){
		
		//����cookie
		Cookie cookie = new Cookie(name, value);

		//MaxAge�����ʱ��
		cookie.setMaxAge(days * 24 * 60 * 60);
		
		//����cookie
		response.addCookie(cookie);
		
	}
	
	//ɾ��cookie
	public static void deleteCookie(HttpServletResponse response, String name){
		
		//����cookie
		Cookie cookie = new Cookie(name, "");

		//MaxAge�����ʱ��
		cookie.setMaxAge(0);
		
		//����cookie
		response.addCookie(cookie);
		
	}
	
}