package com.bjsxt.pojo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 鐢ㄤ簬BI绯荤粺瀵煎嚭鑷畾涔変骇鍝佺殑閿�鍞儏鍐电粦瀹氱殑涓氱哗浜哄憳鍒楄〃
 * @author WangZive
 * @date: 2019骞�6鏈�13鏃� 涓婂崍11:47:36
 */
public class CustomProductSalesEmployeeEarn {
	
	public final static HashMap<Integer, String> getEarnType = new HashMap<Integer, String>(){
		private static final long serialVersionUID = 1L;
		{
        	put(3, "卖产品业绩");
    		put(8, "还产品业绩");
    		put(27, "退项目业绩");
    		put(28, "退产品业绩");
    		put(31, "退至储值");
    		put(81, "卖套餐业绩");
        }
    };

	private String id;
	
	private BigDecimal earn;
	
	private Integer earnType;
	
	private BigDecimal storeEarn;
	
	private Integer isFail;
	
	private String detailId;
	
	private String employeeId;
	
	private String employeeNo;
	
	private String employeeName;
	
	private String gender;
	
	private String phone;
	
	private Date entryDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getEarn() {
		return earn;
	}

	public void setEarn(BigDecimal earn) {
		this.earn = earn;
	}

	public Integer getEarnType() {
		return earnType;
	}

	public void setEarnType(Integer earnType) {
		this.earnType = earnType;
	}

	public BigDecimal getStoreEarn() {
		return storeEarn;
	}

	public void setStoreEarn(BigDecimal storeEarn) {
		this.storeEarn = storeEarn;
	}

	public Integer getIsFail() {
		return isFail;
	}

	public void setIsFail(Integer isFail) {
		this.isFail = isFail;
	}

	public String getDetailId() {
		return detailId;
	}

	public void setDetailId(String detailId) {
		this.detailId = detailId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeNo() {
		return employeeNo;
	}

	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	
}
