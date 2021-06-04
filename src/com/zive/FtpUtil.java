package com.zive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
/**
 * ftp上传
 * @author Lsenrong
 * @date Aug 7, 2018 4:45:59 PM
 * @Description: TODO(描述)
 */
public class FtpUtil {
	private static Log logger = LogFactory.getLog(FtpUtil.class);
	/** 本地字符编码 */
	private static String LOCAL_CHARSET = "GBK";
	// FTP协议里面，规定文件名编码为iso-8859-1
	private static String SERVER_CHARSET = "ISO-8859-1";
	private static String hostname;
	private static String username;
	private static String password;
	private static Integer port;
	public enum PathType{
		/**
		 * 文件
		 */
		File,
		/**
		 * 目录
		 */
		Directory
	}
	static {
		hostname = "192.168.0.40";
//		hostname = "47.115.57.77";
		username = "ftp";
		password = "qhcqhc168";
		port = 21;
	}
	
	/**
	 * 获取ftp连接
	 * @Title: getConection 
	 * @author: Lsenrong
	 * @date Aug 8, 2018 10:05:08 AM
	 * @param @return
	 * @param @throws SocketException
	 * @param @throws IOException    
	 * @return FTPClient
	 */
    public static FTPClient getConection() throws SocketException, IOException{
    	FTPClient ftp = new FTPClient(); 
        //连接   
    	/*URI uri= URI.create(url);
    	String hostName = uri.getHost();*/
    	logger.debug("连接至服务器" + hostname);
        ftp.connect(hostname, port);
        //登陆  
        if(ftp.login(username, password)){
        	ftp.enterLocalPassiveMode();
        	logger.debug("登录成功");
        	if(FTPReply.isPositiveCompletion(ftp.sendCommand("OPTS UTF8", "ON"))){
        		logger.debug("切换至UTF-8编码");
            	LOCAL_CHARSET = "UTF-8";
            };
        }else{
        	logger.debug("登录失败");
        	throw new RuntimeException("登录失败");
        }
        return ftp;
    }
    /**
     * 上传文件
     * @Title: upload 
     * @author: Lsenrong
     * @date Sep 25, 2018 1:55:52 PM
     * @param @param uploadPathName
     * @param @param bytes
     * @param @return
     * @param @throws SocketException
     * @param @throws IOException    
     * @return boolean
     */
    public static boolean upload(String uploadPathName,byte[] bytes) throws SocketException, IOException{
    	return upload(uploadPathName, new ByteArrayInputStream(bytes));
    }
    /**
     * 上传文件
     * @Title: upload 
     * @author: Lsenrong
     * @date Aug 8, 2018 10:05:25 AM
     * @param @param uploadPathName
     * @param @param inputStream
     * @param @return
     * @param @throws SocketException
     * @param @throws IOException    
     * @return boolean
     */
    public static boolean upload(String uploadPathName, InputStream inputStream) throws SocketException, IOException{
    	Boolean flag = false;
    	FTPClient client = null;
    	logger.debug("准备上传...");
    	try{
    		client = getConection();
    		String pathName = new String(uploadPathName.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
			if(pathName.lastIndexOf(".") > -1){
	        	client.setFileType(FTPClient.BINARY_FILE_TYPE);
	        	String dirName = pathName.substring(0,pathName.lastIndexOf("/") + 1);
	        	if(!client.changeWorkingDirectory(dirName)){
	        		//循环创建文件夹
	        		String temp = dirName;
	        		int offset = 1;
	        		while(true){
	        			int index = dirName.indexOf("/", offset);
	        			if(index > -1){
	        				String subStr = dirName.substring(0,index);
		        			if(!client.changeWorkingDirectory(subStr)){
		        				//可能存在其他线程创建了文件夹的情况不作判断防止高并发情况下出现问题...
		        				client.makeDirectory(subStr);
		        				/*if(!client.makeDirectory(subStr)){
		        					throw new BusinessException("创建" + subStr + "文件夹失败");
		        				}*/
		        			}
	        			}else{
	        				break;
	        			}
	        			offset = index + 1;
	        		}
	        	}
	        	flag = client.storeFile(pathName, inputStream);
                client.logout();
	    	}else{
	    		throw new RuntimeException("只能上传文件！请检查文件名是否填写正确");
	    	}
		}finally{
			if(client != null){
				if(client.isConnected()){
					client.disconnect();
				}
			}
			if(inputStream != null){
				inputStream.close();
			}
		}
    	if(flag){
    		logger.debug("文件上传成功！ -> " + uploadPathName);
    	}else{
    		logger.debug("文件上传失败！ -> " + uploadPathName);
    		throw new RuntimeException("文件上传失败！ -> " + uploadPathName);
    	}
    	return flag;
    }
    /**
     * 下载文件
     * @Title: download 
     * @author: Lsenrong
     * @date Sep 25, 2018 1:33:18 PM
     * @param @param downloadPathName
     * @param @return
     * @param @throws SocketException
     * @param @throws IOException    
     * @return byte[]
     */
    public static byte[] download(String downloadPathName) throws SocketException, IOException{
    	FTPClient client = null;
    	logger.debug("准备下载文件 -> " + downloadPathName);
    	boolean flag = false;
    	byte[] bytes = null;
    	try{
    		client = getConection();
    		client.setFileType(FTPClient.BINARY_FILE_TYPE);
        	ByteArrayOutputStream os  = new ByteArrayOutputStream();
        	flag = client.retrieveFile(new String(downloadPathName.getBytes(LOCAL_CHARSET),SERVER_CHARSET), os);
			client.logout();
			bytes = os.toByteArray();
		}finally{
			if(client != null){
				if(client.isConnected()){
					client.disconnect();
				}
			}
		}
    	if(flag){
    		logger.debug("文件下载成功！ -> " + downloadPathName);
    	}else{
    		logger.debug("文件下载失败！ -> " + downloadPathName);
    		throw new RuntimeException("文件下载失败！ -> " + downloadPathName);
    	}
    	return bytes;
    }
    
    public static void main(String[] args) throws SocketException, IOException {
		byte[] download = download("/BI/123.txt");
		System.out.println(download);
	}
    
    
    /**
     * 删除文件
     * @Title: delete 
     * @author: Lsenrong
     * @date Aug 30, 2018 10:11:16 AM
     * @param @param pathName
     * @param @param pathType
     * @param @return
     * @param @throws SocketException
     * @param @throws IOException    
     * @return boolean
     */
    public static boolean delete(String pathName, PathType pathType) throws SocketException, IOException{
    	Boolean flag = false;
    	FTPClient client = null;
    	logger.debug("准备删除文件或文件夹...");
    	try{
    		client = getConection();
        	switch (pathType) {
				case File:
					flag = deleteFile(client, pathName);
					break;
				case Directory:
					flag = deleteDirectory(client, pathName);
					break;
			}
			client.logout();
		}finally{
			if(client != null){
				if(client.isConnected()){
					client.disconnect();
				}
			}
		}
    	if(flag){
    		logger.debug("删除成功！ -> " + pathName);
    	}else{
    		logger.debug("删除失败！ -> " + pathName);
    		throw new RuntimeException("删除失败！ -> " + pathName);
    	}
    	return flag;
    }
    
    
    /**
     * 删除文件
     * @Title: deleteFile 
     * @author: Lsenrong
     * @date Aug 30, 2018 10:40:38 AM
     * @param @param client
     * @param @param pathName
     * @param @return
     * @param @throws IOException    
     * @return boolean
     */
    private static boolean deleteFile(FTPClient client, String pathName) throws IOException{
    	pathName = new String(pathName.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
    	return client.deleteFile(pathName);
    }
    /**
     * 删除文件夹
     * @Title: deleteDirectory 
     * @author: Lsenrong
     * @date Aug 30, 2018 10:40:45 AM
     * @param @param client
     * @param @param pathName
     * @param @return
     * @param @throws IOException    
     * @return boolean
     */
    private static boolean deleteDirectory(FTPClient client, String localCharsetPathName) throws IOException{
    	boolean flag = true;
    	logger.debug("遍历目录  => " + localCharsetPathName);
    	client.enterLocalPassiveMode();
    	String pathName = new String(localCharsetPathName.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
    	FTPFile[] files = client.listFiles(pathName);
	    Pattern r = Pattern.compile("[.]+");
    	for(FTPFile file : files){
    		String localCharsetFileName = new String(file.getName().getBytes(SERVER_CHARSET),LOCAL_CHARSET);
    	    if(!r.matcher(localCharsetFileName).matches()){
    	    	if(!flag){
        			break;
        		}
    	    	//String encodePathName = new String((pathName + "/" + file.getName()).getBytes(LOCAL_CHARSET),SERVER_CHARSET);
    	    	String localCharsetPathFileName = localCharsetPathName + "/" + localCharsetFileName;
    	    	String serverCharsetPathFileName = new String(localCharsetPathFileName.getBytes(LOCAL_CHARSET),SERVER_CHARSET);
    	    	if(file.isDirectory()){
        			flag = deleteDirectory(client, localCharsetPathFileName);
        		} else if(file.isFile()){
        			flag = client.deleteFile(serverCharsetPathFileName);
        			if(flag){
    					logger.debug("文件删除成功 -> " + localCharsetPathFileName);
    				}else{
    					logger.debug("文件删除失败 -> " + localCharsetPathFileName);
    				}
        		}
    	    }
    	}
    	if(flag){
    		flag = client.removeDirectory(pathName);
    		if(flag){
				logger.debug("文件夹删除成功 => " + localCharsetPathName);
			}else{
				logger.debug("文件夹删除失败 => " + localCharsetPathName);
				throw new RuntimeException("文件夹删除失败 => " + localCharsetPathName);
				
			}
    	}
    	return flag;
    }
}
