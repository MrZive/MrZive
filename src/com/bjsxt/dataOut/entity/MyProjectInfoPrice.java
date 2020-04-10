package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.bjsxt.dataOut.annotaion.TableName;
import com.bjsxt.dataOut.annotaion.FieldName;


@TableName("项目连锁店配置")
public class MyProjectInfoPrice {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
    private Integer id;

	@FieldName("门店ID")
    private String shopId;

	@FieldName("项目ID")
    private String projectId;
	
	@FieldName("服务时长")
    private Double serviceTime;

	@FieldName("推广价格")
    private Double promotionPrice;

	@FieldName("市场价格")
    private Double marketPrice;

	@FieldName("体验价")
    private Double experiencePrice;
	
	@FieldName("创建时间")
    private String createDateTime;

	@FieldName("创建人ID")
    private String createUserId;
	
	
	public void fromSuper(ProjectInfoPrice info){
		this.createUserId = info.getCreateUserId();
		this.experiencePrice = info.getExperiencePrice();
		this.id = info.getId();
		this.marketPrice = info.getMarketPrice();
		this.projectId = info.getProjectId();
		this.promotionPrice = info.getPromotionPrice();
		this.serviceTime = info.getServiceTime();
		this.shopId = info.getShopId();
		
		if(info.getCreateDateTime() != null){
			this.createDateTime = sf.format(info.getCreateDateTime());
		}
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Double getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(Double serviceTime) {
		this.serviceTime = serviceTime;
	}

	public Double getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(Double promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Double getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(Double marketPrice) {
		this.marketPrice = marketPrice;
	}

	public Double getExperiencePrice() {
		return experiencePrice;
	}

	public void setExperiencePrice(Double experiencePrice) {
		this.experiencePrice = experiencePrice;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
}
