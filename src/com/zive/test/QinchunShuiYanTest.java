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

public class QinchunShuiYanTest {

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\青春水淹业绩初始化\\3月水漾订单最终版4.1-单独.xlsx");
		
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
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			Object shop = excelCells.get(0).getValue();
			String shopName = String.valueOf(shop);
			
			Object value = excelCells.get(1).getValue();
			BigDecimal earn = new BigDecimal(String.valueOf(value));
			
			if(map.containsKey(shopName)){
				BigDecimal bigDecimal = map.get(shopName);
				BigDecimal add = bigDecimal.add(earn);
				map.put(shopName, add);
			}else{
				map.put(shopName, earn);
			}
			all = all.add(earn);
		}
		
		Set<Entry<String,BigDecimal>> entrySet = map.entrySet();
		
		JSONArray jsonArray = new JSONArray();
		for (Iterator iterator = entrySet.iterator(); iterator.hasNext();) {
			Entry<String, BigDecimal> entry = (Entry<String, BigDecimal>) iterator.next();
			
			String key = entry.getKey();
			BigDecimal value = entry.getValue();
			
			Shop shop = new Shop();
			if(key.equals("银湾店")){
				shop.setName("063银湾店");
			}else{
				shop.setNameLike(key);
			}
			shop.setNameNotLike("天使之纹");
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
			
			
//			JSONObject pc = new JSONObject();
//			pc.put("shopName", key);
//			pc.put("shopNo", oneShop.getNo());
//			pc.put("earn", value.toString());
			ProjectQC pc = new ProjectQC();
			pc.setShopName(key);
			pc.setShopNo(oneShop.getNo());
			pc.setEarn(value.toString());
			
			jsonArray.add(pc);
		}
		
		System.out.println(all);
		
		ExcelUtilForDO.toFile(jsonArray, ProjectQC.class);
	}
}

@TableName("3月水漾订单最终版")
class ProjectQC{
	@FieldName("门店名")
	private String shopName;
	
	@FieldName("NO")
	private String shopNo;
	
	@FieldName("业绩总和")
	private String earn;

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopNo() {
		return shopNo;
	}

	public void setShopNo(String shopNo) {
		this.shopNo = shopNo;
	}

	public String getEarn() {
		return earn;
	}

	public void setEarn(String earn) {
		this.earn = earn;
	}
	
}
