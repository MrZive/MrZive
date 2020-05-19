package com.zive.bi.entity;

import java.util.Date;

public class Payment {
	/**
	 * 单据id
	 */
	private String orderId;
	/**
	 * 门店id
	 */
	private String shopId;
	/**
	 * 门店名称
	 */
    private String shopName;
    /**
     * 状态
     */
    private Integer status;
	/**
     * 储值账户
     */
    private Double store;
    /**
     * 刷卡
     */
    private Double pos;
    /**
     * 现金
     */
    private Double cash;
    /**
     * 积分
     */
    private Double point;
    /**
     * 欠款
     */
    private Double owe;
    /**
     * 缴纳订金
     */
    private Integer isBook;
    /**
     * 应该支付
     */
    private Double shouldPay;
    /**
     * 实际支付
     */
    private Double realPay;
    /**
     * 消费日期
     */
    private Date consumeDate;
    /**
     * 类型
     */
    private String type;
    
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Double getStore() {
		return store;
	}
	public void setStore(Double store) {
		this.store = store;
	}
	public Double getPos() {
		return pos;
	}
	public void setPos(Double pos) {
		this.pos = pos;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Double getOwe() {
		return owe;
	}
	public void setOwe(Double owe) {
		this.owe = owe;
	}
	public Integer getIsBook() {
		return isBook;
	}
	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
	}
	public Double getShouldPay() {
		return shouldPay;
	}
	public void setShouldPay(Double shouldPay) {
		this.shouldPay = shouldPay;
	}
	public Double getRealPay() {
		return realPay;
	}
	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
	public Date getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
    
}
