package com.zive.dataOut.entity;

/**
 * 项目消费记录
 * @author hcxue
 *
 */
public class ProductConsumption {

	private String id;
	
	private Integer isDetailPay;
	
//	消费ID
	private String consumptionId;
	
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
//	
////	是否付款完全
//	private Integer isFinish;
	
//	备注
	private String remark; 
	
//  实际支付
	private Double realPayment;
	
//	欠款
	private Double owe;
//	购买时的欠款，即下单时候的欠款，不随着还款发生改变
	private Double buyOwe;
	
	private String memberCardId;
	
	private String shopId;
	
	private String createDate;
	
	private Integer isFail;

	/**
	 * @return the createDate
	 */
	public String getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
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



	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Double getBuyOwe() {
		return buyOwe;
	}

	public void setBuyOwe(Double buyOwe) {
		this.buyOwe = buyOwe;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public Integer getIsDetailPay() {
		return isDetailPay;
	}

	public void setIsDetailPay(Integer isDetailPay) {
		this.isDetailPay = isDetailPay;
	}

	
	
	
}
