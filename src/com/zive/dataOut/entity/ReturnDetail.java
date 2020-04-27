package com.zive.dataOut.entity;

import java.util.Date;

public class ReturnDetail {

	/**
    * id
    */
    private String id;

    /**
     * detail_id
     */
     private String detailId;
    
    /**
    * 对应欠款项目或者产品id
    */
    private String consumptionProId;
    
    /**
    * 当前还款信息流水号
    */
    private String returnConsumptionId;
    
    /**
    * 还款单类型：cooperation_project,product,project,set
    */
    private String type;

    /**
    * 收款门店类型，0是消费门店，1是星和
    */
    private Integer receiptShopType;

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
    * 有效业绩
    */
    private Double effectiveEarn;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * create_user_id
    */
    private String createUserId;

    /**
    * shop_id
    */
    private String shopId;

    /**
    * 是否作废，1是0不是
    */
    private Integer isFail;

    /**
    * member_card_id
    */
    private String memberCardId;

    /**
    * return_date
    */
    private Date returnDate;

    /**
    * 星和合作项目购买及还款的咨询人
    */
    private String adviser;

    /**
    * 医院美容师
    */
    private String cosmetologist;

    /**
    * cosmetologist2
    */
    private String cosmetologist2;

    /**
    * cosmetologist3
    */
    private String cosmetologist3;
    
    
    
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getConsumptionProId() {
		return consumptionProId;
	}

	public void setConsumptionProId(String consumptionProId) {
		this.consumptionProId = consumptionProId;
	}

	public String getReturnConsumptionId() {
		return returnConsumptionId;
	}

	public void setReturnConsumptionId(String returnConsumptionId) {
		this.returnConsumptionId = returnConsumptionId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getReceiptShopType() {
		return receiptShopType;
	}

	public void setReceiptShopType(Integer receiptShopType) {
		this.receiptShopType = receiptShopType;
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

	public Double getEffectiveEarn() {
		return effectiveEarn;
	}

	public void setEffectiveEarn(Double effectiveEarn) {
		this.effectiveEarn = effectiveEarn;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
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
