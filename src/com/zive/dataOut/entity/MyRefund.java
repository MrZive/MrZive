package com.zive.dataOut.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;


@TableName("退款单与换购单")
public class MyRefund {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
    private String id;
	
	@FieldName("退款门店ID")
    private String shopId;

	@FieldName("会员ID")
    private String memberCardId;
	
	@FieldName("1是退货，2是换")
    private Integer type;
	
	@FieldName("产品退款金额")
    private Double returnProductMoney;

	@FieldName("项目退款金额")
    private Double returnProjectMoney;

	@FieldName("退款金额")
    private Double totalMoney;
	
	@FieldName("换货时的补差额， 退货时的退款金额")
    private Double returnTransferMoney;
	
//	@FieldName("未知--项目退款金额")
//    private Double returnMoneyProject;

//	@FieldName("未知--产品退款金额")
//    private Double returnMoneyProduct;

//	@FieldName("储值退款金额")
//    private Double returnMoneyStore;

	@FieldName("退换时间")
    private String returnTransferDate;
	
	@FieldName("顾客提出退款时间")
    private String returnProposeDate;

	@FieldName("退货交款时间")
    private String returnPayDate;

//	@FieldName("退货交款金额")
//    private Double returnPayMoney;

	@FieldName("录单人ID")
    private String makerId;

//	@FieldName("剩余金额退回储值账户")
//    private Double returnStoreBalance;

	@FieldName("时任店长ID")
    private String shopChiefId;

	@FieldName("时任顾问ID")
    private String shoperId;

	@FieldName("退换项目原因")
    private String reason;

	@FieldName("是否扣0.1%刷卡手续费，1是0不是")
    private Integer returnIsServicePay;

	@FieldName("退货退款银行")
    private String returnBank;

	@FieldName("退货退款开户行")
    private String returnBankOpen;

	@FieldName("退货退款卡号")
    private String returnBankCardNumber;

	@FieldName("退货开户人姓名")
    private String returnBankCardUser;

	@FieldName("退货电话号码")
    private String returnBankPhone;

//	@FieldName("回访备注")
//    private String feedbackVisit;

	@FieldName("附件路径")
    private String picturePath;

	@FieldName("0退到储值，1退到银行卡")
    private Integer isReurnBank;

	@FieldName("审核状态，0审核中，1已通过，2已拒绝")
    private Integer checkStatus;
	
	@FieldName("审核通过日期")
    private String checkDate;
	
	@FieldName("创建人ID")
    private String createUserId;
	
	@FieldName("创建时间")
    private String createDateTime;
//-----------------------------------------------------------------------------换购单相关字段----------------------------------------------------------------------------
	@FieldName("从这里开始右边为换购单相关字段")
	private String content;

	@FieldName("换货消费类型，1项目2产品3合作项目4为同时换购项目和产品")
    private Integer transferType;
//	
//	@FieldName("原购买价格(非必须)")
//    private Double oldPrice;
//
	@FieldName("申请换项目产品/价格")
    private Double transferPrice;

	@FieldName("补差价产品储值")
    private Double storePayProduct;//产品储值补差价+项目储值补差价=储值补差价

	@FieldName("补差价项目储值")
    private Double storePayProject;//当时购买项目时支付的储值金额

	@FieldName("补差价现金")
    private Double finalCash;

	@FieldName("补差价银行卡")
    private Double finalBankcard;
//	
//	@FieldName("换购支付方式--产品现金支付")
//    private Double cashPayProduct;//当时购买产品时支付的现金金额
//
//	@FieldName("换购支付方式--产品银行卡支付")
//    private Double bankcardPayProduct;//当时购买产品时支付的银行卡金额
//
//	@FieldName("换购支付方式--项目现金支付")
//    private Double cashPayProject;//当时购买产品时支付的现金金额
//	
//	@FieldName("换购支付方式--项目银行卡支付")
//    private Double bankcardPayProject;//当时购买项目时支付的银行卡金额
//
//	@FieldName("换购时购买项目使用的退款金额")
//    private Double transferProjectMoney;
//
//	@FieldName("换购时购买产品使用的退款金额")
//    private Double transferProductMoney;
	
	
	public void fromSuper(Refund refund){
		this.checkStatus = refund.getCheckStatus();
		this.createUserId = refund.getCreateUserId();
//		this.feedbackVisit = refund.getFeedbackVisit();
		this.id = refund.getId();
		this.isReurnBank = refund.getIsReurnBank();
		this.makerId = refund.getMakerId();
		this.memberCardId = refund.getMemberCardId();
		this.picturePath = refund.getPicturePath();
		this.reason = refund.getReason();
		this.returnBank = refund.getReturnBank();
		this.returnBankCardNumber = refund.getReturnBankCardNumber();
		this.returnBankCardUser = refund.getReturnBankCardUser();
		this.returnBankOpen = refund.getReturnBankOpen();
		this.returnBankPhone = refund.getReturnBankPhone();
		this.returnIsServicePay = refund.getReturnIsServicePay();
//		this.returnMoneyProduct = refund.getReturnMoneyProduct();
//		this.returnMoneyProject = refund.getReturnMoneyProject();
//		this.returnMoneyStore = refund.getReturnMoneyStore();
//		this.returnPayMoney = refund.getReturnPayMoney();
		this.returnProductMoney = refund.getReturnProductMoney();
		this.returnProjectMoney = refund.getReturnProjectMoney();
//		this.returnStoreBalance = refund.getReturnStoreBalance();
		this.returnTransferMoney = refund.getReturnTransferMoney();
		this.type = refund.getType();
		this.shopId = refund.getShopId();
		this.totalMoney = refund.getTotalMoney();
		this.shoperId = refund.getShoperId();
		this.shopChiefId = refund.getShopChiefId();
		
		
		//换货
		this.transferType = refund.getTransferType();
		this.transferPrice = refund.getTransferPrice();
//		this.oldPrice = refund.getOldPrice();
	    this.storePayProject = refund.getStorePayProject();
	    this.storePayProduct = refund.getStorePayProduct();
//	    this.bankcardPayProduct = refund.getBankcardPayProduct();
//	    this.bankcardPayProject = refund.getBankcardPayProject();
//	    this.cashPayProduct = refund.getCashPayProduct();
//	    this.cashPayProject = refund.getCashPayProject();
	    this.finalBankcard = refund.getFinalBankcard();
		this.finalCash = refund.getFinalCash();
		
		
		if(refund.getCheckDate()!=null){
			this.checkDate = sf.format(refund.getCheckDate());
		}
		if(refund.getReturnPayDate()!=null){
			this.returnPayDate = sf.format(refund.getReturnPayDate());
		}
		if(refund.getReturnProposeDate()!=null){
			this.returnProposeDate = sf.format(refund.getReturnProposeDate());
		}
		if(refund.getReturnTransferDate()!=null){
			this.returnTransferDate = sf.format(refund.getReturnTransferDate());
		}
		if(refund.getCreateDateTime()!=null){
			this.createDateTime = sf.format(refund.getCreateDateTime());
		}
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getReturnTransferDate() {
		return returnTransferDate;
	}

	public void setReturnTransferDate(String returnTransferDate) {
		this.returnTransferDate = returnTransferDate;
	}

	public String getReturnProposeDate() {
		return returnProposeDate;
	}

	public void setReturnProposeDate(String returnProposeDate) {
		this.returnProposeDate = returnProposeDate;
	}

	public String getReturnPayDate() {
		return returnPayDate;
	}

	public void setReturnPayDate(String returnPayDate) {
		this.returnPayDate = returnPayDate;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public Double getReturnTransferMoney() {
		return returnTransferMoney;
	}

	public void setReturnTransferMoney(Double returnTransferMoney) {
		this.returnTransferMoney = returnTransferMoney;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
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

	public String getPicturePath() {
		return picturePath;
	}

	public void setPicturePath(String picturePath) {
		this.picturePath = picturePath;
	}

	public Integer getIsReurnBank() {
		return isReurnBank;
	}

	public void setIsReurnBank(Integer isReurnBank) {
		this.isReurnBank = isReurnBank;
	}

	public Integer getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public SimpleDateFormat getSf() {
		return sf;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public Integer getTransferType() {
		return transferType;
	}


	public void setTransferType(Integer transferType) {
		this.transferType = transferType;
	}


	public Double getTransferPrice() {
		return transferPrice;
	}


	public void setTransferPrice(Double transferPrice) {
		this.transferPrice = transferPrice;
	}


	public Double getStorePayProduct() {
		return storePayProduct;
	}


	public void setStorePayProduct(Double storePayProduct) {
		this.storePayProduct = storePayProduct;
	}


	public Double getStorePayProject() {
		return storePayProject;
	}


	public void setStorePayProject(Double storePayProject) {
		this.storePayProject = storePayProject;
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
}
