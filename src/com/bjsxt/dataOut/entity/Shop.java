package com.bjsxt.dataOut.entity;

import java.util.Date;

public class Shop {
    /**
     * id
     */
     private String id;

     /**
     * 部门编号
     */
     private String no;

     /**
     * 部门名称
     */
     private String name;
     private String nameLike;
     private String nameNotLike;

     /**
     * 父部门id
     */
     private String parentId;

     /**
     * 部门状态，0是在使用，1是关闭状态
     */
     private Integer status;

     /**
     * create_date_time
     */
     private Date createDateTime;

     /**
     * create_user_id
     */
     private String createUserId;

     /**
     * 区别码，1是门店机构，0是非门店机构
     */
     private Integer isShop;

     /**
     * region
     */
     private String region;

     /**
     * chief_id
     */
     private String chiefId;

     /**
     * address
     */
     private String address;

     /**
     * 是否在7号提示过门店没有排班，1已提示，0没有提示
     */
     private Integer isScheduleAlert;

     /**
     * region_id
     */
     private Integer regionId;
     
     

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

	public String getNameLike() {
		return nameLike;
	}

	public void setNameLike(String nameLike) {
		this.nameLike = nameLike;
	}

	public String getNameNotLike() {
		return nameNotLike;
	}

	public void setNameNotLike(String nameNotLike) {
		this.nameNotLike = nameNotLike;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Integer getIsShop() {
		return isShop;
	}

	public void setIsShop(Integer isShop) {
		this.isShop = isShop;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getChiefId() {
		return chiefId;
	}

	public void setChiefId(String chiefId) {
		this.chiefId = chiefId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getIsScheduleAlert() {
		return isScheduleAlert;
	}

	public void setIsScheduleAlert(Integer isScheduleAlert) {
		this.isScheduleAlert = isScheduleAlert;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}
    
}
