package com.bjsxt.pub;

import java.util.Date;

public class JsonResult {
	/**
	 * 请求结果标识
	 */
	public Boolean status;
	/**
	 * 提示信息
	 */
	public String msg;
	/**
	 * 数据
	 */
	public Object data;
	/**
	 * 任意指定
	 */
	public Object object;
	/**
	 * 时间
	 */
	public Date time;
	/**
	 * 返回码
	 */
	public String code;
	
	public JsonResult(Boolean status,String msg)
	{
		this.status = status;
		this.msg = msg;
	}
	public JsonResult(Boolean status,String msg,Object data)
	{
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public JsonResult(Boolean status,String msg,Object data,Object object)
	{
		this.status = status;
		this.msg = msg;
		this.data = data;
		this.object =  object;
	}
	public JsonResult()
	{
		
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@SuppressWarnings("unchecked")
	public <T> T getData() {
		return (T)this.data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Date getTime() {
		return time == null ? new Date() : time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@SuppressWarnings("unchecked")
	public <T> T  getObject() {
		return (T)object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	
}