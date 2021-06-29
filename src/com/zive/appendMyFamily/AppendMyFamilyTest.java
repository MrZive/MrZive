package com.zive.appendMyFamily;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.kangwang.BaseKangWangDao;
import com.zive.kangwang.entity.NameToSystemName;
import com.zive.pub.Excel;
import com.zive.pub.ExcelCell;
import com.zive.pub.ExcelRow;
import com.zive.pub.ExcelSheet;
import com.zive.pub.OfficeUtil;

public class AppendMyFamilyTest extends BaseDao{
	
	final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	final static SimpleDateFormat sdfs = new SimpleDateFormat("yyyy-MM-dd");

	public static void main(String[] args) throws IOException, ParseException {
		
		String phone = "18680688112";
//		String projectName = "免疫调理-三焦艾熏";
		String projectName = "三焦艾熏";
//		String shopName = "总部店";
		String shopName = "000000总部店";
		String orderId = "Z002000070";
		Double price = 99.95D;
		Integer serviceTime = 45;
		Integer buyNumber = 20;
		String buyTime = "2017-11-12 14:02:15";
		String createUser = "100";
		Double payment = price * buyNumber;
		Double storePay = 0D;
		Double bankcardPay = payment;
		Double cashPay = 0D;
		
		MemberCard memberCard = getMemberCardByPhone(phone);
		
		ProjectInfo projectInfo = new ProjectInfo();
		projectInfo.setName(projectName);
		projectInfo = getProjectInfo(projectInfo).get(0);
		Shop shop = getShop(new Shop(){{setName(shopName);}}).get(0);
		
		
		addProjectDetail(memberCard.getId(), shop.getId(), orderId,projectInfo.getId(), price, 20, 20, serviceTime, storePay, bankcardPay, cashPay, 0D, 0, null, createUser, buyTime);
		
		
		
		MemberCard change = new MemberCard();
		change.setId(memberCard.getId());
		change.setTreatmentBalance(memberCard.getTreatmentBalance()+payment);
		updateMemberCard(change);
		
		getSession().commit();
		getSession().close();
	}


	static public int addProjectDetail(String memberCardId,String shopId,String consumptionId,String projectId,Double price,Integer buyNumber,Integer number,Integer serviceTime,Double storePay,Double bankcardPay,Double cashPay,Double owe,Integer isSend,String remark,String createUser,String buyTime){
		ProjectDetailConsumption detail = new ProjectDetailConsumption();
		detail.setId(UUID.randomUUID().toString());
		detail.setConsumptionId(consumptionId);
		detail.setConsumptionProjectId(consumptionId);
		detail.setActivityId("0");
		detail.setBuyNumber(buyNumber);
		detail.setNumber(number);
		detail.setIsSend(isSend);
		try {
			detail.setCreateDate(sdfs.parse(buyTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(detail.getCreateDate());
		int year = 24/12;
		if (year>0){
			rightNow.add(Calendar.YEAR,year);//日期加年
		}
		detail.setEndDate(rightNow.getTime());
		detail.setMemberCardId(memberCardId);
		detail.setConsumptionSetId(null);
		detail.setPrice(price);
		detail.setServiceTime(serviceTime);
		detail.setIsCount(1);
		detail.setShopId(shopId);
		detail.setIsFail(0);
		detail.setExperiencePrice(159D);
		detail.setPromotionPrice(99.95D);
		detail.setMarketPrice(399.9D);
		detail.setBuyType("0");
		detail.setChannelId(0);
		detail.setPayment(null);
		detail.setStorePay(null);
		detail.setBankcardPay(null);
		detail.setCashPay(null);
		detail.setPointPay(null);
		detail.setIsBook(null);
		detail.setOwe(0D);
		detail.setBuyOwe(0D);
		detail.setEffectiveEarn(null);
		detail.setSecondName(null);
		detail.setRealPayment(null);
		detail.setIsPay(0);
		detail.setRemark(null);
		detail.setIsCashCoupon(null);
		detail.setIsTuoke(null);
		detail.setServiceType(0);
		detail.setWechatPay(0D);
		detail.setAliPay(0D);
		detail.setCoupon(null);
		detail.setProjectId(projectId);
		detail.setInvalidNumber(number);
		
		
		double payment = setDoubleScale(price * buyNumber);
		
		
		int addProjectDetailConsumption = ProjectSellDao.addProjectDetailConsumption(detail);
		if(addProjectDetailConsumption==0){
			throw new RuntimeException("新增项目错误");
		}
		
		
		addProjectConsumption(consumptionId, memberCardId, shopId, storePay, bankcardPay, cashPay, payment, buyTime);
		
		addConsumption(consumptionId, memberCardId, buyTime, shopId);
		
		return addProjectDetailConsumption;
	}
	
	
	static int addProjectConsumption(String consumptionId,String memberCardId,String shopid,Double storePay,Double bankcardPay,Double cashPay,Double payment,String buyTime){
		ProjectConsumption detail = new ProjectConsumption();
		detail.setId(consumptionId);
		detail.setConsumptionId(consumptionId);
		detail.setStorePay(storePay);
		detail.setBankcardPay(bankcardPay);
		detail.setCashPay(cashPay);
		detail.setPointPay(0D);
		detail.setIsBook(0);
		detail.setPayment(payment);
		detail.setEffectiveEarn(payment);
		detail.setRemark("");
		detail.setIsBirthday(0);
		detail.setRealPayment(payment);
		detail.setOwe(0D);
		detail.setMemberCardId(memberCardId);
		detail.setIsCashCoupon(0);
		try {
			detail.setCreateDate(sdf.parse(buyTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		detail.setIsFail(0);
		detail.setShopid(shopid);
		detail.setCashCoupon(null);
		detail.setBuyOwe(0D);
		detail.setIsDetailPay(0);
		return ProjectSellDao.addProjectConsumption(detail);
	}
	
	static int addConsumption(String consumptionId,String memberCardId,String buyTime,String shopId){
		Consumption detail = new Consumption();
		detail.setId(consumptionId);
		detail.setMemberCardId(memberCardId);
		detail.setShopId(shopId);
		try {
			detail.setConsumptionDate(sdfs.parse(buyTime));
			detail.setCreateDate(sdf.parse(buyTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		detail.setCreateUserId(shopId);
		detail.setStatus(1);
		detail.setIsLinkFail(0);
		detail.setFailId(null);
		detail.setFailDate(null);
		detail.setIsOverFail(0);
		detail.setIsCooperation(0);
		detail.setIsTuoke(0);
		detail.setFailEarn(0);
		detail.setIsDetailPay(0);
		detail.setReceiptShopType(0);
		detail.setIsInvoice(0);
		
		return addConsumption(detail);
	}
}
