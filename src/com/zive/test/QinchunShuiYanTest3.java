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
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 【5月份线上产品销售】
 * @author Administrator
 *
 */
public class QinchunShuiYanTest3 {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\青春水淹业绩初始化\\5月线上产品、项目门店分配业绩.xlsx");
		
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
		
		Map<String,BigDecimal> projectMap = new HashMap<>();
		Map<String,BigDecimal> productMap = new HashMap<>();
		
		BigDecimal all = BigDecimal.ZERO;
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			//类型
			String type = String.valueOf(excelCells.get(1).getValue());
			//门店
			String no = String.valueOf(excelCells.get(5).getValue());
			if(no.contains(".")){
				no = no.substring(0,no.indexOf("."));
			}
			String shopName = no + String.valueOf(excelCells.get(4).getValue());
			//业绩
			BigDecimal earn = new BigDecimal(String.valueOf(excelCells.get(10).getValue()));
			
			if(type.equals("项目")){
				if(projectMap.containsKey(shopName)){
					BigDecimal bigDecimal = projectMap.get(shopName);
					BigDecimal add = bigDecimal.add(earn);
					projectMap.put(shopName, add);
				}else{
					projectMap.put(shopName, earn);
				}
			}else if(type.equals("产品")){
				if(productMap.containsKey(shopName)){
					BigDecimal bigDecimal = productMap.get(shopName);
					BigDecimal add = bigDecimal.add(earn);
					productMap.put(shopName, add);
				}else{
					productMap.put(shopName, earn);
				}
			}else{
				throw new RuntimeException();
			}
			all = all.add(earn);
		}
		
		Set<Entry<String,BigDecimal>> entrySet = projectMap.entrySet();
		JSONArray projectJsonArray = new JSONArray();
		for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, BigDecimal> entry = (Entry<String, BigDecimal>) iterator.next();
			
			String key = entry.getKey();
			BigDecimal value = entry.getValue();
			
			Shop shop = new Shop();

			shop.setName(key);
			shop.setStatus(0);
			List<Shop> shopList = BaseDao.getShop(shop);
			Shop oneShop = null;
			if(shopList.size()>1){
				for (Shop shop2 : shopList) {
					System.out.println(shop2.getName());
				}
			}else if(shopList.size()==0){
				System.out.println(key);
			}
			
			oneShop = shopList.get(0);
			
			ProjectQC pc = new ProjectQC();
			pc.setShopName(key);
			pc.setShopNo(oneShop.getNo());
			pc.setEarn(value.toString());
			
			projectJsonArray.add(pc);
		}
		
		Set<Entry<String,BigDecimal>> entrySetP = productMap.entrySet();
		JSONArray productJsonArray = new JSONArray();
		for (Iterator iterator = entrySetP.iterator(); iterator.hasNext();) {
			Entry<String, BigDecimal> entry = (Entry<String, BigDecimal>) iterator.next();
			
			String key = entry.getKey();
			BigDecimal value = entry.getValue();
			
			Shop shop = new Shop();

			shop.setName(key);
			shop.setStatus(0);
			List<Shop> shopList = BaseDao.getShop(shop);
			Shop oneShop = null;
			if(shopList.size()>1){
				for (Shop shop2 : shopList) {
					System.out.println(shop2.getName());
				}
			}else if(shopList.size()==0){
				System.out.println(key);
			}
			
			oneShop = shopList.get(0);
			
			ProductQC pc = new ProductQC();
			pc.setShopName(key);
			pc.setShopNo(oneShop.getNo());
			pc.setEarn(value.toString());
			
			productJsonArray.add(pc);
		}
		
		System.out.println(all);
		
		ExcelUtilForDO.toFile(projectJsonArray, ProjectQC.class);
		ExcelUtilForDO.toFile(productJsonArray, ProductQC.class);
	}
}