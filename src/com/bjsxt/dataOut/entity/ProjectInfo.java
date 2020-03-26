package com.bjsxt.dataOut.entity;

import java.util.Date;
import java.util.List;

/**
 * @author Lsenrong
 * @date Apr 28, 2017 2:39:05 PM
 * @Description: TODO(查询项目的基础信息)
 */
public class ProjectInfo {
	
	private String id;
	/**
	 * 项目编号
	 */
	private String no;
	/**
	 * 项目名称
	 */
	private String name;
	
	/**
	 * 项目类型，0普通项目，1可选项目
	 */
	private Integer type;
	/**
	 * 项目状态
	 */
	private Integer status;
	
//	更新状态：0自动更新 1 手动更新
	private Integer updateStatus;
//	手工分享业绩类型：0价值 1 手工
	private Integer sharingType;
//	门店可见：0全部可见   1选中可见
	private Integer showStatus;
	
	
//	0不特价，1特价
	private Integer isSale;
	
	//是否计算工资：0不 1计
	private Integer isCalculateSal;
	//是否收款：0不 1收
	private Integer isCome;
	//是否计算消耗：0不 1计
	private Integer isConsume;
	/**
	 * 创建时间
	 */
	private Date createDateTime;
	
	private String optUserId;//操作人id
	
    
	//  有效期/月，从顾客购买的时候开始计算
	private Integer effectiveMonths;


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


	public Integer getUpdateStatus() {
		return updateStatus;
	}


	public void setUpdateStatus(Integer updateStatus) {
		this.updateStatus = updateStatus;
	}


	public Integer getSharingType() {
		return sharingType;
	}


	public void setSharingType(Integer sharingType) {
		this.sharingType = sharingType;
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


	public Date getCreateDateTime() {
		return createDateTime;
	}


	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}


	public String getOptUserId() {
		return optUserId;
	}


	public void setOptUserId(String optUserId) {
		this.optUserId = optUserId;
	}


	public Integer getEffectiveMonths() {
		return effectiveMonths;
	}


	public void setEffectiveMonths(Integer effectiveMonths) {
		this.effectiveMonths = effectiveMonths;
	}
	
}
