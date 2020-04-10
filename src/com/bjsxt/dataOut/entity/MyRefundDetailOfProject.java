package com.bjsxt.dataOut.entity;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;

@TableName("退款单-项目")
public class MyRefundDetailOfProject {

	@FieldName("ID")
    private String id;

	@FieldName("流水单号")
    private String consumptionId;
	
	@FieldName("消费单号")
    private String consumptionProjectId;
	
	@FieldName("消费单流水号中的ID")
	private String projectDetailId;
	
	@FieldName("退货单号")
	private String returnTransferId;

	@FieldName("退款类型1是项目，2是产品，3是合作项目")
    private Integer type;

	@FieldName("退项目次数")
    private Double number;
	
	@FieldName("剩余金额")
    private Double leftMoney;

	@FieldName("退款金额")
    private Double returnMoney;
	
	public void fromSuper(ProjectDetailConsumption projectDetailConsumptionById,RefundDetail detail){
		this.id = detail.getId();
		this.consumptionId = projectDetailConsumptionById.getConsumptionId();
		this.consumptionProjectId = projectDetailConsumptionById.getConsumptionProjectId();
		this.projectDetailId = detail.getDetailId();
		this.returnTransferId = detail.getReturnTransferId();
		this.type = detail.getType();
		this.number = detail.getNumber();
		this.leftMoney = detail.getLeftMoney();
		this.returnMoney = detail.getReturnMoney();
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

	public String getConsumptionProjectId() {
		return consumptionProjectId;
	}

	public void setConsumptionProjectId(String consumptionProjectId) {
		this.consumptionProjectId = consumptionProjectId;
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

}
