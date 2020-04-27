package com.zive.dataOut.entity;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;

@TableName("家居产品连锁店配置")
public class MyProductInfoPrice {

	@FieldName("ID")
	private String id;

	@FieldName("门店ID")
	private String shopId;

	@FieldName("产品ID")
	private String productId;
	
	@FieldName("推广价格")
	private Double promotionPrice;

	@FieldName("市场价格")
	private Double marketPrice;
	
	@FieldName("体验价")
	private Double experiencePrice;
	
	@FieldName("盒装推广价格")
	private Double boxesPromotionPrice;

	@FieldName("盒装市场价格")
	private Double boxesMarketPrice;

	@FieldName("盒装体验价")
	private Double boxesExperiencePrice;
	
	@FieldName("创建时间")
    private String createDateTime;

	@FieldName("创建人ID")
    private String createUserId;
	
	
	public void fromSuper(ProductInfoPrice info){
		this.boxesExperiencePrice = info.getBoxesExperiencePrice();
		this.boxesMarketPrice = info.getBoxesMarketPrice();
		this.boxesPromotionPrice = info.getBoxesPromotionPrice();
		this.createDateTime = "无";
		this.createUserId = "无";
		this.experiencePrice = info.getExperiencePrice();
		this.id = info.getId();
		this.marketPrice = info.getMarketPrice();
		this.productId = info.getProductId();
		this.promotionPrice = info.getPromotionPrice();
		this.shopId = info.getShopId();
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public Double getBoxesPromotionPrice() {
		return boxesPromotionPrice;
	}

	public void setBoxesPromotionPrice(Double boxesPromotionPrice) {
		this.boxesPromotionPrice = boxesPromotionPrice;
	}

	public Double getBoxesMarketPrice() {
		return boxesMarketPrice;
	}

	public void setBoxesMarketPrice(Double boxesMarketPrice) {
		this.boxesMarketPrice = boxesMarketPrice;
	}

	public Double getBoxesExperiencePrice() {
		return boxesExperiencePrice;
	}

	public void setBoxesExperiencePrice(Double boxesExperiencePrice) {
		this.boxesExperiencePrice = boxesExperiencePrice;
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
