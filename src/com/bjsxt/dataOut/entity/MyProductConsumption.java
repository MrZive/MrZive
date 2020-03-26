package com.bjsxt.dataOut.entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;


@TableName("产品销售表")
public class MyProductConsumption {
	
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
	private String id;
	
	@FieldName("流水单号")
	private String consumptionId;
	
	@FieldName("产品消费单号")
	private String consumptionProductId;
	
	@FieldName("门店ID")
	private String shopId;
	
	@FieldName("会员卡ID")
	private String memberCardId;
	
	@FieldName("产品ID")
	private String productId;
	
	@FieldName("活动ID")
	private String activityId;
	
	@FieldName("购买数量")
	private Integer buyNumber;
	
	@FieldName("购买单位")
	private String buyUnit;
	
	@FieldName("购买单价")
	private Double productPrice;
	
	@FieldName("购买单位")
	private Double productUnit;
	
	@FieldName("是否赠送")
	private Integer isSend;
	
	@FieldName("购买时间")
	private Date bookDate;
	
	@FieldName("创建人ID")
	private Date cereateUserId;
	
	@FieldName("创建时间")
	private Date createDate;
	
	@FieldName("备注")
	private String remark; 
	
	
	
	
	@FieldName("0青花瓷 1线上 2天使之纹 3丘山医美 4其他")
	private int channelId;
	
	@FieldName("1是快递，2是已发货不用再发")
	private Integer expressType;
	
	@FieldName("储值账号支付")
	private Double storePay;
	
	@FieldName("银行卡刷卡支付")
	private Double bankCardPay;
	
	@FieldName("现金支付")
	private Double cashPay;
	
	@FieldName("是否是定金支付")
	private Integer isBook;
	
	@FieldName("应该支付")
	private Double payment;
	
	@FieldName("有效业绩")
	private Double effectiveEarn;
	
	@FieldName("实际支付")
	private Double realPayment;
	
	@FieldName("欠款")
	private Double owe;
	
	@FieldName("购买时的欠款，即下单时候的欠款，不随着还款发生改变")
	private Double buyOwe;
	
	@FieldName("是否有支付信息")
	private Integer isPay;

	@FieldName("套餐消费ID")
	private String consumptionSetId;
	
	@FieldName("套餐消费ID")
//	实际数量,实际数量,取产品后剩余的数量
	private Integer leftNumber;
//	实际单位,取产品后剩余的数量的单位
	private String leftUnit;
	
//	优惠码
	private String coupon;
	
	
//	是否是老会员推荐赠送,1是，0不是
//	private Integer isIntroduce;
//	
////	介绍人卡号
//	private String introducer;


	
	/**
	 * 产品体验价
	 */
	private Double experiencePrice;
	/**
	 * 产品市场价
	 */
	private Double marketPrice;
	/**
	 * 产品推广价
	 */
	private Double promotionPrice;

	
//	如果使用了优惠活动用以区分是否是同一个购买的数据
	private String buyType;

	private Integer isFail;
	
//	以最小单位计算，产品的实际剩余数量
	private Integer actualNumber;
//	以最小单位计算，产品的实际价格
	private Double actualPrice;
//	产品的实际最小单位
	private String actualUnit;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsumptionProductId() {
		return consumptionProductId;
	}

	public void setConsumptionProductId(String consumptionProductId) {
		this.consumptionProductId = consumptionProductId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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


	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public String getConsumptionSetId() {
		return consumptionSetId;
	}

	public void setConsumptionSetId(String consumptionSetId) {
		this.consumptionSetId = consumptionSetId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
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

	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	public Integer getLeftNumber() {
		return leftNumber;
	}

	public void setLeftNumber(Integer leftNumber) {
		this.leftNumber = leftNumber;
	}

	public String getLeftUnit() {
		return leftUnit;
	}

	public void setLeftUnit(String leftUnit) {
		this.leftUnit = leftUnit;
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

	public Integer getExpressType() {
		return expressType;
	}

	public void setExpressType(Integer expressType) {
		this.expressType = expressType;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

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
	
	public Integer getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
	}

	public Double getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(Double actualPrice) {
		this.actualPrice = actualPrice;
	}

	public String getActualUnit() {
		return actualUnit;
	}

	public void setActualUnit(String actualUnit) {
		this.actualUnit = actualUnit;
	}

}
