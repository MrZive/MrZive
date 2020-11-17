package com.zive.materialPrice;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import javax.management.RuntimeErrorException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MaterialInfo;
import com.zive.dataOut.entity.MaterialInventory;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectInfoDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;
import com.zive.shop.entity.ProjectConsumption;
import com.zive.shop.entity.ProjectDetailConsumption;

public class MaterialPrice extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("C:\\7月库存.xls");
		
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
			
			String shopName = String.valueOf(excelCells.get(2).getValue());
			String materialNo = String.valueOf(excelCells.get(3).getValue());
			String materialName = String.valueOf(excelCells.get(4).getValue());
			Double materialPrice = new Double(String.valueOf(excelCells.get(8).getValue()));
			
			if(shopName.equals("总仓") || shopName.equals("深圳子公司") || shopName.equals("孝感子公司")){
//				shopName = "000000总部店";
				continue;
			}
			
			//查找门店
			Shop shop = new Shop();
			shop.setName(shopName);
			shop.setStatus(0);
			List<Shop> shopList = getShop(shop);
			if(shopList.size()==0){
//				throw new RuntimeException(shopName);
				System.out.println("未有门店："+shopName);
				continue;
			}
			shop = shopList.get(0);
			
			//查找物料
			MaterialInfo materialInfo = BaseDao.getMaterialByNo(materialNo);
			if(materialInfo == null){
//				throw new RuntimeException(materialName);
				System.out.println("未有物料："+materialNo+ "：" +materialName);
				continue;
			}
			
			//查找库存
			MaterialInventory materialInventory = BaseDao.getMaterialInventoryByIdAndShopId(materialInfo.getId(), shop.getId());
			if(materialInventory == null){
				System.out.println("未有库存的门店与物料："+shop.getName()+ "：" +materialName);
				System.out.println("未有库存的门店与物料："+shop.getId()+ "：" + materialInfo.getId());
				continue;
			}
			
			MaterialInfo materialCon = new MaterialInfo();
			materialCon.setShopId(shop.getId());
			materialCon.setMaterialId(materialInfo.getId());
			materialCon.setMaterialPrice(materialPrice);
			int number = updateMaterialInventoryPrice(materialCon);
			
			if(number == 0){
				throw new RuntimeException();
			}
			
//			if(i==1){
//				System.out.println("已更新："+shop.getId()+ "：" + materialInfo.getId()+ "：" + materialPrice);
//				getSession().commit();
//				break;
//			}
		}
		
		getSession().commit();
		closeSession();
	}
	
	static public int updateMaterialInventoryPrice(MaterialInfo materialInfo){
		int number = getSession().update("com.zive.materialPrice.updateMaterialInventoryPrice",materialInfo);
		return number;
	}
}


