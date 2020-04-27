package com.zive.pub;

import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.VerticalAlignment;

/**
 * 
 * @author Lsenrong
 * @date Nov 01, 2017 10:41:20 AM
 * @Description: TODO(描述)
 */
public class ExcelCell {
    /**
     * 表格类型
     */
    private CellType cellType;
    /**
     * 值
     */
    private Object value;
    /**
     * 数据格式
     */
    private String dataFormat;
    /**
     * 背景颜色
     *//*
    private String backgroundHexStr = "#FFFFFF";
    *//**
     * 字体颜色
     *//*
    private String colorHexStr = "#000000";*/
    /**
     * 背景颜色
     */
    private short background;
    /**
     * 字体颜色
     */
    private short color;
    /**
     * 字体颜色
     */
    private String fontName;
    /**
     * 水平对齐方式
     */
    private HorizontalAlignment align;
    /**
     * 垂直对齐方式
     */
    private VerticalAlignment vertical;
    /**
     * 字体加粗
     */
    private Boolean bold;
    /**
     * 列合并
     */
    private Integer colspan = 1;
    /**
     * 行合并
     */
    private Integer rowspan = 1;
    /**
     * 边框
     */
    private Boolean border = true;
    /**
     * 自动换行
     */
    private Boolean wrapText = false;
    /**
     * 合并开始行位置
     */
    private Integer firstRow;
    /**
     * 合并开始列坐标
     */
    private Integer firstCol;
    public HorizontalAlignment getAlign() {
		return align;
	}
	public void setAlign(HorizontalAlignment align) {
		this.align = align;
	}
	public VerticalAlignment getVertical() {
		return vertical;
	}
	public void setVertical(VerticalAlignment vertical) {
		this.vertical = vertical;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public void setValue(String value) {
		this.cellType = CellType.STRING;
		this.value = value;
	}
	public void setValue(Double value) {
		this.cellType = CellType.NUMERIC;
		this.value = value;
	}
	public void setValue(Boolean value) {
		this.cellType = CellType.BOOLEAN;
		this.value = value;
	}
	public void setValue(Date value) {
		this.cellType = CellType.NUMERIC;
		this.value = value;
		this.dataFormat = this.dataFormat == null ? "yyyy-MM-dd HH:mm:ss" : this.dataFormat;
	}
	public void setValue(Date value, String dataFormat) {
		this.cellType = CellType.NUMERIC;
		this.value = value;
		this.dataFormat = dataFormat;
	}
	public void setValue(RichTextString value) {
		this.cellType = CellType.STRING;
		this.value = value;
	}
	/*public String getBackgroundHexStr() {
		return backgroundHexStr;
	}
	public void setBackgroundHexStr(String backgroundHexStr) {
		this.backgroundHexStr = backgroundHexStr;
	}
	public String getColorHexStr() {
		return colorHexStr;
	}
	public void setColorHexStr(String colorHexStr) {
		this.colorHexStr = colorHexStr;
	}*/
	
	public CellType getCellType() {
		return cellType;
	}
	public short getBackground() {
        
		return background;
	}
	public void setBackground(short background) {
		this.background = background;
	}
	public short getColor() {
		return color;
	}
	public void setColor(short color) {
		this.color = color;
	}
	public void setCellType(CellType cellType) {
		this.cellType = cellType;
	}
	public Integer getColspan() {
		return colspan;
	}
	public void setColspan(Integer colspan) {
		this.colspan = colspan;
	}
	public Integer getRowspan() {
		return rowspan;
	}
	public void setRowspan(Integer rowspan) {
		this.rowspan = rowspan;
	}
	public Integer getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}
	public Integer getFirstCol() {
		return firstCol;
	}
	public void setFirstCol(Integer firstCol) {
		this.firstCol = firstCol;
	}
	public Integer getLastRow() {
		if(firstRow != null)
		    return firstRow + rowspan;
		else
			return null;
	}
	/*public void setLastRow(Integer lastRow) {
		this.lastRow = lastRow;
	}*/
	public Integer getLastCol() {
		if(firstCol != null)
		   return firstCol + colspan;
		else 
		   return null;
	}
	/*public void setLastCol(Integer lastCol) {
		this.lastCol = lastCol;
	}*/
	public Boolean getBold() {
		return bold;
	}
	public void setBold(Boolean bold) {
		this.bold = bold;
	}
	public Boolean getBorder() {
		return border;
	}
	public void setBorder(Boolean border) {
		this.border = border;
	}
	public Boolean getWrapText() {
		return wrapText;
	}
	public void setWrapText(Boolean wrapText) {
		this.wrapText = wrapText;
	}
	public String getDataFormat() {
		return dataFormat;
	}
	public void setDataFormat(String dataFormat) {
		this.dataFormat = dataFormat;
	}
	public String getFontName() {
		return fontName;
	}
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	
    
}
