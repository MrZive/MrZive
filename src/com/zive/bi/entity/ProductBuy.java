package com.zive.bi.entity;

import java.util.Date;

/**
 * 购买产品
 * @author Lsenrong
 * @date Sep 14, 2017 4:35:49 PM
 * @Description: TODO(描述)
 */
public class ProductBuy extends ProductPayment{
	/**
	 * 业务编号
	 */
	private String businessId;
	/**
	 * 单据id
	 */
    private String orderId;
    /**
	 * 用于标记会员此单是否为有效业绩分享
	 */
	private Integer failEarn;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 产品id
     */
    private String productId;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 活动id
     */
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 卡号id
     */
    private String cardId;
    /**
     * 会员卡编号
     */
    private String cardNo;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 是否是有效会员
     */
    private Integer isPass;
    /**
     * 成为有效会员时间
     */
    private Date passTime;
    /**
     * 优惠码
     */
    private String coupon;
    /**
     * 赠送
     */
    private Integer isSend;
    /**
     * 购买单价价格
     */
    private Double buyPrice;
    /**
     * 购买数量
     */
    private Double buyNumber;
    /**
     * 购买金额
     */
    private Double buyMoney;
    /**
     * 购买单位
     */
    private String buyUnit;
    /**
     * 区域
     */
    private String area;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 0青花资 1线上 2天使之纹 3丘山医美 4其他
     */
    private Integer channelId;
    /**
     * 作废日期
     */
    private Date failDate;
    /**
     * 消费日期
     */
    private Date consumeDate;
    /**
     * 做单人
     */
    private String makerName;
    /**
     * 养生顾问和业绩
     */
    private String shoperAndEarn;
    /**
     * 是否提货
     */
    private String isPick;
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
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public String getProductNo() {
		return productNo;
	}
	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getActivityId() {
		return activityId;
	}
	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public Integer getChannelId() {
		return channelId;
	}
	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
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
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(Double buyNumber) {
		this.buyNumber = buyNumber;
	}
	public Double getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
	}
	public String getBuyUnit() {
		return buyUnit;
	}
	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getFailDate() {
		return failDate;
	}
	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}
	public Date getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Integer getIsPass() {
		return isPass;
	}
	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}
	public Date getPassTime() {
		return passTime;
	}
	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}
	public String getMakerName() {
		return makerName;
	}
	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}
	public Integer getFailEarn() {
		return failEarn;
	}
	public void setFailEarn(Integer failEarn) {
		this.failEarn = failEarn;
	}
	public String getShoperAndEarn() {
		return shoperAndEarn;
	}
	public void setShoperAndEarn(String shoperAndEarn) {
		this.shoperAndEarn = shoperAndEarn;
	}
	public String getIsPick() {
		return isPick;
	}
	public void setIsPick(String isPick) {
		this.isPick = isPick;
	}
    
}
