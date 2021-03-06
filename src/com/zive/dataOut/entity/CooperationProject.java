package com.zive.dataOut.entity;

import java.util.Date;

/**
 * 合作项目实体类
 * 
 */
public class CooperationProject {

	/**
	 * id
	 */
	private String id;

	/**
	 * 合作项目编号
	 */
	private String no;

	/**
	 * 合作项目名称
	 */
	private String name;

	/**
	 * 项目单位
	 */
	private String unit;

	/**
	 * 项目单价
	 */
	private Double price;

	/**
	 * 业绩比例
	 */
	private Double percent;

	/**
	 * 有效周期/年
	 */
	private Double effectiveCycle;

	/**
	 * 不限价格 选中此项不限单价，可随便填写
	 */
	private Integer noLimitPrice;

	/**
	 * create_user_id
	 */
	private String createUserId;

	/**
	 * create_date_time
	 */
	private Date createDateTime;

	/**
	 * 状态，0停用1正常，-1删除
	 */
	private Integer status;

	/**
	 * 是否是纹绣
	 */
	private Integer isWenxiu;

	/**
	 * 首次提成
	 */
	private Double firstEarn;

	/**
	 * 是否计入合作消耗，1是，0否
	 */
	private Integer isCountComsume;

	/**
	 * 是否计算工资：0不 1是
	 */
	private Integer isCalculateSal;

	/**
	 * 是否收款：0不 1是
	 */
	private Integer isCome;

	/**
	 * 是否计算消耗：0不 1是
	 */
	private Integer isConsume;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getPercent() {
		return percent;
	}

	public void setPercent(Double percent) {
		this.percent = percent;
	}

	public Double getEffectiveCycle() {
		return effectiveCycle;
	}

	public void setEffectiveCycle(Double effectiveCycle) {
		this.effectiveCycle = effectiveCycle;
	}

	public Integer getNoLimitPrice() {
		return noLimitPrice;
	}

	public void setNoLimitPrice(Integer noLimitPrice) {
		this.noLimitPrice = noLimitPrice;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsWenxiu() {
		return isWenxiu;
	}

	public void setIsWenxiu(Integer isWenxiu) {
		this.isWenxiu = isWenxiu;
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

	public Integer getIsCalculateSal() {
		return isCalculateSal;
	}

	public void setIsCalculateSal(Integer isCalculateSal) {
		this.isCalculateSal = isCalculateSal;
	}

	public Integer getIsCome() {
		return isCome;
	}

	public void setIsCome(Integer isCome) {
		this.isCome = isCome;
	}

	public Integer getIsConsume() {
		return isConsume;
	}

	public void setIsConsume(Integer isConsume) {
		this.isConsume = isConsume;
	}

}
