package com.zive.dataOut.entity;

import java.util.Date;

public class MemberCard {
	/**
	 * 卡id
	 */
	private String id;
	/**
	 * 卡号
	 */
	private String phone;
	/**
	 * 会员姓名
	 */
	private String name;
//	是否是老会员介绍,1是0不是
	private Integer isIndroduce;
	
//	老会员卡号
	private String introducer;
    
	/**
	 * 开卡日期
	 */
	private Date activateDate;
	/**
	 * 生日
	 */
	private String birthday;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 是否提供手机号码 1是客人不肯提供手机号，0提供
	 */
	private Integer noPhone;
	/**
	 * 门店id
	 */
	private String shopId;
	/**
	 * 是否有效会员
	 */
	private Integer isPass;
	/**
	 * 是否流失会员
	 */
	private Integer isAbandon;
	/**
	 * 是否休眠会员
	 */
	private Integer isSleep;
	/**
	 * 是否活跃会员
	 */
	private Integer isActive;
	/**
	 * 是否转转介绍会员
	 */
	private Integer isIntroduce;
	/**
	 * 成为有效会员时间
	 */
	private Date passTime;
	/**
	 * 成为有效会员门店id
	 */
	private String passShopId;
	/**
	 * 会员卡状态1是激活状态，-1是删除状态，0是未激活状态
	 */
	private Integer status;
	/**
	 * 储值
	 */
	private Double storeBalance;
	/**
	 * 积分
	 */
	private Double pointBalance;
	/**
	 * 疗程账户
	 */
	private Double treatmentBalance;
	/**
	 * 存货余额
	 */
	private Double stockBalance;
	/**
	 * 欠款账户
	 */
	private Double oweBalance;
	/**
	 * 标准会员
	 */
	private Integer isStandard;
	/**
	 * 成为标准会员时间
	 */
	private Date standardTime;
    /**
     * 创建时间
     */
	private Date createDate;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Integer getNoPhone() {
		return noPhone;
	}

	public void setNoPhone(Integer noPhone) {
		this.noPhone = noPhone;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Integer getIsIntroduce() {
		return isIntroduce;
	}

	public void setIsIntroduce(Integer isIntroduce) {
		this.isIntroduce = isIntroduce;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Double getStoreBalance() {
		return storeBalance;
	}

	public void setStoreBalance(Double storeBalance) {
		this.storeBalance = storeBalance;
	}

	public Double getPointBalance() {
		return pointBalance;
	}

	public void setPointBalance(Double pointBalance) {
		this.pointBalance = pointBalance;
	}

	public Double getTreatmentBalance() {
		return treatmentBalance;
	}

	public void setTreatmentBalance(Double treatmentBalance) {
		this.treatmentBalance = treatmentBalance;
	}

	public Double getStockBalance() {
		return stockBalance;
	}

	public void setStockBalance(Double stockBalance) {
		this.stockBalance = stockBalance;
	}

	public Double getOweBalance() {
		return oweBalance;
	}

	public void setOweBalance(Double oweBalance) {
		this.oweBalance = oweBalance;
	}

	public Integer getIsStandard() {
		return isStandard;
	}

	public void setIsStandard(Integer isStandard) {
		this.isStandard = isStandard;
	}

	public Date getStandardTime() {
		return standardTime;
	}

	public void setStandardTime(Date standardTime) {
		this.standardTime = standardTime;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getActivateDate() {
		return activateDate;
	}

	public void setActivateDate(Date activateDate) {
		this.activateDate = activateDate;
	}

	public String getPassShopId() {
		return passShopId;
	}

	public void setPassShopId(String passShopId) {
		this.passShopId = passShopId;
	}

	public Integer getIsAbandon() {
		return isAbandon;
	}

	public void setIsAbandon(Integer isAbandon) {
		this.isAbandon = isAbandon;
	}

	public Integer getIsSleep() {
		return isSleep;
	}

	public void setIsSleep(Integer isSleep) {
		this.isSleep = isSleep;
	}

	public Integer getIsIndroduce() {
		return isIndroduce;
	}

	public void setIsIndroduce(Integer isIndroduce) {
		this.isIndroduce = isIndroduce;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

}
