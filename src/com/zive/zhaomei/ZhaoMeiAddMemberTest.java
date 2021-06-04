package com.zive.zhaomei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class ZhaoMeiAddMemberTest extends BaseKangWangDao{

public static void main(String[] args) throws IOException, ParseException {
		
		File file = new File("D:\\公司数据\\操作数据\\找美网\\会员.xls");
		
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
		ExcelSheet excelSheet = excel.getSheets().get(0);
		
		Map<String, NameToSystemName> kangWangName = getKangWangName();
		
		for(int i = 2; i < excelSheet.getRows().size();i++){
			System.out.println("当前行数："+ (i+1));
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String name = excelCells.get(1).getValue().toString();
			String type = excelCells.get(2).getValue().toString();
			String phone = excelCells.get(4).getValue().toString();
			String shopName = excelCells.get(5).getValue().toString();
			String store = excelCells.get(7).getValue().toString();
			String owe = excelCells.get(8).getValue().toString();
			
			String createDate = excelCells.get(9).getValue().toString();
			String lastServiceTime = excelCells.get(10).getValue().toString();
			
			String birthday = excelCells.get(11).getValue().toString();
			if(birthday.length()>5){
				String[] birthdayList = birthday.split("-");
				String month = birthdayList[1];
				String day = birthdayList[2];
				if(month.length()==1){
					month = "0" + month;
				}
				if(day.length()==1){
					day = "0" + day;
				}
				birthday=month+"-"+day;
			}else{
				birthday = null;
			}
			String fromType = "227";
			
			
			MemberCard memberCard = getMemberCardByPhone(phone);
			List<Shop> shopList = getShop(new Shop(){{setName(shopName);}});
			if(shopList.size()==0){
				continue;
			}
			Shop shop = shopList.get(0);
			if(memberCard == null){
				memberCard = new MemberCard();
				memberCard.setId("zhaoMei"+System.currentTimeMillis());
				memberCard.setPhone(phone);
				memberCard.setName(name);
				memberCard.setNickName(name);
				memberCard.setShopId(shop.getId());
				memberCard.setIsIndroduce(0);
				memberCard.setStoreBalance(Double.valueOf(store));
				memberCard.setOweBalance(Double.valueOf(owe));
				memberCard.setFromType(fromType);
				memberCard.setCreateDate(sdf.parse(createDate));
				memberCard.setBirthday(birthday);
				memberCard.setChannelId(1);
				addMemberCard(memberCard);
			}else{
				MemberCard change = new MemberCard();
				change.setId(memberCard.getId());
//				change.setName(name);
//				change.setBirthday(birthday);
				change.setStoreBalance(memberCard.getStoreBalance()+Double.valueOf(store));
				change.setOweBalance(memberCard.getOweBalance()+Double.valueOf(owe));
				updateMemberCard(change);
			}
			
			
		}
		getSession().commit();
		getSession().close();
	}
}
