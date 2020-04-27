package com.zive.pojo;

import java.util.Date;

public class Earn {

	private String id;
	
	private String consumption_id;
	
	private String consumption_type_id;
	
	private String shoper_id;
	
	private String shoper_name;
	
	private Double earn;
	
	private String type_id;
	
	private Integer type;
	
	private Date create_date;
	
	private Integer is_fail;
	
	private String shop_id;
	
	private String shop_no;
	
	private String consumption_shop_id;
	
	private Date consumption_date;
	
	private Double store_earn;
	
	private Double return_product_earn;
	
	private Double return_project_earn;
	
	private String buy_type;
	
	private String detail_id;

	@Override
	public String toString() {
		return "Earn [id=" + id + ", consumption_id=" + consumption_id + ", consumption_type_id=" + consumption_type_id
				+ ", shoper_id=" + shoper_id + ", earn=" + earn + ", type_id=" + type_id + ", type=" + type
				+ ", create_date=" + create_date + ", is_fail=" + is_fail + ", shop_id=" + shop_id
				+ ", consumption_shop_id=" + consumption_shop_id + ", consumption_date=" + consumption_date
				+ ", store_earn=" + store_earn + ", return_product_earn=" + return_product_earn
				+ ", return_project_earn=" + return_project_earn + ", buy_type=" + buy_type + ", detail_id=" + detail_id
				+ "]";
	}

	public String getId() {
		return id;
	}

	public String getShop_no() {
		return shop_no;
	}

	public void setShop_no(String shop_no) {
		this.shop_no = shop_no;
	}

	public String getShoper_name() {
		return shoper_name;
	}

	public void setShoper_name(String shoper_name) {
		this.shoper_name = shoper_name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsumption_id() {
		return consumption_id;
	}

	public void setConsumption_id(String consumption_id) {
		this.consumption_id = consumption_id;
	}

	public String getConsumption_type_id() {
		return consumption_type_id;
	}

	public void setConsumption_type_id(String consumption_type_id) {
		this.consumption_type_id = consumption_type_id;
	}

	public String getShoper_id() {
		return shoper_id;
	}

	public void setShoper_id(String shoper_id) {
		this.shoper_id = shoper_id;
	}

	public Double getEarn() {
		return earn;
	}

	public void setEarn(Double earn) {
		this.earn = earn;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getCreate_date() {
		return create_date;
	}

	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}

	public Integer getIs_fail() {
		return is_fail;
	}

	public void setIs_fail(Integer is_fail) {
		this.is_fail = is_fail;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public String getConsumption_shop_id() {
		return consumption_shop_id;
	}

	public void setConsumption_shop_id(String consumption_shop_id) {
		this.consumption_shop_id = consumption_shop_id;
	}

	public Date getConsumption_date() {
		return consumption_date;
	}

	public void setConsumption_date(Date consumption_date) {
		this.consumption_date = consumption_date;
	}

	public Double getStore_earn() {
		return store_earn;
	}

	public void setStore_earn(Double store_earn) {
		this.store_earn = store_earn;
	}

	public Double getReturn_product_earn() {
		return return_product_earn;
	}

	public void setReturn_product_earn(Double return_product_earn) {
		this.return_product_earn = return_product_earn;
	}

	public Double getReturn_project_earn() {
		return return_project_earn;
	}

	public void setReturn_project_earn(Double return_project_earn) {
		this.return_project_earn = return_project_earn;
	}

	public String getBuy_type() {
		return buy_type;
	}

	public void setBuy_type(String buy_type) {
		this.buy_type = buy_type;
	}

	public String getDetail_id() {
		return detail_id;
	}

	public void setDetail_id(String detail_id) {
		this.detail_id = detail_id;
	}
}
