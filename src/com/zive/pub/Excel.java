package com.zive.pub;

import java.util.List;
/**
 * Excel
 * @author Lsenrong
 * @date Nov 01, 2017 10:40:35 AM
 * @Description: TODO(描述)
 */
public class Excel {
	/**
	 * excel名称
	 */
    private String name;
    /**
     * excel sheet
     */
    private List<ExcelSheet> sheets;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ExcelSheet> getSheets() {
		return sheets;
	}
	public void setSheets(List<ExcelSheet> sheets) {
		this.sheets = sheets;
	}
    
}
