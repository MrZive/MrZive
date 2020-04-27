package com.zive.pub;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


/**
 * 操作office
 * @author Lsenrong
 * @date Sep 15, 2017 4:36:10 PM
 * @Description: TODO(描述)
 */
public class OfficeUtil {
	/**
	 * 2003�?大行�?
	 */
	private static int EXCEL_2003_ROW_MAX = 65536;
	/**
	 * 2007�?大行�?
	 */
	private static int EXCEL_2007_ROW_MAX = 1048576;//1048576;
	/**
	 * excel指定行数写入硬盘
	 */
	private static int EXCEL_WRITE_TO_DISK_MAX_ROW = 5000;
	/**
	 * 边框默认颜色
	 */
	private static short DEFAULT_BORDER_COLOR = IndexedColors.PALE_BLUE.getIndex();
	
	/**
	 * 获取2003�?大行�?
	 * @Title: getDefaultRowMax2003 
	 * @author: Lsenrong
	 * @date Jan 30, 2018 3:24:42 PM
	 * @Description: TODO(描述) 
	 * @param @return    
	 * @return int
	 */
	public static int getDefaultRowMax2003(){
		return EXCEL_2003_ROW_MAX;
	}
	/**
	 * 获取2007�?大行�?
	 * @Title: getDefaultRowMax2007 
	 * @author: Lsenrong
	 * @date Jan 30, 2018 3:24:55 PM
	 * @Description: TODO(描述) 
	 * @param @return    
	 * @return int
	 */
	public static int getDefaultRowMax2007(){
		return EXCEL_2007_ROW_MAX;
	}
	public enum ExcelVersion{
		/**
		 * excel 2003   .xls
		 */
		Version2003,
		/**
		 * excel 2007    .xlsx
		 */
		Version2007
	}
	public enum AreaType{
		/**
		 * 内容
		 */
		Content,
		/**
		 * 合并
		 */
		Span
	}
	
	private static Log logger = LogFactory.getLog(OfficeUtil.class);
    /**
     * 读取excel
     * @Title: readExcel 
     * @author: Lsenrong
     * @date Sep 19, 2017 4:56:24 PM
     * @Description: TODO(描述) 
     * @param @param pathname 绝对路径
     * @param @return
     * @param @throws IOException    
     * @return Excel
     */
	@SuppressWarnings({ "unused", "null" })
	public static Excel readExcel(String pathname) throws IOException
    {
        FileInputStream fileInputStream = new FileInputStream(pathname);
    	return readExcel(fileInputStream,pathname);
    }
	public static Excel readExcel(InputStream inputStream, String pathname) throws IOException
    {
		logger.debug("准备导入excle数据....");
    	Excel excel = null;
    	Workbook workbook = null;
        try{
        	File file = new File(pathname);
			if(pathname.endsWith(".xlsx")) {//2007
				logger.debug("excel 2007");
				workbook = new XSSFWorkbook(inputStream);
			} else if (pathname.endsWith(".xls")) {//2003
				logger.debug("excel 2003");
				workbook = new HSSFWorkbook(inputStream);
			} else{
				logger.debug("无法识别的excel文件");
			}
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
			excel = new Excel();
			excel.setName(file.getName());
			excel.setSheets(new ArrayList<ExcelSheet>());
			Sheet sheet = null;
			
			Row row = null;
			Cell cell = null;
			for(int i = 0;i< workbook.getNumberOfSheets();i++)
			{
				sheet = workbook.getSheetAt(i);
				ExcelSheet excelSheet = new ExcelSheet();
				excelSheet.setName(sheet.getSheetName());
				excelSheet.setRows(new ArrayList<ExcelRow>());
				logger.debug("正在获取�?" + (i + 1) + "个sheet" + "�?" + excelSheet.getName() + "�?");
				//循环�?
				for(int firstRowNum = sheet.getFirstRowNum(),lastRowNum = sheet.getLastRowNum();firstRowNum<=lastRowNum;firstRowNum++){
					row = sheet.getRow(firstRowNum);
					if(row == null)
						continue;
					logger.debug("读取�?" + firstRowNum + "行数�?");
					ExcelRow sheetRow = new ExcelRow();
					sheetRow.setCells(new ArrayList<ExcelCell>());
					//循环�?
					for(int firstCellNum = row.getFirstCellNum(),lastCellNum = row.getLastCellNum();firstCellNum < lastCellNum; firstCellNum ++){
						cell = row.getCell(firstCellNum);
						ExcelCell excelCell = new ExcelCell();
						Object value = null;
						if(cell != null){
							DecimalFormat df = new DecimalFormat("0");// 格式�? number String
    					    DecimalFormat nf = new DecimalFormat("0.00");// 格式化数�?
    						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 格式化日期字符串
    	        			excelCell = new ExcelCell();
    						switch (cell.getCellTypeEnum()) {
								case STRING://字符�?
									excelCell.setCellType(CellType.STRING);
									value = cell.getStringCellValue().trim();
									break;
								case NUMERIC://数字
									excelCell.setCellType(CellType.NUMERIC);
									String dataFormatString = cell.getCellStyle().getDataFormatString();
									if (dataFormatString.lastIndexOf("m") > -1 || dataFormatString.lastIndexOf("y") > -1 || dataFormatString.lastIndexOf("d") > -1 || dataFormatString.lastIndexOf("h") > -1 || dataFormatString.lastIndexOf("s") > -1) {
										value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
									} else{
										value = cell.getNumericCellValue();
									}
									
									break;
								case BOOLEAN:// boolean—�?�Boolean type
									excelCell.setCellType(CellType.BOOLEAN);
									value = cell.getBooleanCellValue();
								    break;
								case FORMULA:// boolean—�?�Boolean type
									excelCell.setCellType(CellType.FORMULA);
									value = evaluator.evaluate(cell);
								    break;
								case BLANK:// 空白—�?�Blank type
									excelCell.setCellType(CellType.BLANK);
									value = "";
									break;
								default:// default type
									value = cell.toString();
									break;
								
    						}
    						if(value.equals("NULL") || value.toString().isEmpty())
								value = null;
    						logger.debug("[" + firstRowNum + "][" + firstCellNum + "](" + cell.getCellTypeEnum()+ ")---" + (value != null ? value.toString() : ""));
						}
						excelCell.setValue(value);
						sheetRow.getCells().add(excelCell);
					}
					excelSheet.getRows().add(sheetRow);
				}
				excel.getSheets().add(excelSheet);
			}
        		
        } finally {
			if(inputStream != null)
				inputStream.close();
			
		} 
    	return excel;
    }
    
	/**
     * 创建 excel 
     * @Title: createExcel 
     * @author: Lsenrong
     * @date Sep 19, 2017 5:04:50 PM
     * @Description: TODO(描述) 
     * @param @param pathname 文件绝对路径
     * @param @param excelVersion excel 版本
     * @param @param excel    
     * @return void
     * @throws IOException 
     */
	public static void createExcel(String pathname,ExcelVersion excelVersion,Excel excel) throws IOException
    {
    	FileOutputStream outputStream = null;
    	try{
    		Workbook workbook = buildExcel(excel,excelVersion,getRowMax(excelVersion),EXCEL_WRITE_TO_DISK_MAX_ROW);
    		if(workbook != null){
    			File file = new File(pathname);
            	File parentFile = new File(file.getParent());
            	if(!parentFile.exists())
            		parentFile.mkdirs();
        		file.createNewFile();
                outputStream = new FileOutputStream(pathname);
        		workbook.write(outputStream);
        		workbook.close();
        		logger.debug("生成excel文件�?" + pathname);
    		}
        	
    	}finally{
    		if(outputStream != null)
    			outputStream.close();
    	}
    }
	
	
	/**
	 * 生成excel输出�?
	 * @Title: createExcel 
	 * @author: Lsenrong
	 * @date Oct 31, 2017 11:42:29 AM
	 * @Description: TODO(描述) 
	 * @param @param excelVersion
	 * @param @param excel
	 * @param @param rowMax
	 * @param @return    
	 * @return byte[]
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static byte[] createExcel(ExcelVersion excelVersion,Excel excel,int rowMax) throws IOException
	{
    	FileOutputStream outputStream = null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	try{
    		
    		Workbook workbook = buildExcel(excel,excelVersion,rowMax,EXCEL_WRITE_TO_DISK_MAX_ROW);
            byteArrayOutputStream = new ByteArrayOutputStream();
    		workbook.write(byteArrayOutputStream);
    		workbook.close();
    		logger.debug("生成excel至输出流�?");
    		return byteArrayOutputStream.toByteArray();
    	}finally{
    		if(byteArrayOutputStream != null)
    			byteArrayOutputStream.close();
    	}
	}
	
	/**
     * 创建 excel 
     * @Title: createExcel 
     * @author: Lsenrong
     * @date Sep 19, 2017 5:04:50 PM
     * @Description: TODO(描述) 
     * @param @param pathname 文件绝对路径
     * @param @param excelVersion excel 版本
     * @param @param excel    
     * @param @param rowMax
     * @return void
     * @throws IOException 
     */
	public static void createExcel(String pathname,ExcelVersion excelVersion,Excel excel,int rowMax) throws IOException
    {
    	FileOutputStream outputStream = null;
    	try{
    		Workbook workbook = buildExcel(excel,excelVersion,rowMax,EXCEL_WRITE_TO_DISK_MAX_ROW);
        	File file = new File(pathname);
        	File parentFile = new File(file.getParent());
        	if(!parentFile.exists())
        		parentFile.mkdirs();
    		file.createNewFile();
            outputStream = new FileOutputStream(pathname);
    		workbook.write(outputStream);
    		workbook.close();
    		logger.debug("生成excel文件�?" + pathname);
    	}finally{
    		if(outputStream != null)
    			outputStream.close();
    	}
    }
	/**
	 * 创建 excel 
	 * @Title: createExcel 
	 * @author: Lsenrong
	 * @date Jan 30, 2018 3:23:03 PM
	 * @Description: TODO(描述) 
	 * @param @param pathname
	 * @param @param excelVersion
	 * @param @param excel
	 * @param @param rowMax
	 * @param @param toDiskRowMax
	 * @param @throws IOException    
	 * @return void
	 */
	public static void createExcel(String pathname,ExcelVersion excelVersion,Excel excel,int rowMax,int toDiskRowMax) throws IOException
    {
    	FileOutputStream outputStream = null;
    	try{
    		Workbook workbook = buildExcel(excel,excelVersion,rowMax,toDiskRowMax);
        	File file = new File(pathname);
        	File parentFile = new File(file.getParent());
        	if(!parentFile.exists())
        		parentFile.mkdirs();
    		file.createNewFile();
            outputStream = new FileOutputStream(pathname);
    		workbook.write(outputStream);
    		workbook.close();
    		logger.debug("生成excel文件�?" + pathname);
    	}finally{
    		if(outputStream != null)
    			outputStream.close();
    	}
    }
	
	/**
	 * 生成excel输出�?
	 * @Title: createExcel 
	 * @author: Lsenrong
	 * @date Oct 31, 2017 11:42:29 AM
	 * @Description: TODO(描述) 
	 * @param @param excelVersion
	 * @param @param excel
	 * @param @return    
	 * @return byte[]
	 * @throws IOException 
	 */
	@SuppressWarnings("unused")
	public static byte[] createExcel(ExcelVersion excelVersion,Excel excel) throws IOException
	{
    	FileOutputStream outputStream = null;
    	ByteArrayOutputStream byteArrayOutputStream = null;
    	try{
    		
    		Workbook workbook = buildExcel(excel,excelVersion,getRowMax(excelVersion),EXCEL_WRITE_TO_DISK_MAX_ROW);
            byteArrayOutputStream = new ByteArrayOutputStream();
    		workbook.write(byteArrayOutputStream);
    		workbook.close();
    		logger.debug("生成excel至输出流�?");
    		return byteArrayOutputStream.toByteArray();
    	}finally{
    		if(byteArrayOutputStream != null)
    			byteArrayOutputStream.close();
    	}
	}
	
	
	/**
	 * 2003自定义颜�?
	 * @Title: setHSSFPaletteColor 
	 * @author: Lsenrong
	 * @date Jan 16, 2018 1:13:29 PM
	 * @Description: TODO(描述) 
	 * @param @param hssfWorkbook
	 * @param @param color
	 * @param @param setShort    
	 * @return void
	 */
    public static void setHSSFPaletteColor(HSSFWorkbook hssfWorkbook,String color,short setShort){
    	
		color = color.replaceAll("#", ""); //16进制的字符串  
		//转为RGB�?  
		int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制  
		int g = Integer.parseInt((color.substring(2,4)),16);  
		int b = Integer.parseInt((color.substring(4,6)),16);  
		//自定义cell颜色  hssfWorkbook
		if(hssfWorkbook != null){
			HSSFPalette palette = hssfWorkbook.getCustomPalette();   
			//这里�?9是索�?  
			palette.setColorAtIndex(setShort, (byte) r, (byte) g, (byte) b);
		}
		
	}
    /**
     * 2007自定义颜�?
     * @Title: getXSSFPaletteColor 
     * @author: Lsenrong
     * @date Jan 16, 2018 1:53:31 PM
     * @Description: TODO(描述) 
     * @param @param xssfWorkbook
     * @param @param color
     * @param @return    
     * @return XSSFColor
     */
    public static short getXSSFPaletteColor(XSSFWorkbook xssfWorkbook,String color){
    	
		color = color.replaceAll("#", ""); //16进制的字符串  
		//转为RGB�?  
		int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制  
		int g = Integer.parseInt((color.substring(2,4)),16);  
		int b = Integer.parseInt((color.substring(4,6)),16);  
		//自定义cell颜色  hssfWorkbook
		if(xssfWorkbook != null){
			return new XSSFColor(new java.awt.Color(r, g, b)).getIndex();
		}
		return 0;
		
	}
	
	/**
	 * 标记表格位置
	 * @Title: getCellArea 
	 * @author: Lsenrong
	 * @date Jan 5, 2018 11:28:10 AM
	 * @Description: TODO(描述) 
	 * @param @param excelSheet    
	 * @return void
	 */
	public static void getCellArea(ExcelSheet excelSheet){
		int colSize = 0;
		int rowSize = excelSheet.getRows().size();
		//设置�?大列�?
		excelSheet.setColCount(0);
		//指定单元格类型的二维数组(初始化最起码十列防止 �?定情况之下兼容表格行列数不一致的问题导致的异�?)
		int arrayColSize = 10;
		for(ExcelCell excelCell : excelSheet.getRows().get(0).getCells()){
			arrayColSize += excelCell.getColspan();
		}
		AreaType[][] areas = new AreaType[rowSize][arrayColSize];
		for(int i = 0;i<rowSize; i++){
			colSize = 0;
			ExcelRow excelRow = excelSheet.getRows().get(i);
			for(int j = 0,cellSize = excelRow.getCells().size(); j < cellSize;j++){
				ExcelCell excelCell = excelRow.getCells().get(j);
				//设置�?始坐标位�?
				int colIndex = 0;
				for(int x = 0;x<areas[i].length;x++){
					if(areas[i][x] == null){
						colIndex = x;
						break;
					}
				}
				excelCell.setFirstCol(colIndex);
				excelCell.setFirstRow(i);
				//遍历二维数组标记指定行列单元格的类型
				for(int m = excelCell.getFirstRow();m < excelCell.getLastRow();m++){
					for(int n = excelCell.getFirstCol(); n < excelCell.getLastCol();n++ ){
						if(n == excelCell.getFirstCol() && m == excelCell.getFirstRow()){
							areas[m][n] = AreaType.Content;
						} else{
							areas[m][n] = AreaType.Span;
						}
					}
				}
				colSize = excelCell.getLastCol();
			}
			if(colSize > excelSheet.getColCount()){
				//设置�?大列�?
				excelSheet.setColCount(colSize);
			}
		}
	}
	
	/**
	 * 生成workbook对象
	 * @Title: buildExcel 
	 * @author: Lsenrong
	 * @date Jan 30, 2018 3:17:32 PM
	 * @Description: TODO(描述) 
	 * @param @param excel
	 * @param @param excelVersion
	 * @param @param rowMax
	 * @param @param toDiskMax
	 * @param @return    
	 * @return Workbook
	 */
	public static Workbook buildExcel(Excel excel,ExcelVersion excelVersion,int rowMax,int toDiskMax)
    {
		if(excel.getSheets() == null){
			throw new RuntimeException("excel没有数据");
		}
    	logger.debug("接下来生成excel文件操作...");
    	SXSSFWorkbook workbook = null;
		switch (excelVersion) {
			case Version2003:
				logger.debug("excel2003版本 xls");
			    //workbook = new HSSFWorkbook();
				workbook = new SXSSFWorkbook(toDiskMax);
				break;
			case Version2007:
				logger.debug("excel2007版本 xlsx");
				//workbook = new XSSFWorkbook();
				workbook = new SXSSFWorkbook(toDiskMax);
				break;
			/*case BigData:
				logger.debug("大数据版�?  xlsx");
				workbook = new SXSSFWorkbook(EXCEL_WRITE_TO_DISK_MAX_ROW);
				break;	*/
			default:
				break;
		}
    	if(excel.getSheets() != null){
			Map<String,CellStyle> cellStyles = new HashMap<String,CellStyle>();
			
    		for(final ExcelSheet excelSheet:excel.getSheets())
        	{
    			/*logger.debug("正在生成片（" + excelSheet.getName() + "�?");*/
        		if(excelSheet.getRows() != null && excelSheet.getRows().size() > 0){
        			//
        			//对行数过多的sheet进行分割
        			int fromIndex = 0;
        			int toIndex = 0;
        			int maxToIndex = excelSheet.getRows().size();
        			Integer increase = 0;
        			while (toIndex != maxToIndex) {
        				if(toIndex + rowMax < maxToIndex){
        					toIndex = toIndex + rowMax;
        				}else{
        					toIndex = maxToIndex;
        				}
        				
        				final String sheetName = excelSheet.getName() + (increase.equals(0) ? "" : "(" + increase.toString() + ")");
        				final List<ExcelRow> excelRows = excelSheet.getRows().subList(fromIndex, toIndex);
						buildSheet(new ExcelSheet(){{
							setAutoSizeColumn(excelSheet.getAutoSizeColumn());
							setName(sheetName);
							setRows(excelRows);
							setFreezePane(excelSheet.getFreezePane());
							setDefaultColumnWidth(excelSheet.getDefaultColumnWidth());
							setColunmWidth(excelSheet.getColunmWidth());
						}}, workbook, cellStyles);
						increase += 1;
						fromIndex += rowMax;
					};
        			
        		}
        	}
    	}
    	return workbook;
    	
    }
	
	/**
	 * 生成 excel �?
	 * @Title: buildSheet 
	 * @author: Lsenrong
	 * @date Jan 13, 2018 5:04:26 PM
	 * @Description: TODO(描述) 
	 * @param @param excelSheet
	 * @param @param workbook
	 * @param @param cellStyles    
	 * @return void
	 */
	public static void buildSheet(ExcelSheet excelSheet,SXSSFWorkbook workbook, Map<String,CellStyle> cellStyles){
		logger.debug("正在生成片（" + excelSheet.getName() + "�?");
		SXSSFSheet sheet = workbook.createSheet(excelSheet.getName());
		getCellArea(excelSheet);
		//冻结窗口
		if(excelSheet.getFreezePane() != null){
			if(excelSheet.getFreezePane().length == 4){
				logger.debug("正在设置冻结窗口");
				sheet.createFreezePane(excelSheet.getFreezePane()[0],excelSheet.getFreezePane()[1],excelSheet.getFreezePane()[2],excelSheet.getFreezePane()[3]);
			}else{
				throw new RuntimeException("冻结窗口参数设置错误");
			}
			
		}
		logger.debug("准备单元格�?�填�?...");
		DataFormat dataFormat = workbook.createDataFormat();
		//先填充�??
		for(int i = 0, rowSize = excelSheet.getRows().size(); i< rowSize; i++)
		{
			logger.debug("片：" + excelSheet.getName() + " -> " + "行：" + (i + 1));
    		ExcelRow excelRow = excelSheet.getRows().get(i);
    		Row row = sheet.getRow(i);
    		if(row == null){
    			row = sheet.createRow(i);
    		}
    		for(int j = 0, cellSize = excelRow.getCells().size();j<cellSize;j++){
    			ExcelCell excelCell = excelRow.getCells().get(j);
    			if(excelCell.getBackground() == 0){
    				excelCell.setBackground(excelRow.getBackground());
    			}
    			if(excelCell.getColor() == 0){
    				excelCell.setColor(excelRow.getColor());
    			}
    			if(excelCell.getAlign() == null){
    				excelCell.setAlign(excelRow.getAlign());
    			}
    			if(excelCell.getVertical() == null){
    				excelCell.setVertical(excelRow.getVertical());
    			}
    			if(excelCell.getFontName() == null){
    				excelCell.setFontName(excelRow.getFontName());
    			}
    			if(excelCell.getBold() == null){
    				excelCell.setBold(excelRow.getBold());
    			}
    			Object value = excelCell.getValue();
    			String format = excelCell.getDataFormat();
    			CellType cellType = excelCell.getCellType();
    			Cell cell = row.createCell(excelCell.getFirstCol());
    			CellStyle cellStyle = getCellStyle(workbook,excelCell,cellStyles);
    			cell.setCellStyle(cellStyle);// 应用样式对象
    			if(value != null){
    				if (value instanceof String) {
    					cell.setCellValue(value.toString());
    					cellType = cellType == null ? CellType.STRING : cellType;
    				} else if(value instanceof Boolean){
    					cell.setCellValue((Boolean)value);
    					cellType = cellType == null ? CellType.BOOLEAN : cellType;
    				} else if(value instanceof Date){
    					cell.setCellValue((Date)value);
    					cellType = cellType == null ? CellType.NUMERIC : cellType;
    				} else if(value instanceof Double){
    					cell.setCellValue((Double)value);
    					cellType = cellType == null ? CellType.NUMERIC : cellType;
    				} else if(value instanceof Calendar){
    					cell.setCellValue((Calendar)value);
    					cellType = cellType == null ? CellType.NUMERIC : cellType;
    				} else if(value instanceof RichTextString){
    					cell.setCellValue((RichTextString)value);
    					cellType = cellType == null ? CellType.STRING : cellType;
    				} else {
    					cell.setCellValue(value.toString());
    					cellType = cellType == null ? CellType.STRING : cellType;
    				}
    			}else{
    				cellType = CellType.BLANK;
    			}
    			if(cellType == null){
    				cellType = CellType.BLANK;
    			}
    			cell.setCellType(cellType);
    			if(format != null && !format.trim().isEmpty()){
    				cell.getCellStyle().setDataFormat(dataFormat.getFormat(format));
    			}
    			//合并单元�?
    			if(excelCell.getColspan() > 1 || excelCell.getRowspan() > 1){
    				logger.debug("正在合并单元�?...");
    				CellRangeAddress cra = new CellRangeAddress(excelCell.getFirstRow(), excelCell.getLastRow() -1, excelCell.getFirstCol(), excelCell.getLastCol() -1);
    				if(excelCell.getBorder()){
    					try{
    						for(int m=cra.getFirstRow();m<= cra.getLastRow() ;m++){
    							Row mergedRow = sheet.getRow(m);
    							if(mergedRow == null){
    								mergedRow = sheet.createRow(m);
    							}
    		    				for(int n = cra.getFirstColumn();n<=cra.getLastColumn();n++){
    		    					Cell mergerdCell = mergedRow.getCell(n);
    		    					if(mergerdCell == null){
    		    						mergerdCell = mergedRow.createCell(n);
    		    						mergerdCell.setCellStyle(cellStyle);// 应用样式对象
    		    					}
    		    				}
    		    			}
    					}catch(Exception e){
    						e.printStackTrace();
    					}
    				}
    				sheet.addMergedRegion(cra);
    			}
    			
		   }
		}
		
		try{
			for(int m = 0 ;m<excelSheet.getColCount();m++)
			{
				if(excelSheet.getColunmWidth().keySet().size() > 0){
					if(excelSheet.getColunmWidth().containsKey(new Integer(m))){
						logger.debug("正在按设置�?�调整第" + (m + 1) + "列的列宽");
						sheet.setColumnWidth(m, excelSheet.getColunmWidth().get(new Integer(m)));
					} else {
						logger.debug("正在按默认�?�调整第" + (m + 1) + "列的列宽");
						sheet.setColumnWidth(m, excelSheet.getDefaultColumnWidth());
					}
				} else if(excelSheet.getAutoSizeColumn()){
					sheet.trackAllColumnsForAutoSizing();
					logger.debug("正在自动调整�?" + (m + 1) + "列的列宽");
					sheet.autoSizeColumn(m);// 自动调整列宽 
				} else{
					logger.debug("正在按默认�?�调整第" + (m + 1) + "列的列宽");
					sheet.setColumnWidth(m, excelSheet.getDefaultColumnWidth());
				}
				
			}
		} catch (Exception ex){
			logger.debug("出错�?" + ex.toString());
		}
	}
	
	
	/**
	 * html table 标签转化为excel
	 * @Title: resolveHtmlStrToExcel 
	 * @author: Lsenrong
	 * @date Jan 5, 2018 3:18:02 PM
	 * @Description: TODO(描述) 
	 * @param @param tableHtmlStr
	 * @param @param excelName
	 * @param @param sheetName
	 * @param @param excelVersion
	 * @param @return
	 * @param @throws DocumentException
	 * @param @throws IOException    
	 * @return Excel
	 */
	@SuppressWarnings({ "unchecked", "serial" })
	public static Excel resolveHtmlStrToExcel(String tableHtmlStr,final String excelName,String sheetName, ExcelVersion excelVersion) throws DocumentException, IOException{
		logger.debug("执行html table 转化为Excel...");
		
		ByteArrayInputStream inputStream = null;
    	try{
    		SAXReader reader = new SAXReader();
    		reader.setEncoding("UTF-8");//这里设置文件编码
    		inputStream = new ByteArrayInputStream(tableHtmlStr.getBytes("UTF-8"));  
    		Document document = reader.read(inputStream);
    		Element root = document.getRootElement();
    		//List<Element> elements = root.elements();
    		List<Element> trElements = new ArrayList<Element>();
    		
    		trElements.addAll(root.elements("tr"));
    		Element thead = root.element("thead");
    		if(thead != null)
    			trElements.addAll(thead.elements("tr"));
    		Element tbody = root.element("tbody");
    		if(tbody != null)
    			trElements.addAll(tbody.elements("tr"));
    		Element tfoot = root.element("tfoot");
    		if(tfoot != null)
    			trElements.addAll(tfoot.elements("tr"));
    		/*elements.addAll(root.selectNodes("//table//tr"));
    		elements.addAll(root.selectNodes("//table//thead//tr"));
    		elements.addAll(root.selectNodes("//table//tbody//tr"));*/
    		Pattern cssPatern = Pattern.compile("([a-zA-Z\\-]+)(\\:)([a-zA-Z\\-]+)(\\;)");  
    	    Matcher matcher = null;  
    	    Integer index = -1;
    	    final ExcelSheet excelSheet = new ExcelSheet(){{setRows(new ArrayList<ExcelRow>());}};
    	    excelSheet.setName(sheetName == null || sheetName.trim().isEmpty() ? "青花瓷导�?" : sheetName);
    	    Excel excel = new Excel(){{
    	    	setName(excelName == null || excelName.trim().isEmpty() ? "青花瓷导�?" : excelName);
    	    	setSheets(new ArrayList<ExcelSheet>(){{
    	    		add(excelSheet);
    	    	}});
    	    }};
    	    
    		// 遍历�?有子节点
    		for (Element trElement : trElements){
    			ExcelRow excelRow = new ExcelRow(){{setCells(new ArrayList<ExcelCell>());}};
    			excelSheet.getRows().add(excelRow);
    			List<Element> contentElements = trElement.elements();
    			for(Element contentElement : contentElements){
    				ExcelCell excelCell = new ExcelCell();
    				List<Attribute> attributes = contentElement.attributes();
    				for(Attribute attribute: attributes){
    					String attributeName = attribute.getName().toLowerCase();
    					String attributeValue = attribute.getValue();
    					if("style".equals(attributeName)){
    						String style = attribute.getData().toString().replaceAll("\\s", "");
    					    Map<String, String> styleMap = new HashMap<String, String>();
    					    while (cssPatern.matcher(style).find()) {  
    					    	matcher = cssPatern.matcher(style);
    					    	if (matcher.find()) {  
    					    		index = style.indexOf(matcher.group());
    					    		style = style.substring(0, index) + style.substring(index + matcher.group().length());
    					    		String key = matcher.group(1);
        					    	String value = matcher.group(3);
        					    	if(key != null && !key.isEmpty() && value != null){
        					    		styleMap.put(key.toLowerCase(), value.toLowerCase());
        					    	};
    					    	}
    					    	
    					    };
    					    if(styleMap.containsKey("font-weight")){
    					    	if(styleMap.get("font-weight").equals("normal")){
    					    		excelCell.setBold(false);
    					    	} else if(styleMap.get("font-weight").equals("bold")){
    					    		excelCell.setBold(true);
    					    	}
    					    }
    					    if(styleMap.containsKey("text-align")){
    					    	if(styleMap.get("text-align").equals("center")){
    					    		excelCell.setAlign(HorizontalAlignment.CENTER);
    					    	} else if(styleMap.get("text-align").equals("left")){
    					    		excelCell.setAlign(HorizontalAlignment.LEFT);
    					    	} else if(styleMap.get("text-align").equals("right")){
    					    		excelCell.setAlign(HorizontalAlignment.RIGHT);
    					    	}
    					    }
    					    if(styleMap.containsKey("vertical-align")){
    					    	if(styleMap.get("vertical-align").equals("middle")){
    					    		excelCell.setVertical(VerticalAlignment.CENTER);
    					    	} else if(styleMap.get("vertical-align").equals("top")){
    					    		excelCell.setVertical(VerticalAlignment.TOP);
    					    	} else if(styleMap.get("vertical-align").equals("bottom")){
    					    		excelCell.setVertical(VerticalAlignment.BOTTOM);
    					    	}
    					    }
    					    if(styleMap.containsKey("color")){
    					    	/*try{
    					    		excelCell.setColor(getPaletteColor("#E81717"));
    					    		//excelCell.setColor(getPaletteColor(styleMap.get("color")));
    					    		logger.debug("bbbbbb");
    					    	}catch(Exception ex){
    					    		logger.debug("aaaaaaa");
    					    	}*/
    					    }
                            if(styleMap.containsKey("backgroud-color")){
    					    	
    					    }
    					} else if ("colspan".equals(attributeName)){
    						if(attributeValue != null && !attributeValue.isEmpty()){{
    							excelCell.setColspan(Integer.valueOf(attributeValue));
    						}};
    					} else if ("rowspan".equals(attributeName)){
                            if(attributeValue != null && !attributeValue.isEmpty()){{
                            	excelCell.setRowspan(Integer.valueOf(attributeValue));
    						}};
    					}
    					
    				}
    				try {
    					excelCell.setCellType(CellType.NUMERIC);
    					excelCell.setValue(Double.valueOf(contentElement.getStringValue().replace(",", "").trim()));
					} catch (Exception e) {
						excelCell.setCellType(CellType.STRING);
    					excelCell.setValue(contentElement.getStringValue() != null ? contentElement.getStringValue().trim() : "");
						// TODO: handle exception
					}
    				excelRow.getCells().add(excelCell);
    			};
    			//logger.debug("name�?" + e.getName() + "；text�?" + e.getTextTrim());
    		}
    		return excel;
    	}finally{
    		if(inputStream != null)
    			inputStream.close();
    	}
		
	}
    
	/**
     * 获取 cell 样式
     * @Title: getCellStyle 
     * @author: Lsenrong
     * @date Jan 4, 2018 10:20:34 AM
     * @Description: TODO(描述) 
     * @param @param workbook
     * @param @param excelCell
     * @param @param cellStyles
     * @param @return    
     * @return CellStyle
     */
    public static CellStyle getCellStyle(SXSSFWorkbook workbook,ExcelCell excelCell, Map<String, CellStyle> cellStyles)
    {
    	if(workbook != null && excelCell != null){
    		StringBuffer sb = new StringBuffer();
    		/*.append(excelCell.getBackgroundHexStr()).append("|")
        	.append(excelCell.getColorHexStr()).append("|")*/
        	sb.append(excelCell.getBackground()).append("|")
        	.append(excelCell.getColor()).append("|")
        	.append(excelCell.getFontName()).append("|")
        	.append(excelCell.getAlign()).append("|")
        	.append(excelCell.getVertical()).append("|")
        	.append(excelCell.getWrapText()).append("|")
        	.append(excelCell.getBorder()).append("|")
        	.append(excelCell.getDataFormat()).append("|")
        	.append(excelCell.getBold()).append("|");
        	String key = sb.toString();
        	if(cellStyles.containsKey(key)){
        		return cellStyles.get(key);
        	} else{
        		CellStyle style = null;
        		
        		short background = excelCell.getBackground();
        		short color = excelCell.getColor();
        		style = workbook.createCellStyle();
        		/*if(style == null){
        			try{
            			style = ((HSSFWorkbook) workbook).createCellStyle();// 创建样式对象
            			color = (short)8;
            			background = (short)9;
            			setHSSFPaletteColor(((HSSFWorkbook) workbook), excelCell.getColorHexStr(), color);
            			setHSSFPaletteColor(((HSSFWorkbook) workbook), excelCell.getBackgroundHexStr(), background);
            		} catch(Exception e){}
        		} 
        		if(style == null){
        			try{
            			style = ((XSSFWorkbook) workbook).createCellStyle();// 创建样式对象
            			color = getXSSFPaletteColor(((XSSFWorkbook) workbook), excelCell.getColorHexStr());
            		} catch(Exception e){}
        		} */
        		
        		// 设置对齐方式
        		style.setAlignment(excelCell.getAlign());
        		style.setVerticalAlignment(excelCell.getVertical());
        		// 设置边框      
        		if(excelCell.getBorder()){
        			style.setBorderTop(BorderStyle.THIN);
            		style.setBorderBottom(BorderStyle.THIN);
            		style.setBorderLeft(BorderStyle.THIN);
            		style.setBorderRight(BorderStyle.THIN);
            		style.setTopBorderColor(DEFAULT_BORDER_COLOR);
            		style.setBottomBorderColor(DEFAULT_BORDER_COLOR);
            		style.setLeftBorderColor(DEFAULT_BORDER_COLOR);
            		style.setRightBorderColor(DEFAULT_BORDER_COLOR);
        		}
        		
        		style.setWrapText(excelCell.getWrapText());// 设置单元格内容是否自动换�?
        		// 格式化日�?
        		//style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        		if(background != 0){
        			style.setFillForegroundColor(background);//设置背景颜色
        		}
        		
        		// 设置单元格字�?
        		Font font = workbook.createFont(); // 创建字体对象
        		font.setFontHeightInPoints((short) 11);// 设置字体大小
        		font.setFontName(excelCell.getFontName());// 设置为宋体字
        		font.setBold(excelCell.getBold()); //设置粗体
        		font.setColor(color);//设置颜色
        		style.setFont(font);// 将字体加入到样式对象
        		cellStyles.put(key, style);
        		return style;
        	}
    	}
    	return null;
		
    }
    
    /**
     * 根据版本获取�?大行
     * @Title: getRowMax 
     * @author: Lsenrong
     * @date Jan 24, 2018 4:32:10 PM
     * @Description: TODO(描述) 
     * @param @param excelVersion
     * @param @return    
     * @return int
     */
    public static int getRowMax(ExcelVersion excelVersion){
    	int rowMax = 0;
		if(excelVersion == ExcelVersion.Version2003){
			rowMax = EXCEL_2003_ROW_MAX;
		} else if(excelVersion == ExcelVersion.Version2007){
			rowMax = EXCEL_2007_ROW_MAX;
		} else {
			rowMax = EXCEL_2003_ROW_MAX;
		}
		return rowMax;
    }
}
