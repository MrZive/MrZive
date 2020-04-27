package com.zive.dataOut.entity;

import java.util.Date;

public class Refund {

	/**
    * id
    */
    private String id;

    /**
    * create_user_id
    */
    private String createUserId;

    /**
    * create_date_time
    */
    private Date createDateTime;

    /**
    * 1是退货，2是换
    */
    private Integer type;

    /**
    * 换货类型，1项目2产品3合作项目
    */
    private Integer transferType;

    /**
    * 会员id
    */
    private String memberCardId;

    /**
    * 退款时，产品退款金额
    */
    private Double returnProductMoney;

    /**
    * 退款时，项目退款金额
    */
    private Double returnProjectMoney;

    /**
    * 退换金额
    */
    private Double totalMoney;

    /**
    * 换货时的更换日期，退货时的顾客提出退款时间
    */
    private Date returnTransferDate;

    /**
    * 操作人id
    */
    private String makerId;

    /**
    * 储值账户支付
    */
    private Double storePayProject;

    /**
    * store_pay_product
    */
    private Double storePayProduct;

    /**
    * bankcard_pay_product
    */
    private Double bankcardPayProduct;

    /**
    * 银行卡支付
    */
    private Double bankcardPayProject;

    /**
    * cash_pay_product
    */
    private Double cashPayProduct;

    /**
    * 现金支付
    */
    private Double cashPayProject;

    /**
    * 换购剩余金额退回储值账户
    */
    private Double returnStoreBalance;

    /**
    * 时任店长
    */
    private String shopChiefId;

    /**
    * 时任顾问
    */
    private String shoperId;

    /**
    * 原购买产品/价格
    */
    private Double oldPrice;

    /**
    * 申请换项目产品/价格
    */
    private Double transferPrice;

    /**
    * 换货时的补差额， 退货时的退款金额
    */
    private Double returnTransferMoney;

    /**
    * 换项目原因
    */
    private String reason;

    /**
    * 顾客提出退款时间
    */
    private Date returnProposeDate;

    /**
    * 退货交款时间
    */
    private Date returnPayDate;

    /**
    * 退货交款金额
    */
    private Double returnPayMoney;

    /**
    * 是否扣0.1%刷卡手续费，1是0不是
    */
    private Integer returnIsServicePay;

    /**
    * 退货退款银行
    */
    private String returnBank;

    /**
    * 退货退款开户行
    */
    private String returnBankOpen;

    /**
    * 退货退款卡号
    */
    private String returnBankCardNumber;

    /**
    * 退货开户人姓名
    */
    private String returnBankCardUser;

    /**
    * 退货电话号码
    */
    private String returnBankPhone;

    /**
    * 回访备注
    */
    private String feedbackVisit;

    /**
    * 图片路径
    */
    private String picturePath;

    /**
    * 审核状态，0审核中，1已通过，2已拒绝
    */
    private Integer checkStatus;

    /**
    * shop_id
    */
    private String shopId;

    /**
    * 审核通过日期
    */
    private Date checkDate;

    /**
    * 换购时购买项目使用的退款金额
    */
    private Double transferProjectMoney;

    /**
    * 换购时购买产品使用的退款金额
    */
    private Double transferProductMoney;

    /**
    * final_cash
    */
    private Double finalCash;

    /**
    * final_bankcard
    */
    private Double finalBankcard;

    /**
    * return_money_project
    */
    private Double returnMoneyProject;

    /**
    * return_money_product
    */
    private Double returnMoneyProduct;

    /**
    * 从储值账户退款
    */
    private Double returnMoneyStore;

    /**
    * 0退到储值，1退到银行卡
    */
    private Integer isReurnBank;
    
    
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getTransferType() {
		return transferType;
	}

	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Double getReturnProductMoney() {
		return returnProductMoney;
	}

	public void setReturnProductMoney(Double returnProductMoney) {
		this.returnProductMoney = returnProductMoney;
	}

	public Double getReturnProjectMoney() {
		return returnProjectMoney;
	}

	public void setReturnProjectMoney(Double returnProjectMoney) {
		this.returnProjectMoney = returnProjectMoney;
	}

	public Double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Date getReturnTransferDate() {
		return returnTransferDate;
	}

	public void setReturnTransferDate(Date returnTransferDate) {
		this.returnTransferDate = returnTransferDate;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public Double getStorePayProject() {
		return storePayProject;
	}

	public void setStorePayProject(Double storePayProject) {
		this.storePayProject = storePayProject;
	}

	public Double getStorePayProduct() {
		return storePayProduct;
	}

	public void setStorePayProduct(Double storePayProduct) {
		this.storePayProduct = storePayProduct;
	}

	public Double getBankcardPayProduct() {
		return bankcardPayProduct;
	}

	public void setBankcardPayProduct(Double bankcardPayProduct) {
		this.bankcardPayProduct = bankcardPayProduct;
	}

	public Double getBankcardPayProject() {
		return bankcardPayProject;
	}

	public void setBankcardPayProject(Double bankcardPayProject) {
		this.bankcardPayProject = bankcardPayProject;
	}

	public Double getCashPayProduct() {
		return cashPayProduct;
	}

	public void setCashPayProduct(Double cashPayProduct) {
		this.cashPayProduct = cashPayProduct;
	}

	public Double getCashPayProject() {
		return cashPayProject;
	}

	public void setCashPayProject(Double cashPayProject) {
		this.cashPayProject = cashPayProject;
	}

	public Double getReturnStoreBalance() {
		return returnStoreBalance;
	}

	public void setReturnStoreBalance(Double returnStoreBalance) {
		this.returnStoreBalance = returnStoreBalance;
	}

	public String getShopChiefId() {
		return shopChiefId;
	}

	public void setShopChiefId(String shopChiefId) {
		this.shopChiefId = shopChiefId;
	}

	public String getShoperId() {
		return shoperId;
	}

	public void setShoperId(String shoperId) {
		this.shoperId = shoperId;
	}

	public Double getOldPrice() {
		return oldPrice;
	}

	public void setOldPrice(Double oldPrice) {
		this.oldPrice = oldPrice;
	}

	public Double getTransferPrice() {
		return transferPrice;
	}

	public void setTransferPrice(Double transferPrice) {
		this.transferPrice = transferPrice;
	}

	public Double getReturnTransferMoney() {
		return returnTransferMoney;
	}

	public void setReturnTransferMoney(Double returnTransferMoney) {
		this.returnTransferMoney = returnTransferMoney;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getReturnProposeDate() {
		return returnProposeDate;
	}

	public void setReturnProposeDate(Date returnProposeDate) {
		this.returnProposeDate = returnProposeDate;
	}

	public Date getReturnPayDate() {
		return returnPayDate;
	}

	public void setReturnPayDate(Date returnPayDate) {
		this.returnPayDate = returnPayDate;
	}

	public Double getReturnPayMoney() {
		return returnPayMoney;
	}

	public void setReturnPayMoney(Double returnPayMoney) {
		this.returnPayMoney = returnPayMoney;
	}

	public Integer getReturnIsServicePay() {
		return returnIsServicePay;
	}

	public void setReturnIsServicePay(Integer returnIsServicePay) {
		this.returnIsServicePay = returnIsServicePay;
	}

	public String getReturnBank() {
		return returnBank;
	}

	public void setReturnBank(String returnBank) {
		this.returnBank = returnBank;
	}

	public String getReturnBankOpen() {
		return returnBankOpen;
	}

	public void setReturnBankOpen(String returnBankOpen) {
		this.returnBankOpen = returnBankOpen;
	}

	public String getReturnBankCardNumber() {
		return returnBankCardNumber;
	}

	public void setReturnBankCardNumber(String returnBankCardNumber) {
		this.returnBankCardNumber = returnBankCardNumber;
	}

	public String getReturnBankCardUser() {
		return returnBankCardUser;
	}

	public void setReturnBankCardUser(String returnBankCardUser) {
		this.returnBankCardUser = returnBankCardUser;
	}

	public String getReturnBankPhone() {
		return returnBankPhone;
	}

	public void setReturnBankPhone(String returnBankPhone) {
		this.returnBankPhone = returnBankPhone;
	}

	public String getFeedbackVisit() {
		return feedbackVisit;
	}

	public void setFeedbackVisit(String feedbackVisit) {
		this.feedbackVisit = feedbackVisit;
	}

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Date getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public Double getTransferProjectMoney() {
		return transferProjectMoney;
	}

	public void setTransferProjectMoney(Double transferProjectMoney) {
		this.transferProjectMoney = transferProjectMoney;
	}

	public Double getTransferProductMoney() {
		return transferProductMoney;
	}

	public void setTransferProductMoney(Double transferProductMoney) {
		this.transferProductMoney = transferProductMoney;
	}

	public Double getFinalCash() {
		return finalCash;
	}

	public void setFinalCash(Double finalCash) {
		this.finalCash = finalCash;
	}

	public Double getFinalBankcard() {
		return finalBankcard;
	}

	public void setFinalBankcard(Double finalBankcard) {
		this.finalBankcard = finalBankcard;
	}

	public Double getReturnMoneyProject() {
		return returnMoneyProject;
	}

	public void setReturnMoneyProject(Double returnMoneyProject) {
		this.returnMoneyProject = returnMoneyProject;
	}

	public Double getReturnMoneyProduct() {
		return returnMoneyProduct;
	}

	public void setReturnMoneyProduct(Double returnMoneyProduct) {
		this.returnMoneyProduct = returnMoneyProduct;
	}

	public Double getReturnMoneyStore() {
		return returnMoneyStore;
	}

	public void setReturnMoneyStore(Double returnMoneyStore) {
		this.returnMoneyStore = returnMoneyStore;
	}

	public Integer getIsReurnBank() {
		return isReurnBank;
	}

	public void setIsReurnBank(Integer isReurnBank) {
		this.isReurnBank = isReurnBank;
	}
}
