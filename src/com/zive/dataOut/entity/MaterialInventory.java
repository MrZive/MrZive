package com.zive.dataOut.entity;
/**
 * 物料库存
 * @author Lsenrong
 * @date Jul 14, 2017 11:27:29 AM
 * @Description: TODO(描述)
 */
public class MaterialInventory {
	
	/**
	 * inventoryId
	 */
    private String inventoryId;
    /**
     * 物料id
     */
    private String materialId;
    /**
     * 结存单价
     */
    private Double materialPrice;
    /**
     * 门店id
     */
    private String shopId;
    /**
     * 门店编号
     */
    private String shopNo;
    /**
     * 门店姓名
     */
    private String shopName;
    /**
     * 可用库存
     */
    private Double usableInventory;
    /**
     * 寄存库存
     */
    private Double checkInventory;
    /**
     * 警戒库存线
     */
    private Double alertInventory;
	public String getMaterialId() {
		return materialId;
	}
	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
	public String getShopId() {
		return shopId;
	}
	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	public Double getMaterialPrice() {
		return materialPrice;
	}
	public void setMaterialPrice(Double materialPrice) {
		this.materialPrice = materialPrice;
	}
	public Double getUsableInventory() {
		return usableInventory;
	}
	public void setUsableInventory(Double usableInventory) {
		this.usableInventory = usableInventory;
	}
	public Double getCheckInventory() {
		return checkInventory;
	}
	public void setCheckInventory(Double checkInventory) {
		this.checkInventory = checkInventory;
	}
	public Double getAlertInventory() {
		return alertInventory;
	}
	public void setAlertInventory(Double alertInventory) {
		this.alertInventory = alertInventory;
	}
	public String getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}
	public String getShopNo() {
		return shopNo;
	}
	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
    
}
