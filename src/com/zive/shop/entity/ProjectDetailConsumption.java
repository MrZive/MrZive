package com.zive.shop.entity;

import java.util.Date;
import java.util.List;

/**
 * 项目消费明显记录
 * @author hcxue
 *
 */
public class ProjectDetailConsumption {

	private String id;
	private String consumptionId;
	private int channelId;//0青花资 1线上 2天使之纹 3丘山医美 4其他
	
//	微信支付
	private Double weChatPay;
//	支付宝支付
	private Double aliPay;
	
	private Integer isWenxiu;
	/**
	 * 服务类型，0可以服务，1不可以服务
	 */
	private int serviceType;
	
	
	/**
	 * ********暂存的合作项目购买需要字段***************************************************************
	 */
	/**
	 * 收款门店类型，0是消费门店，1是星和
	 */
	private Integer receiptShopType;
	/**
	 * 星和合作项目购买及还款的咨询人
	 */
	private String adviser;
	/**
	 * 医院美容师
	 */
	private String cosmetologist;
	private String cosmetologist2;
	private String cosmetologist3;
	/**
	 * ********暂存的合作项目购买需要字段***************************************************************
	 */
	
	
//	是否使用现金券，1是0不是
	private Integer isCashCoupon;
	
//	消费业绩
//	private List<EarnConsumption> earnConsumptions;
//	private List<EarnConsumption> earnDoneConsumptions;
	
//	如果项目是二选一
	private String selectProjectId;

//	储值账号支付
	private Double storePay;
	
//	银行卡刷卡支付
	private Double bankCardPay;
	
//	现金支付
	private Double cashPay;
	
//	积分支付
	private Double pointPay;
	
//	是否是定金支付
	private Integer isBook;
	
//	应该支付
	private Double payment;
	
//	有效业绩
	private Double effectiveEarn;
	
	
//  实际支付
	private Double realPayment;
	
//	欠款
	private Double owe;
//	购买时的欠款，即下单时候的欠款，不随着还款发生改变
	private Double buyOwe;
	
//	是否有支付信息
	private Integer isPay;
	
//	备注
	private String remark; 
	
	
	
	
	
//  有效期/月，从顾客购买的时候开始计算
  private Integer effectiveMonths;
	
//	会员卡ID
	private String memberCardId;
	
//	是否是套餐
	private Integer isSet;
	
//	套餐消费ID
	private String consumptionSetId;
	
//	项目消费ID
	private String consumptionProjectId;
	
//	项目ID
	private String projectId;
	
	private String projectName;
	private String projectNo;
	
//	活动ID
	private String activityId;
	
	private String activityName;
	
//	购买数量
	private Integer buyNumber;
	
//	实际数量,实际数量,做服务后剩余的数量
	private Integer number;
	
//	优惠码
	private String coupon;
	
//	是否是老会员推荐赠送,1是，0不是
//	private Integer isIntroduce;
//	
////	介绍人卡号
//	private String introducer;
	
//	是否是赠送，1是，0不是
	private Integer isSend;
	
//	创建日期
	private Date createDate;
	
//	有效期
	private Date endDate;
	
//	服务时间
	private Integer serviceTime;
	
//	做服务次数
	private Integer doneNumber;
	private String doneRemark;
	
//	做服务时间
	private Integer doneServiceTime;
	
//	是否满意
	private Integer isSatisfied;
	
	private Double price;
	
	/**
	 * 项目体验价
	 */
	private Double experiencePrice;
	/**
	 * 项目市场价
	 */
	private Double marketPrice;
	/**
	 * 项目推广价
	 */
	private Double promotionPrice;
	
//	是否计次,1是0不是
	private Integer isCount;
	
//	门店ID
	private String shopId;
	
	
//	如果使用了优惠活动用以区分是否是同一个购买的数据
	private String buyType;
	
	private Integer isFail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	

	public Integer getDoneNumber() {
		return doneNumber;
	}

	public void setDoneNumber(Integer doneNumber) {
		this.doneNumber = doneNumber;
	}

	public Integer getDoneServiceTime() {
		return doneServiceTime;
	}

	public void setDoneServiceTime(Integer doneServiceTime) {
		this.doneServiceTime = doneServiceTime;
	}

	public Integer getIsSatisfied() {
		return isSatisfied;
	}

	public void setIsSatisfied(Integer isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Integer getIsSet() {
		return isSet;
	}

	public void setIsSet(Integer isSet) {
		this.isSet = isSet;
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

	public Integer getEffectiveMonths() {
		return effectiveMonths;
	}

	public void setEffectiveMonths(Integer effectiveMonths) {
		this.effectiveMonths = effectiveMonths;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Double getExperiencePrice() {
		return experiencePrice;
	}

	public void setExperiencePrice(Double experiencePrice) {
		this.experiencePrice = experiencePrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
	}

	public Integer getIsCashCoupon() {
		return isCashCoupon;
	}

	public void setIsCashCoupon(Integer isCashCoupon) {
		this.isCashCoupon = isCashCoupon;
	}

//	public List<EarnConsumption> getEarnConsumptions() {
//		return earnConsumptions;
//	}
//
//	public void setEarnConsumptions(List<EarnConsumption> earnConsumptions) {
//		this.earnConsumptions = earnConsumptions;
//	}
//
//	public List<EarnConsumption> getEarnDoneConsumptions() {
//		return earnDoneConsumptions;
//	}
//
//	public void setEarnDoneConsumptions(List<EarnConsumption> earnDoneConsumptions) {
//		this.earnDoneConsumptions = earnDoneConsumptions;
//	}

	public Double getStorePay() {
		return storePay;
	}

	public void setStorePay(Double storePay) {
		this.storePay = storePay;
	}

	public Double getBankCardPay() {
		return bankCardPay;
	}

	public void setBankCardPay(Double bankCardPay) {
		this.bankCardPay = bankCardPay;
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

	public Double getBuyOwe() {
		return buyOwe;
	}

	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
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

	public Integer getIsWenxiu() {
		return isWenxiu;
	}

	public void setIsWenxiu(Integer isWenxiu) {
		this.isWenxiu = isWenxiu;
	}

	public String getSelectProjectId() {
		return selectProjectId;
	}

	public void setSelectProjectId(String selectProjectId) {
		this.selectProjectId = selectProjectId;
	}

	public int getServiceType() {
		return serviceType;
	}

	public void setServiceType(int serviceType) {
		this.serviceType = serviceType;
	}

	public String getDoneRemark() {
		return doneRemark;
	}

	public void setDoneRemark(String doneRemark) {
		this.doneRemark = doneRemark;
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

	public Double getWeChatPay() {
		return weChatPay;
	}

	public void setWeChatPay(Double weChatPay) {
		this.weChatPay = weChatPay;
	}

	public Double getAliPay() {
		return aliPay;
	}

	public void setAliPay(Double aliPay) {
		this.aliPay = aliPay;
	}

	
}
