package com.bjsxt.dataOut.entity;

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
	private Double buyPrice;
	
	@FieldName("是否赠送")
	private Integer isSend;
	
	@FieldName("购买时间")
	private String bookDate;
	
	@FieldName("创建人ID")
	private String cereateUserId;
	
	@FieldName("创建时间")
	private String createDate;
	
	@FieldName("备注")
	private String remark; 
	
	@FieldName("是否成功购买")
	private Integer isFail;
	
//	@FieldName("0青花瓷 1线上 2天使之纹 3丘山医美 4其他")
//	private int channelId;
//	
//	@FieldName("1是快递，2是已发货不用再发")
//	private Integer expressType;
//	
//	@FieldName("储值账号支付")
//	private Double storePay;
//	
//	@FieldName("银行卡刷卡支付")
//	private Double bankCardPay;
//	
//	@FieldName("现金支付")
//	private Double cashPay;
//	
//	@FieldName("是否是定金支付")
//	private Integer isBook;
//	
//	@FieldName("应该支付")
//	private Double payment;
//	
//	@FieldName("有效业绩")
//	private Double effectiveEarn;
//	
//	@FieldName("实际支付")
//	private Double realPayment;
//	
//	@FieldName("欠款")
//	private Double owe;
//	
//	@FieldName("购买时的欠款，即下单时候的欠款，不随着还款发生改变")
//	private Double buyOwe;
//	
//	@FieldName("是否有支付信息")
//	private Integer isPay;
//
//	@FieldName("套餐消费ID")
//	private String consumptionSetId;
//	
//	@FieldName("实际数量取产品后剩余的数量")
//	private Integer leftNumber;
//	
//	@FieldName("实际单位,取产品后剩余的数量的单位")
//	private String leftUnit;
//	
//	@FieldName("优惠码")
//	private String coupon;
//	
//	@FieldName("是否是老会员推荐赠送,1是，0不是")
//	private Integer isIntroduce;
//	
//	@FieldName("介绍人卡号")
//	private String introducer;
//	
//	@FieldName("产品体验价")
//	private Double experiencePrice;
//	
//	@FieldName("产品市场价")
//	private Double marketPrice;
//	
//	@FieldName("产品推广价")
//	private Double promotionPrice;
//
//	@FieldName("如果使用了优惠活动用以区分是否是同一个购买的数据")
//	private String buyType;
//	
//	@FieldName("位计算，产品的实际剩余数量")
//	private Integer actualNumber;
//	
//	@FieldName("以最小单位计算，产品的实际价格")
//	private Double actualPrice;
//	
//	@FieldName("产品的实际最小单位")
//	private String actualUnit;
	
	
	
	public void fromSuper(Consumption consumption, ProductConsumption productConsumption, ProductDetailConsumption productDetailConsumption){
		this.activityId = productDetailConsumption.getActivityId();
		
		this.buyNumber = productDetailConsumption.getBuyNumber();
		this.buyUnit = productDetailConsumption.getBuyUnit();
		this.cereateUserId = consumption.getCreateUserId();
		this.consumptionId = productConsumption.getConsumptionId();
		this.consumptionProductId = productDetailConsumption.getConsumptionProductId();
		this.id = productDetailConsumption.getId();
		this.isFail = productDetailConsumption.getIsFail();
		this.isSend = productDetailConsumption.getIsSend();
		this.memberCardId = productDetailConsumption.getMemberCardId();
		this.productId = productDetailConsumption.getProductId();
		this.buyPrice = productDetailConsumption.getPrice();
		this.shopId = consumption.getShopId();
		
		if(consumption.getConsumptionDate()!=null){
			this.bookDate = sf.format(consumption.getConsumptionDate());
		}
		if(productDetailConsumption.getCreateDate()!=null){
			this.createDate = sf.format(productDetailConsumption.getCreateDate());
		}
		
		if(productDetailConsumption.getRemark()!=null && productDetailConsumption.getRemark().length()>0){
			this.remark = productDetailConsumption.getRemark();
		}else if(productConsumption.getRemark()!=null && productConsumption.getRemark().length()>0){
			this.remark = productConsumption.getRemark();
		}
	}
	
	

	public SimpleDateFormat getSf() {
		return sf;
	}

	public void setSf(SimpleDateFormat sf) {
		this.sf = sf;
	}

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

	public String getConsumptionProductId() {
		return consumptionProductId;
	}

	public void setConsumptionProductId(String consumptionProductId) {
		this.consumptionProductId = consumptionProductId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
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

	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getCereateUserId() {
		return cereateUserId;
	}

	public void setCereateUserId(String cereateUserId) {
		this.cereateUserId = cereateUserId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

}
