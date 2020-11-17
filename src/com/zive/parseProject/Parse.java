package com.zive.parseProject;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;

@TableName("必赢店转换历史")
public class Parse {
	
	@FieldName("原流水单")
	private String oldConsumptionId;

	@FieldName("原购买单Id")
	private String oldBuyOrderId;
	
	@FieldName("原购买单细节Id")
	private String oldOrderDetailId;
	
	@FieldName("新流水单")
	private String newConsumptionId;
	
	@FieldName("新购买单Id")
	private String newBuyOrderId;
	
	@FieldName("新购买单细节Id")
	private String newOrderDetailId;
	
	@FieldName("原项目id")
	private String oldProjectId;
	
	@FieldName("原项目名")
	private String oldProjectName;
	
	@FieldName("新项目id")
	private String newProjectId;
	
	@FieldName("新项目名")
	private String newProjectName;
	
	@FieldName("转换数量")
	private String number;
	
	@FieldName("转换门店id")
	private String shopId;
	
	@FieldName("转换门店")
	private String shopName;

	@FieldName("会员手机号")
	private String phone;

	@FieldName("会员姓名")
	private String name;

	public String getOldConsumptionId() {
		return oldConsumptionId;
	}

	public void setOldConsumptionId(String oldConsumptionId) {
		this.oldConsumptionId = oldConsumptionId;
	}

	public String getOldBuyOrderId() {
		return oldBuyOrderId;
	}

	public void setOldBuyOrderId(String oldBuyOrderId) {
		this.oldBuyOrderId = oldBuyOrderId;
	}

	public String getOldOrderDetailId() {
		return oldOrderDetailId;
	}

	public void setOldOrderDetailId(String oldOrderDetailId) {
		this.oldOrderDetailId = oldOrderDetailId;
	}

	public String getNewConsumptionId() {
		return newConsumptionId;
	}

	public void setNewConsumptionId(String newConsumptionId) {
		this.newConsumptionId = newConsumptionId;
	}

	public String getNewBuyOrderId() {
		return newBuyOrderId;
	}

	public void setNewBuyOrderId(String newBuyOrderId) {
		this.newBuyOrderId = newBuyOrderId;
	}

	public String getNewOrderDetailId() {
		return newOrderDetailId;
	}

	public void setNewOrderDetailId(String newOrderDetailId) {
		this.newOrderDetailId = newOrderDetailId;
	}


	public String getOldProjectId() {
		return oldProjectId;
	}

	public void setOldProjectId(String oldProjectId) {
		this.oldProjectId = oldProjectId;
	}

	public String getOldProjectName() {
		return oldProjectName;
	}

	public void setOldProjectName(String oldProjectName) {
		this.oldProjectName = oldProjectName;
	}

	public String getNewProjectId() {
		return newProjectId;
	}

	public void setNewProjectId(String newProjectId) {
		this.newProjectId = newProjectId;
	}

	public String getNewProjectName() {
		return newProjectName;
	}

	public void setNewProjectName(String newProjectName) {
		this.newProjectName = newProjectName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
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

	
}
