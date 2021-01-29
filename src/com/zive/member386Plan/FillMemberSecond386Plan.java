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
public class FillMemberSecond386Plan extends BaseDao{

	public static void main(String[] args) throws IOException, ParseException {
//		File file = new File("E:\\操作数据\\12月份1到13号所有购买太极健胸二转的信息.xlsx");
		File file = new File("E:\\操作数据\\二转转套餐11月所有定金的会员.xlsx");
		
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
		
		for(int i = 1; i < excelSheet.getRows().size();i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String memberCardId = excelCells.get(1).getValue().toString();
			String shopId = excelCells.get(3).getValue().toString();
			String consumptionId = excelCells.get(4).getValue().toString();
			String dateStr = excelCells.get(5).getValue().toString();
			Date consumptionDate = sdf.parse(dateStr);
			String oweStr = excelCells.get(7).getValue().toString();
			BigDecimal owe = new BigDecimal(oweStr);
			
			MemberCard memberCard = BaseDao.getMemberCardById(memberCardId);
			MemberCard386Plan plan = MemberCard386PlanDao.getByMemberCardId(memberCardId);
			if(plan==null){
				plan = MemberCard386Plan.init(memberCardId);
			}
			
			boolean update = false;
			
			//老会员
			if((memberCard.getIsPass()!=null && memberCard.getIsPass()==1) || (memberCard.getIsNewPass()!=null && memberCard.getIsNewPass()==1) || (plan.getIs386Pass()!=null && plan.getIs386Pass()==1)){
					if(owe.compareTo(BigDecimal.ZERO) == 1){
						plan.setIs386Second(0);
					}else{
						plan.setIs386Second(1);
					}
					plan.setSecond386ConsumptionId(consumptionId);
					plan.setSecond386ShopId(shopId);
					plan.setSecond386Time(consumptionDate);
					update = true;
			}
			
			if(update){
				int update2 = MemberCard386PlanDao.update(plan);
				System.out.println(update2);
			}
		}
		getSession().commit();
	}
	
}