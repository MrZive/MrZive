package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.zive.dataOut.entity.Activity;
import com.zive.dataOut.entity.ActivityShop;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.KangWangOperationAdd;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ChangeShopIdTest extends BaseKangWangDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\改变归属门店.xlsx");
		
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
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(0).getValue().toString();
			String oldShopName = excelCells.get(1).getValue().toString();
			String newShopName = excelCells.get(2).getValue().toString();
			String phone = excelCells.get(3).getValue().toString();
			
			String operation = excelCells.get(5).getValue().toString();
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			
			Shop shop = getShop(new Shop(){{
				setName(oldShopName);
			}}).get(0);
			List<Shop> shopLIst = getShop(new Shop(){{
				setName(newShopName);
			}});
			if(shopLIst.size()==0){
				System.out.println(newShopName);
			}
			Shop newShop = shopLIst.get(0);
			if(memberCard==null){
				System.out.println(name+"找不到");
				continue;
			}
			if(memberCard.getPassShopId()==null){
				System.out.println(memberCard.getName()+"没有归属门店");
			}else if(!memberCard.getPassShopId().equals(shop.getId())){
				System.out.println(memberCard.getName()+"我们不一样");
			}
			
			if(operation.equals("pass")){
				continue;
			}
			
			MemberCard newMemberCard = new MemberCard();
			newMemberCard.setId(memberCard.getId());
			newMemberCard.setPassShopId(newShop.getId());
			
			updateMemberCard(newMemberCard);
		}
		
		getSession().commit();
	}
}
