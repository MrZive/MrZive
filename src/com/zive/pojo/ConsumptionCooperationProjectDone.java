package com.zive.pojo;

import java.util.Date;
import java.util.List;

/**
 * 合作项目销售明细做服务
 * @author hcxue
 *
 */
public class ConsumptionCooperationProjectDone {

	private String id;
	
	/**
	 * 星和合作项目医生
	 */
	private String doctor;
	/**
	 * 星和合作项目护士
	 */
	private String nurse;
	
//	合作项目销售明细ID
	private String detailId;
	
	private String consumptionId;
	private String memberCardId;

//	服务数量
	private Integer serviceNumber;
	private String serviceShopId;
	private String serviceShopName;
	private String serviceCompany;
	
//	服务时间
	private Date serviceDate;
	
//	创建时间
	private Date createDate;
	private Double price;
	private String shopId;
	private String cooperationId;
	
//	备注
	private String remark;
	private String consumptionDate;
	
//	往期收款业绩
	private Double shareLeftEarn;
//	首次提成
	private Double firstEarn;
//	是否计入合作消耗，1是，0否
	private Integer isCountComsume;
//	业绩比例
	private Double percent;
//	非纹绣项目剩下的一成业绩分享
	/**
	 * @return the consumptionDate
	 */
	public String getConsumptionDate() {
		return consumptionDate;
	}

	/**
	 * @param consumptionDate the consumptionDate to set
	 */
	public void setConsumptionDate(String consumptionDate) {
		this.consumptionDate = consumptionDate;
	}

	/**
	 * @return the memberCardId
	 */
	public String getMemberCardId() {
		return memberCardId;
	}

	/**
	 * @param memberCardId the memberCardId to set
	 */
	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the shopId
	 */
	public String getShopId() {
		return shopId;
	}

	/**
	 * @param shopId the shopId to set
	 */
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	/**
	 * @return the cooperationId
	 */
	public String getCooperationId() {
		return cooperationId;
	}

	/**
	 * @param cooperationId the cooperationId to set
	 */
	public void setCooperationId(String cooperationId) {
		this.cooperationId = cooperationId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public Integer getServiceNumber() {
		return serviceNumber;
	}

	public void setServiceNumber(Integer serviceNumber) {
		this.serviceNumber = serviceNumber;
	}

	public Date getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Date serviceDate) {
		this.serviceDate = serviceDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
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

	public String getServiceShopName() {
		return serviceShopName;
	}

	public void setServiceShopName(String serviceShopName) {
		this.serviceShopName = serviceShopName;
	}

	public Double getShareLeftEarn() {
		return shareLeftEarn;
	}

	public void setShareLeftEarn(Double shareLeftEarn) {
		this.shareLeftEarn = shareLeftEarn;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public Double getFirstEarn() {
		return firstEarn;
	}

	public void setFirstEarn(Double firstEarn) {
		this.firstEarn = firstEarn;
	}

	

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
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
