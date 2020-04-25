package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;


@TableName("还款单")
public class MyReturn {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
    private String id;
	
	@FieldName("购买单流水号")
    private String consumptionProId;

	@FieldName("购买单流水号中的ID")
    private String detailId;
    
	@FieldName("还款单号")
    private String returnConsumptionId;
    
	@FieldName("还款单类型")
    private String type;//还款单类型：cooperation_project,product,project,set

	@FieldName("收款门店类型：0门店，1星和")
    private Integer receiptShopType;

	@FieldName("会员ID")
    private String memberCardId;
	
	@FieldName("储值支付")
    private Double storePay;

	@FieldName("银行卡")
    private Double bankcardPay;

	@FieldName("现金支付")
    private Double cashPay;
	
	@FieldName("还款门店")
    private String shopId;

	@FieldName("还款时间")
    private String returnDate;
	
	@FieldName("创建人ID")
    private String createUserId;
	
	@FieldName("创建时间")
	private String createDate;
	
	@FieldName("是否成功")
    private Integer isFail;

//	@FieldName("星和合作项目购买及还款的咨询人")
//    private String adviser;
//
//	@FieldName("医院美容师")
//    private String cosmetologist;
//
//	@FieldName("医院美容师2")
//    private String cosmetologist2;
//
//	@FieldName("医院美容师3")
//    private String cosmetologist3;
	
	
	public void fromSuper(ReturnDetail detail) {
		this.bankcardPay = detail.getBankcardPay();
		this.cashPay = detail.getCashPay();
		this.consumptionProId = detail.getConsumptionProId();
		this.createUserId = detail.getCreateUserId();
		this.detailId = detail.getDetailId();
		this.id = detail.getId();
		this.isFail = detail.getIsFail();
		this.memberCardId = detail.getMemberCardId();
		this.receiptShopType = detail.getReceiptShopType();
		this.returnConsumptionId = detail.getReturnConsumptionId();
		this.shopId = detail.getShopId();
		this.storePay = detail.getStorePay();
		this.type = detail.getType();
		
		if(detail.getReturnDate()!=null){
			this.returnDate = sf.format(detail.getReturnDate());
		}
		if(detail.getCreateDate()!=null){
			this.createDate = sf.format(detail.getCreateDate());
		}
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsumptionProId() {
		return consumptionProId;
	}

	public void setConsumptionProId(String consumptionProId) {
		this.consumptionProId = consumptionProId;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
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

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
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

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}
}
