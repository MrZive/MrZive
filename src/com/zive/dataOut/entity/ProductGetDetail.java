package com.zive.dataOut.entity;

import java.sql.Date;

public class ProductGetDetail {
	 /**
    * id
    */
    private String id;

    /**
    * consumption_id
    */
    private String consumptionId;

    /**
    * product_detail_id
    */
    private String productDetailId;

    /**
    * number
    */
    private Integer number;

    /**
    * unit
    */
    private String unit;

    /**
    * create_date
    */
    private Date createDate;

    /**
    * is_fail
    */
    private Integer isFail;

    /**
    * order_id
    */
    private String orderId;

    /**
    * price
    */
    private Double price;

    /**
    * 以最小单位计算，产品的实际取货数量
    */
    private Integer actualGetNumber;

    /**
    * remark
    */
    private String remark;
    

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

	public String getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(String productDetailId) {
		this.productDetailId = productDetailId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getActualGetNumber() {
		return actualGetNumber;
	}

	public void setActualGetNumber(Integer actualGetNumber) {
		this.actualGetNumber = actualGetNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
