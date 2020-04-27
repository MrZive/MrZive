package com.zive.pojo;

import java.util.Date;
import java.util.List;

public class OneCooDetail {

	private String id;
	
	private String consumption_id;
	
	private String consumption_cooperation_id;
	
	private String cooperation_id;
	
	private String projectName;
	
	private String member_card_id;
	
	private String member_card_phone;
	
	private Double left_earn;
	
	private Double left_store_earn;
	
	private Double left_one_earn;
	
	private String shopId;
	
	private String shopNo;
	
	private String shopName;
	
	private Date consumption_date;
	
	private List<Earn> earnList;

	@Override
	public String toString() {
		return "OneCooDetail [id=" + id + ", consumption_id=" + consumption_id + ", consumption_cooperation_id="
				+ consumption_cooperation_id + ", cooperation_id=" + cooperation_id + ", projectName=" + projectName
				+ ", member_card_id=" + member_card_id + ", left_earn=" + left_earn + ", shopNo=" + shopNo
				+ ", shopName=" + shopName + ", consumption_date=" + consumption_date + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Earn> getEarnList() {
		return earnList;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public void setEarnList(List<Earn> earnList) {
		this.earnList = earnList;
	}

	public String getConsumption_id() {
		return consumption_id;
	}

	public Double getLeft_store_earn() {
		return left_store_earn;
	}

	public void setLeft_store_earn(Double left_store_earn) {
		this.left_store_earn = left_store_earn;
	}

	public Double getLeft_one_earn() {
		return left_one_earn;
	}

	public void setLeft_one_earn(Double left_one_earn) {
		this.left_one_earn = left_one_earn;
	}

	public void setConsumption_id(String consumption_id) {
		this.consumption_id = consumption_id;
	}

	public String getConsumption_cooperation_id() {
		return consumption_cooperation_id;
	}

	public void setConsumption_cooperation_id(String consumption_cooperation_id) {
		this.consumption_cooperation_id = consumption_cooperation_id;
	}

	public String getCooperation_id() {
		return cooperation_id;
	}

	public void setCooperation_id(String cooperation_id) {
		this.cooperation_id = cooperation_id;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getMember_card_id() {
		return member_card_id;
	}

	public void setMember_card_id(String member_card_id) {
		this.member_card_id = member_card_id;
	}

	public Double getLeft_earn() {
		return left_earn;
	}

	public void setLeft_earn(Double left_earn) {
		this.left_earn = left_earn;
	}

	public String getMember_card_phone() {
		return member_card_phone;
	}

	public void setMember_card_phone(String member_card_phone) {
		this.member_card_phone = member_card_phone;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public Date getConsumption_date() {
		return consumption_date;
	}

	public void setConsumption_date(Date consumption_date) {
		this.consumption_date = consumption_date;
	}
	
}
