package com.zive.dataOut.entity;

import java.sql.Date;

/**
 * 产品消费明细记录
 * 
 * @author hcxue
 *
 */
public class ProjectCooperationConsumption {

	/**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * member_card_id
    */
    private String memberCardId;

    /**
    * 应该支付
    */
    private Double payment;

    /**
    * bank_card_pay
    */
    private Double bankCardPay;

    /**
    * cash_pay
    */
    private Double cashPay;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * owe
    */
    private Double owe;

    /**
    * 状态，0正常，1已完成，2欠款，3退款
    */
    private Integer status;

    /**
    * store_pay
    */
    private Double storePay;

    /**
    * is_book
    */
    private Integer isBook;

    /**
    * effective_earn
    */
    private Double effectiveEarn;

    /**
    * 实际支付
    */
    private Double realPayment;

    /**
    * remark
    */
    private String remark;

    /**
    * is_fail
    */
    private Integer isFail;

    /**
    * 门店id
    */
    private String shopid;

    /**
    * buy_owe
    */
    private Double buyOwe;

    /**
    * buy_type
    */
    private String buyType;

    /**
    * activity_id
    */
    private String activityId;

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

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Double getOwe() {
		return owe;
	}

	public void setOwe(Double owe) {
		this.owe = owe;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getStorePay() {
		return storePay;
	}

	public void setStorePay(Double storePay) {
		this.storePay = storePay;
	}

	public Integer getIsBook() {
		return isBook;
	}

	public void setIsBook(Integer isBook) {
		this.isBook = isBook;
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

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public Double getBuyOwe() {
		return buyOwe;
	}

	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
