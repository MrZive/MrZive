package com.zive.dataOut.entity;

import java.text.SimpleDateFormat;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;

/**
 * @Description: TODO(产品基础数据)
 */
@TableName("家居产品")
public class MyProductInfo {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@FieldName("ID")
    private String id;

	@FieldName("编号")
    private String no;

	@FieldName("名称")
    private String name;
	
	@FieldName("产品类型，0普通产品，1可选产品")
    private String type;

	@FieldName("产品状态，0是停用，1是正常，-1是删除")
    private Integer status;
	
	@FieldName("有效期")
    private Integer effectiveMonths;

	@FieldName("规格")
    private String standard;
	
	@FieldName("产品数量")
    private Integer num;

	@FieldName("散装单位")
    private String bulkUnit;

	@FieldName("套盒单位")
    private String boxesUnit;

	@FieldName("更新状态：0自动更新 1 手动更新")
    private Integer updateStatus;

	@FieldName("门店可见：0全部可见  1选中可见")
    private Integer showStatus;

	@FieldName("0不特价，1特价")
    private Integer isSale;
	
	@FieldName("0不发快递，1发快递")
    private Integer isExpress;
	
	@FieldName("创建时间")
    private String createDateTime;
	
	
	public void fromSuper(ProductInfo info){
		this.boxesUnit = info.getBoxesUnit();
		this.bulkUnit = info.getBulkUnit();
		this.effectiveMonths = info.getEffectiveMonths();
		this.id = info.getId();
		this.isExpress = info.getIsExpress();
		this.isSale = info.getIsSale();
		this.name = info.getName();
		this.no = info.getNo();
		this.num = info.getNum();
		this.showStatus = info.getShowStatus();
		this.standard = info.getStandard();
		this.status = info.getStatus();
		this.type = info.getType();
		this.updateStatus = info.getUpdateStatus();
		
		if(info.getCreateDateTime()!=null){
			this.createDateTime = sf.format(info.getCreateDateTime());
		}
	}
    

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBulkUnit() {
		return bulkUnit;
	}

	public void setBulkUnit(String bulkUnit) {
		this.bulkUnit = bulkUnit;
	}

	public String getBoxesUnit() {
		return boxesUnit;
	}

	public void setBoxesUnit(String boxesUnit) {
		this.boxesUnit = boxesUnit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getEffectiveMonths() {
		return effectiveMonths;
	}

	public void setEffectiveMonths(Integer effectiveMonths) {
		this.effectiveMonths = effectiveMonths;
	}

	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}

	public Integer getUpdateStatus() {
		return updateStatus;
	}

	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}

	public Integer getShowStatus() {
		return showStatus;
	}

	public void setShowStatus(Integer showStatus) {
		this.showStatus = showStatus;
	}

	public Integer getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(Integer isExpress) {
		this.isExpress = isExpress;
	}

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}
}
