package com.zive.member386Plan.entity;

import java.util.Date;

/**
 * shop_s_member_card_386_plan
 * 
 * @author MrZive 2020-10-29
 */
public class MemberCard386Plan {
	/**
	 * id
	 */
	private Long id;

	/**
	 * 会员卡id
	 */
	private String memberCardId;

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
	
	
	public static MemberCard386Plan init(String memberCardId) {
		// TODO Auto-generated constructor stub
		MemberCard386Plan plan = new MemberCard386Plan();
		plan.setIs386Abandon(0);
		plan.setIs386First(0);
		plan.setIs386Pass(0);
		plan.setIs386Second(0);
		plan.setIs386Sleep(0);
		plan.setIs386Tuoke(0);
		plan.setMemberCardId(memberCardId);
		return plan;
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