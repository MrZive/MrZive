package com.zive.dataOut.entity;

import java.util.Date;

public class ProjectCooperationWxDetailConsumption {

	/**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * consumption_project_id
    */
    private String consumptionProjectId;

    /**
    * project_id
    */
    private String projectId;

    /**
    * activity_id
    */
    private String activityId;

    /**
    * 购买数量
    */
    private Integer buyNumber;

    /**
    * 实际数量，做服务后剩余的数量
    */
    private Integer number;

    /**
    * 优惠码
    */
    private String coupon;

    /**
    * 是否是赠送，1是，0不是
    */
    private Integer isSend;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * 0青花资 1线上 2天使之纹 3丘山医美 4其他
    */
    private Date endDate;

    /**
    * member_card_id
    */
    private String memberCardId;

    /**
    * consumption_set_id
    */
    private String consumptionSetId;

    /**
    * 单价
    */
    private Double price;

    /**
    * 服务时长
    */
    private Integer serviceTime;

    /**
    * 是否计次
    */
    private Integer isCount;

    /**
    * shop_id
    */
    private String shopId;

    /**
    * 0:正常；1：作废；2：退款。
    */
    private Integer isFail;

    /**
    * 体验价
    */
    private Double experiencePrice;

    /**
    * 推广价
    */
    private Double promotionPrice;

    /**
    * 原价
    */
    private Double marketPrice;

    /**
    * buy_type
    */
    private String buyType;

    /**
    * payment
    */
    private Double payment;

    /**
    * store_pay
    */
    private Double storePay;

    /**
    * bankcard_pay
    */
    private Double bankcardPay;

    /**
    * cash_pay
    */
    private Double cashPay;

    /**
    * point_pay
    */
    private Double pointPay;

    /**
    * is_book
    */
    private Integer isBook;

    /**
    * owe
    */
    private Double owe;

    /**
    * buy_owe
    */
    private Double buyOwe;

    /**
    * effective_earn
    */
    private Double effectiveEarn;

    /**
    * real_payment
    */
    private Double realPayment;

    /**
    * is_pay
    */
    private Integer isPay;

    /**
    * remark
    */
    private String remark;

    /**
    * 是否使用现金券，1是0不是
    */
    private Integer isCashCoupon;

    /**
    * channel_id
    */
    private Integer channelId;
    

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

	public String getConsumptionProjectId() {
		return consumptionProjectId;
	}

	public void setConsumptionProjectId(String consumptionProjectId) {
		this.consumptionProjectId = consumptionProjectId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getConsumptionSetId() {
		return consumptionSetId;
	}

	public void setConsumptionSetId(String consumptionSetId) {
		this.consumptionSetId = consumptionSetId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Integer serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Integer getIsCount() {
		return isCount;
	}

	public void setIsCount(Integer isCount) {
		this.isCount = isCount;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public Double getExperiencePrice() {
		return experiencePrice;
	}

	public void setExperiencePrice(Double experiencePrice) {
		this.experiencePrice = experiencePrice;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
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

	public Double getOwe() {
		return owe;
	}

	public void setOwe(Double owe) {
		this.owe = owe;
	}

	public Double getBuyOwe() {
		return buyOwe;
	}

	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
	}

	public Double getEffectiveEarn() {
		return effectiveEarn;
	}

	public void setEffectiveEarn(Double effectiveEarn) {
		this.effectiveEarn = effectiveEarn;
	}

	public Double getRealPayment() {
		return realPayment;
	}

	public void setRealPayment(Double realPayment) {
		this.realPayment = realPayment;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsCashCoupon() {
		return isCashCoupon;
	}

	public void setIsCashCoupon(Integer isCashCoupon) {
		this.isCashCoupon = isCashCoupon;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
    
}
