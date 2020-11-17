package com.zive.dataOut.entity;
/**
 * @author Lsenrong
 * @date May 4, 2017 1:56:34 PM
 * @Description: TODO(物料基础信息)
 */
public class MaterialInfo extends MaterialInventory{
	private Boolean haveInventory;
	/**
	 * 序列化之后字符串
	 */
	private String jsonDataStr;
	/**
	 * 唯一id
	 */
    private String id;
    
    /**
     * [用友物料id]
     */
    private String yongyouId;
    
    /**
     * 物料编号
     */
    private String no;
    /**
     * 物料名称
     */
    private String name;
    /**
     * 小单位
     */
    private String smallUnit;
    /**
     * 大单位
     */
    private String bigUnit;
    
//    发货单位，1的小单位，2是大单位
    private Integer deliverUnit;
    
    /**
     * 大单位包含小单位数量
     */
    private Integer number;
    /**
     * 状态，0是停用，1是正常,-1是删除
     */
    private Integer status;
    /**
     * 物料类型
     */
    private String type;
    /**
     * 物料备注
     */
    private String remark;
    
    private Double price;
    
    private Double boxesPrice;
    
    /**
     * 可申请,1是，0不是
     */
    private Integer isApply;
    /**
     * 可盘点,1是，0不是
     */
    private Integer isCheck;
    
    /**
     * 物料规格
     */
    private String form;
    
    private Double tax;
    
    private Double taxPrice;
    
    /**
     * [结存金额]
     */
    private Double balanceAmount;
    
	public String getId() {
		return this.id;
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
	
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSmallUnit() {
		return smallUnit;
	}
	public void setSmallUnit(String smallUnit) {
		this.smallUnit = smallUnit;
	}
	public String getBigUnit() {
		return bigUnit;
	}
	public void setBigUnit(String bigUnit) {
		this.bigUnit = bigUnit;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getBoxesPrice() {
		return boxesPrice;
	}
	public void setBoxesPrice(Double boxesPrice) {
		this.boxesPrice = boxesPrice;
	}
	public Integer getIsApply() {
		return isApply;
	}
	public void setIsApply(Integer isApply) {
		this.isApply = isApply;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public Double getTax() {
		return tax;
	}
	public void setTax(Double tax) {
		this.tax = tax;
	}
	public Double getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(Double taxPrice) {
		this.taxPrice = taxPrice;
	}
	public String getJsonDataStr() {
		return jsonDataStr;
	}
	public void setJsonDataStr(String jsonDataStr) {
		this.jsonDataStr = jsonDataStr;
	}
	public Boolean getHaveInventory() {
		return haveInventory;
	}
	public void setHaveInventory(Boolean haveInventory) {
		this.haveInventory = haveInventory;
	}
	public Integer getDeliverUnit() {
		return deliverUnit;
	}
	public void setDeliverUnit(Integer deliverUnit) {
		this.deliverUnit = deliverUnit;
	}
	public Double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(Double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public String getYongyouId() {
		return yongyouId;
	}
	public void setYongyouId(String yongyouId) {
		this.yongyouId = yongyouId;
	}
}
