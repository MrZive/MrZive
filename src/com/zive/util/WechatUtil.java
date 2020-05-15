package com.zive.util;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zive.pojo.TemplateMessage;


/**
 * [微信发送相关工具]
 * @author WangZive
 * @date: 2020年4月24日 上午10:49:14
 */
public class WechatUtil {
	private final static Log logger = LogFactory.getLog(WechatUtil.class);
	
	//token
	@Value("${wechat.token}")
	public static String TOKEN = "qhcwechat";
	//开发者id
	@Value("${wechat.appid}")
//	public static String APP_ID = "wxc105e0da6e9316f0";
	public static String APP_ID = "wx01c16e1da5c3e2a4";
//	public static String APP_ID = "wxaf38fd1ad22456cf";
//	public static String APP_ID = "wxf72b55bfc102411d";
//	public static String APP_ID = "wx0c4cdc3b883a2e07";
	//开发者密码
	@Value("${wechat.appsecret}")
//	public static String APP_SECRET = "1d0f4857d9083405bf89d7a7d6418e1c";
	public static String APP_SECRET = "5ae28ae009e4e3b7f4274d4a338135f4";
//	public static String APP_SECRET = "5c0ea766c087c21f0d85b3205d88291e";
//	public static String APP_SECRET = "242dae2db3f0a093d87ce419ceeeb15e";
//	public static String APP_SECRET = "a8570d5036a90b1627b84d1bddb2e48b";
	//API
	@Value("${wechat.rooturl}")
	public static String ROOT_URL = "https://api.weixin.qq.com";
	//授权凭证
	public static String ACCESS_TOKEN = "33_-W3Bj6OBGe8c_jisH4sPGSc-8sMLvyMtRJV6H9P0JIZ0Lla9odl9uCMd3Kw1-GOgVE-1wCM4RSgqzt2HlRpUXZUStIhH8DRQslRNWbIgM3DSeHCZcfO4zLEbQ49yusxiZgRBtgd219NU57WtOSEfAJAUYK";
	
//	static {
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while (true) {
//					int expiresIn = refreshAccessToken();
//					try {
//						Thread.sleep((expiresIn - 20) * 1000);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						logger.error("WechatUtil.static初始化失败");
//					}
//				}
//			}
//		}).start();
//	}
	
    /**
     * @Title: refreshAccessToken 
     * @Description: TODO(刷新token) 
     * @date 2019年9月3日 上午10:42:41 
     * @author Lsenrong
     * @param @return 
     * @return int 返回类型 
     * @throws
     */
    @SuppressWarnings("unchecked")
	public static int refreshAccessToken(){
    	int expiresIn = 0;
    	try {
    		String url = WechatAPI.ACCESS_TOKEN.value() + "?grant_type=client_credential&appid=" + APP_ID +"&secret=" + APP_SECRET;
        	Map<String, Object> result = NetworkUtil.get(url,Map.class);
        	ACCESS_TOKEN = result.get("access_token").toString();
        	expiresIn = Integer.valueOf(result.get("expires_in").toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error("WechatUtil.refreshAccessToken()失败");
			try {
				Thread.sleep(1000);
//				expiresIn = refreshAccessToken();
			} catch (InterruptedException e1) {}
		}
    	return expiresIn;
    }
    
    public static String getOpenid(String code){
		String url = WechatAPI.GET_OPENID.value() + "?grant_type=authorization_code&appid=" + APP_ID +"&secret=" + APP_SECRET + "&code=" + code;
    	JSONObject result = NetworkUtil.get(url,JSONObject.class);
    	String openid = result.getString("openid");
		return openid;
    }
    
    static public JSONObject getTicket() {
//    	String urlx = WechatAPI.ACCESS_TOKEN.value() + "?grant_type=client_credential&appid=" + APP_ID +"&secret=" + APP_SECRET;
//    	Map<String, Object> resultx = NetworkUtil.get(urlx,Map.class);
//    	String access_token = resultx.get("access_token").toString();
	   String url = WechatAPI.GET_Ticket.value() + "?access_token="+WechatUtil.ACCESS_TOKEN+"&type=jsapi";
	   JSONObject result = NetworkUtil.get(url,JSONObject.class);
	   return result;
	}
    
    /**
     * @Title: sendTemplateMessage 
     * @Description: TODO(发送模板消息) 
     * @date 2019年9月3日 上午10:43:00 
     * @author Lsenrong
     * @param @param templateMessage
     * @param @return
     * @param @throws KeyManagementException
     * @param @throws NoSuchAlgorithmException
     * @param @throws NoSuchProviderException
     * @param @throws IOException 
     * @return boolean 返回类型 
     * @throws
     */
    @SuppressWarnings("unchecked")
	public static boolean sendTemplateMessage(TemplateMessage templateMessage) throws KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException, IOException {
    	Map<Object, Object> params = new HashMap<Object, Object>();
    	params.put("touser", templateMessage.getTouser());
    	params.put("template_id", templateMessage.getTemplateid());
    	if(templateMessage.getUrl() != null && templateMessage.getUrl().trim().length()>0) {
    		params.put("url", templateMessage.getUrl());
    	}
    if(templateMessage.getMiniprogram() != null && templateMessage.getMiniprogram().size()>0) {
    		params.put("miniprogram", templateMessage.getMiniprogram());
    	}
    	params.put("data", templateMessage.getData());
    	if(templateMessage.getColor() != null && templateMessage.getColor().trim().length()>0) {
    		params.put("color", templateMessage.getColor());
    	}
    	String url = WechatAPI.TEMPLATE_MESSAGE.value() + "?access_token=" + WechatUtil.ACCESS_TOKEN;
    	System.out.println(url);
    	System.out.println(JSON.toJSONString(params));
    	Map<String,Object> result = NetworkUtil.post(url, params, Map.class);
    	if(result.get("errcode").equals(0)) {
    		return true;
    	} else {
    		return false;
    	}
    }
}
