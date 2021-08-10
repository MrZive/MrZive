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

import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectCooperationConsumption;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.ProjectCooperationSellDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class  UpdateMemberAssetsAddCooperationTest extends BaseKangWangDao{
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

	

	public static boolean checkAndAddPCooperationInfo(
			String shopId, String name, int buyNumber, Integer leftNumber, Date createDate,String remark, double price, String memberCardId,String type) {
		
		//------------------------------开始合作项目的业务-------------------------------
		CooperationProject cooInfo = new CooperationProject();
		cooInfo.setName(name);
		List<CooperationProject> cooList = getCooperationProject(cooInfo);
		
		if(cooList.size() > 0){//项目处理逻辑
			CooperationProject info = cooList.get(0);
			
			addZhaoMeiCooperationConsumption(memberCardId, shopId, info.getId(), price,buyNumber, leftNumber, 0,remark, createDate,type);
			
			return true;
		}else{
//			throw new RuntimeException("找不到对应的信息:" + name);
			if(type.equals("coo")){
				System.out.println("找不到对应的信息:" + name);
			}
			return false;
		}
	}



	static public int addZhaoMeiCooperationConsumption(String memberCardId,String shopId,String projectId,Double price,Integer buyNumber,Integer number,Integer serviceTime,String remark,Date createDate,String type){
		Date date = new Date();
		String consumptionId = "Assets"+date.getTime();
		ProjectCooperationConsumption detail = new ProjectCooperationConsumption();
		detail.setActivityId("");
		detail.setAdviser("");
		detail.setBankCardPay(0D);
		detail.setBuyOwe(0D);
		detail.setBuyType("Assets");
		detail.setCashPay(0D);
		detail.setConsumptionId(consumptionId);
		detail.setCreateDate(createDate);
		detail.setEffectiveEarn(0D);
		detail.setId(consumptionId);
		detail.setIsBook(0);
		detail.setIsFail(0);
		detail.setMemberCardId(memberCardId);
		detail.setOwe(0D);
		detail.setPayment(setDoubleScale(buyNumber * price));
		detail.setRealPayment(detail.getPayment() - detail.getOwe());
		detail.setRemark("资产盘点，数量：0==》"+buyNumber+"，修改时间："+sdf.format(date)+"，备注："+remark);
		detail.setShopid(shopId);
		detail.setStatus(0);
		detail.setStorePay(0D);
		
		int addProjectCooperationDetailConsumption = ProjectCooperationSellDao.addProjectCooperationConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		
		int addCooperationDetail = addZhaoMeiCooperationDetail(memberCardId, projectId, consumptionId, price, buyNumber, number, createDate);
		if(addCooperationDetail==0){
			throw new RuntimeException("新增项目错误");
		}
		
		addConsumption(shopId, null, memberCardId, consumptionId, date, shopId, 0);
		
		return addCooperationDetail;
	}
	
	
	
	static public int addZhaoMeiCooperationDetail(String memberCardId,String cooperationId,String consumptionId,Double price,Integer buyNumber,Integer leftNumber, Date buyTime){
		ProjectCooperationDetailConsumption detail = new ProjectCooperationDetailConsumption();
		detail.setActivityId("");
		detail.setBuyNumber(buyNumber);
		detail.setBuyType("Assets");
		detail.setChannelId(null);
		detail.setConsumptionCooperationId(consumptionId);
		detail.setConsumptionDate(buyTime);
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionSetId("");
		detail.setCooperationId(cooperationId);
		detail.setCoupon("");
		detail.setId(UUID.randomUUID().toString());
		detail.setIsFail(0);
		detail.setLeftNumber(leftNumber);
		detail.setLeftStoreEarn(0D);
		detail.setMemberCardId(memberCardId);
		detail.setPrice(price);
		detail.setShopid(null);
		detail.setSort(null);
		detail.setStatus(1);
		detail.setUnit("次");
		detail.setLeftEarn(0D);
		
		int addProjectCooperationDetailConsumption = ProjectCooperationSellDao.addProjectCooperationDetailConsumption(detail);
		if(addProjectCooperationDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		return addProjectCooperationDetailConsumption;
	}
}
