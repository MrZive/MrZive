package com.bjsxt.pub;

import java.util.Date;

/**
 * 门店基本信息
 * @author Lsenrong
 * @date Oct 20, 2017 11:50:57 AM
 * @Description: TODO(描述)
 */
public class ShopInfo {
	/**
	 * 唯一id
	 */
    private String id;
    /**
     * 门店编号
     */
    private String no;
    /**
     * 门店名称
     */
    private String name;
    /**
     * 门店状态
     */
    private Integer status;
    /**
     * 门店区域
     */
    private String region;
    /**
     * 门店地址
     */
    private String address;
    /**
     * 开业日期
     */
    private Date openDate;
    /**
     * 是否门店机构 
     */
    private Integer isShop;
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
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
	public Integer getIsShop() {
		return isShop;
	}
	public void setIsShop(Integer isShop) {
		this.isShop = isShop;
	}
    
}
