package com.bjsxt.dataOut.entity;

import java.util.Date;

public class ProjectCooperationDone {
	/**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * detail_id
    */
    private String detailId;

    /**
    * service_date
    */
    private Date serviceDate;

    /**
    * service_number
    */
    private Integer serviceNumber;

    /**
    * remark
    */
    private String remark;

    /**
    * is_fail
    */
    private Integer isFail;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * service_shop_id
    */
    private String serviceShopId;

    /**
    * 往期收款业绩
    */
    private Double shareLeftEarn;

    /**
    * price
    */
    private Double price;

    /**
    * 服务公司
    */
    private String serviceCompany;

    /**
    * percent
    */
    private Double percent;

    /**
    * first_earn
    */
    private Double firstEarn;

    /**
    * is_count_comsume
    */
    private Integer isCountComsume;

    /**
    * 星和合作项目医生
    */
    private String doctor;

    /**
    * 星和合作项目护士
    */
    private String nurse;
    
    

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

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Integer getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(Integer serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getServiceShopId() {
		return serviceShopId;
	}

	public void setServiceShopId(String serviceShopId) {
		this.serviceShopId = serviceShopId;
	}

	public Double getShareLeftEarn() {
		return shareLeftEarn;
	}

	public void setShareLeftEarn(Double shareLeftEarn) {
		this.shareLeftEarn = shareLeftEarn;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getFirstEarn() {
		return firstEarn;
	}

	public void setFirstEarn(Double firstEarn) {
		this.firstEarn = firstEarn;
	}

	public Integer getIsCountComsume() {
		return isCountComsume;
	}

	public void setIsCountComsume(Integer isCountComsume) {
		this.isCountComsume = isCountComsume;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public String getNurse() {
		return nurse;
	}

	public void setNurse(String nurse) {
		this.nurse = nurse;
	}
}
