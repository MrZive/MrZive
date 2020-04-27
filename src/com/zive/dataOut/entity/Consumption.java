package com.zive.dataOut.entity;

import java.util.Date;

/**
 * 消费记录单
 * 
 * @author hcxue
 *
 */
public class Consumption {

	/**
	 * 流水号
	 */
	private String id;

	/**
	 * 会员卡id
	 */
	private String memberCardId;

	/**
	 * 门店id
	 */
	private String shopId;

	/**
	 * 消费日期
	 */
	private Date consumptionDate;

	/**
	 * create_user_id
	 */
	private String createUserId;

	/**
	 * create_date
	 */
	private Date createDate;

	/**
	 * 2是作废单，1是正常
	 */
	private Integer status;

	/**
	 * 是否关联作废单
	 */
	private Integer isLinkFail;

	/**
	 * 关联作废单id
	 */
	private String failId;

	/**
	 * fail_date
	 */
	private Date failDate;

	/**
	 * 如果当前消费单是作废单，是否已经补单，1是，0不是
	 */
	private Integer isOverFail;

	/**
	 * 是否是合作项目，1是，0不是
	 */
	private Integer isCooperation;

	/**
	 * 消费门店所在区域
	 */
	private String region;

	/**
	 * 录单人id
	 */
	private String makerId;

	/**
	 * 录单平台，电脑录入为空，安卓：android，苹果：iphone
	 */
	private String ostype;

	/**
	 * 作废当前单据用户id
	 */
	private String failUserId;

	/**
	 * 0非拓客单，1拓客单
	 */
	private Integer isTuoke;

	/**
	 * 当需要验证码的门店，不发送验证码则作废当前单据业绩，0不作废业绩，1作废业绩
	 */
	private Integer failEarn;

	/**
	 * 是否是新消费单，1是，0不是
	 */
	private Integer isDetailPay;

	/**
	 * 收款门店类型，0是消费门店，1是星和
	 */
	private Integer receiptShopType;
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsLinkFail() {
		return isLinkFail;
	}

	public void setIsLinkFail(Integer isLinkFail) {
		this.isLinkFail = isLinkFail;
	}

	public String getFailId() {
		return failId;
	}

	public void setFailId(String failId) {
		this.failId = failId;
	}

	public Date getFailDate() {
		return failDate;
	}

	public void setFailDate(Date failDate) {
		this.failDate = failDate;
	}

	public Integer getIsOverFail() {
		return isOverFail;
	}

	public void setIsOverFail(Integer isOverFail) {
		this.isOverFail = isOverFail;
	}

	public Integer getIsCooperation() {
		return isCooperation;
	}

	public void setIsCooperation(Integer isCooperation) {
		this.isCooperation = isCooperation;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getMakerId() {
		return makerId;
	}

	public void setMakerId(String makerId) {
		this.makerId = makerId;
	}

	public String getOstype() {
		return ostype;
	}

	public void setOstype(String ostype) {
		this.ostype = ostype;
	}

	public String getFailUserId() {
		return failUserId;
	}

	public void setFailUserId(String failUserId) {
		this.failUserId = failUserId;
	}

	public Integer getIsTuoke() {
		return isTuoke;
	}

	public void setIsTuoke(Integer isTuoke) {
		this.isTuoke = isTuoke;
	}

	public Integer getFailEarn() {
		return failEarn;
	}

	public void setFailEarn(Integer failEarn) {
		this.failEarn = failEarn;
	}

	public Integer getIsDetailPay() {
		return isDetailPay;
	}

	public void setIsDetailPay(Integer isDetailPay) {
		this.isDetailPay = isDetailPay;
	}

	public Integer getReceiptShopType() {
		return receiptShopType;
	}

	public void setReceiptShopType(Integer receiptShopType) {
		this.receiptShopType = receiptShopType;
	}
	
}
