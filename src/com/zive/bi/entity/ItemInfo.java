package com.zive.bi.entity;

import java.math.BigDecimal;

import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;

@TableName("所有收款项目金额")
public class ItemInfo {
	
	@FieldName("项目id")
	private String itemId;
	
	@FieldName("项目名称")
	private String itemName;
	
	@FieldName("类型")
	private String type;
	
	@FieldName("收款金额")
	private BigDecimal price;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
