package com.zive.dataOut.entity;

import java.text.SimpleDateFormat;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;

/**
 * 项目消费明细记录
 */
@TableName("传统项目销售单")
public class MyProjectConsumption {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
	private String id;

	@FieldName("流水单号")
	private String consumptionId;

	@FieldName("销售单号")
	private String consumptionProjectId;

	@FieldName("门店ID")
	private String shopId;
	
	@FieldName("会员卡ID")
	private String memberCardId;

	@FieldName("项目ID")
	private String projectId;
	
	@FieldName("服务时长")
	private Integer serviceTime;
	
	@FieldName("活动ID")
	private String activityId;
	
	@FieldName("优惠码")
	private String coupon;
	
	@FieldName("单价")
	private Double price;

	@FieldName("购买数量")
	private Integer buyNumber;

	@FieldName("剩余数量")
	private Integer number;

	@FieldName("是否是赠送，1是，0不是")
	private Integer isSend;

	@FieldName("是否计次")
	private Integer isCount;
	
	@FieldName("是否失败：1失败0成功")
	private Integer isFail;
	
	@FieldName("创建时间")
	private String createDate;

	@FieldName("结束时间")
	private String endDate;

	@FieldName("如果使用了优惠活动用以区分是否是同一个购买的数据")
	private String buyType;

	@FieldName("0青花资 1线上 2天使之纹 3丘山医美 4其他")
	private Integer channelId;

	@FieldName("总价")
	private Double payment;

	@FieldName("储值支付")
	private Double storePay;

	@FieldName("银行卡支付")
	private Double bankcardPay;

	@FieldName("刷卡支付")
	private Double cashPay;

	@FieldName("积分支付")
	private Double pointPay;

	@FieldName("是否是定金单")
	private Integer isBook;

	@FieldName("欠款")
	private Double owe;

	@FieldName("第一次购买时的欠款")
	private Double buyOwe;

	@FieldName("有效业绩")
	private Double effectiveEarn;

	@FieldName("支付金额")
	private Double realPayment;
	
	//-------------------------
	@FieldName("总价All")
	private Double paymentAll;

	@FieldName("储值支付All")
	private Double storePayAll;

	@FieldName("银行卡支付All")
	private Double bankcardPayAll;

	@FieldName("刷卡支付All")
	private Double cashPayAll;

	@FieldName("积分支付All")
	private Double pointPayAll;

	@FieldName("是否是定金单All")
	private Integer isBookAll;

	@FieldName("欠款All")
	private Double oweAll;

	@FieldName("第一次购买时的欠款All")
	private Double buyOweAll;

	@FieldName("有效业绩All")
	private Double effectiveEarnAll;

	@FieldName("支付金额All")
	private Double realPaymentAll;
	//--------------------------------

	@FieldName("是否有支付信息")
	private Integer isPay;

	@FieldName("是否使用现金券，1是0不是")
	private Integer isCashCoupon;

	@FieldName("服务类型，0可以服务，1不可以服务")
	private Integer serviceType;

	@FieldName("备注")
	private String remark;
	
	
	public void fromSuper(Consumption consumption, ProjectConsumption projectConsumption, ProjectDetailConsumption projectDetailConsumption){
		this.activityId = projectDetailConsumption.getActivityId();
		this.bankcardPay = projectDetailConsumption.getBankcardPay();
		this.buyNumber = projectDetailConsumption.getBuyNumber();
		this.buyOwe = projectDetailConsumption.getBuyOwe();
		this.buyType = projectDetailConsumption.getBuyType();
		this.cashPay = projectDetailConsumption.getCashPay();
		this.channelId = projectDetailConsumption.getChannelId();
		this.consumptionId = projectDetailConsumption.getConsumptionId();
		this.consumptionProjectId = projectDetailConsumption.getConsumptionProjectId();
		this.coupon = projectDetailConsumption.getCoupon();
		this.effectiveEarn = projectDetailConsumption.getEffectiveEarn();
		this.id = projectDetailConsumption.getId();
		this.isBook = projectDetailConsumption.getIsBook();
		this.isCashCoupon = projectDetailConsumption.getIsCashCoupon();
		this.isCount = projectDetailConsumption.getIsCount();
		this.isFail = projectDetailConsumption.getIsFail();
		this.isPay = projectDetailConsumption.getIsPay();
		this.isSend = projectDetailConsumption.getIsSend();
		this.memberCardId = projectDetailConsumption.getMemberCardId();
		this.number = projectDetailConsumption.getNumber();
		this.owe = projectDetailConsumption.getOwe();
		this.payment = projectDetailConsumption.getPayment();
		this.pointPay = projectDetailConsumption.getPointPay();
		this.price = projectDetailConsumption.getPrice();
		this.projectId = projectDetailConsumption.getProjectId();
		this.realPayment = projectDetailConsumption.getRealPayment();
		this.serviceTime = projectDetailConsumption.getServiceTime();
		this.serviceType =  projectDetailConsumption.getServiceType();
		this.shopId = projectDetailConsumption.getShopId();
		this.storePay = projectDetailConsumption.getStorePay();
		
		if(projectDetailConsumption.getCreateDate()!=null){
			this.createDate = sf.format(projectDetailConsumption.getCreateDate());
		}
		if(projectDetailConsumption.getEndDate()!=null){
			this.endDate = sf.format(projectDetailConsumption.getEndDate());
		}
		
		if(projectConsumption.getRemark()!=null && projectConsumption.getRemark().length()>0){
			this.remark = projectConsumption.getRemark();
		}else if(projectDetailConsumption.getRemark()!=null && projectDetailConsumption.getRemark().length()>0){
			this.remark = projectDetailConsumption.getRemark();
		}
		
		
		this.bankcardPayAll = projectConsumption.getBankcardPay();
		this.buyOweAll = projectConsumption.getBuyOwe();
		this.cashPayAll = projectConsumption.getCashPay();
		this.effectiveEarnAll = projectConsumption.getEffectiveEarn();
		this.isBookAll = projectConsumption.getIsBook();
		this.oweAll = projectConsumption.getOwe();
		this.paymentAll = projectConsumption.getPayment();
		this.pointPayAll = projectConsumption.getPointPay();
		this.realPaymentAll = projectConsumption.getRealPayment();
		this.storePayAll = projectConsumption.getStorePay();
	}
	

	public Double getPaymentAll() {
		return paymentAll;
	}


	public void setPaymentAll(Double paymentAll) {
		this.paymentAll = paymentAll;
	}


	public Double getStorePayAll() {
		return storePayAll;
	}


	public void setStorePayAll(Double storePayAll) {
		this.storePayAll = storePayAll;
	}


	public Double getBankcardPayAll() {
		return bankcardPayAll;
	}


	public void setBankcardPayAll(Double bankcardPayAll) {
		this.bankcardPayAll = bankcardPayAll;
	}


	public Double getCashPayAll() {
		return cashPayAll;
	}


	public void setCashPayAll(Double cashPayAll) {
		this.cashPayAll = cashPayAll;
	}


	public Double getPointPayAll() {
		return pointPayAll;
	}


	public void setPointPayAll(Double pointPayAll) {
		this.pointPayAll = pointPayAll;
	}


	public Integer getIsBookAll() {
		return isBookAll;
	}


	public void setIsBookAll(Integer isBookAll) {
		this.isBookAll = isBookAll;
	}


	public Double getOweAll() {
		return oweAll;
	}


	public void setOweAll(Double oweAll) {
		this.oweAll = oweAll;
	}


	public Double getBuyOweAll() {
		return buyOweAll;
	}


	public void setBuyOweAll(Double buyOweAll) {
		this.buyOweAll = buyOweAll;
	}


	public Double getEffectiveEarnAll() {
		return effectiveEarnAll;
	}


	public void setEffectiveEarnAll(Double effectiveEarnAll) {
		this.effectiveEarnAll = effectiveEarnAll;
	}


	public Double getRealPaymentAll() {
		return realPaymentAll;
	}


	public void setRealPaymentAll(Double realPaymentAll) {
		this.realPaymentAll = realPaymentAll;
	}


	public SimpleDateFormat getSf() {
		return sf;
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

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
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

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
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

	public Integer getServiceType() {
		return serviceType;
	}

	public void setServiceType(Integer serviceType) {
		this.serviceType = serviceType;
	}
}
