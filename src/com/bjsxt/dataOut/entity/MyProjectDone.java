package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;


@TableName("传统项目手工表")
public class MyProjectDone {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
    private String id;

	@FieldName("消费单流水号")
    private String consumptionId;
	
	@FieldName("消费单流水号中的id")
	private String projectDetailId;
	
	@FieldName("服务单号")
    private String orderId;
	
	@FieldName("服务门店ID")
    private String shopId;
	
	@FieldName("会员卡ID")
    private String memberCardId;
	
	@FieldName("传统项目ID")
    private String projectId;
	
	@FieldName("项目单价")
    private Double projectPrice;
	
	@FieldName("消耗数量")
    private Integer doneNumber;
	
	@FieldName("是否满意")
    private Integer isSatisfied;
	
	@FieldName("单次服务时间")
	private Integer doneServiceTime;
	
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
    
    
    public void fromSuper(Consumption consumption, ProjectDetailConsumption productDetailConsumption, ProjectDone productGet, ProjectDoneDetail productGetDetail) {
    	this.id = productGetDetail.getId();
		this.consumptionId = consumption.getId();
		this.projectDetailId = productGetDetail.getProjectDetailId();
		this.orderId = productGet.getId();
		this.memberCardId = productGet.getMemberCardId();
		this.createUserId = consumption.getCreateUserId();
		this.projectId = productDetailConsumption.getProjectId();
		this.isFail = productGet.getIsFail();
		this.projectPrice = productGetDetail.getPrice();
		this.doneNumber = productGetDetail.getDoneNumber();
		this.isSatisfied = productGetDetail.getIsSatisfied();
		this.doneServiceTime = productGetDetail.getDoneServiceTime();
		this.shopId = productDetailConsumption.getShopId();
		this.remark = productGetDetail.getRemark();
		
		if(consumption.getConsumptionDate()!=null){
			this.bookDate = sf.format(consumption.getConsumptionDate());
		}
		if(productGet.getCreateDate()!=null){
			this.createDate = sf.format(productGet.getCreateDate());
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

	public Integer getIsSatisfied() {
		return isSatisfied;
	}

	public void setIsSatisfied(Integer isSatisfied) {
		this.isSatisfied = isSatisfied;
	}

	public Integer getDoneServiceTime() {
		return doneServiceTime;
	}

	public void setDoneServiceTime(Integer doneServiceTime) {
		this.doneServiceTime = doneServiceTime;
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
    

}
