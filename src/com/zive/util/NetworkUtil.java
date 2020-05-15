package com.zive.util;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class NetworkUtil {
	private static Log logger = LogFactory.getLog(NetworkUtil.class);
	private static int connetTimeoutSecond = 60;
	private static int readTimeoutSecond = 120;
	public static class BZX509TrustManager   implements X509TrustManager {  
	    public BZX509TrustManager(){}  
	    @Override  
	    public void checkClientTrusted(X509Certificate[] arg0, String arg1)  
	            throws CertificateException {  
	        // TODO Auto-generated method stub  
	    }  
	  
	    @Override  
	    public void checkServerTrusted(X509Certificate[] arg0, String arg1)  
	            throws CertificateException {  
	        // TODO Auto-generated method stub  
	    }  
	  
	    @Override  
	    public X509Certificate[] getAcceptedIssuers() {  
	        // TODO Auto-generated method stub  
	        return null;  
	    }  
	      
	    public static SSLSocketFactory getSSFactory() throws NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException{  
	        TrustManager[] tm = { new BZX509TrustManager()};  
	        SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
	        sslContext.init(null, tm, new java.security.SecureRandom());  
	        SSLSocketFactory ssf = sslContext.getSocketFactory();  
	        return  ssf;  
	    }  
	}  
	public enum HttpMethod{
		GET,
		POST
	}
	/**
	 * 请求带参数指定返回值类型
	 * @Title: go 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 1:50:33 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param params
	 * @param @param returnType
	 * @param @param httpMethod
	 * @param @param timeoutSeconds 超时时间
	 * @param @return
	 * @param @throws IOException
	 * @param @throws KeyManagementException
	 * @param @throws NoSuchAlgorithmException
	 * @param @throws NoSuchProviderException  
	 * @return T
	 */
	public static <T> T go(String url, Object params, Class<T> returnType,HttpMethod httpMethod,Integer timeoutSeconds) throws IOException, KeyManagementException, NoSuchAlgorithmException, NoSuchProviderException
	{
		URL u = new URL(url);
		InputStreamReader  isr = null;
		try{
			String body = "";
			if(params != null){
				if(params instanceof String){
					body = (String)params;
				}else{
					body = JSON.toJSONString(params,SerializerFeature.DisableCircularReferenceDetect);
				}
			}
			if(url.toLowerCase().startsWith("https://")){
				logger.debug("https_url -> " + url);
				HttpsURLConnection con = (HttpsURLConnection)u.openConnection();
				con.setConnectTimeout((timeoutSeconds == null ? connetTimeoutSecond : timeoutSeconds) * 1000);
				con.setReadTimeout((timeoutSeconds == null ? readTimeoutSecond : timeoutSeconds) * 1000);
				con.setSSLSocketFactory(BZX509TrustManager.getSSFactory());
				con.setRequestProperty("Method", httpMethod.name());
				con.setRequestProperty("Connection", "Keep-Alive"); 
				con.setRequestProperty("Accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				con.setRequestProperty("X-Requested-With", "XMLHttpRequest");  
				con.setDoInput(true);
				con.setDoOutput(true);
				if(httpMethod == HttpMethod.POST){
					OutputStream ops = con.getOutputStream();
					if(params != null){
						logger.debug("https_body -> " + body);
						ops.write(body.getBytes());
						ops.flush();
					}else{
						con.connect();
					}
				} else {
					con.connect();
				}
				isr = new InputStreamReader(con.getInputStream(),"utf-8");
				char[] buffer = new char[1024];
				int len = -1;
				StringBuffer sb = new StringBuffer();
				while ((len = isr.read(buffer)) != -1) {
					sb.append(buffer,0,len);
				}
				String resStr =  new String(sb.toString().getBytes());
				logger.debug("https_response -> " + resStr);
				if(returnType == null){
					return null;
				}else{
					if(returnType == String.class){
						return (T)resStr;
					}else{
						return JSON.parseObject(resStr, returnType);
					}
				}
			} else {
				logger.debug("http_url -> " + url);
				HttpURLConnection con = (HttpURLConnection)u.openConnection();
				con.setConnectTimeout((timeoutSeconds == null ? connetTimeoutSecond : timeoutSeconds) * 1000);
				con.setReadTimeout((timeoutSeconds == null ? readTimeoutSecond : timeoutSeconds) * 1000);
				con.setRequestProperty("Method", httpMethod.name());
				con.setRequestProperty("Connection", "Keep-Alive"); 
				con.setRequestProperty("Accept", "application/json");
				con.setRequestProperty("Content-Type", "application/json;charset=utf-8");
				con.setRequestProperty("X-Requested-With", "XMLHttpRequest");  
				con.setDoInput(true);
				con.setDoOutput(true);
				if(httpMethod == HttpMethod.POST){
					OutputStream ops = con.getOutputStream();
					if(params != null){
						logger.debug("http_body -> " + body);
						ops.write(body.getBytes());
						ops.flush();
					}else{
						con.connect();
					}
				} else {
					con.connect();
				}
				isr = new InputStreamReader(con.getInputStream(),"utf-8");
				char[] buffer = new char[1024];
				int len = -1;
				StringBuffer sb = new StringBuffer();
				while ((len = isr.read(buffer)) != -1) {
					sb.append(buffer,0,len);
				}
				String resStr =  new String(sb.toString().getBytes());
				logger.debug("http_response -> " + resStr);
				if(returnType == null){
					return null;
				}else{
					if(returnType == String.class){
						return (T)resStr;
					}else{
						return JSON.parseObject(resStr, returnType);
					}
				}
			}
			
		} finally{
			if(isr != null){
				isr.close();
			}
		}
	}
	/**
	 * post 
	 * @Title: doSSLPost 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 2:47:38 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param params
	 * @param @param returnType
	 * @param @return    
	 * @return T
	 */
	public static <T> T post(String url, Object params,Class<T> returnType)  
	{
		try {
			return go(url,params,returnType, HttpMethod.POST, null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("连接出错了");
		}
	}
	public static <T> T post(String url, Object params,Class<T> returnType,Integer timoutSeconds)  
	{
		try {
			return go(url,params,returnType, HttpMethod.POST, timoutSeconds);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * post 请求带参数返回字符串
	 * @Title: post 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 1:43:51 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param params
	 * @param @return    
	 * @return String
	 */
	public static String post(String url, Object params)  
	{
		try {
			return go(url,params,null, HttpMethod.POST,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * post 请求 无参数返回字符串
	 * @Title: post 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 1:45:26 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @return    
	 * @return String
	 */
	public static String post(String url){
	    try {
			return go(url,null,String.class,HttpMethod.POST,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * post 请求 无参数带返回值类型
	 * @Title: post 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 1:46:23 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param returnType
	 * @param @return    
	 * @return T
	 */
	public static <T> T post(String url,Class<T> returnType) {
	    try {
			return go(url,null,returnType,HttpMethod.POST,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * get 请求 指定返回值类型
	 * @Title: doSSLGet 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 2:01:31 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param returnType
	 * @param @return    
	 * @return T
	 */
	public static <T> T get(String url, Class<T> returnType)  
	{
		try {
			return go(url,null,returnType,HttpMethod.GET,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * 获取get
	 * @Title: get 
	 * @author: Lsenrong
	 * @date Sep 28, 2018 5:03:22 PM
	 * @param @param url
	 * @param @param params
	 * @param @param returnType
	 * @param @return    
	 * @return T
	 */
	public static <T> T get(String url,Object params, Class<T> returnType)  
	{
		try {
			return go(url,params,returnType,HttpMethod.GET,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了");
		}
	}
	/**
	 * get 请求 指定返回字符串
	 * @Title: get 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 2:01:24 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @return    
	 * @return String
	 */
	public static String get(String url) 
	{
		try {
			return go(url,null,String.class,HttpMethod.GET,null);
		} catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException | IOException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("连接出错了！");
		}
	}
	/**
	 * 拼写url
	 * @Title: spellUrl 
	 * @author: Lsenrong
	 * @date Jun 5, 2018 2:07:31 PM
	 * @Description: TODO(描述) 
	 * @param @param url
	 * @param @param params
	 * @param @return    
	 * @return String
	 */
	public static String spellUrl(String url,Map<String, String> params){
		StringBuffer sb = new StringBuffer();
		sb.append(url);
		if(params != null && params.keySet().size() > 0){
			if(!url.contains("?")){
				sb.append("?");
			}
			for(String key : params.keySet()){
				sb.append(key)
				.append("=")
				.append(params.get(key) == null ? "" : params.get(key))
				.append("&");
			}
			sb.delete(sb.length() -1,sb.length());
		}
		return sb.toString();
	}
	/**
	 * 获取本机私有ip地址
	 * @Title: getSiteLocalAddress 
	 * @author: Lsenrong
	 * @date May 7, 2018 10:36:49 AM
	 * @Description: TODO(描述) 
	 * @param @return
	 * @param @throws SocketException    
	 * @return InetAddress
	 */
	public static InetAddress getSiteLocalAddress() throws SocketException
    {
    	
	    try {
	        InetAddress candidateAddress = null;
	        for (Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements(); ) {
	            NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
	            // 在所有的接口下再遍历IP
	            for (Enumeration inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
	                InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
	                if (!inetAddr.isLoopbackAddress()) {// 排除loopback类型地址
	                	if(inetAddr.isLinkLocalAddress()){
		                    if (inetAddr.isSiteLocalAddress()) {
		                    	//局域网地址
		                        return inetAddr;
		                    } else if (candidateAddress == null) {
		                        //候选地址
		                        candidateAddress = inetAddr;
		                    }
	                	}
	                }
	            }
	        }
	        if (candidateAddress != null) {
	            return candidateAddress;
	        }
	        // 如果没有发现 non-loopback地址.只能用最次选的方案
	        InetAddress jdkSuppliedAddress = InetAddress.getLocalHost();
	        return jdkSuppliedAddress;
	    } catch (Exception e) {
	    }
	    return null;
    }
	/**
	 * 获取外网地址
	 * @Title: getExtranetAddress 
	 * @author: Lsenrong
	 * @date May 4, 2018 6:35:32 PM
	 * @Description: TODO(描述) 
	 * @param @return    
	 * @return String
	 */
	public static String getExtranetAddress(){
        try {
            Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
            while (enumeration.hasMoreElements()) {
                NetworkInterface networkInterface = enumeration.nextElement();
                //Ignore Loop/virtual/Non-started network interface
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                Enumeration<InetAddress> addressEnumeration = networkInterface.getInetAddresses();
                while (addressEnumeration.hasMoreElements()) {
                    InetAddress inetAddress = addressEnumeration.nextElement();
                    String address = inetAddress.getHostAddress();
                    //if(ipFilter.accept(address)){
                        return address;
                    //}
                }
            }
        } catch (SocketException e) {
            //consider log for this exception
        }
        return null;
    }
}
