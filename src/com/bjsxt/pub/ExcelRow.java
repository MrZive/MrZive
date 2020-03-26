package com.bjsxt.pub;

import java.util.List;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
/**
 * 
 * @author Lsenrong
 * @date Nov 01, 2017 10:41:08 AM
 * @Description: TODO(描述)
 */
public class ExcelRow {
    private List<ExcelCell> cells;
    @SuppressWarnings("deprecation")
	private short background = HSSFColor.WHITE.index;
    @SuppressWarnings("deprecation")
	private short color = HSSFColor.BLACK.index;
    private HorizontalAlignment align = HorizontalAlignment.LEFT;
    private VerticalAlignment vertical = VerticalAlignment.CENTER;
    private String fontName = "宋体";
    private Boolean bold = false;
	public List<ExcelCell> getCells() {
		return cells;
	}

	public void setCells(List<ExcelCell> cells) {
		this.cells = cells;
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

	public String getFontName() {
		return fontName;
	}

	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	public Boolean getBold() {
		return bold;
	}

	public void setBold(Boolean bold) {
		this.bold = bold;
	}
	
    
}
