package com.zive.dataOut.entity;

import java.util.Date;

public class ActivityShop {

	/**
	 * id
	 */
	private String id;

	/**
	 * 门店id
	 */
	private String shopId;

	/**
	 * 活动id
	 */
	private String activityId;

	/**
	 * create_date_time
	 */
	private Date createDateTime;

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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
}
