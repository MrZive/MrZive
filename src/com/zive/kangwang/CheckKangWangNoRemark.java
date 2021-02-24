package com.zive.kangwang;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.java.BaseDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 *  设置旧项目备注
 * @author Administrator
 *
 */
public class CheckKangWangNoRemark extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\康王店\\003康王店会员筛选 - 无备注.xlsx");
		
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
		
		Set<String> setList = new LinkedHashSet<String>();
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String name = excelCells.get(3).getValue().toString();
			String name2 = excelCells.get(4).getValue() == null ? "" : excelCells.get(4).getValue().toString();
			int danBuyNumber = Double.valueOf(excelCells.get(6).getValue().toString()).intValue();
			int danLeftNumber = Double.valueOf(excelCells.get(11).getValue().toString()).intValue();
			
			int buyNumber = Double.valueOf(excelCells.get(16).getValue().toString()).intValue();
			int leftNumber =Double.valueOf(excelCells.get(21).getValue().toString()).intValue();
			
			Double checkNumber = Double.valueOf(excelCells.get(32).getValue().toString());
			boolean isCheck = excelCells.get(33).getValue() == null ? false : Boolean.valueOf(excelCells.get(33).getValue().toString().toLowerCase());
			if(isCheck){
				continue;
			}
			
			if(checkNumber==0){
				continue;
			}
			
			if(danLeftNumber == 0){
				continue;
			}
			if(name.contains("旧项目")){
				name = "旧项目";
			}
			System.out.println(i);
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			
			//处理项目
			List<Map<String, Object>> projectDetailLeft = getProjectDetailLeft(memberCard.getId());
			int index = 0;
			
			for (Map<String, Object> map : projectDetailLeft) {
				String oldName = map.get("name").toString();
				int oldBuyNumber = Double.valueOf(map.get("buy_number").toString()).intValue();
				int oldLeftNumber = Double.valueOf(map.get("number").toString()).intValue();
				
				if((oldName.equals(name) || oldName.equals(name2)) && oldLeftNumber == leftNumber && oldBuyNumber == buyNumber){
					if(++index>1){
						System.out.println("出现多个:"+phone+":"+oldName);
					}
				}
			}
			
			//处理产品
			List<Map<String, Object>> productDetailLeft = getProductDetailLeft(memberCard.getId());
			
			for (Map<String, Object> map : productDetailLeft) {
				String oldName = map.get("name").toString();
				int oldBuyNumber = Double.valueOf(map.get("buy_number").toString()).intValue();
				int oldLeftNumber = Double.valueOf(map.get("left_number").toString()).intValue();
				
				if((oldName.equals(name) || oldName.equals(name2)) && oldLeftNumber == leftNumber && oldBuyNumber == buyNumber){
					if(++index>1){
						System.out.println("出现多个:"+phone+":"+oldName);
					}
				}
			}
			
			if(index == 0){
				System.out.println("找不到:"+phone+":"+name);
			}
		}
		
//		getSession().commit();
	}
	
	static public int updateProjectDetailSecondName(Map<String,Object> map){
		int update = getSession().update("com.zive.kangwang.updateProjectDetailSecondName", map);
		return update;
	}
	
	
}
