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
public class CheckKangWangRenameOldProject extends BaseKangWangDao{

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
			System.out.println(i);
			excelCells = excelRow.getCells();
			String phone = excelCells.get(2).getValue().toString();
			String name = excelCells.get(3).getValue().toString();
			String name2 = excelCells.get(4).getValue() == null ? "" : excelCells.get(4).getValue().toString();
			int danBuyNumber = Double.valueOf(excelCells.get(6).getValue().toString()).intValue();
			int danLeftNumber = Double.valueOf(excelCells.get(11).getValue().toString()).intValue();
			
			int buyNumber = Double.valueOf(excelCells.get(16).getValue().toString()).intValue();
			String leftNumberString = excelCells.get(21).getValue().toString();
			System.out.println(leftNumberString);
			int leftNumber =Double.valueOf(excelCells.get(21).getValue().toString()).intValue();
			
			if(danLeftNumber == 0){
				continue;
			}
			if(!name.contains("旧项目")){
				continue;
			}
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			
			//处理名称
			List<Map<String, Object>> projectDetailLeft = getProjectDetailLeft(memberCard.getId());
			int index = 0;
			
			for (Map<String, Object> map : projectDetailLeft) {
				String oldName = map.get("name").toString();
				int oldBuyNumber = Double.valueOf(map.get("buy_number").toString()).intValue();
				int oldLeftNumber = Double.valueOf(map.get("number").toString()).intValue();
				
				if(oldName.contains("旧项目") && oldLeftNumber == leftNumber && oldBuyNumber == buyNumber){
					if(++index>1){
						System.out.println("出现多个:"+phone+":"+oldName);
					}
					
					if(name.contains("（")){
						String secondName = name.substring(name.indexOf("（")+1, name.lastIndexOf("）"));
						System.out.println(secondName);
						
						Map<String,Object> con = new HashMap<>();
						con.put("id", map.get("projectDetailId"));
						con.put("secondName", secondName);
						
						int updateProjectDetailSecondName = updateProjectDetailSecondName(con);
					}
				}
			}
			if(index==0){
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
