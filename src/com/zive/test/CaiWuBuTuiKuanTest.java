package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class CaiWuBuTuiKuanTest {

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\退款审核.xlsx");
		
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
		
		Map<String,List<ReturnCheckCaiwubu>> map = new HashMap<>();
		
		BigDecimal all = BigDecimal.ZERO;
		
		JSONArray jsonArray = new JSONArray();
		
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String orderId = excelCells.get(3).getValue().toString();
			String menId = excelCells.get(10).getValue().toString();
			
			if(menId.equals("caiwubu")){
				String secondDate = excelCells.get(12).getValue().toString();
				
				ReturnCheckCaiwubu caiwu = new ReturnCheckCaiwubu();
				caiwu.setOrderId(orderId);
				caiwu.setSecondDate(secondDate);
				
				ExcelRow excelRow2 = excelSheet.getRows().get(i-1);
				List<ExcelCell> excelCells2 = excelRow2.getCells();
				String orderId2 = excelCells2.get(3).getValue().toString();
				
				if(!orderId2.equals(orderId)){
					continue;
				}
				
				String firstDate = excelCells2.get(12).getValue().toString();
				
				caiwu.setFirstDate(firstDate);
				
				String distanceTimemin = getDistanceTimemin(firstDate, secondDate);
				caiwu.setTime(distanceTimemin);
				
				jsonArray.add(caiwu);
			}
		}
		
		ExcelUtilForDO.toFile(jsonArray, ReturnCheckCaiwubu.class);
	}
	
	public static String getDistanceTimemin(String str1, String str2) {
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date one;
	    Date two;
	    long day = 0;//天数差
	    long hour = 0;//小时数差
	    long min = 0;//分钟数差
	    long second=0;//秒数差
	    
	    String time = "";
	    try {

	        final Calendar c = Calendar.getInstance();
	        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

	        one = df.parse(str1);
	        c.setTime(one);
	        two = df.parse(str2);
	        long time1 = one.getTime();
	        long time2 = two.getTime();
	        long diff ;
	        diff = time2 - time1;

	        day = diff / (24 * 60 * 60 * 1000);
//	        Log.i("lgq","diff--==="+diff+"...one="+time1+"..-----.two==="+time2);
	        hour = (diff / (60 * 60 * 1000) - day * 24);
	        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
	        second = diff/1000;
	        
	        time = day + "天"+ hour + "小时" + min + "分钟";
	        
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return time;
	}
}



@TableName("退款审核时间")
class ReturnCheckCaiwubu{
	@FieldName("退款单")
	private String orderId;
	
	@FieldName("上个人审核时间")
	private String firstDate;
	
	@FieldName("财务审核时间")
	private String secondDate;
	
	@FieldName("相差时间时间")
	private String time;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(String firstDate) {
		this.firstDate = firstDate;
	}

	public String getSecondDate() {
		return secondDate;
	}

	public void setSecondDate(String secondDate) {
		this.secondDate = secondDate;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
