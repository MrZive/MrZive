package com.zive.dataOut.entity;

import java.util.Date;

public class ProjectConsumption {

	/**
	* id
	*/
	private String id;
	
	/**
	* consumption_id
	*/
	private String consumptionId;
	
	/**
	* 储值账号支付
	*/
	private Double storePay;
	
	/**
	* 银行卡支付
	*/
	private Double bankcardPay;
	
	/**
	* 现金支付
	*/
	private Double cashPay;
	
	/**
	* 积分支付
	*/
	private Double pointPay;
	
	/**
	* 是否是定金支付
	*/
	private Integer isBook;
	
	/**
	* 应该支付
	*/
	private Double payment;
	
	/**
	* 有效业绩
	*/
	private Double effectiveEarn;
	
	/**
	* remark
	*/
	private String remark;
	
	/**
	* 是否生日赠送，1是，0不是
	*/
	private Integer isBirthday;
	
	/**
	* 实际支付
	*/
	private Double realPayment;
	
	/**
	* 欠款
	*/
	private Double owe;
	
	/**
	* member_card_id
	*/
	private String memberCardId;
	
	/**
	* 是否使用现金券，1是0不是
	*/
	private Integer isCashCoupon;
	
	/**
	* create_date
	*/
	private Date createDate;
	
	/**
	* 0正常，1作废，2退款
	*/
	private Integer isFail;
	
	/**
	* shopid
	*/
	private String shopid;
	
	/**
	* cash_coupon
	*/
	private String cashCoupon;
	
	/**
	* buy_owe
	*/
	private Double buyOwe;
	
	/**
	* is_detail_pay
	*/
	private Integer isDetailPay;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
	}

	public Double getStorePay() {
		return storePay;
	}

	public void setStorePay(Double storePay) {
		this.storePay = storePay;
	}

	public Double getBankcardPay() {
		return bankcardPay;
	}

	public void setBankcardPay(Double bankcardPay) {
		this.bankcardPay = bankcardPay;
	}

	public Double getCashPay() {
		return cashPay;
	}

	public void setCashPay(Double cashPay) {
		this.cashPay = cashPay;
	}

	public Double getPointPay() {
		return pointPay;
	}

	public void setPointPay(Double pointPay) {
		this.pointPay = pointPay;
	}

	public Integer getIsBook() {
		return isBook;
	}

	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public Double getEffectiveEarn() {
		return effectiveEarn;
	}

	public void setEffectiveEarn(Double effectiveEarn) {
		this.effectiveEarn = effectiveEarn;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsBirthday() {
		return isBirthday;
	}

	public void setIsBirthday(Integer isBirthday) {
		this.isBirthday = isBirthday;
	}

	public Double getRealPayment() {
		return realPayment;
	}

	public void setRealPayment(Double realPayment) {
		this.realPayment = realPayment;
	}

	public Double getOwe() {
		return owe;
	}

	public void setOwe(Double owe) {
		this.owe = owe;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Integer getIsCashCoupon() {
		return isCashCoupon;
	}

	public void setIsCashCoupon(Integer isCashCoupon) {
		this.isCashCoupon = isCashCoupon;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getCashCoupon() {
		return cashCoupon;
	}

	public void setCashCoupon(String cashCoupon) {
		this.cashCoupon = cashCoupon;
	}

	public Double getBuyOwe() {
		return buyOwe;
	}

	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
	}

	public Integer getIsDetailPay() {
		return isDetailPay;
	}

	public void setIsDetailPay(Integer isDetailPay) {
		this.isDetailPay = isDetailPay;
	}
		
}
