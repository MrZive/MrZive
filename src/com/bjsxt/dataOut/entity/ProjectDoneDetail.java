package com.bjsxt.dataOut.entity;

import java.util.Date;

public class ProjectDoneDetail {
	 /**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * project_detail_id
    */
    private String projectDetailId;

    /**
    * 做项目的次数
    */
    private Integer doneNumber;

    /**
    * 是否满意，0不满意，1满意
    */
    private Integer isSatisfied;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * 服务时长
    */
    private Integer doneServiceTime;

    /**
    * is_fail
    */
    private Integer isFail;

    /**
    * 项目id
    */
    private String projectId;

    /**
    * order_id
    */
    private String orderId;

    /**
    * membercardid
    */
    private String memberCardId;

    /**
    * select_project_id
    */
    private String selectProjectId;

    /**
    * price
    */
    private Double price;

    /**
    * remark
    */
    private String remark;
    
    

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

	public String getProjectDetailId() {
		return projectDetailId;
	}

	public void setProjectDetailId(String projectDetailId) {
		this.projectDetailId = projectDetailId;
	}

	public Integer getDoneNumber() {
		return doneNumber;
	}

	public void setDoneNumber(Integer doneNumber) {
		this.doneNumber = doneNumber;
	}

	public Integer getIsSatisfied() {
		return isSatisfied;
	}

	public void setIsSatisfied(Integer isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getDoneServiceTime() {
		return doneServiceTime;
	}

	public void setDoneServiceTime(Integer doneServiceTime) {
		this.doneServiceTime = doneServiceTime;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getSelectProjectId() {
		return selectProjectId;
	}

	public void setSelectProjectId(String selectProjectId) {
		this.selectProjectId = selectProjectId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}
