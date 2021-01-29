package com.zive.dataOut.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 门店账号，用户账号
 * @author hcxue
 *
 */
public class UserAccountModel implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -3326592381688857364L;

//	id
	private String id;
	
//	名字
	private String name;
	
//	门店编号,或者总部职员的邮箱
	private String accountNo;
	
//	门店创建日期
	private Date createDate;
	
//	角色ID
	private String roleId;
	
//	权限角色名字
	private String roleName;
	
//	当前数据创建人ID
	private String createUserId;
	
//	当前数据创建时间
	private Date createTime;	
	
//	密码
	private String password;
	
//	说明
	private String remark;
	
//	机构
	private String structureId;	
	
	/**
	    * 账号关联仓库id，用###分隔
	    */
	private String storehouseIds;
	/**
	 * 账号关联的自身仓库
	 */
	private String storehouseOwnId;
	private String storehouseOwnName;
	
	private List<Map<String, Object>> storehouses;
	
//	账号分类，0是门店账号，1是职员账号
	private Integer type;
	
//	账号状态，0是正在使用，-1是禁用
	private Integer status;
	
	
//	门店账号，手机
	private String phone;
	
//	手机号码主人ID
	private String phoneUserId;
	
//	门店账号发货地址
	private String address;
	
//	门店电话，快递收货电话号码
	private String shopPhone;
	
//	门店开业时间
	private Date openDate;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}



	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getStructureId() {
		return structureId;
	}

	public void setStructureId(String structureId) {
		this.structureId = structureId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhoneUserId() {
		return phoneUserId;
	}

	public void setPhoneUserId(String phoneUserId) {
		this.phoneUserId = phoneUserId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public String getShopPhone() {
		return shopPhone;
	}

	public void setShopPhone(String shopPhone) {
		this.shopPhone = shopPhone;
	}

	public String getStorehouseIds() {
		return storehouseIds;
	}

	public void setStorehouseIds(String storehouseIds) {
		this.storehouseIds = storehouseIds;
	}

	public List<Map<String, Object>> getStorehouses() {
		return storehouses;
	}

	public void setStorehouses(List<Map<String, Object>> storehouses) {
		this.storehouses = storehouses;
	}

	public String getStorehouseOwnId() {
		return storehouseOwnId;
	}

	public void setStorehouseOwnId(String storehouseOwnId) {
		this.storehouseOwnId = storehouseOwnId;
	}

	public String getStorehouseOwnName() {
		return storehouseOwnName;
	}

	public void setStorehouseOwnName(String storehouseOwnName) {
		this.storehouseOwnName = storehouseOwnName;
	}
	
	
	
}
