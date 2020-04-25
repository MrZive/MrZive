package com.bjsxt.dataOut.entity;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;

@TableName("家居产品耗材")
public class MyProductInfoMeasure {

	@FieldName("ID")
    private String id;

	@FieldName("产品ID")
    private String productId;

	@FieldName("物料ID")
    private String materialId;

	@FieldName("消耗数量")
    private Double number;

	@FieldName("消耗单位")
    private String unit;
	
	public void fromSuper(ProductInfoMeasure info){
		this.id = info.getId();
		this.materialId = info.getMaterialId();
		this.number = info.getNumber();
		this.productId = info.getProductId();
		this.unit = info.getUnit();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public Double getNumber() {
		return number;
	}

	public void setNumber(Double number) {
		this.number = number;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
    
}
