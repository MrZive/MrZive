package com.zive.bi.entity;

/**
 * 购买项目支付详情
 * @author Lsenrong
 * @date Sep 14, 2017 4:23:33 PM
 * @Description: TODO(描述)
 */
public class ProjectPayment  extends Payment{
    /**
     * 生日赠送
     */
    private Integer isBirthday;
	
	public Integer getIsBirthday() {
		return isBirthday;
	}
	public void setIsBirthday(Integer isBirthday) {
		this.isBirthday = isBirthday;
	}
    
}
