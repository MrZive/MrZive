package com.zive.dataOut.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账号消费历史
 * @author hcxue
 *
 */
public class HistoryConsumption {

	private String id;
	
	private String memberCardId;
	
	private String consumptionId;
	
//	原来的储值账户
	private BigDecimal storeBefore;
//	原来的积分账户
	private BigDecimal pointBefore;
//	原来的疗程账户
	private BigDecimal treatmentBefore;
//	原来的存货余额
	private BigDecimal stockBefore;
	
	private Integer isFail;
	
	private BigDecimal oweBefore;
	private BigDecimal wenxiuBefore;
	private BigDecimal wenxiuAfter;
//	消费后的储值账户
	private BigDecimal storeAfter;
//	消费后的积分账户
	private BigDecimal pointAfter;
//	消费后的疗程账户
	private BigDecimal treatmentAfter;
//	消费后的存货余额
	private BigDecimal stockAfter;
	
	private BigDecimal oweAfter;
	
//	创建日期
	private Date createDate;
	
	
	private String keyword;
	private String beginDate;
	private String endDate;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMemberCardId() {
		return memberCardId;
	}
	public void setMemberCardId(String memberCardId) {
		this.memberCardId = memberCardId;
	}
	public String getConsumptionId() {
		return consumptionId;
	}
	public void setConsumptionId(String consumptionId) {
		this.consumptionId = consumptionId;
	}
	public BigDecimal getStoreBefore() {
		return storeBefore;
	}
	public void setStoreBefore(BigDecimal storeBefore) {
		this.storeBefore = storeBefore;
	}
	public Integer getIsFail() {
		return isFail;
	}
	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}
	public BigDecimal getPointBefore() {
		return pointBefore;
	}
	public void setPointBefore(BigDecimal pointBefore) {
		this.pointBefore = pointBefore;
	}
	public BigDecimal getTreatmentBefore() {
		return treatmentBefore;
	}
	public void setTreatmentBefore(BigDecimal treatmentBefore) {
		this.treatmentBefore = treatmentBefore;
	}
	public BigDecimal getStockBefore() {
		return stockBefore;
	}
	public void setStockBefore(BigDecimal stockBefore) {
		this.stockBefore = stockBefore;
	}
	public BigDecimal getStoreAfter() {
		return storeAfter;
	}
	public void setStoreAfter(BigDecimal storeAfter) {
		this.storeAfter = storeAfter;
	}
	public BigDecimal getPointAfter() {
		return pointAfter;
	}
	public void setPointAfter(BigDecimal pointAfter) {
		this.pointAfter = pointAfter;
	}
	public BigDecimal getTreatmentAfter() {
		return treatmentAfter;
	}
	public void setTreatmentAfter(BigDecimal treatmentAfter) {
		this.treatmentAfter = treatmentAfter;
	}
	public BigDecimal getStockAfter() {
		return stockAfter;
	}
	public void setStockAfter(BigDecimal stockAfter) {
		this.stockAfter = stockAfter;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getOweBefore() {
		return oweBefore;
	}
	public void setOweBefore(BigDecimal oweBefore) {
		this.oweBefore = oweBefore;
	}
	public BigDecimal getOweAfter() {
		return oweAfter;
	}
	public void setOweAfter(BigDecimal oweAfter) {
		this.oweAfter = oweAfter;
	}

	public BigDecimal getWenxiuBefore() {
		return wenxiuBefore;
	}

	public void setWenxiuBefore(BigDecimal wenxiuBefore) {
		this.wenxiuBefore = wenxiuBefore;
	}

	public BigDecimal getWenxiuAfter() {
		return wenxiuAfter;
	}

	public void setWenxiuAfter(BigDecimal wenxiuAfter) {
		this.wenxiuAfter = wenxiuAfter;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
