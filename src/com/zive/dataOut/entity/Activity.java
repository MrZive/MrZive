package com.zive.dataOut.entity;

import java.util.Date;

public class Activity {
	/**
	 * id
	 */
	private String id;

	/**
	 * 活动标题
	 */
	private String name;

	/**
	 * 活动类型，1指定赠送，2满减，3优惠码，4套餐，5推广价
	 */
	private Integer type;

	/**
	 * 开始日期
	 */
	private Date beginDate;

	/**
	 * 结束日期
	 */
	private Date endDate;

	/**
	 * 活动状态， 0过期，1进行中，2暂停，-1删除
	 */
	private Integer status;

	/**
	 * 是否限制时间，0不限制，1限制
	 */
	private Integer isLimitTime;

	/**
	 * 优惠循环，1按年，2按月，3按周，4按日
	 */
	private Integer loopType;

	/**
	 * 充值消耗限制。0不限制，1限制
	 */
	private Integer isLimitConsume;

	/**
	 * 消耗大于
	 */
	private Double consume1;

	/**
	 * 消耗等于
	 */
	private Double consume2;

	/**
	 * consume3
	 */
	private Double consume3;

	/**
	 * consume_type1
	 */
	private Integer consumeType1;

	/**
	 * 消耗小于
	 */
	private Integer consumeType2;

	/**
	 * consume_type3
	 */
	private Integer consumeType3;

	/**
	 * 充值大于
	 */
	private Double charge1;

	/**
	 * 充值等于
	 */
	private Double charge2;

	/**
	 * charge3
	 */
	private Double charge3;

	/**
	 * charge_type1
	 */
	private Integer chargeType1;

	/**
	 * 充值小于
	 */
	private Integer chargeType2;

	/**
	 * charge_type3
	 */
	private Integer chargeType3;

	/**
	 * create_user_id
	 */
	private String createUserId;

	/**
	 * create_date
	 */
	private Date createDate;

	/**
	 * 优惠循环，0不循环，1循环
	 */
	private Integer isLoop;

	/**
	 * 1是项目，2是产品，3是合作项目，4纹绣项目
	 */
	private Integer actType;

	/**
	 * 是否限制地区，1限制，0不限
	 */
	private Integer isLimitRegion;

	/**
	 * 地区在字典表的区别码，所有是all
	 */
	private String region;

	/**
	 * 前置条件: 0:不限 1:套餐 2:活动 3:项目 4:产品
	 */
	private Integer condition;

	/**
	 * 是否发送短信通知
	 */
	private boolean isMessage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsLimitTime() {
		return isLimitTime;
	}

	public void setIsLimitTime(Integer isLimitTime) {
		this.isLimitTime = isLimitTime;
	}

	public Integer getLoopType() {
		return loopType;
	}

	public void setLoopType(Integer loopType) {
		this.loopType = loopType;
	}

	public Integer getIsLimitConsume() {
		return isLimitConsume;
	}

	public void setIsLimitConsume(Integer isLimitConsume) {
		this.isLimitConsume = isLimitConsume;
	}

	public Double getConsume1() {
		return consume1;
	}

	public void setConsume1(Double consume1) {
		this.consume1 = consume1;
	}

	public Double getConsume2() {
		return consume2;
	}

	public void setConsume2(Double consume2) {
		this.consume2 = consume2;
	}

	public Double getConsume3() {
		return consume3;
	}

	public void setConsume3(Double consume3) {
		this.consume3 = consume3;
	}

	public Integer getConsumeType1() {
		return consumeType1;
	}

	public void setConsumeType1(Integer consumeType1) {
		this.consumeType1 = consumeType1;
	}

	public Integer getConsumeType2() {
		return consumeType2;
	}

	public void setConsumeType2(Integer consumeType2) {
		this.consumeType2 = consumeType2;
	}

	public Integer getConsumeType3() {
		return consumeType3;
	}

	public void setConsumeType3(Integer consumeType3) {
		this.consumeType3 = consumeType3;
	}

	public Double getCharge1() {
		return charge1;
	}

	public void setCharge1(Double charge1) {
		this.charge1 = charge1;
	}

	public Double getCharge2() {
		return charge2;
	}

	public void setCharge2(Double charge2) {
		this.charge2 = charge2;
	}

	public Double getCharge3() {
		return charge3;
	}

	public void setCharge3(Double charge3) {
		this.charge3 = charge3;
	}

	public Integer getChargeType1() {
		return chargeType1;
	}

	public void setChargeType1(Integer chargeType1) {
		this.chargeType1 = chargeType1;
	}

	public Integer getChargeType2() {
		return chargeType2;
	}

	public void setChargeType2(Integer chargeType2) {
		this.chargeType2 = chargeType2;
	}

	public Integer getChargeType3() {
		return chargeType3;
	}

	public void setChargeType3(Integer chargeType3) {
		this.chargeType3 = chargeType3;
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

	public Integer getIsLoop() {
		return isLoop;
	}

	public void setIsLoop(Integer isLoop) {
		this.isLoop = isLoop;
	}

	public Integer getActType() {
		return actType;
	}

	public void setActType(Integer actType) {
		this.actType = actType;
	}

	public Integer getIsLimitRegion() {
		return isLimitRegion;
	}

	public void setIsLimitRegion(Integer isLimitRegion) {
		this.isLimitRegion = isLimitRegion;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public boolean isMessage() {
		return isMessage;
	}

	public void setMessage(boolean isMessage) {
		this.isMessage = isMessage;
	}
	
}
