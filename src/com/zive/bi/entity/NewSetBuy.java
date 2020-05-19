package com.zive.bi.entity;

import java.util.Date;
import java.util.List;

public class NewSetBuy {
    private String bussionessId;
    private String orderId;
    private String shopId;
    private String shopName;
    private Integer failEarn;
    private Integer isTuoke;
    private Integer isFail;
    private String osType;
    private String region;
    private Date consumptionDate;
    private Date createDate;
    private String cardNo;
    private String memberName;
    /**
     * 是否有效会员
     */
    private Integer isPass;
    /**
     * 成为有效会员时间
     */
    private Date passTime;
    private Integer isIntroduce;
    private String markerName;
    private Double storePay;
    private Double bankcardPay;
    private Double cashPay;
    private Double buyOwe;
    private Integer isBook;
    private Double payment;
    
    private String activityId;
    private String activityName;
    private String buyTypeSet;
    private String buyTypeItem;
    private Double buySetNumber;
    private Double buyItemNumber;
    private String buyUnit;
    private Double buyPrice;
    private String itemId;
    private String itemNo;
    private String itemName;
    private String type;
    
    private String detailId;
    
    
    private List<EmployeeEarn> employeeEarnList;
    
    
	public String getBussionessId() {
		return bussionessId;
	}
	public void setBussionessId(String bussionessId) {
		this.bussionessId = bussionessId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
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
	public Integer getFailEarn() {
		return failEarn;
	}
	public void setFailEarn(Integer failEarn) {
		this.failEarn = failEarn;
	}
	public Integer getIsTuoke() {
		return isTuoke;
	}
	public void setIsTuoke(Integer isTuoke) {
		this.isTuoke = isTuoke;
	}
	public Integer getIsFail() {
		return isFail;
	}
	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
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
	public String getOsType() {
		return osType;
	}
	public void setOsType(String osType) {
		this.osType = osType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Date getConsumptionDate() {
		return consumptionDate;
	}
	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
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
	public Integer getIsIntroduce() {
		return isIntroduce;
	}
	public void setIsIntroduce(Integer isIntroduce) {
		this.isIntroduce = isIntroduce;
	}
	public String getMarkerName() {
		return markerName;
	}
	public void setMarkerName(String markerName) {
		this.markerName = markerName;
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
	public Double getBuyOwe() {
		return buyOwe;
	}
	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
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
	public String getBuyTypeSet() {
		return buyTypeSet;
	}
	public void setBuyTypeSet(String buyTypeSet) {
		this.buyTypeSet = buyTypeSet;
	}
	public String getBuyTypeItem() {
		return buyTypeItem;
	}
	public void setBuyTypeItem(String buyTypeItem) {
		this.buyTypeItem = buyTypeItem;
	}
	public Double getBuySetNumber() {
		return buySetNumber;
	}
	public void setBuySetNumber(Double buySetNumber) {
		this.buySetNumber = buySetNumber;
	}
	public Double getBuyItemNumber() {
		return buyItemNumber;
	}
	public void setBuyItemNumber(Double buyItemNumber) {
		this.buyItemNumber = buyItemNumber;
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
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<EmployeeEarn> getEmployeeEarnList() {
		return employeeEarnList;
	}
	public void setEmployeeEarnList(List<EmployeeEarn> employeeEarnList) {
		this.employeeEarnList = employeeEarnList;
	}
	
    
}
