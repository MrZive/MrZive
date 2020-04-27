package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;

import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class CheckTest {

	public static void main(String[] args) {
		List<String> list = getValue("c://11.xlsx",3,25,26);
		System.out.println(list.size());
		List<String> list2 = getValue("c://22.xlsx",1,9,10);
		System.out.println(list2.size());
//		List<String> list3 = getValue("c://33.xlsx",3,25,26);
		
		list.removeAll(list2);
		
		System.out.println(list.size());
		
		System.out.println(list.toString());
	}

	static public List<String> getValue(String path,int number,int storeNum,int cardNum) {
		List<String> list = new ArrayList<String>();
		File file = new File(path);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		Excel excel = null;
		try {
			excel = OfficeUtil.readExcel(is, path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		Double storePayAll = 0d;
		Double cardPayAll = 0d;
		Double earnAll = 0d;
		
		for (ExcelSheet excelSheet : excel.getSheets()) {
			ExcelRow excelRow = null;
			List<ExcelCell> excelCells = null;
			int currentIndex = 1;
			for (int i = 1, size = excelSheet.getRows().size(); i < size; i++) {
				currentIndex = i;
				excelRow = excelSheet.getRows().get(i);
				excelCells = excelRow.getCells();
				ExcelCell excelCell = excelRow.getCells().get(number);
				Object value = excelCell.getValue();
				
				Object storePay=excelRow.getCells().get(storeNum).getValue();
				Object cardPay=excelRow.getCells().get(cardNum).getValue();
				
				Object earn=excelRow.getCells().get(30).getValue();
				
				if(storePay!=null){
//					System.out.println("storePay:"+storePay.toString());
					storePayAll+=Double.valueOf(storePay.toString());
				}
				if(cardPay!=null){
					cardPayAll+=Double.valueOf(cardPay.toString());
				}
				
				if (storePay != null&&cardPay != null && (Double.valueOf(storePay.toString())>0 || Double.valueOf(cardPay.toString())>0)) {
					list.add(value.toString());
				}
				
				
//				if(earn!=null){
//					System.out.println("earn:"+earn.toString());
//					earnAll+=Double.valueOf(earn.toString());;
//				}
			}
		}
		System.out.println(storePayAll+"------"+cardPayAll+"------"+earnAll);
		System.out.println("�ܣ�"+storePayAll+cardPayAll);
		return list;
	}
}
