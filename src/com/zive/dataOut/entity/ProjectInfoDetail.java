package com.zive.dataOut.entity;

import java.util.Date;

public class ProjectInfoDetail {

	/**
	 * id
	 */
	private Integer id;

	/**
	 * 门店id
	 */
	private String shopId;

	/**
	 * 项目id
	 */
	private String projectId;

	/**
	 * 服务时长
	 */
	private Double serviceTime;

	/**
	 * 推广单价价
	 */
	private Double promotionPrice;

	/**
	 * 市场单价
	 */
	private Double marketPrice;

	/**
	 * 体验单价
	 */
	private Double experiencePrice;

	/**
	 * create_date_time
	 */
	private Date createDateTime;

	/**
	 * create_user_id
	 */
	private String createUserId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getExperiencePrice() {
		return experiencePrice;
	}

	public void setExperiencePrice(Double experiencePrice) {
		this.experiencePrice = experiencePrice;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	
	
}
