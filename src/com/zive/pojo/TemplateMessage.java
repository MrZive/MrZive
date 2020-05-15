package com.zive.pojo;

import java.util.Map;

public class TemplateMessage {
	
	public static enum TemplateIdEnum{
		/**
		 * [预约成功之后]
		 */
		reservationSucceedWechat,
		/**
		 * [取消预约之后]
		 */
		reservationCancelWechat,
		/**
		 * [预约超时之后]
		 */
		reservationOutDateWechat,
		/**
		 * [预约将近提醒]
		 */
		reservationComingDateWechat
	}
	
	/**
	 * [接收者openid]
	 */
    private String touser;
    
    /**
     * [模板ID]
     */
    private String templateid;
    
    /**
     *[ 模板跳转链接（海外帐号没有跳转能力）非必填]
     */
    private String url;
    
    /**
     * [跳小程序所需数据，不需跳小程序可不用传该数据 非必填]
     */
    private Map<String, Object> miniprogram;
    
    /**
     * [模板数据]
     */
    private Map<String, Object> data;
    
    /**
     * [模板内容字体颜色，不填默认为黑色 非必填]
     */
    private String color;
    
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	public String getTemplateid() {
		return templateid;
	}
	public void setTemplateid(String templateid) {
		this.templateid = templateid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Map<String, Object> getMiniprogram() {
		return miniprogram;
	}
	public void setMiniprogram(Map<String, Object> miniprogram) {
		this.miniprogram = miniprogram;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
    
}
