package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.ProductGet;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改项目手工工时
 * @author Administrator
 *
 */
public class XingheTest extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\处理星和.xlsx");
		
		int number = 4;
		
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
		
		Map<String,String> map = new HashMap<>();
		Map<String,Xinghe> card = new HashMap<>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String orderId = excelCells.get(0).getValue().toString();
			String cardId = excelCells.get(1).getValue().toString();
			String cardNo = excelCells.get(2).getValue().toString();
			String cardName = excelCells.get(3).getValue().toString();
			Double price = Double.valueOf(excelCells.get(4).getValue().toString());
			String date = excelCells.get(5).getValue().toString();
			
			if(orderId.contains("_")){
				orderId = orderId.split("_")[0];
			}
			
			if(map.containsKey(orderId)){
				continue;
			}
			map.put(orderId, null);
			if(card.containsKey(cardId)){
				Xinghe xing = card.get(cardId);
				int num = xing.getNumber()+1;
				if(num > number){
					xing.setPrice(xing.getPrice() + price);
				}
				xing.setNumber(num);
			}else{
				Xinghe xing = new Xinghe();
				xing.setName(cardName);
				xing.setPhone(cardNo);
				xing.setNumber(1);
				xing.setPrice(0D);
				card.put(cardId, xing);
			}
		}
		
		JSONArray list = new JSONArray();
		Iterator<Entry<String, Xinghe>> iterator = card.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Xinghe> next = iterator.next();
			if(next.getValue().getPrice()>0D){
				list.add(next.getValue());
			}
		}
		
		ExcelUtilForDO.toFile(list, Xinghe.class);
	}
}


@TableName("星和")
class Xinghe{
	@FieldName("手机号")
	private String phone;
	
	@FieldName("姓名")
	private String name;
	
	@FieldName("次数")
	private Integer number;
	
	@FieldName("金额")
	private Double price;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
