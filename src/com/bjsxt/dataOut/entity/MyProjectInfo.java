package com.bjsxt.dataOut.entity;

import java.text.SimpleDateFormat;
import java.util.List;

import com.bjsxt.dataOut.annotaion.FieldName;
import com.bjsxt.dataOut.annotaion.TableName;


@TableName("传统项目")
public class MyProjectInfo {
	
	final SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@FieldName("ID")
	private String id;

	@FieldName("编号")
	private String no;

	@FieldName("名称")
	private String name;

	@FieldName("项目类型：0普通项目，1可选项目")
	private Integer type;

	@FieldName("状态：1是停用，0正常")
	private Integer status;

	@FieldName("有效期/月")
	private Integer effectiveMonths;

	@FieldName("更新状态：0自动更新 1 手动更新")
	private Integer updateStatus;

	@FieldName("门店可见：0全部可见 1选中可见")
	private Integer showStatus;

	@FieldName("0不特价，1特价")
	private Integer isSale;

	@FieldName("是否计算工资：0不 1计")
	private Integer isCalculateSal;

	@FieldName("是否收款：0不 1收")
	private Integer isCome;

	@FieldName("是否计算消耗：0不 1计")
	private Integer isConsume;
	
	@FieldName("创建用户ID")
	private String createUserId;
	
	@FieldName("创建时间")
	private String createDateTime;
	
	
	public void fromSuper(ProjectInfo info){
		this.createUserId = "无此字段";
		this.effectiveMonths = info.getEffectiveMonths();
		this.id = info.getId();
		this.isCalculateSal = info.getIsCalculateSal();
		this.isCome = info.getIsCome();
		this.isConsume = info.getIsConsume();
		this.isSale = info.getIsSale();
		this.name = info.getName();
		this.no = info.getNo();
		this.showStatus = info.getShowStatus();
		this.status = info.getStatus();
		this.type = info.getType();
		this.updateStatus = info.getUpdateStatus();
		
		if(info.getCreateDateTime() != null){
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
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

	public Integer getEffectiveMonths() {
		return effectiveMonths;
	}

	public void setEffectiveMonths(Integer effectiveMonths) {
		this.effectiveMonths = effectiveMonths;
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

	public Integer getIsSale() {
		return isSale;
	}

	public void setIsSale(Integer isSale) {
		this.isSale = isSale;
	}

	public Integer getIsCalculateSal() {
		return isCalculateSal;
	}

	public void setIsCalculateSal(Integer isCalculateSal) {
		this.isCalculateSal = isCalculateSal;
	}

	public Integer getIsCome() {
		return isCome;
	}

	public void setIsCome(Integer isCome) {
		this.isCome = isCome;
	}

	public Integer getIsConsume() {
		return isConsume;
	}

	public void setIsConsume(Integer isConsume) {
		this.isConsume = isConsume;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public SimpleDateFormat getSf() {
		return sf;
	}
	
}
