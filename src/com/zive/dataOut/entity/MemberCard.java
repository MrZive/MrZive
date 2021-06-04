package com.zive.dataOut.entity;

import java.util.Date;

import com.zive.member386Plan.entity.MemberCard386Plan;

public class MemberCard {
	private static final long serialVersionUID = 1L;

    /**
    * id
    */
    private String id;

    /**
    * 会员手机
    */
    private String phone;

    /**
    * 会员姓名
    */
    private String name;
    
    /**
     * 昵称
     */
    private String nickName;

    /**
    * 开卡日期
    */
    private Date activateDate;

    /**
    * 会员生日
    */
    private String birthday;

    /**
    * 开卡门店id
    */
    private String shopId;

    /**
    * 会员性别，0女，1男
    */
    private Integer gender;

    /**
    * 目测年龄
    */
    private String ages;

    /**
    * 来源渠道。222，自主进店；223，门店拓客；224，客带客；225，网络拓客；226，其他；227，微信（当操作为更新会员时此字段为空）
    */
    private String fromType;
    
    /**
     * 头像
     */
    private String avatar;

    /**
    * 会员职业
    */
    private String job;

    /**
    * 会员地址
    */
    private String addr;

    /**
    * 备注
    */
    private String remark;

    /**
    * 1是客人不肯提供手机号，0提供
    */
    private Integer noPhone;
    
    /**
     * 是否介绍
     */
    private Integer IsIndroduce;

    /**
    * 操作人的id
    */
    private String createUserId;

    /**
    * 创建时间
    */
    private Date createDate;

    /**
    * 1是激活状态，-1是删除状态，0是未激活状态
    */
    private Integer status;

    /**
    * 是否是有效会员1有效0无效
    */
    private Integer isPass;

    /**
    * pass_time
    */
    private Date passTime;

    /**
    * 是否是活跃会员（活跃等级，以当前月前6个月为期，某个月内消费一次则加一级，即6个月内只有一个月进行消费，消费多次也是一级，6个月内 每个月都消费了至少一次则为六级）
    */
    private Integer isActive;

    /**
    * 是否是流失会员，0不是，1是
    */
    private Integer isAbandon;

    /**
    * 是否是睡眠会员，0不是，1是
    */
    private Integer isSleep;

    /**
    * 储值账户
    */
    private Double storeBalance;

    /**
    * 积分账户
    */
    private Double pointBalance;

    /**
    * 疗程余额
    */
    private Double treatmentBalance;

    /**
    * 存货余额
    */
    private Double stockBalance;

    /**
    * wenxiu_balance
    */
    private Double wenxiuBalance;

    /**
    * 欠款账户
    */
    private Double oweBalance;

    /**
    * 是否是老会员介绍，1是0不是
    */
    private Integer isIntroduce;

    /**
    * 老会员卡号
    */
    private String introducer;

    /**
    * 是否标准会员，经过99-999-1300或者老会员介绍-999-1300
    */
    private Integer isStandard;

    /**
    * standard_time
    */
    private Date standardTime;

    /**
    * 成为有效会员门店
    */
    private String passShopId;

    /**
    * pass_consumption_id
    */
    private String passConsumptionId;

    /**
    * 剩余储值余额和疗程之和
    */
    private Double leftTotal;

    /**
    * 0青花瓷 1线上 2天使之纹 3丘山医美 4其他 5微信6海报
    */
    private Integer channelId;

    /**
    * 最后操作时间
    */
    private Date lastUpdateTime;

    /**
    * 会员是否异常状态，0不是，1是终身免费异常
    */
    private Integer error;

    /**
    * 会员标签
    */
    private String label;

    /**
    * 经期每月开始日期
    */
    private Integer period;

    /**
    * 空闲开始时间
    */
    private String freeStart;

    /**
    * 空闲结束时间
    */
    private String freeEnd;

    /**
    * 提醒方式
    */
    private String remindType;

    /**
    * 是否是新标准的有效会员
    */
    private Integer isNewPass;

    /**
    * 是否是新标准的休眠会员
    */
    private Integer isNewSleep;

    /**
    * 是否是新标准的流失会员
    */
    private Integer isNewAbandon;

    /**
    * 新标准的有效会员消费单
    */
    private String newPassConsumptionId;

    /**
    * 新标准的有效会员消费门店id
    */
    private String newPassShopId;

    /**
    * 新标准的有效会员消费时间
    */
    private Date newPassTime;

    /**
    * 新拓客标志
    */
    private Integer isNewTuoke;

    /**
    * new_tuoke_consumption_id
    */
    private String newTuokeConsumptionId;

    /**
    * new_tuoke_shop_id
    */
    private String newTuokeShopId;

    /**
    * new_tuoke_time
    */
    private Date newTuokeTime;
    
    /**
	 * 客户经理
	 */
	private String managerId;
	
	/**
	 * 客户经理
	 */
	private String managerName;
	
	
	/**
	 * 老客带新客--是否老客
	 */
	private Integer isOld;
	
	/**
	 * 老客带新客--员工推荐老客户
	 */
	private String employeeId;
	
	/**
	 * 老客带新客--新客绑定的老客客
	 */
	private String oldMemberCardId;

    /**
    * open_id
    */
    private String openId;

    /**
    * 是否已经签署协议
    */
    private Integer isSign;
    
    /**
     * 386状态
     */
    private MemberCard386Plan memberCard386Plan;
    
    

	public MemberCard386Plan getMemberCard386Plan() {
		return memberCard386Plan;
	}

	public void setMemberCard386Plan(MemberCard386Plan memberCard386Plan) {
		this.memberCard386Plan = memberCard386Plan;
	}

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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getAges() {
		return ages;
	}

	public void setAges(String ages) {
		this.ages = ages;
	}

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getNoPhone() {
		return noPhone;
	}

	public void setNoPhone(Integer noPhone) {
		this.noPhone = noPhone;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
	}

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Integer getIsActive() {
		return isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
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

	public Double getWenxiuBalance() {
		return wenxiuBalance;
	}

	public void setWenxiuBalance(Double wenxiuBalance) {
		this.wenxiuBalance = wenxiuBalance;
	}

	public Double getOweBalance() {
		return oweBalance;
	}

	public void setOweBalance(Double oweBalance) {
		this.oweBalance = oweBalance;
	}

	public Integer getIsIntroduce() {
		return isIntroduce;
	}

	public void setIsIntroduce(Integer isIntroduce) {
		this.isIntroduce = isIntroduce;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
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

	public String getPassShopId() {
		return passShopId;
	}

	public void setPassShopId(String passShopId) {
		this.passShopId = passShopId;
	}

	public String getPassConsumptionId() {
		return passConsumptionId;
	}

	public void setPassConsumptionId(String passConsumptionId) {
		this.passConsumptionId = passConsumptionId;
	}

	public Double getLeftTotal() {
		return leftTotal;
	}

	public void setLeftTotal(Double leftTotal) {
		this.leftTotal = leftTotal;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getError() {
		return error;
	}

	public void setError(Integer error) {
		this.error = error;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public String getFreeStart() {
		return freeStart;
	}

	public void setFreeStart(String freeStart) {
		this.freeStart = freeStart;
	}

	public String getFreeEnd() {
		return freeEnd;
	}

	public void setFreeEnd(String freeEnd) {
		this.freeEnd = freeEnd;
	}

	public String getRemindType() {
		return remindType;
	}

	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}

	public Integer getIsNewPass() {
		return isNewPass;
	}

	public void setIsNewPass(Integer isNewPass) {
		this.isNewPass = isNewPass;
	}

	public Integer isNewSleep() {
		return isNewSleep;
	}

	public void setNewSleep(Integer isNewSleep) {
		this.isNewSleep = isNewSleep;
	}

	public Integer isNewAbandon() {
		return isNewAbandon;
	}

	public void setNewAbandon(Integer isNewAbandon) {
		this.isNewAbandon = isNewAbandon;
	}

	public String getNewPassConsumptionId() {
		return newPassConsumptionId;
	}

	public void setNewPassConsumptionId(String newPassConsumptionId) {
		this.newPassConsumptionId = newPassConsumptionId;
	}

	public String getNewPassShopId() {
		return newPassShopId;
	}

	public void setNewPassShopId(String newPassShopId) {
		this.newPassShopId = newPassShopId;
	}

	public Date getNewPassTime() {
		return newPassTime;
	}

	public void setNewPassTime(Date newPassTime) {
		this.newPassTime = newPassTime;
	}

	public Integer getIsNewTuoke() {
		return isNewTuoke;
	}

	public void setIsNewTuoke(Integer isNewTuoke) {
		this.isNewTuoke = isNewTuoke;
	}

	public String getNewTuokeConsumptionId() {
		return newTuokeConsumptionId;
	}

	public void setNewTuokeConsumptionId(String newTuokeConsumptionId) {
		this.newTuokeConsumptionId = newTuokeConsumptionId;
	}

	public String getNewTuokeShopId() {
		return newTuokeShopId;
	}

	public void setNewTuokeShopId(String newTuokeShopId) {
		this.newTuokeShopId = newTuokeShopId;
	}

	public Date getNewTuokeTime() {
		return newTuokeTime;
	}

	public void setNewTuokeTime(Date newTuokeTime) {
		this.newTuokeTime = newTuokeTime;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getIsNewSleep() {
		return isNewSleep;
	}

	public void setIsNewSleep(Integer isNewSleep) {
		this.isNewSleep = isNewSleep;
	}

	public Integer getIsNewAbandon() {
		return isNewAbandon;
	}

	public void setIsNewAbandon(Integer isNewAbandon) {
		this.isNewAbandon = isNewAbandon;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public Integer getIsOld() {
		return isOld;
	}

	public void setIsOld(Integer isOld) {
		this.isOld = isOld;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getOldMemberCardId() {
		return oldMemberCardId;
	}

	public void setOldMemberCardId(String oldMemberCardId) {
		this.oldMemberCardId = oldMemberCardId;
	}

	public Integer getIsIndroduce() {
		return IsIndroduce;
	}

	public void setIsIndroduce(Integer isIndroduce) {
		IsIndroduce = isIndroduce;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
    
    

}
