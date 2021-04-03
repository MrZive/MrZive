package com.zive.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.zive.dataOut.entity.ProjectCooperationWxDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectCooperationWxSellDao;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

/**
 * 修改纹绣剩余项目
 * @author Administrator
 *
 */
public class UpdateWenXiuLeftNumber extends BaseDao{

	public static void main(String[] args) throws IOException {
		File file = new File("D:\\公司数据\\操作数据\\副本199纹绣券.xlsx");
		
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
			Object orderId = excelCells.get(5).getValue();
			String orderIdStr = orderId.toString();
			if(!orderIdStr.equals("A1731577242531306")){
				orderIdStr = orderIdStr.contains("wx")?orderIdStr:orderIdStr+"_wx";
			}
			System.out.println(orderId);
			
			ProjectCooperationWxDetailConsumption detail = new ProjectCooperationWxDetailConsumption();
			detail.setConsumptionId(orderIdStr);
			List<ProjectCooperationWxDetailConsumption> list = ProjectCooperationWxSellDao.getProjectCooperationWxDetailConsumption(detail);
			
			ProjectCooperationWxDetailConsumption info = list.get(0);
			
			ProjectCooperationWxDetailConsumption con = new ProjectCooperationWxDetailConsumption();
			con.setId(info.getId());
			con.setNumber(0);
			int update = ProjectCooperationWxSellDao.updateProjectCooperationWxDetailConsumption(con);
			if(update==0){
				throw new RuntimeException();
			}
			MemberCard memberCardById = getMemberCardById(info.getMemberCardId());

			MemberCard memberCard = new MemberCard();
			memberCard.setId(info.getMemberCardId());
			memberCard.setWenxiuBalance(memberCardById.getWenxiuBalance()-(info.getPrice() * info.getNumber()));
			int updateMemberCard = updateMemberCard(memberCard);
			if(updateMemberCard == 0){
				throw new RuntimeException();
			}
			System.out.println(memberCard.getId());
			System.out.println(memberCard.getWenxiuBalance());
		}
		getSession().commit();
	}

}