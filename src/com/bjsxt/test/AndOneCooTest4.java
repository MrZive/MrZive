package com.bjsxt.test;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.alibaba.fastjson.JSON;
import com.bjsxt.pojo.Earn;
import com.bjsxt.pojo.OneCooDetail;
import com.bjsxt.pub.Excel;
import com.bjsxt.pub.ExcelCell;
import com.bjsxt.pub.ExcelRow;
import com.bjsxt.pub.ExcelSheet;
import com.bjsxt.pub.OfficeUtil;

public class AndOneCooTest4 {
	public static void main(String[] args) throws IOException, ParseException {
		InputStream is = Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession(false);
		Map<String,Object> kxi = new HashMap<>();
		List<Earn> selectList = session.selectList("com.bjsxt.getOnePercentEarn",kxi);
		
		
		File file = new File("C://22/统计后合作表格2.xlsx");
		
		List<Integer> index = new ArrayList<>();
		
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
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			Object value = excelCells.get(3).getValue();
			boolean flag = false;
			if(value != null) {
				for(Earn earn : selectList){
					if(earn.getConsumption_id().trim().equals(value.toString().trim())){
						flag = true;
						break;
					}
				}
				if(!flag){
					index.add(i);
				}
			}
		}
		session.close();
		
		
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("D:/a.txt");//创建文本文件
			
			for(int i=0;i<index.size();i++){//循环写入
				fileWriter.write(Integer.valueOf(index.get(i))+1+"\r\n");//写入 \r\n换行
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(index.size());
	}
	
}
