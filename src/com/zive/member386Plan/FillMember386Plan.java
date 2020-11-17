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
public class FillMember386Plan extends BaseDao{

	public static void main(String[] args) throws IOException, ParseException {
		File file = new File("E:\\操作数据\\新加套餐10月到11月13号.xlsx");
		
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
			
			MemberCard memberCard = BaseDao.getMemberCardById(memberCardId);
			MemberCard386Plan plan = MemberCard386PlanDao.getByMemberCardId(memberCardId);
			if(plan==null){
				plan = MemberCard386Plan.init(memberCardId);
			}
			
			//老会员
			if((memberCard.getIsPass()!=null && memberCard.getIsPass()==1) || (memberCard.getIsNewPass()!=null && memberCard.getIsNewPass()==1)){
				if(plan.getTuoke386ConsumptionId()==null || plan.getTuoke386ConsumptionId().length()==0){
//					plan.setIs386Tuoke(0);
					plan.setTuoke386ConsumptionId(consumptionId);
					plan.setTuoke386ShopId(shopId);
					plan.setTuoke386Time(consumptionDate);
				}
			}else if(plan.getIs386Tuoke()==null || plan.getIs386Tuoke()==0){
				plan.setIs386Tuoke(1);
				plan.setTuoke386ConsumptionId(consumptionId);
				plan.setTuoke386ShopId(shopId);
				plan.setTuoke386Time(consumptionDate);
			}
			
			if(plan.getId()==null){
				MemberCard386PlanDao.add(plan);
			}else{
				MemberCard386PlanDao.update(plan);
			}
		}
		getSession().commit();
	}
	
}