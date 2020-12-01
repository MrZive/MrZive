package com.zive.member386Plan;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.annotaion.FieldName;
import com.zive.dataOut.annotaion.TableName;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProductGet;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.member386Plan.entity.MemberCard386Plan;
import com.zive.member386Plan.entity.MemberPassStatus;
import com.zive.member386Plan.entity.MemberPassStatus.EnumType;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改成老会员
 * @author Administrator
 *
 */
public class FillMember386Plan2Data4 extends BaseDao{

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("C:\\Users\\Administrator\\Desktop\\小罐艾\\通络净颜20个新客名单1120.xlsx");
		
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
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String shopName = excelCells.get(0).getValue().toString();
			String consumptionId = excelCells.get(1).getValue().toString();
			String phone = excelCells.get(6).getValue().toString();
			String memberName = excelCells.get(7).getValue().toString();
			String consumptionDateStr = excelCells.get(13).getValue().toString();
			Date consumptionDate = sdf.parse(consumptionDateStr);
			
			Shop shop = new Shop();
			shop.setName(shopName);
			shop = BaseDao.getShop(shop).get(0);
			
			MemberCard memberCard = BaseDao.getMemberCardByPhone(phone);
			MemberCard386Plan plan = MemberCard386PlanDao.getByMemberCardId(memberCard.getId());
			if(plan==null){
				plan = MemberCard386Plan.init(memberCard.getId());
			}
//			memberCard.setMemberCard386Plan(plan);
			
			
			memberCard.setIsPass(1);
			memberCard.setIsAbandon(0);
			memberCard.setIsSleep(0);
			memberCard.setPassConsumptionId(consumptionId);
			memberCard.setPassShopId(shop.getId());
			memberCard.setPassTime(consumptionDate);
			
			System.out.println(JSON.toJSONString(memberCard));
			
			
//			updateMemberCardPass(memberCard);
		}
//		getSession().commit();
	}
	
	static public int updateMemberCardPass(MemberCard member) {
		return getSession().insert("com.zive.update.updateMemberCardPass", member);
	}
	
}