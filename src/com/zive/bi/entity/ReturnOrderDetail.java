package com.zive.bi.entity;

import java.util.Date;
import java.util.List;

/**
 * 还款单
 * @author Lsenrong
 * @date Oct 31, 2017 2:24:26 PM
 * @Description: TODO(描述)
 */
public class ReturnOrderDetail {
	/**
	 * 还款单号
	 */
	private String returnOrderId;
	
	private String id;
	private String detailId;
	
	private List<NewEarn> earnList;
	/**
	 * 收款门店类型，0是消费门店，1是星和
	 */
	private int receiptShopType;
	/**
	 * 咨询人
	 */
	private String adviser;
	/**
	 * 光电美容师
	 */
	private String cosmetologist;
	private String cosmetologist2;
	private String cosmetologist3;
	
	/**
	 * 储值
	 */
    private Double store;
    /**
     * 刷卡
     */
    private Double pos;
    /**
     * 现金
     */
    private Double cash;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 是否作废
     */
    private Integer isFail;
    /**
     * 还款单类型
     */
    private String type;
    /**
     * 还款单创建日期
     */
    private Date createDate;
    private Date returnDate;
    /**
     * 购买单
     */
    private String buyOrderId;
    /**
     * 业务id
     */
    private String businessId;
    /**
     * 购买单状态
     */
    private Integer status;
    /**
     * 会员卡号
     */
    private String cardNo;
    private String cardShop;
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
     *  购买项编号
     */
    private String itemId;
    /**
     *  购买项编号
     */
    private String itemNo;
    /**
     *  购买项名称
     */
    private String itemName;
    /**
     * 项类型
     */
    private String itemType;
    /**
     * 购买数量
     */
    private Double itemNumber;
    private Double percent;
    
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
	public Double getStore() {
		return store;
	}
	public void setStore(Double store) {
		this.store = store;
	}
	public Double getPos() {
		return pos;
	}
	public void setPos(Double pos) {
		this.pos = pos;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
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
	public Integer getIsFail() {
		return isFail;
	}
	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getBuyOrderId() {
		return buyOrderId;
	}
	public void setBuyOrderId(String buyOrderId) {
		this.buyOrderId = buyOrderId;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
	public String getReturnOrderId() {
		return returnOrderId;
	}
	public void setReturnOrderId(String returnOrderId) {
		this.returnOrderId = returnOrderId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemNo() {
		return itemNo;
	}
	public void setItemNo(String itemNo) {
		this.itemNo = itemNo;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public Double getItemNumber() {
		return itemNumber;
	}
	public void setItemNumber(Double itemNumber) {
		this.itemNumber = itemNumber;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public List<NewEarn> getEarnList() {
		return earnList;
	}
	public void setEarnList(List<NewEarn> earnList) {
		this.earnList = earnList;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCardShop() {
		return cardShop;
	}
	public void setCardShop(String cardShop) {
		this.cardShop = cardShop;
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
	public int getReceiptShopType() {
		return receiptShopType;
	}
	public void setReceiptShopType(int receiptShopType) {
		this.receiptShopType = receiptShopType;
	}
	
}
