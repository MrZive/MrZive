package com.bjsxt.dataOut.entity;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;

@TableName("项目耗材")
public class MyProjectInfoMeasure {

	@FieldName("ID")
    private Integer id;

	@FieldName("项目ID")
    private String projectId;

	@FieldName("物料ID")
    private String materialId;

	@FieldName("消耗数量")
    private Double number;

	@FieldName("消耗单位")
    private String unit;
	
	public void fromSuper(ProjectInfoMeasure info){
		this.id = info.getId();
		this.materialId = info.getMaterialId();
		this.number = info.getNumber();
		this.projectId = info.getProjectId();
		this.unit = info.getUnit();
	}

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
