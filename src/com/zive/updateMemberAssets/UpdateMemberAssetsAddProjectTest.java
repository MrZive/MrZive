package com.zive.updateMemberAssets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zive.dataOut.entity.MemberCard;
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

public class UpdateMemberAssetsAddProjectTest extends BaseKangWangDao{
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");



	public static boolean checkAndAddProjdectInfo(
			String shopId, String name, int buyNumber, Integer leftNumber, Date createDate,String remark, double price, String memberCardId,String type) {
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setName(name);
		List<ProjectInfo> projectList = getProjectInfo(projectInfo);
		
		if(projectList.size() > 0){//项目处理逻辑
			ProjectInfo info = projectList.get(0);
			
			Integer serviceTime = getProjectServiceTime(info.getId(),shopId);
			
			addZhaoMeiProjectDetail(memberCardId, shopId, info.getId(), price, buyNumber, leftNumber, serviceTime,remark, createDate,type);
		
//			MemberCard change = new MemberCard();
//			change.setId(memberCard.getId());
//			change.setTreatmentBalance(memberCard.getTreatmentBalance()+leftPayment);
//			change.setOweBalance(memberCard.getOweBalance()+owe);
//			updateMemberCard(change);
			return true;
		}else{
//			throw new RuntimeException("找不到对应的信息:" + name);
			if(type.equals("project")){
				System.out.println("找不到对应的信息:" + name);
			}
			return false;
		}
	}



	static public int addZhaoMeiProjectDetail(String memberCardId,String shopId,String projectId,Double price,Integer buyNumber,Integer number,Integer serviceTime,String remark,Date createDate,String type){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		Date date = new Date();
		detail.setActivityId("");
		detail.setAliPay(0D);
		detail.setBuyType("Assets");
		detail.setChannelId(0);
		detail.setConsumptionId("Assets"+date.getTime());
		detail.setConsumptionProjectId("Assets"+date.getTime());
		detail.setConsumptionSetId("");
		detail.setCoupon("");
		detail.setCreateDate(createDate);
		detail.setEndDate(null);
		detail.setId(UUID.randomUUID().toString());
		detail.setInvalidNumber(number);
		detail.setIsBook(0);
		detail.setIsCashCoupon(null);
		detail.setIsCount(1);
		detail.setIsFail(0);
		detail.setIsPay(1);
		detail.setIsSend(0);
		detail.setIsTuoke(0);
		detail.setMarketPrice(0D);
		detail.setPointPay(0D);
		detail.setRemark("资产盘点，类型："+type+"，修改时间："+sdf.format(date)+"，备注："+remark);
		detail.setServiceType(0);
		detail.setShopId(shopId);
		detail.setWechatPay(0D);
		detail.setExperiencePrice(0D);
		detail.setPromotionPrice(0D);
		
		detail.setStorePay(0D);
		detail.setBankcardPay(0D);
		detail.setBuyNumber(buyNumber);
		detail.setBuyOwe(0D);
		detail.setCashPay(0D);
		detail.setMemberCardId(memberCardId);
		detail.setNumber(number);
		detail.setOwe(0D);
		
		double payment = setDoubleScale(price * buyNumber);
		detail.setPayment(payment);
		
		detail.setPrice(price);
		detail.setProjectId(projectId);
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setEffectiveEarn(detail.getRealPayment());
		detail.setSecondName(null);
		detail.setServiceTime(serviceTime);
		
		int addProjectDetailConsumption = ProjectSellDao.addProjectDetailConsumption(detail);
		if(addProjectDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		
		addConsumption(shopId, null, memberCardId, consumptionId, date, shopId, 0);
		
		return addProjectDetailConsumption;
	}
}
