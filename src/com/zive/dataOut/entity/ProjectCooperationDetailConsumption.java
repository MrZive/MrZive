package com.zive.dataOut.entity;

import java.sql.Date;

/**
 * 产品消费明细记录
 * 
 * @author hcxue
 *
 */
public class ProjectCooperationDetailConsumption {

	/**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * consumption_cooperation_id
    */
    private String consumptionCooperationId;

    /**
    * buy_number
    */
    private Integer buyNumber;

    /**
    * unit
    */
    private String unit;

    /**
    * 项目分类
    */
    private Integer sort;

    /**
    * left_number
    */
    private Integer leftNumber;

    /**
    * 没有实际用处，不用理会该字段
    */
    private Integer status;

    /**
    * activity_id
    */
    private String activityId;

    /**
    * 合作项目id
    */
    private String cooperationId;

    /**
    * member_card_id
    */
    private String memberCardId;

    /**
    * is_fail
    */
    private Integer isFail;

    /**
    * 门店id
    */
    private String shopid;

    /**
    * 优惠券
    */
    private String coupon;

    /**
    * consumption_date
    */
    private Date consumptionDate;

    /**
    * price
    */
    private Double price;

    /**
    * 非纹绣项目业绩九一分成，剩下的一成业绩要用于做服务
    */
    private Double leftEarn;

    /**
    * 储值业绩
    */
    private Double leftStoreEarn;

    /**
    * 0青花资 1线上 2天使之纹 3丘山医美 4其他
    */
    private Integer channelId;

    /**
    * consumption_set_id
    */
    private String consumptionSetId;

    /**
    * buy_type
    */
    private String buyType;
    
    
    

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

	public String getConsumptionCooperationId() {
		return consumptionCooperationId;
	}

	public void setConsumptionCooperationId(String consumptionCooperationId) {
		this.consumptionCooperationId = consumptionCooperationId;
	}

	public Integer getBuyNumber() {
		return buyNumber;
	}

	public void setBuyNumber(Integer buyNumber) {
		this.buyNumber = buyNumber;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getLeftNumber() {
		return leftNumber;
	}

	public void setLeftNumber(Integer leftNumber) {
		this.leftNumber = leftNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getCooperationId() {
		return cooperationId;
	}

	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
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

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getLeftEarn() {
		return leftEarn;
	}

	public void setLeftEarn(Double leftEarn) {
		this.leftEarn = leftEarn;
	}

	public Double getLeftStoreEarn() {
		return leftStoreEarn;
	}

	public void setLeftStoreEarn(Double leftStoreEarn) {
		this.leftStoreEarn = leftStoreEarn;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getConsumptionSetId() {
		return consumptionSetId;
	}

	public void setConsumptionSetId(String consumptionSetId) {
		this.consumptionSetId = consumptionSetId;
	}

	public String getBuyType() {
		return buyType;
	}

	public void setBuyType(String buyType) {
		this.buyType = buyType;
	}
    
}
