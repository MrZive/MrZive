package com.zive.member386Plan.entity;

import java.util.Date;

public class MemberPassStatus {
	
	/**
	 * 必盈店的会员状态
	 * @author WangZive
	 * @date: 2020年11月1日 下午4:32:49
	 */
	public enum EnumType{
		open,
		//必盈会员状态start-----------
		isPass,
		isActive,
		isActiveB,
		isActiveC,
		isAbandon,
		isSleep,
		isChange,
		noPass,
		//386会员状态start-----------
		is386Pass,
		is386Active,
		is386ActiveB,
		is386ActiveC,
		is386Abandon,
		is386Sleep,
		is386Change,
		no386Pass,
		is386Second,
		no386Second
	}

	/**
	 * id
	 */
	private Long id;

	/**
	 * member_card_id
	 */
	private String memberCardId;

	/**
	 * is_pass
	 */
	private Integer isPass;

	/**
	 * is_abandon
	 */
	private Integer isAbandon;

	/**
	 * is_sleep
	 */
	private Integer isSleep;

	/**
	 * pass_shop_id
	 */
	private String passShopId;

	/**
	 * pass_consumption_id
	 */
	private String passConsumptionId;

	/**
	 * pass_time
	 */
	private Date passTime;

	/**
	 * is_new_pass
	 */
	private Integer isNewPass;

	/**
	 * is_new_abandon
	 */
	private Integer isNewAbandon;

	/**
	 * is_new_sleep
	 */
	private Integer isNewSleep;

	/**
	 * new_pass_shop_id
	 */
	private String newPassShopId;

	/**
	 * new_pass_consumption_id
	 */
	private String newPassConsumptionId;

	/**
	 * new_pass_time
	 */
	private Date newPassTime;
	
	//------------------------------------------------------------------386字段start-----------------------------------------------------------------------
	
	/**
	 * 是否购买了拓客套餐
	 */
	private Integer is386Tuoke;

	/**
	 * 拓客套餐流水单
	 */
	private String tuoke386ConsumptionId;

	/**
	 * 拓客套餐门店
	 */
	private String tuoke386ShopId;

	/**
	 * 拓客套餐时间
	 */
	private Date tuoke386Time;

	/**
	 * 是否一转
	 */
	private Integer is386First;

	/**
	 * 一转流水单
	 */
	private String first386ConsumptionId;

	/**
	 * 一转门店
	 */
	private String first386ShopId;

	/**
	 * 一转时间
	 */
	private Date first386Time;

	/**
	 * 是否二转
	 */
	private Integer is386Second;

	/**
	 * 二转流水单
	 */
	private String second386ConsumptionId;

	/**
	 * 二转门店
	 */
	private String second386ShopId;

	/**
	 * 二转时间
	 */
	private Date second386Time;

	/**
	 * 是否有效会员
	 */
	private Integer is386Pass;

	/**
	 * 是否流失会员
	 */
	private Integer is386Abandon;

	/**
	 * 是否休眠会员
	 */
	private Integer is386Sleep;

	/**
	 * 有效门店
	 */
	private String pass386ShopId;

	/**
	 * 有效时间
	 */
	private Date pass386Time;

	/**
	 * 有效流水单
	 */
	private String pass386ConsumptionId;

	//------------------------------------------------------------------386字段end-----------------------------------------------------------------------‘
	
	/**
	 * type
	 */
	private String type;
	
	private Date createDate;
	
	
	
	
	
	private Date startCreateDate;
	private Date endCreateDate;
	
	
	
	public void setType(EnumType enumType){
		type = enumType.name();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public Integer getIsPass() {
		return isPass;
	}

	public void setIsPass(Integer isPass) {
		this.isPass = isPass;
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

	public Date getPassTime() {
		return passTime;
	}

	public void setPassTime(Date passTime) {
		this.passTime = passTime;
	}

	public Integer getIsNewPass() {
		return isNewPass;
	}

	public void setIsNewPass(Integer isNewPass) {
		this.isNewPass = isNewPass;
	}

	public Integer getIsNewAbandon() {
		return isNewAbandon;
	}

	public void setIsNewAbandon(Integer isNewAbandon) {
		this.isNewAbandon = isNewAbandon;
	}

	public Integer getIsNewSleep() {
		return isNewSleep;
	}

	public void setIsNewSleep(Integer isNewSleep) {
		this.isNewSleep = isNewSleep;
	}

	public String getNewPassShopId() {
		return newPassShopId;
	}

	public void setNewPassShopId(String newPassShopId) {
		this.newPassShopId = newPassShopId;
	}

	public String getNewPassConsumptionId() {
		return newPassConsumptionId;
	}

	public void setNewPassConsumptionId(String newPassConsumptionId) {
		this.newPassConsumptionId = newPassConsumptionId;
	}

	public Date getNewPassTime() {
		return newPassTime;
	}

	public void setNewPassTime(Date newPassTime) {
		this.newPassTime = newPassTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public Date getStartCreateDate() {
		return startCreateDate;
	}

	public void setStartCreateDate(Date startCreateDate) {
		this.startCreateDate = startCreateDate;
	}

	public Date getEndCreateDate() {
		return endCreateDate;
	}

	public void setEndCreateDate(Date endCreateDate) {
		this.endCreateDate = endCreateDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIs386Tuoke() {
		return is386Tuoke;
	}

	public void setIs386Tuoke(Integer is386Tuoke) {
		this.is386Tuoke = is386Tuoke;
	}

	public String getTuoke386ConsumptionId() {
		return tuoke386ConsumptionId;
	}

	public void setTuoke386ConsumptionId(String tuoke386ConsumptionId) {
		this.tuoke386ConsumptionId = tuoke386ConsumptionId;
	}

	public String getTuoke386ShopId() {
		return tuoke386ShopId;
	}

	public void setTuoke386ShopId(String tuoke386ShopId) {
		this.tuoke386ShopId = tuoke386ShopId;
	}

	public Date getTuoke386Time() {
		return tuoke386Time;
	}

	public void setTuoke386Time(Date tuoke386Time) {
		this.tuoke386Time = tuoke386Time;
	}

	public Integer getIs386First() {
		return is386First;
	}

	public void setIs386First(Integer is386First) {
		this.is386First = is386First;
	}

	public String getFirst386ConsumptionId() {
		return first386ConsumptionId;
	}

	public void setFirst386ConsumptionId(String first386ConsumptionId) {
		this.first386ConsumptionId = first386ConsumptionId;
	}

	public String getFirst386ShopId() {
		return first386ShopId;
	}

	public void setFirst386ShopId(String first386ShopId) {
		this.first386ShopId = first386ShopId;
	}

	public Date getFirst386Time() {
		return first386Time;
	}

	public void setFirst386Time(Date first386Time) {
		this.first386Time = first386Time;
	}

	public Integer getIs386Second() {
		return is386Second;
	}

	public void setIs386Second(Integer is386Second) {
		this.is386Second = is386Second;
	}

	public String getSecond386ConsumptionId() {
		return second386ConsumptionId;
	}

	public void setSecond386ConsumptionId(String second386ConsumptionId) {
		this.second386ConsumptionId = second386ConsumptionId;
	}

	public String getSecond386ShopId() {
		return second386ShopId;
	}

	public void setSecond386ShopId(String second386ShopId) {
		this.second386ShopId = second386ShopId;
	}

	public Date getSecond386Time() {
		return second386Time;
	}

	public void setSecond386Time(Date second386Time) {
		this.second386Time = second386Time;
	}

	public Integer getIs386Pass() {
		return is386Pass;
	}

	public void setIs386Pass(Integer is386Pass) {
		this.is386Pass = is386Pass;
	}

	public Integer getIs386Abandon() {
		return is386Abandon;
	}

	public void setIs386Abandon(Integer is386Abandon) {
		this.is386Abandon = is386Abandon;
	}

	public Integer getIs386Sleep() {
		return is386Sleep;
	}

	public void setIs386Sleep(Integer is386Sleep) {
		this.is386Sleep = is386Sleep;
	}

	public String getPass386ShopId() {
		return pass386ShopId;
	}

	public void setPass386ShopId(String pass386ShopId) {
		this.pass386ShopId = pass386ShopId;
	}

	public Date getPass386Time() {
		return pass386Time;
	}

	public void setPass386Time(Date pass386Time) {
		this.pass386Time = pass386Time;
	}

	public String getPass386ConsumptionId() {
		return pass386ConsumptionId;
	}

	public void setPass386ConsumptionId(String pass386ConsumptionId) {
		this.pass386ConsumptionId = pass386ConsumptionId;
	}
	
}
