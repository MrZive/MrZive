package com.zive.dataOut.entity;

public class ProjectInfoMeasure {

	/**
    * id
    */
    private Integer id;

    /**
    * 项目id
    */
    private String projectId;

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
    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
