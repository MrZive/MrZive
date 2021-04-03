package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.entity.ActivityShop;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
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
public class AddShopToActivityTest extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\批量设置套餐门店2.xlsx");
		
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
		
		List<String> activityList = new ArrayList<>();
		
		for(int i = 0; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(0).getValue().toString();
			Activity activity = new Activity();
			activity.setName(name);
			Activity info = getActivity(activity).get(0);
			
			activityList.add(info.getId());
		}
		
		ExcelRow excelRow2 = null;
		List<ExcelCell> excelCells2 = null;
		ExcelSheet excelSheet2 = excel.getSheets().get(1);
		
		List<String> shopList = new ArrayList<>();
		
		for(int i = 0; i < excelSheet2.getRows().size();i++){
			excelRow2 = excelSheet2.getRows().get(i);
			excelCells2 = excelRow2.getCells();
			String name = excelCells2.get(0).getValue().toString();
			String no = name.substring(0, 3);
			Shop con = new Shop();
			con.setNo(no);
			Shop shop = getShop(con).get(0);
			
			shopList.add(shop.getId());
		}
		
		int index = 0;
		for (String activity : activityList) {
			for (String shopId : shopList) {
				ActivityShop activityShop = new ActivityShop();
				activityShop.setId(UUID.randomUUID().toString());
				activityShop.setActivityId(activity);
				activityShop.setShopId(shopId);
				int addActivityShop = addActivityShop(activityShop);
				if(addActivityShop==0){
					throw new RuntimeException("添加失败！");
				}
				System.out.println(++index);
			}
		}
		
		getSession().commit();
	}
	
	
}