package com.bjsxt.dataOut.entity;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;

@TableName("退款单-产品")
public class MyRefundDetailOfProduct {

	@FieldName("ID")
    private String id;

	@FieldName("流水单号")
    private String consumptionId;
	
	@FieldName("消费单号")
    private String consumptionProductId;
	
	@FieldName("消费单中的ID")
	private String projectDetailId;
	
	@FieldName("退货单号")
	private String returnTransferId;

	@FieldName("退款类型1是项目，2是产品，3是合作项目")
    private Integer type;

	@FieldName("退产品数量")
    private Double number;
	
	@FieldName("退产品单位")
    private String unit;

	@FieldName("剩余金额")
    private Double leftMoney;

	@FieldName("退款金额")
    private Double returnMoney;
	
	
	public void fromSuper(ProductDetailConsumption productDetailConsumptionById,RefundDetail detail){
		this.id = detail.getId();
		this.consumptionId = productDetailConsumptionById.getConsumptionId();
		this.consumptionProductId = productDetailConsumptionById.getConsumptionProductId();
		this.projectDetailId = detail.getDetailId();
		this.returnTransferId = detail.getReturnTransferId();
		this.type = detail.getType();
		this.number = detail.getNumber();
		this.leftMoney = detail.getLeftMoney();
		this.returnMoney = detail.getReturnMoney();
		this.unit = detail.getUnit();
	}
	

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

	public String getConsumptionProductId() {
		return consumptionProductId;
	}

	public void setConsumptionProductId(String consumptionProductId) {
		this.consumptionProductId = consumptionProductId;
	}

	public String getProjectDetailId() {
		return projectDetailId;
	}

	public void setProjectDetailId(String projectDetailId) {
		this.projectDetailId = projectDetailId;
	}

	public String getReturnTransferId() {
		return returnTransferId;
	}

	public void setReturnTransferId(String returnTransferId) {
		this.returnTransferId = returnTransferId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
