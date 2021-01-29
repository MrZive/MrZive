package com.zive.operationData;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class AddProjectToMember extends BaseDao{

	public static void main(String[] args) {
		File file = new File("E:\\操作数据\\工作簿1(5).xlsx");
		
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
		
		
		for(int i = 1, size = excelSheet.getRows().size();i< size;i++){
			excelRow = excelSheet.getRows().get(i);
			excelCells = excelRow.getCells();
			String orderId = String.valueOf(excelCells.get(1).getValue());
			
			ProjectDetailConsumption con = new ProjectDetailConsumption();
			con.setConsumptionId(orderId);
			List<ProjectDetailConsumption> projectDetailConsumption = ProjectSellDao.getProjectDetailConsumption(con);
			
			System.out.println(orderId + "=====" + projectDetailConsumption.size());
			
			projectDetailConsumption = projectDetailConsumption.stream().filter(detail-> 
					detail.getActivityId().equals("0f4aa753-fa1c-4551-bcfa-1f09a69d8c33")
					||
					detail.getActivityId().equals("307ade5a-4ae0-4440-9c6f-87c54282fbf4")
					||
					detail.getActivityId().equals("b88a30b4-7ecd-48a5-9ba6-60a7197ece68")
					).collect(Collectors.toList());
			
			for (int index = 0 ; index < projectDetailConsumption.size() ; index++) {
				ProjectDetailConsumption detail = projectDetailConsumption.get(index);
				String id = detail.getId();
				String consumptionId = detail.getConsumptionId();
				String consumptionSetId = detail.getConsumptionSetId();
				Integer buyNumber = detail.getBuyNumber();
				Integer leftNumber = detail.getNumber();
				Double price = detail.getPrice();
				Double experiencePrice = detail.getExperiencePrice();
				Double marketPrice = detail.getMarketPrice();
				Double promotionPrice = detail.getPromotionPrice();
				
				ProjectDetailConsumption newDetail = JSON.parseObject(JSON.toJSONString(detail), ProjectDetailConsumption.class);
				newDetail.setId("set"+new Date().getTime());
				newDetail.setProjectId("d56f4d2d-b7af-4508-b6c7-0b51c64b55a6");
				
				Integer buyNumberTemp = detail.getBuyNumber() / 2;
				newDetail.setBuyNumber(detail.getBuyNumber() - buyNumberTemp);
				detail.setBuyNumber(detail.getBuyNumber() - newDetail.getBuyNumber());
				
				Integer numberTemp = detail.getNumber() / 2;
				newDetail.setNumber(detail.getNumber() - numberTemp);
				detail.setNumber(detail.getNumber() - newDetail.getNumber());
				
				newDetail.setPayment(newDetail.getBuyNumber() * newDetail.getPrice());
				detail.setPayment(detail.getBuyNumber() * detail.getPrice());
				
				Double storePayTemp = detail.getStorePay() / 2;
				newDetail.setStorePay(detail.getStorePay() - storePayTemp);
				detail.setStorePay(detail.getStorePay() - newDetail.getStorePay());
				
				
				Double bankcardPayTemp = detail.getBankcardPay() / 2;
				newDetail.setBankcardPay(detail.getBankcardPay() - bankcardPayTemp);
				detail.setBankcardPay(detail.getBankcardPay() - newDetail.getBankcardPay());
				
				
				Double cashPayTemp = detail.getCashPay() / 2;
				newDetail.setCashPay(detail.getCashPay() - cashPayTemp);
				detail.setCashPay(detail.getCashPay() - newDetail.getCashPay());
				
				
				Double oweTemp = detail.getOwe() / 2;
				newDetail.setOwe(detail.getOwe() - oweTemp);
				detail.setOwe(detail.getOwe() - newDetail.getOwe());
				
				
				Double buyOweTemp = detail.getBuyOwe() / 2;
				newDetail.setBuyOwe(detail.getBuyOwe() - buyOweTemp);
				detail.setBuyOwe(detail.getBuyOwe() - newDetail.getBuyOwe());
				
				
				Double effectiveEarnTemp = detail.getEffectiveEarn() / 2;
				newDetail.setEffectiveEarn(detail.getEffectiveEarn() - effectiveEarnTemp);
				detail.setEffectiveEarn(detail.getEffectiveEarn() - newDetail.getEffectiveEarn());
				
				
				Double realPaymentTemp = detail.getRealPayment() / 2;
				newDetail.setRealPayment(detail.getRealPayment() - realPaymentTemp);
				detail.setRealPayment(detail.getRealPayment() - newDetail.getRealPayment());
				
				if(detail.getActivityId().equals("0f4aa753-fa1c-4551-bcfa-1f09a69d8c33")){
					//三焦水润C
					System.out.println(id+"====="+consumptionId+"====="+consumptionSetId+"====="+buyNumber+"====="+leftNumber+"====="+price+"====="+experiencePrice+"===="+marketPrice+"===="+promotionPrice);
				}else if(detail.getActivityId().equals("307ade5a-4ae0-4440-9c6f-87c54282fbf4")){
					//三焦水润B
					System.out.println(id+"====="+consumptionId+"====="+consumptionSetId+"====="+buyNumber+"====="+leftNumber+"====="+price+"====="+experiencePrice+"===="+marketPrice+"===="+promotionPrice);
				}else if(detail.getActivityId().equals("b88a30b4-7ecd-48a5-9ba6-60a7197ece68")){
					//三焦水润D
					System.out.println(id+"====="+consumptionId+"====="+consumptionSetId+"====="+buyNumber+"====="+leftNumber+"====="+price+"====="+experiencePrice+"===="+marketPrice+"===="+promotionPrice);
				}else{
					System.out.println("------------");
					continue;
				}
				
				ProjectSellDao.updateProjectDetailConsumption(detail);
				ProjectSellDao.addProjectDetailConsumption(newDetail);
			}
			
		}
		
		getSession().commit();
		
	}
}
