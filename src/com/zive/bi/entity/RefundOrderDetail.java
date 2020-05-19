package com.zive.bi.entity;

import java.util.Date;

/**
 * 退款单据
 * @author Lsenrong
 * @date Oct 31, 2017 4:36:54 PM
 * @Description: TODO(描述)
 */
public class RefundOrderDetail {
	/**
	 * 退款单id
	 */
    private String transferId;
    /**
     * 退款单创建日期
     */
    private Date createDate;
    /**
     * 退款日期
     */
    private Date transferDate;
    /**
     * 退项目产品价值金额
     */
    private Double totalMoney;
    /**
     * 退款金额
     */
    private Double returnTransferMoney;
    /**
     * 退至储值
     */
    private Double returnStoreBalance;
    /**
     * 推掉储值
     */
    private Double returnMoneyStore;
    /**
     *    退货交款金额
     */
    private Double returnPayMoney;
 
    /**
     * 刷卡手续费
     */
    private Double returnIsServicePay;
    /**
     * 退款银行
     */
    private String returnBank;
    /**
     * 退款开户行
     */
    private String returnBankOpen;
    /**
     * 退款卡号
     */
    private String returnBankCardNumber;
    /**
     * 退款开户人姓名
     */
    private String returnBankCardUser;
    /**
     * 退款手机号码
     */
    private String returnBankPhone;
    /**
     * 审核状态，0审核中，1已通过，2已拒绝
     */
    private Integer checkStatus;
    /**
     * 审核日期
     */
    private Date checkDate;
    /**
     * 退款备注
     */
    private String remark;
    /**
     * 退款门店
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 退数量
     */
    private Integer tranferNumber;
    /**
     * 退单位
     */
    private String tranferUnit;
    /**
     * 剩余金额
     */
    private Double leftMoney;
    /**
     * 退款金额
     */
    private Double returnMoney;
    /**
     * 购买业务单号		
     */
    private String buyBusinessId;
    /**
     * 购买单号
     */
    private String buyOrderId;
    /**
     * 会员卡号
     */
    private String cardNo;
    /**
     * 会员姓名
     */
    private String memberName;
    /**
     * 是否有效会员
     */
    private Integer isPass;
    /**
     * 成为有效会员时间
     */
    private Date passTime;
    /**
     * 项id
     */
    private String proId;
    /**
     * 项编号
     */
    private String proNo;
    /**
     * 项名称
     */
    private String proName;
    /**
     * 项类型
     */
    private String proType;
    private Double percent;
    
    /**
     * 养生顾问业绩及业绩门店名称
     */
     private Double earn1;
     private String consumpShop1;
     private Double earn2;
     private String consumpShop2;
     private Double earn3;
     private String consumpShop3;
     private Double earn4;
     private String consumpShop4;
     private Double earn5;
     private String consumpShop5;
     private Double earn6;
     private String consumpShop6;
     private Double earn7;
     private String consumpShop7;
     private Double earn8;
     private String consumpShop8;
     /**
      * 0退到储值，1退到银行卡
      */
     private int isReurnBank;
    
	/**
	 * @return the earn1
	 */
	public Double getEarn1() {
		return earn1;
	}
	/**
	 * @param earn1 the earn1 to set
	 */
	public void setEarn1(Double earn1) {
		this.earn1 = earn1;
	}
	/**
	 * @return the consumpShop1
	 */
	public String getConsumpShop1() {
		return consumpShop1;
	}
	/**
	 * @param consumpShop1 the consumpShop1 to set
	 */
	public void setConsumpShop1(String consumpShop1) {
		this.consumpShop1 = consumpShop1;
	}
	/**
	 * @return the earn2
	 */
	public Double getEarn2() {
		return earn2;
	}
	/**
	 * @param earn2 the earn2 to set
	 */
	public void setEarn2(Double earn2) {
		this.earn2 = earn2;
	}
	/**
	 * @return the consumpShop2
	 */
	public String getConsumpShop2() {
		return consumpShop2;
	}
	/**
	 * @param consumpShop2 the consumpShop2 to set
	 */
	public void setConsumpShop2(String consumpShop2) {
		this.consumpShop2 = consumpShop2;
	}
	/**
	 * @return the earn3
	 */
	public Double getEarn3() {
		return earn3;
	}
	/**
	 * @param earn3 the earn3 to set
	 */
	public void setEarn3(Double earn3) {
		this.earn3 = earn3;
	}
	/**
	 * @return the consumpShop3
	 */
	public String getConsumpShop3() {
		return consumpShop3;
	}
	/**
	 * @param consumpShop3 the consumpShop3 to set
	 */
	public void setConsumpShop3(String consumpShop3) {
		this.consumpShop3 = consumpShop3;
	}
	/**
	 * @return the earn4
	 */
	public Double getEarn4() {
		return earn4;
	}
	/**
	 * @param earn4 the earn4 to set
	 */
	public void setEarn4(Double earn4) {
		this.earn4 = earn4;
	}
	/**
	 * @return the consumpShop4
	 */
	public String getConsumpShop4() {
		return consumpShop4;
	}
	/**
	 * @param consumpShop4 the consumpShop4 to set
	 */
	public void setConsumpShop4(String consumpShop4) {
		this.consumpShop4 = consumpShop4;
	}
	/**
	 * @return the earn5
	 */
	public Double getEarn5() {
		return earn5;
	}
	/**
	 * @param earn5 the earn5 to set
	 */
	public void setEarn5(Double earn5) {
		this.earn5 = earn5;
	}
	/**
	 * @return the consumpShop5
	 */
	public String getConsumpShop5() {
		return consumpShop5;
	}
	/**
	 * @param consumpShop5 the consumpShop5 to set
	 */
	public void setConsumpShop5(String consumpShop5) {
		this.consumpShop5 = consumpShop5;
	}
	/**
	 * @return the earn6
	 */
	public Double getEarn6() {
		return earn6;
	}
	/**
	 * @param earn6 the earn6 to set
	 */
	public void setEarn6(Double earn6) {
		this.earn6 = earn6;
	}
	/**
	 * @return the consumpShop6
	 */
	public String getConsumpShop6() {
		return consumpShop6;
	}
	/**
	 * @param consumpShop6 the consumpShop6 to set
	 */
	public void setConsumpShop6(String consumpShop6) {
		this.consumpShop6 = consumpShop6;
	}
	/**
	 * @return the earn7
	 */
	public Double getEarn7() {
		return earn7;
	}
	/**
	 * @param earn7 the earn7 to set
	 */
	public void setEarn7(Double earn7) {
		this.earn7 = earn7;
	}
	/**
	 * @return the consumpShop7
	 */
	public String getConsumpShop7() {
		return consumpShop7;
	}
	/**
	 * @param consumpShop7 the consumpShop7 to set
	 */
	public void setConsumpShop7(String consumpShop7) {
		this.consumpShop7 = consumpShop7;
	}
	/**
	 * @return the earn8
	 */
	public Double getEarn8() {
		return earn8;
	}
	/**
	 * @param earn8 the earn8 to set
	 */
	public void setEarn8(Double earn8) {
		this.earn8 = earn8;
	}
	/**
	 * @return the consumpShop8
	 */
	public String getConsumpShop8() {
		return consumpShop8;
	}
	/**
	 * @param consumpShop8 the consumpShop8 to set
	 */
	public void setConsumpShop8(String consumpShop8) {
		this.consumpShop8 = consumpShop8;
	}
	/**
	 * @return the percent
	 */
	public Double getPercent() {
		return percent;
	}
	/**
	 * @param percent the percent to set
	 */
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public Double getReturnPayMoney() {
		return returnPayMoney;
	}
	public void setReturnPayMoney(Double returnPayMoney) {
		this.returnPayMoney = returnPayMoney;
	}
	public Double getReturnIsServicePay() {
		return returnIsServicePay;
	}
	public void setReturnIsServicePay(Double returnIsServicePay) {
		this.returnIsServicePay = returnIsServicePay;
	}
	public String getReturnBank() {
		return returnBank;
	}
	public void setReturnBank(String returnBank) {
		this.returnBank = returnBank;
	}
	public String getTranferUnit() {
		return tranferUnit;
	}
	public void setTranferUnit(String tranferUnit) {
		this.tranferUnit = tranferUnit;
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
	public Integer getCheckStatus() {
		return checkStatus;
	}
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Integer getTranferNumber() {
		return tranferNumber;
	}
	public void setTranferNumber(Integer tranferNumber) {
		this.tranferNumber = tranferNumber;
	}
	public Double getLeftMoney() {
		return leftMoney;
	}
	public void setLeftMoney(Double leftMoney) {
		this.leftMoney = leftMoney;
	}
	public Double getReturnMoney() {
		return returnMoney;
	}
	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}
	public String getBuyBusinessId() {
		return buyBusinessId;
	}
	public void setBuyBusinessId(String buyBusinessId) {
		this.buyBusinessId = buyBusinessId;
	}
	public String getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(String buyOrderId) {
		this.buyOrderId = buyOrderId;
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
	public String getProId() {
		return proId;
	}
	public void setProId(String proId) {
		this.proId = proId;
	}
	public String getProNo() {
		return proNo;
	}
	public void setProNo(String proNo) {
		this.proNo = proNo;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProType() {
		return proType;
	}
	public void setProType(String proType) {
		this.proType = proType;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Date getCheckDate() {
		return checkDate;
	}
	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}
	public Double getReturnTransferMoney() {
		return returnTransferMoney;
	}
	public void setReturnTransferMoney(Double returnTransferMoney) {
		this.returnTransferMoney = returnTransferMoney;
	}
	public Double getReturnStoreBalance() {
		return returnStoreBalance;
	}
	public void setReturnStoreBalance(Double returnStoreBalance) {
		this.returnStoreBalance = returnStoreBalance;
	}
	public Double getReturnMoneyStore() {
		return returnMoneyStore;
	}
	public void setReturnMoneyStore(Double returnMoneyStore) {
		this.returnMoneyStore = returnMoneyStore;
	}
	public int getIsReurnBank() {
		return isReurnBank;
	}
	public void setIsReurnBank(int isReurnBank) {
		this.isReurnBank = isReurnBank;
	}
    
}
