package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class UserDefinedTest {

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\副本青花瓷数据.xlsx");
		
		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(new FileInputStream(file), file.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
        //第一行为表头
		ExcelRow excelRow = null;
		List<ExcelCell> excelCells = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		Map<String,BigDecimal> map = new HashMap<>();
		
		BigDecimal all = BigDecimal.ZERO;
		
		JSONArray list = new JSONArray();
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String title = excelCells.get(3).getValue().toString();
			String createTimeStr = excelCells.get(4).getValue().toString();
			String buyTimeStr = excelCells.get(5).getValue().toString();
			
			String[] split = createTimeStr.split(" ");
			//日
			String[] split2 = split[0].split("-");
			String month = split2[1];
			if(month.length()==1){
				month = "0" + month;
			}
			String day = split2[2];
			if(day.length()==1){
				day = "0" + day;
			}
			//时
			String[] split3 = split[1].split(":");
			String hour = split3[0];
			if(hour.length()==1){
				hour = "0" + hour;
			}
			String minute = split3[1];
			if(minute.length()==1){
				minute = "0" + minute;
			}
			String second = split3[2];
			if(second.length()==1){
				second = "0" + second;
			}
			String createDate = split2[0] + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second;
			
			//日
			String[] splitTwo = buyTimeStr.split(" ");
			String[] splitTwo2 = splitTwo[0].split("-");
			String monthTwo = splitTwo2[1];
			if(monthTwo.length()==1){
				monthTwo = "0" + monthTwo;
			}
			String dayTwo = splitTwo2[2];
			if(dayTwo.length()==1){
				dayTwo = "0" + dayTwo;
			}
			
			//时
			String[] splitTwo3 = splitTwo[1].split(":");
			String hourTwo = splitTwo3[0];
			if(hourTwo.length()==1){
				hourTwo = "0" + hourTwo;
			}
			String minuteTwo = splitTwo3[1];
			if(minuteTwo.length()==1){
				minuteTwo = "0" + minuteTwo;
			}
			String secondTwo = splitTwo3[2];
			if(secondTwo.length()==1){
				secondTwo = "0" + secondTwo;
			}
			String buyDate = splitTwo2[0] + "-" + monthTwo + "-" + dayTwo + " " + hourTwo + ":" + minuteTwo + ":" + secondTwo;
			
			
			ZHAOMEIFAIL zhao = new ZHAOMEIFAIL();
			zhao.setBuyDate(buyDate);
			zhao.setCreateDate(createDate);
			zhao.setPhone(phone);
			zhao.setTitle(title);
			list.add(zhao);
		}
		
		ExcelUtilForDO.toFile(list, ZHAOMEIFAIL.class);
	}
}

@TableName("ZHAOMEIFAIL")
class ZHAOMEIFAIL{
	@FieldName("手机号")
	private String phone;
	
	@FieldName("名称")
	private String title;

	@FieldName("创建时间")
	private String createDate;
	
	@FieldName("购买时间")
	private String buyDate;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	
	
}
