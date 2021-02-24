package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.ProjectInfoDetail;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProductSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  003康王店会员筛选 - 档案产品已取完系统未取完
 * @author Administrator
 *
 */
public class CheckKangWangProductIsNotSystemIsYes extends BaseKangWangDao{

	public static void main(String[] args) throws IOException {
		
		File file = new File("D:\\公司数据\\操作数据\\康王店\\整理\\003康王店会员筛选 - 档案产品已取完系统未取完.xlsx");
		
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
		SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");
		ExcelSheet excelSheet = excel.getSheets().get(1);
		
		Map<String, NameToSystemName> kangWangName = getKangWangName();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ i);
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String name = excelCells.get(3).getValue().toString();
			Integer buyNumber = Double.valueOf(excelCells.get(4).getValue().toString()).intValue();
			Integer leftNumber = Double.valueOf(excelCells.get(9).getValue().toString()).intValue();
			String buyDate = excelCells.get(10).getValue().toString().length() > 2 ? excelCells.get(10).getValue().toString() : "未知";
			String buyDate2 = excelCells.get(20).getValue().toString().length() > 2 ? excelCells.get(20).getValue().toString() : "未知";
			boolean isCheck = excelCells.get(31).getValue() == null ? false : Boolean.valueOf(excelCells.get(31).getValue().toString());
			buyDate = buyDate.length()<3 ? buyDate : buyDate.substring(0,buyDate.indexOf(" "));
			buyDate2 = buyDate2.length()<3 ? buyDate2 : buyDate2.substring(0,buyDate2.indexOf(" "));
			String remark = excelCells.get(13).getValue() == null ? "无" : excelCells.get(13).getValue().toString();
			remark = remark.length()==0 ? "无" : remark;
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			if(memberCard == null){
				throw new RuntimeException("手机号不存在");
			}
			
			ProductInfo productInfo = new ProductInfo();
			productInfo.setName(name);
			List<ProductInfo> productList = getProductInfo(productInfo);
			if(productList.size()==0){
				//查询对应的名称
				if(kangWangName.containsKey(name)){
					NameToSystemName nameToSystemName = kangWangName.get(name);
					String secondName = "";
					Integer time = nameToSystemName.getTime();
					if(nameToSystemName.getNewName().equals("旧项目")){
						secondName = getSecondName(nameToSystemName.getOldName());
					}
					if(nameToSystemName.getType().equals("product")){
						productInfo.setName(nameToSystemName.getNewName());
						productList = getProductInfo(productInfo);
						if(productList.size()==0){
							throw new RuntimeException("找不到项目信息");
						}
					}
				}
			}
			
			List<Map<String, Object>> productDetailLeft = getProductDetailLeft(memberCard.getId());
			int index = 0;
			for (Map<String, Object> map : productDetailLeft) {
				String id = map.get("productDetailId").toString();
				String oldName = map.get("name").toString();
				int oldBuyNumber = Double.valueOf(map.get("buy_number").toString()).intValue();
				int oldLeftNumber = Double.valueOf(map.get("left_number").toString()).intValue();
				String oldCreateDate = map.get("create_date").toString();
				oldCreateDate = oldCreateDate.length()<3 ? oldCreateDate : oldCreateDate.substring(0,oldCreateDate.indexOf(" "));
				
				String oldRemark = map.get("remark") == null ? "无" : map.get("remark").toString();
				
				boolean isOk = oldRemark.contains("康王纸质档案录入系统")?true:false;
				
				if(!isOk && name.contains(oldName) && oldBuyNumber == buyNumber && oldCreateDate.equals(buyDate2)){
					++index;
					System.out.println(id);
					
					ProductDetailConsumption detail = new ProductDetailConsumption();
					detail.setId(id);
					detail.setIsFail(1);
					detail.setRemark("康王纸质档案录入系统，分类：档案产品已取完系统未取完，备注：" + remark + "，旧备注：" + oldRemark);
					int updateProductDetailConsumption = ProductSellDao.updateProductDetailConsumption(detail);
					
					if(updateProductDetailConsumption == 0){
						throw new RuntimeException("更新失败");
					}
				}
			}
			
			if(index == 0){
				System.out.println(buyDate2+"==="+"，"+"找不到:"+phone+":"+name);
			}
		}
		
		getSession().commit();
	}
}
