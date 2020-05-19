package com.zive.bi.entity;

import java.util.Date;
/**
 * 购买合作项目
 * @author Lsenrong
 * @date Oct 26, 2017 5:15:57 PM
 * @Description: TODO(描述)
 */
public class CooperationBuy extends CooperationPayment{
	/**
	 * 收款门店
	 */
	private Integer receiptShopType;
	/**
	 * 咨询人
	 */
	private String adviser;
	/**
	 *  医院美容师
	 */
	private String cosmetologist;
	private String cosmetologist2;
	private String cosmetologist3;
	/**
	 * 业务id
	 */
	private String businessId;
	/**
	 * 合作项目id
	 */
    private String cooperationId;
    /**
     * 合作项目编号
     */
    private String cooperationNo;
    /**
     * 合作项目名称
     */
    private String cooperationName;
    /**
     * 活动id
     */
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 购买数量
     */
    private Double buyNumber;
    /**
     * 优惠码
     */
    private String coupon;
    /**
     * 会员id
     */
    private String cardId;
    /**
     * 会员卡号
     */
    private String cardNo;
    /**
     * 会员名称
     */
    private String memberName;
    /**
     * 是否有效会员
     */
    private Integer isPass;
    /**
     * 成为有效会员时间
     */
    private Date passTime;
    /**
     * 购买单价
     */
    private Double buyPrice;
    /**
     * 购买金额
     */
    private Double buyMoney;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 购买日期
     */
    private Date consumeDate;
    /**
     * 单据状态
     */
    private Integer status;
    /**
     * 作废日期
     */
    private Date failDate;
    /**
     * 区域
     */
    private String area;
    /**
     * 做单人
     */
    private String makerName;
    /**
     * 顾问编号
     */
    private String employeeNo;
    /**
     * 顾问名称
     */
    private String employeeName;
    /**
     * 业绩比例
     */
    private Double percent;
   
    private String earn;
    private String storeEarn;
    
	/**
	 * @return the percent
	 */
	public Double getPercent() {
		return percent;
	}
	/**
	 * @param percent the percent to set
	 */
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public String getCooperationId() {
		return cooperationId;
	}
	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}
	public String getCooperationNo() {
		return cooperationNo;
	}
	public void setCooperationNo(String cooperationNo) {
		this.cooperationNo = cooperationNo;
	}
	public String getCooperationName() {
		return cooperationName;
	}
	public void setCooperationName(String cooperationName) {
		this.cooperationName = cooperationName;
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
	public Double getBuyNumber() {
		return buyNumber;
	}
	public void setBuyNumber(Double buyNumber) {
		this.buyNumber = buyNumber;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
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
	public Double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Double getBuyMoney() {
		return buyMoney;
	}
	public void setBuyMoney(Double buyMoney) {
		this.buyMoney = buyMoney;
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
	public Date getConsumeDate() {
		return consumeDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
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
	public String getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEarn() {
		return earn;
	}
	public void setEarn(String earn) {
		this.earn = earn;
	}
	public String getStoreEarn() {
		return storeEarn;
	}
	public void setStoreEarn(String storeEarn) {
		this.storeEarn = storeEarn;
	}
	public Integer getReceiptShopType() {
		return receiptShopType;
	}
	public void setReceiptShopType(Integer receiptShopType) {
		this.receiptShopType = receiptShopType;
	}
	public String getAdviser() {
		return adviser;
	}
	public void setAdviser(String adviser) {
		this.adviser = adviser;
	}
	public String getCosmetologist() {
		return cosmetologist;
	}
	public void setCosmetologist(String cosmetologist) {
		this.cosmetologist = cosmetologist;
	}
	public String getCosmetologist2() {
		return cosmetologist2;
	}
	public void setCosmetologist2(String cosmetologist2) {
		this.cosmetologist2 = cosmetologist2;
	}
	public String getCosmetologist3() {
		return cosmetologist3;
	}
	public void setCosmetologist3(String cosmetologist3) {
		this.cosmetologist3 = cosmetologist3;
	}
	
    
}
