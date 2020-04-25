package com.bjsxt.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 用于BI系统导出自定义产品的销售情况
 * @author WangZive
 * @date: 2019年6月12日 下午3:30:26
 */
public class CustomProductSales implements Comparable<CustomProductSales>{

	private String id;
	
	private String orderType;
	
	private String consumptionId;
	
	private String consumptionProductId;
	
	private String returnConsumptionId;
	
	private String refundConsumptionId;
	
	private String activityId;
	
	private String activityName;
	
	private Integer activityType;
	
	private Integer activityStatus;
	
	private Integer activityActType;
	
	private Integer buyNumber;
	
	private Integer refundNumber;
	
	private String memberCardId;
	
	private Date createDate;
	
	private Integer isSend;
	
	private String buyUnit;
	
	private BigDecimal price;
	
	private String shopId;
	
	private String shopName;
	
	private String shopRegion;
	
	private String creator;
	
	private String creatorNo;
	
	private String memberName;
	
	private Integer memberGender;
	
	private String memberPhone;
	 
	private BigDecimal experiencePrice;
	
	private BigDecimal promotoinPrice;
	
	private BigDecimal marketPrice;
	
	private String leftUnit;
	
	private String buyType;
	
	private BigDecimal payment;
	
	private BigDecimal refundMoney;
	
	private BigDecimal storePay;
	
	private BigDecimal bankcardPay;
	
	private BigDecimal cashPay;
	
	private BigDecimal effectiveEarn;
	
	private BigDecimal realPayment;
	
	private Integer isPay;
	
	private Integer actualNumber;
	
	private String actualUnit;
	
	private BigDecimal actualPrice;
	
	private String productNo;
	
	private String productName;
	
	private String bulkUnit;
	
	private String boxesUnit;
	
	private Integer type;
	
	private Integer num;
	
	private String standard;
	
	private String ostype;
	
	private Integer isTuoke;
	
	private Integer isCooperation;
	
	private List<CustomProductSalesEmployeeEarn> employeeEarnList;
	
	@Override
	public int compareTo(CustomProductSales sales) {
		String consumptionId = sales.getConsumptionId();
		consumptionId = consumptionId.substring(1, consumptionId.length());
		String oldId = this.consumptionId.substring(1, this.consumptionId.length());
		
		long nId = Long.valueOf(consumptionId);
		long oId = Long.valueOf(oldId);
		
		if(nId>oId){
			return 1;
		}else if(nId<oId){
			return -1;
		}
		return 0;
	}
	
	private static long getASCii(String aa) {
		String a = aa;
		byte[] i = a.getBytes();
		long num = 0l;
		for (byte b : i) {
			num+=b;
		}
		return num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public String getReturnConsumptionId() {
		return returnConsumptionId;
	}

	public void setReturnConsumptionId(String returnConsumptionId) {
		this.returnConsumptionId = returnConsumptionId;
	}

	public String getRefundConsumptionId() {
		return refundConsumptionId;
	}

	public void setRefundConsumptionId(String refundConsumptionId) {
		this.refundConsumptionId = refundConsumptionId;
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

	public Integer getActivityType() {
		return activityType;
	}

	public void setActivityType(Integer activityType) {
		this.activityType = activityType;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Integer getActivityActType() {
		return activityActType;
	}

	public void setActivityActType(Integer activityActType) {
		this.activityActType = activityActType;
	}

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsSend() {
		return isSend;
	}

	public void setIsSend(Integer isSend) {
		this.isSend = isSend;
	}

	public String getBuyUnit() {
		return buyUnit;
	}

	public void setBuyUnit(String buyUnit) {
		this.buyUnit = buyUnit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public BigDecimal getExperiencePrice() {
		return experiencePrice;
	}

	public void setExperiencePrice(BigDecimal experiencePrice) {
		this.experiencePrice = experiencePrice;
	}

	public BigDecimal getPromotoinPrice() {
		return promotoinPrice;
	}

	public void setPromotoinPrice(BigDecimal promotoinPrice) {
		this.promotoinPrice = promotoinPrice;
	}

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
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

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public Integer getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(Integer refundNumber) {
		this.refundNumber = refundNumber;
	}

	public BigDecimal getRefundMoney() {
		return refundMoney;
	}

	public void setRefundMoney(BigDecimal refundMoney) {
		this.refundMoney = refundMoney;
	}

	public BigDecimal getBankcardPay() {
		return bankcardPay;
	}

	public void setBankcardPay(BigDecimal bankcardPay) {
		this.bankcardPay = bankcardPay;
	}

	public BigDecimal getCashPay() {
		return cashPay;
	}

	public void setCashPay(BigDecimal cashPay) {
		this.cashPay = cashPay;
	}

	public BigDecimal getEffectiveEarn() {
		return effectiveEarn;
	}

	public void setEffectiveEarn(BigDecimal effectiveEarn) {
		this.effectiveEarn = effectiveEarn;
	}

	public BigDecimal getRealPayment() {
		return realPayment;
	}

	public void setRealPayment(BigDecimal realPayment) {
		this.realPayment = realPayment;
	}

	public Integer getIsPay() {
		return isPay;
	}

	public void setIsPay(Integer isPay) {
		this.isPay = isPay;
	}

	public Integer getActualNumber() {
		return actualNumber;
	}

	public void setActualNumber(Integer actualNumber) {
		this.actualNumber = actualNumber;
	}

	public String getActualUnit() {
		return actualUnit;
	}

	public void setActualUnit(String actualUnit) {
		this.actualUnit = actualUnit;
	}

	public BigDecimal getActualPrice() {
		return actualPrice;
	}

	public void setActualPrice(BigDecimal actualPrice) {
		this.actualPrice = actualPrice;
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

	public String getBulkUnit() {
		return bulkUnit;
	}

	public void setBulkUnit(String bulkUnit) {
		this.bulkUnit = bulkUnit;
	}

	public String getBoxesUnit() {
		return boxesUnit;
	}

	public void setBoxesUnit(String boxesUnit) {
		this.boxesUnit = boxesUnit;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopRegion() {
		return shopRegion;
	}

	public void setShopRegion(String shopRegion) {
		this.shopRegion = shopRegion;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreatorNo() {
		return creatorNo;
	}

	public void setCreatorNo(String creatorNo) {
		this.creatorNo = creatorNo;
	}

	public String getOstype() {
		return ostype;
	}

	public BigDecimal getStorePay() {
		return storePay;
	}

	public void setStorePay(BigDecimal storePay) {
		this.storePay = storePay;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public Integer getIsTuoke() {
		return isTuoke;
	}

	public void setIsTuoke(Integer isTuoke) {
		this.isTuoke = isTuoke;
	}

	public Integer getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(Integer isCooperation) {
		this.isCooperation = isCooperation;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public Integer getMemberGender() {
		return memberGender;
	}

	public void setMemberGender(Integer memberGender) {
		this.memberGender = memberGender;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public List<CustomProductSalesEmployeeEarn> getEmployeeEarnList() {
		return employeeEarnList;
	}

	public void setEmployeeEarnList(List<CustomProductSalesEmployeeEarn> employeeEarnList) {
		this.employeeEarnList = employeeEarnList;
	}

}
