package com.zive.bi.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 员工业绩
 * @author Lsenrong
 * @date Dec 15, 2017 3:21:36 PM
 * @Description: TODO(描述)
 */
public class NewEarn {
	public static enum Type{
		//在人事薪酬中没有针对套餐设置的业绩 在卖还环节兼容（套餐分拆至项目或产品）
		RechargeSellProjectSet,
		RechargeSellProductSet,
		RechargeSellCooperationSet,
		RechargeSellWenxiuSet,
		RechargeReturnProjectSet,
		RechargeReturnProductSet,
		SellBuyProjectSet,
		SellBuyProductSet,
		SellBuyCooperationSet,
		SellBuyWenxiuSet,
		SellReturnProjectSet,
		SellReturnProductSet,
		
		/**
		 * 购买项目产生充值
		 */
		RechargeSellProject,
		/**
		 * 购买产品产生充值
		 */
		RechargeSellProduct,
		/**
		 * 购买套餐产生充值
		 */
		//RechargeSellSet,
		/**
		 * 购买合作项目产生充值
		 */
		RechargeSellCooperation,
		/**
		 * 购买合作项目产生充值(采用储值需扣业绩)
		 */
		RechargeSellCooperationStore,
		
		/**
		 * 还项目产生充值
		 */
		RechargeReturnProject,
		/**
		 * 还产品产生充值
		 */
		RechargeReturnProduct,
		/**
		 * 还套餐产生充值
		 */
		//RechargeReturnSet,
		/**
		 * 还合作项目产生充值
		 */
		RechargeReturnCooperation,
		
		/**
		 * 还合作项目产生充值(采用储值需扣业绩)
		 */
		RechargeReturnCooperationStore,
		
		/**
		 * 替换前项目产生业绩
		 */
		RechargeExchangeFromProject,
		/**
		 * 替换前产品产生业绩
		 */
		RechargeExchangeFromProduct,
		/**
		 * 替换前合作项目产生业绩
		 */
		RechargeExchangeFromCooperation,
		/**
		 * 替换后项目产生业绩
		 */
		RechargeExchangeToProject,
		/**
		 * 替换后产品产生业绩
		 */
		RechargeExchangeToProduct,
		/**
		 * 替换后合作项目产生业绩
		 */
		RechargeExchangeToCooperation,
		/**
		 * 替换后合作项目产生业绩(采用储值需扣业绩)
		 */
		RechargeExchangeToCooperationStore,
		/**
		 * 换购后剩余金额退回储值业绩
		 */
		RechargeExchangeToStore,
		/**
		 * 退项目产生负业绩
		 */
		RechargeRefundProject,
		/**
		 * 退产品产生负业绩
		 */
		RechargeRefundProduct,
		/**
		 * 退合作项目产生负业绩
		 */
		RechargeRefundCooperation,
		/**
		 * 退至储值
		 */
		RechargeRefundToStore,
		/**
		 * 退至储值--纹绣
		 */
		RechargeRefundToStoreOfWenXiu,
		/**
		 * 退至储值--合作项目
		 */
		RechargeRefundToStoreOfCooperation,
		/**
		 * 退掉储值
		 */
		RechargeRefundFromStore,
		/**
		 * 卖项目业绩
		 */
		SellBuyProject,
		/**
		 * 卖产品业绩
		 */
		SellBuyProduct,
		/**
		 * 卖套餐业绩
		 */
		//SellBuySet,
		/**
		 * 卖合作项目业绩
		 */
		SellBuyCooperation,
		/**
		 * 还项目业绩
		 */
		SellReturnProject,
		/**
		 * 还产品业绩
		 */
		SellReturnProduct,
		/**
		 * 还套餐业绩
		 */
		//SellReturnSet,
		/**
		 * 还合作项目业绩
		 */
		SellReturnCooperation,
		/**
		 * 替换项目前业绩
		 */
		SellExchangeFromProject,
		/**
		 * 替换产品前业绩
		 */
		SellExchangeFromProduct,
		/**
		 * 替换合作项目前业绩
		 */
		SellExchangeFromCooperation,
		/**
		 * 替换项目后业绩
		 */
		SellExchangeToProject,
		/**
		 * 替换产品后业绩
		 */
		SellExchangeToProduct,
		/**
		 * 替换合作项目后业绩
		 */
		SellExchangeToCooperation,
		/**
		 * 退项目业绩
		 */
		SellRefundProject,
		/**
		 * 退产品业绩
		 */
		SellRefundProduct,
		/**
		 * 退合作项目业绩
		 */
		SellRefundCooperation,
		
		/**
		 * 做项目工时业绩
		 */
		ConsumeDoneProjectTime,
		/**
		 * 做项目时价值业绩
		 */
		ConsumeDoneProjectWorth,
		/**
		 * 取产品业绩
		 */
		ConsumePickProduct,
		/**
		 * 做合作项目工时业绩
		 */
		ConsumeDoneCooperationTime,
		/**
		 * 做合作项目价值业绩
		 */
		ConsumeDoneCooperationWorth,
		/**
		 * 拓客业绩
		 */
		ExpandMember,
		
		
		
		
		
		
		/*-----------------------------针对5月1-----------------------*/
		/**
		 * 购买非纹绣合作项目，或者后续做服务产生业绩（5月1起非纹绣项目购买给9成1成按购买次数均价分次返还）
		 */
		RechargeSellCooperationNotWenXiuBuyPercent,
		/**
		 * 购买非纹绣合作项目，或者后续做服务产生业绩（5月1起非纹绣项目购买给9成1成按购买次数均价分次返还）
		 */
		RechargeSellCooperationNotWenXiuDonePercent,
		/**
		 * 购买非纹绣合作项目产生充值(采用储值需扣业绩)（5月1起非纹绣项目购买给9成1成按购买次数均价分次返还）
		 */
		RechargeSellCooperationStoreNotWenXiuBuyPercent,
		/**
		 * 购买非纹绣合作项目产生充值(采用储值需扣业绩)（5月1起非纹绣项目购买给9成1成按购买次数均价分次返还）
		 */
		RechargeSellCooperationStoreNotWenXiuDonePercent,
		/**
		 * 还非纹绣合作项目产生充值
		 */
		RechargeReturnCooperationNotWenXiu,
		/**
		 * 还非纹绣合作项目产生充值(采用储值需扣业绩)
		 */
		RechargeReturnCooperationStoreNotWenXiu,
		/**
		 * 退非纹绣合作项目产生负业绩
		 */
		RechargeRefundCooperationNotWenXiu,
		/**
		 * 退绣合作项目产生负业绩
		 */
		RechargeRefundCooperationWenXiu
		
	}
	private Type earnType;
	private String id;
	private String detailId;
	private String returnId;
	private String consumptionOrderId;
	private String serialOrderId;
	private String earnStructureId;
	private String earnStructureName;
	private String employeeStructureId;
	private String employeeStructureName;
	private String employeeId;
	private String employeeName;
	private BigDecimal earn;
	private BigDecimal refundProductEarn;
	private BigDecimal refundProjectEarn;
	private Date consumptionDate;
	public Type getEarnType() {
		return earnType;
	}
	
	public void setEarnType(Type earnType) {
		this.earnType = earnType;
	}
	public String getConsumptionOrderId() {
		return consumptionOrderId;
	}
	public void setConsumptionOrderId(String consumptionOrderId) {
		this.consumptionOrderId = consumptionOrderId;
	}
	public String getSerialOrderId() {
		return serialOrderId;
	}
	public void setSerialOrderId(String serialOrderId) {
		this.serialOrderId = serialOrderId;
	}
	public String getEarnStructureId() {
		return earnStructureId;
	}
	public void setEarnStructureId(String earnStructureId) {
		this.earnStructureId = earnStructureId;
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
	public BigDecimal getEarn() {
		return earn;
	}
	public void setEarn(BigDecimal earn) {
		this.earn = earn;
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
		this.refundProjectEarn = refundProductEarn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEarnStructureName() {
		return earnStructureName;
	}

	public void setEarnStructureName(String earnStructureName) {
		this.earnStructureName = earnStructureName;
	}

	public String getEmployeeStructureName() {
		return employeeStructureName;
	}

	public void setEmployeeStructureName(String employeeStructureName) {
		this.employeeStructureName = employeeStructureName;
	}

	public Date getConsumptionDate() {
		return consumptionDate;
	}

	public void setConsumptionDate(Date consumptionDate) {
		this.consumptionDate = consumptionDate;
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
