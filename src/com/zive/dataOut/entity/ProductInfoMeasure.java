package com.bjsxt.dataOut.entity;

public class ProductInfoMeasure {

	/**
    * id
    */
    private String id;

    /**
    * 项目id
    */
    private String productId;

    /**
    * 物料id
    */
    private String materialId;

    /**
    * 消耗数量
    */
    private Double number;

    /**
    * 消耗单位
    */
    private String unit;
    

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
