package com.zive.dataOut.entity;

import java.text.SimpleDateFormat;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;


@TableName("产品取货表")
public class MyProductGet{
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@FieldName("ID")
    private String id;

	@FieldName("流水号")
    private String consumptionId;
	
	@FieldName("消费单号")
    private String consumptionProductId;
	
	@FieldName("消费单中的id")
	private String productDetailId;
	
	@FieldName("取货单号")
    private String orderId;
	
	@FieldName("取货门店ID")
    private String shopId;
	
	@FieldName("会员卡ID")
    private String memberCardId;
	
	@FieldName("家具产品ID")
    private String productId;
	
	@FieldName("产品单价")
    private Double productPrice;
	
	@FieldName("取货数量")
    private Integer productGetNumber;
	
	@FieldName("取货单价")
    private String productGetUnit;
	
	@FieldName("取货日期")
    private String bookDate;
	
	@FieldName("创建用户ID")
    private String createUserId;
	
	@FieldName("创建日期")
    private String createDate;
	
	@FieldName("备注")
    private String remark;

    @FieldName("是否成功")
    private Integer isFail;
    
    

	public void fromSuper(Consumption consumption, ProductDetailConsumption productDetailConsumption, ProductGet productGet, ProductGetDetail productGetDetail) {
		this.consumptionId = consumption.getId();
		this.consumptionProductId = productDetailConsumption.getConsumptionProductId();
		this.createUserId = consumption.getCreateUserId();
		this.id = productGetDetail.getId();
		this.isFail = productGet.getIsFail();
		this.memberCardId = productGet.getMemberCardId();
		this.orderId = productGet.getId();
		this.productDetailId = productGetDetail.getProductDetailId();
		this.productGetNumber = productGetDetail.getNumber();
		this.productGetUnit = productGetDetail.getUnit();
		this.productId = productDetailConsumption.getProductId();
		this.productPrice = productGetDetail.getPrice();
		this.shopId = productDetailConsumption.getShopId();
		
		if(consumption.getConsumptionDate()!=null){
			this.bookDate = sf.format(consumption.getConsumptionDate());
		}
		if(productGet.getCreateDate()!=null){
			this.createDate = sf.format(productGet.getCreateDate());
		}
		
		if(productGetDetail.getRemark()!=null && productGetDetail.getRemark().length()>0){
			this.remark = productGetDetail.getRemark();
		}else if(productGet.getRemark()!=null && productGet.getRemark().length()>0){
			this.remark = productGet.getRemark();
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

	public String getProductDetailId() {
		return productDetailId;
	}

	public void setProductDetailId(String productDetailId) {
		this.productDetailId = productDetailId;
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

	public String getConsumptionProductId() {
		return consumptionProductId;
	}


	public void setConsumptionProductId(String consumptionProductId) {
		this.consumptionProductId = consumptionProductId;
	}


	public String getMemberCardId() {
		return memberCardId;
	}

	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductGetNumber() {
		return productGetNumber;
	}

	public void setProductGetNumber(Integer productGetNumber) {
		this.productGetNumber = productGetNumber;
	}

	public String getProductGetUnit() {
		return productGetUnit;
	}

	public void setProductGetUnit(String productGetUnit) {
		this.productGetUnit = productGetUnit;
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
