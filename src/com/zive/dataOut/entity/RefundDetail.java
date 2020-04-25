package com.bjsxt.dataOut.entity;

public class RefundDetail {

	/**
    * id
    */
    private String id;

    /**
    * detail_id
    */
    private String detailId;

    /**
    * 1是项目，2是产品，3是合作项目
    */
    private Integer type;

    /**
    * 退换货id
    */
    private String returnTransferId;

    /**
    * 退换货数量
    */
    private Double number;

    /**
    * 剩余金额
    */
    private Double leftMoney;

    /**
    * 退款金额
    */
    private Double returnMoney;

    /**
    * 退换明细的消费单流水号
    */
    private String consumptionId;

    /**
    * 退产品的单位
    */
    private String unit;
    
    

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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getReturnTransferId() {
		return returnTransferId;
	}

	public void setReturnTransferId(String returnTransferId) {
		this.returnTransferId = returnTransferId;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public Double getLeftMoney() {
		return leftMoney;
	}

	public void setLeftMoney(Double leftMoney) {
		this.leftMoney = leftMoney;
	}

	public Double getReturnMoney() {
		return returnMoney;
	}

	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}

	public String getConsumptionId() {
		return consumptionId;
	}

	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
    
}
