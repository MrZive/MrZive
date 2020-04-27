package com.zive.pub;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Lsenrong
 * @date Nov 01, 2017 10:40:54 AM
 * @Description: TODO(描述)
 */
public class ExcelSheet {
	/**
	 * sheet 名称
	 */
    private String name;
    /**
     * 行数据
     */
    private List<ExcelRow> rows;
    /**
     * 列数统计
     */
    private Integer colCount;
    /**
     * 自动调节列宽
     */
    private Boolean autoSizeColumn = true;
    /**
     * 设置默认列宽度
     */
    private Integer defaultColumnWidth = 5000;
    /**
     * 设置列宽
     */
    private Map<Integer, Integer> colunmWidth = new HashMap<Integer,Integer>();
    /**
     * 冻结窗口 长度为4的整型数组 
     * 第一个参数表示要冻结的列数；
     * 第二个参数表示要冻结的行数，这里只冻结列所以为0；
     * 第三个参数表示右边区域可见的首列序号，从1开始计算；
     * 第四个参数表示下边区域可见的首行序号，也是从1开始计算，这里是冻结列，所以为0；
     * 例如冻结表格第一行 0, 1, 0, 1 
     * 冻结第一列1, 0, 1, 0
     */
    private int[] freezePane;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<ExcelRow> getRows() {
		return rows;
	}
	public void setRows(List<ExcelRow> rows) {
		this.rows = rows;
	}
	public Integer getColCount() {
		return colCount;
	}
	public void setColCount(Integer colCount) {
		this.colCount = colCount;
	}
	public Boolean getAutoSizeColumn() {
		return autoSizeColumn;
	}
	public void setAutoSizeColumn(Boolean autoSizeColumn) {
		this.autoSizeColumn = autoSizeColumn;
	}
	public Integer getDefaultColumnWidth() {
		return defaultColumnWidth;
	}
	public void setDefaultColumnWidth(Integer defaultColumnWidth) {
		this.defaultColumnWidth = defaultColumnWidth;
	}
	public Map<Integer, Integer> getColunmWidth() {
		return colunmWidth;
	}
	public void setColunmWidth(Map<Integer, Integer> colunmWidth) {
		this.colunmWidth = colunmWidth;
	}
	public int[] getFreezePane() {
		return freezePane;
	}
	public void setFreezePane(int[] freezePane) {
		this.freezePane = freezePane;
	}
	
    
}
