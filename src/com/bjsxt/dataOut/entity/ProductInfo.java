package com.bjsxt.dataOut.entity;

import java.util.Date;

/**
 * @author Lsenrong
 * @date Apr 28, 2017 2:41:00 PM
 * @Description: TODO(产品基础数据)
 */
public class ProductInfo {
	
    private String id;
    /**
     * 产品编号
     */
    private String no;
    /**
     * 产品名称
     */
    private String name;
    /**
     * 产品散装单位
     */
    private String bulkUnit;
    /**
     * 产品套盒单位
     */
    private String boxesUnit;
    /**
     * 产品类型，0普通产品，1可选产品
     */
    private String type;
    /**
     * 产品状态，0是停用，1是正常,-1是删除
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createDateTime;
    
    //    规格
    private String standard;
    
	//	更新状态：0自动更新 1 手动更新
	private Integer updateStatus;
//	门店可见：0全部可见   1选中可见
	private Integer showStatus;
	
//	0不发快递，1发快递
	private Integer isExpress;
//	0不特价，1特价
	private Integer isSale;
    
//    private Double price;
//    private Double boxesPrice;
    
    
//    private Double marketPrice;
//    
//    private Double promotionPrice;
//    
//    private Double experiencePrice;
//    
//    private Double boxesMarketPrice;
//    
//    private Double boxesPromotionPrice;
//    
//    private Double boxesExperiencePrice;
    
//    有效期/月，从顾客购买的时候开始计算
    private Integer effectiveMonths;

    /**
     * 产品数量
     * @author hcxue
     * @date 2017年6月1日 下午1:14:44
     * @return
     */
    private Integer num;
    
    private String optUserid;

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

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
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

	public Integer getEffectiveMonths() {
		return effectiveMonths;
	}

	public void setEffectiveMonths(Integer effectiveMonths) {
		this.effectiveMonths = effectiveMonths;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getOptUserid() {
		return optUserid;
	}

	public void setOptUserid(String optUserid) {
		this.optUserid = optUserid;
	}
    
}
