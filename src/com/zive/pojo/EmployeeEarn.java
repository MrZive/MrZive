package com.zive.pojo;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 业绩
 * @author Lsenrong
 * @date Dec 13, 2017 2:41:18 PM
 * @Description: TODO(描述)
 */
public class EmployeeEarn {
	private String id;
	/**
	 * 业绩归属门店;
	 */
    private String resultStructureId;
    
    private String detailId;
    private String returnId;
    /**
     * 员工所在的现门店;
     */
    private String employeeStructureId;
    /**
	 * 业绩归属门店;
	 */
    private String resultStructureName;
    /**
     * 员工所在的现门店;
     */
    private String employeeStructureName;
    /**
     * 员工id;
     */
    private String employeeId;
    /**
     * 员工姓名;
     */
    private String employeeName;
    /**
     * 流水号
     */
    private String businessId;
    /**
     * 单据号
     */
    private String orderId;
    /**
     * 业绩
     */
    private BigDecimal earn;
    /**
     * 业绩类型
     */
    private Integer resultType;
    /**
     * 是否作废
     */
    private Integer isFail;
    /**
     * 消费日期
     */
    private Date consumptionDate;
    /**
     * 储值（..根据同事设计在三种支付方式中  储值独立存储在此字段针对刷卡和现金存放在 earn中  ）
     */
    private BigDecimal storeEarn;
    /**
     * 退产品金额（...在退项目或产品）
     */
    private BigDecimal refundProductEarn;
    /**
     * 退项目金额 （...在退项目或产品）
     */
    private BigDecimal refundProjectEarn;
    /**
     * 比例
     */
    private Double percent;
	public String getResultStructureId() {
		return resultStructureId;
	}
	public void setResultStructureId(String resultStructureId) {
		this.resultStructureId = resultStructureId;
	}
	public String getEmployeeStructureId() {
		return employeeStructureId;
	}
	public void setEmployeeStructureId(String employeeStructureId) {
		this.employeeStructureId = employeeStructureId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public BigDecimal getEarn() {
		return earn;
	}
	public void setEarn(BigDecimal earn) {
		this.earn = earn;
	}
	public Integer getResultType() {
		return resultType;
	}
	public void setResultType(Integer resultType) {
		this.resultType = resultType;
	}
	public Integer getIsFail() {
		return isFail;
	}
	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}
	public Date getConsumptionDate() {
		return consumptionDate;
	}
	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
	}
	public BigDecimal getStoreEarn() {
		return storeEarn;
	}
	public void setStoreEarn(BigDecimal storeEarn) {
		this.storeEarn = storeEarn;
	}
	public BigDecimal getRefundProductEarn() {
		return refundProductEarn;
	}
	public void setRefundProductEarn(BigDecimal refundProductEarn) {
		this.refundProductEarn = refundProductEarn;
	}
	public BigDecimal getRefundProjectEarn() {
		return refundProjectEarn;
	}
	public void setRefundProjectEarn(BigDecimal refundProjectEarn) {
		this.refundProjectEarn = refundProjectEarn;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getResultStructureName() {
		return resultStructureName;
	}
	public void setResultStructureName(String resultStructureName) {
		this.resultStructureName = resultStructureName;
	}
	public String getEmployeeStructureName() {
		return employeeStructureName;
	}
	public void setEmployeeStructureName(String employeeStructureName) {
		this.employeeStructureName = employeeStructureName;
	}
	public Double getPercent() {
		return percent;
	}
	public void setPercent(Double percent) {
		this.percent = percent;
	}
	public String getDetailId() {
		return detailId;
	}
	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}
	public String getReturnId() {
		return returnId;
	}
	public void setReturnId(String returnId) {
		this.returnId = returnId;
	}
	
	
	
    
    
}
