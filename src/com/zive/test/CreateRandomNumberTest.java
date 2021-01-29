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

public class CreateRandomNumberTest {

	public static void main(String[] args) throws IOException {
		JSONArray list = new JSONArray();
		for(int i = 1;i<1100;i++){
			Random r = new Random();
			String numberStr = "202100"+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10)+r.nextInt(10);
			RandomNumber numberO = new RandomNumber();
			numberO.setNumber(numberStr);
			
			System.out.println(numberStr);
			list.add(numberO);
		}
		ExcelUtilForDO.toFile(list, RandomNumber.class);
	}
}

@TableName("随机数字")
class RandomNumber{
	@FieldName("随机数字")
	private String number;

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
