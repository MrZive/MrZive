package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;


@TableName("合作项目服务表")
public class MyProjectCooperationDone {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
    private String id;

	@FieldName("流水单号")
    private String consumptionId;
	
	@FieldName("消费单号")
    private String consumptionProjectId;
	
	@FieldName("消费单中的id")
	private String projectDetailId;
	
	@FieldName("服务单号")
    private String orderId;
	
	@FieldName("服务门店ID")
    private String shopId;
	
	@FieldName("会员卡ID")
    private String memberCardId;
	
	@FieldName("合作项目ID")
    private String projectId;
	
	@FieldName("项目单价")
    private Double projectPrice;
	
	@FieldName("服务数量")
    private Integer doneNumber;
	
	@FieldName("服务公司")
    private String serviceCompany;
	
	@FieldName("服务日期")
    private String bookDate;
	
	@FieldName("创建用户ID")
    private String createUserId;
	
	@FieldName("创建日期")
    private String createDate;
	
	@FieldName("备注")
    private String remark;

    @FieldName("是否成功")
    private Integer isFail;
    
    
    public void fromSuper(Consumption consumption, ProjectCooperationDetailConsumption productDetailConsumption, ProjectCooperationDone productDone) {
    	this.id = "无";
		this.consumptionId = consumption.getId();
		this.consumptionProjectId = productDetailConsumption.getConsumptionCooperationId();
		this.projectDetailId = productDone.getDetailId();
		this.orderId = productDone.getId();
		this.memberCardId = consumption.getMemberCardId();
		this.createUserId = consumption.getCreateUserId();
		this.projectId = productDetailConsumption.getCooperationId();
		this.isFail = productDone.getIsFail();
//		this.projectPrice = productDone.getPrice();
		this.projectPrice = productDetailConsumption.getPrice();
		this.doneNumber = productDone.getServiceNumber();
		this.shopId = productDone.getServiceShopId();
		this.serviceCompany = productDone.getServiceCompany();
		this.remark = productDone.getRemark();
		
		if(consumption.getConsumptionDate()!=null){
			this.bookDate = sf.format(consumption.getConsumptionDate());
		}
		if(productDone.getCreateDate()!=null){
			this.createDate = sf.format(productDone.getCreateDate());
		}
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

	public String getProjectDetailId() {
		return projectDetailId;
	}

	public void setProjectDetailId(String projectDetailId) {
		this.projectDetailId = projectDetailId;
	}

	public String getConsumptionProjectId() {
		return consumptionProjectId;
	}

	public void setConsumptionProjectId(String consumptionProjectId) {
		this.consumptionProjectId = consumptionProjectId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Double getProjectPrice() {
		return projectPrice;
	}

	public void setProjectPrice(Double projectPrice) {
		this.projectPrice = projectPrice;
	}

	public Integer getDoneNumber() {
		return doneNumber;
	}

	public void setDoneNumber(Integer doneNumber) {
		this.doneNumber = doneNumber;
	}

	public String getServiceCompany() {
		return serviceCompany;
	}

	public void setServiceCompany(String serviceCompany) {
		this.serviceCompany = serviceCompany;
	}

	public String getBookDate() {
		return bookDate;
	}

	public void setBookDate(String bookDate) {
		this.bookDate = bookDate;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public SimpleDateFormat getSf() {
		return sf;
	}

}
