package com.zive.location;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.zive.util.NetworkUtil;
import com.zive.util.WechatAPI;

public class Test {
	
	//开发者id
//	@Value("${wechat.appid}")
//	public static String APP_ID = "wxc105e0da6e9316f0";
	public static String APP_ID = "wx01c16e1da5c3e2a4";
//	public static String APP_ID = "wxaf38fd1ad22456cf";
//	public static String APP_ID = "wxf72b55bfc102411d";
	//开发者密码
//	@Value("${wechat.appsecret}")
//	public static String APP_SECRET = "1d0f4857d9083405bf89d7a7d6418e1c";
	public static String APP_SECRET = "5ae28ae009e4e3b7f4274d4a338135f4";
//	public static String APP_SECRET = "5c0ea766c087c21f0d85b3205d88291e";
//	public static String APP_SECRET = "242dae2db3f0a093d87ce419ceeeb15e";
	
	static public String ACCESS_TOKEN = "/cgi-bin/token";
	
	public static String ROOT_URL = "https://api.weixin.qq.com";

	public static void main(String[] args) {
		 ModelAndView mav = new ModelAndView("index");  
	        //获取access_token
	        Map<String, String> params = new HashMap<String, String>();
//	        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
//	        url = url.replace("APPID","wxf72b55bfc102411d");
//	        url = url.replace("APPSECRET","242dae2db3f0a093d87ce419ceeeb15e");
//	        url = url.replace("grant_type","client_credential");
//	        String xml = HttpXmlClient.get(url);
//	        JSONObject jsonMap  = JSONObject.parseObject(xml);
	        Map<String, String> map = new HashMap<String, String>();
//	        Iterator<String> it = jsonMap.keySet().iterator();
//	        while(it.hasNext()) {  
//	            String key = (String) it.next();  
//	            String u = jsonMap.get(key).toString();
//	            map.put(key, u);  
//	        }
	        String url = ROOT_URL + ACCESS_TOKEN + "?grant_type=client_credential&appid=" + APP_ID +"&secret=" + APP_SECRET;
        	Map<String, Object> result = NetworkUtil.get(url,Map.class);
	        String access_token = result.get("access_token").toString();
	 
	        //获取ticket
	        params.put("access_token",access_token);
	        String xml = HttpXmlClient.post("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket",params); 
	        JSONObject jsonMap  = new JSONObject();
	        jsonMap  = JSONObject.parseObject(xml);
	        map = new HashMap<String, String>();
	        Iterator<String> it = jsonMap.keySet().iterator();
	        while(it.hasNext()) {  
	            String key = (String) it.next();  
	            String u = jsonMap.get(key).toString();
	            map.put(key, u);  
	        }
	        String jsapi_ticket = map.get("ticket");
	 
	        //获取签名signature
	        String noncestr = UUID.randomUUID().toString();
	        String timestamp = Long.toString(System.currentTimeMillis() / 1000);
	        //获取请求url
//	        String path = request.getContextPath();
	        //以为我配置的菜单是http://yo.bbdfun.com/first_maven_project/，最后是有"/"的，所以url也加上了"/"
//	        String url = request.getScheme() + "://" + request.getServerName() +  path + "/";
	        url = "http://test.qhc1.com/";
	        String str = "jsapi_ticket=" + jsapi_ticket +
	                "&noncestr=" + noncestr +
	                "&timestamp=" + timestamp +
	                "&url=" + url;
	        //sha1加密
	        String signature = HttpXmlClient.SHA1(str);
	        mav.addObject("signature", signature);   
	        mav.addObject("timestamp", timestamp);   
	        mav.addObject("noncestr", noncestr);   
	        mav.addObject("appId", "wx7099477f2de8aded"); 
	        System.out.println("jsapi_ticket=" + jsapi_ticket);
	        System.out.println("noncestr=" + noncestr);
	        System.out.println("timestamp=" + timestamp);
	        System.out.println("url=" + url);
	        System.out.println("str=" + str);
	        System.out.println("signature=" + signature);
	}
}
