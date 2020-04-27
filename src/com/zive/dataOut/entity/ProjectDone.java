package com.zive.dataOut.entity;

import java.util.Date;

public class ProjectDone {

	/**
	* id
	*/
	private String id;
	
	/**
	* consumption_id
	*/
	private String consumptionId;
	
	/**
	* is_fail
	*/
	private Integer isFail;
	
	/**
	* create_date
	*/
	private Date createDate;
	
	/**
	* member_card_id
	*/
	private String memberCardId;
	
	

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

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}
	
}
