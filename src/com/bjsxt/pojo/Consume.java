package com.bjsxt.pojo;

import java.math.BigDecimal;

public class Consume {
	/**
	 * 门店id
	 */
    private String shopId;
    
    /**
     * 会员卡
     */
    private String memberCardId;
    
    /**
     * 消耗次数
     */
    private Integer consumeNumber;
    /**
     * 消耗金额
     */
    private BigDecimal consumeMoney;
    /**
     * 消耗类型
     */
    private String type;
    /**
     * 数据（未知待定）合作项目消耗此处存放的是是否纹绣
     */
    private Object data;
    /**
     * 数据（未知待定）合作项目消耗此处存放的是是否计入门店消耗
     */
    private Object data2;
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Integer getConsumeNumber() {
		return consumeNumber;
	}
	public void setConsumeNumber(Integer consumeNumber) {
		this.consumeNumber = consumeNumber;
	}
	public BigDecimal getConsumeMoney() {
		return consumeMoney;
	}
	public void setConsumeMoney(BigDecimal consumeMoney) {
		this.consumeMoney = consumeMoney;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMemberCardId() {
		return memberCardId;
	}
	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Object getData2() {
		return data2;
	}
	public void setData2(Object data2) {
		this.data2 = data2;
	}
    
    
}
