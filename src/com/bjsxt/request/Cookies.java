package com.bjsxt.request;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Cookies {
	@RequestMapping("/getCookie")
	public String hello(HttpServletRequest req,HttpServletResponse res) {
		Cookie[] cookies = req.getCookies();
		boolean flag = false;
		if(cookies!=null&&cookies.length>0){
			for(Cookie c : cookies){
				if(c.getName().equals("who")&&c.getValue().equals("zive")){
					flag=true;
				}
			}
		}
		if(!flag){
			Cookie cookie = new Cookie("who","zive");
			cookie.setPath("/zive/zive");
			res.addCookie(cookie);
		}
		return "cookies";
	}
}
