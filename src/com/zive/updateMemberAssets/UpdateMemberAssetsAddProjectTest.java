package com.zive.updateMemberAssets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.SetConsumption;
import com.zive.dataOut.java.ProjectDoneDao;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.dataOut.java.SetSellDao;
import com.zive.kangwang.BaseKangWangDao;

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
	
	
	
	public static boolean checkProjectCanChange(MemberCard memberCard,ProjectDetailConsumption detail,String unitTime,double price,int buyNumber,int leftNumber,Double autalNumber,Double owe,Double realPayment,String sheetDate,String remark) throws ParseException{
		String unitTimeNow = detail.getServiceTime()==null?null:detail.getServiceTime() + "";
		double priceNow = detail.getPrice();
		int buyNumberNow = detail.getBuyNumber();
		int leftNumberNow = detail.getNumber();
		Double oweNow = detail.getOwe();
		Double realPaymentNow = detail.getRealPayment();
		
		if(unitTimeNow!=null && unitTime!=null && !unitTimeNow.equals(unitTime)){
			throw new RuntimeException(detail.getId()+":单位不一样");
		}
		if(priceNow != price){
			throw new RuntimeException(detail.getId()+":单价不一样");
		}
		if(buyNumberNow != buyNumber){
			throw new RuntimeException(detail.getId()+":购买价格不一样");
		}
		
		//定金单过滤
		if((owe!=null && owe>0) || (oweNow!=null && oweNow>0)){
			return false;
		}
		
		//不知道是否定金单情况下，查找父单
		if(owe==null && oweNow==null){
			if(StringUtils.isNotBlank(detail.getConsumptionProjectId())){
				ProjectConsumption detailCon = new ProjectConsumption();
				detailCon.setId(detail.getConsumptionProjectId());
				List<ProjectConsumption> projectConsumptionList = ProjectSellDao.getProjectConsumption(detailCon);
				for (ProjectConsumption projectConsumption : projectConsumptionList) {
					if(projectConsumption.getOwe() > 0){
						return false;
					}
				}
			}
			if(StringUtils.isNotBlank(detail.getConsumptionProjectId())){
				SetConsumption setConsumption = SetSellDao.getSetConsumption(detail.getConsumptionProjectId(), null);
				if(setConsumption != null){
					if(setConsumption.getOwe() > 0){
						return false;
					}
				}
			}
		}
		
		
		boolean isLeft = false;
		boolean isAutal = false;
		
		if(leftNumberNow != leftNumber){
			//查找这几天有没有手工
			String startDate = sheetDate + " 00:00:00";
			String endDate = sheetDate + " 23:59:59";
			
			Date sheetEndDate = sdf.parse(startDate);
			
			ProjectDoneDetail doneDetail = new ProjectDoneDetail();
			doneDetail.setProjectDetailId(detail.getId());
			doneDetail.setIsFail(0);
			List<ProjectDoneDetail> projectDoneDetailList = ProjectDoneDao.getProjectDoneDetail(doneDetail);
			for (ProjectDoneDetail projectDoneDetail : projectDoneDetailList) {
				if(projectDoneDetail.getCreateDate().getTime() > sheetEndDate.getTime()){
					throw new RuntimeException(detail.getId()+":最近有做过手工，系统："+leftNumberNow+"次，报表："+leftNumber+"次，手工"+projectDoneDetail.getDoneNumber()+"次");
				}
			}
			isLeft = true;
		}
		
		Double autalLeftNumberNow = UpdateMemberAssets.calculateAutalLeftNumber(oweNow, buyNumberNow, leftNumberNow, priceNow, realPaymentNow);
		if(autalLeftNumberNow != null && autalNumber != null){
			double bigDecimalNow = setDoubleScale(autalLeftNumberNow, 1);
			double bigDecimal = setDoubleScale(autalNumber, 1);
			if(bigDecimalNow != bigDecimal){
				if(!UpdateMemberAssets.isInt(autalNumber)){
					throw new RuntimeException(detail.getId()+":可用数量被改成小数了："+autalNumber+"次");
				}
				isAutal = true;
			}
		}
		
		if(!isLeft && !isAutal){
			return false;
		}
		
		Integer realLeftNumber = null;

		if(isLeft && !isAutal){
			realLeftNumber = leftNumber;
		}else if(!isLeft && isAutal){
			realLeftNumber = autalNumber.intValue();
		}else if(isLeft && isAutal){
			if(leftNumber == autalNumber.intValue()){
				realLeftNumber = leftNumber;
			}else{
				throw new RuntimeException(detail.getId()+":剩余数量、可用数量填写");
			}
		}
		
		int chaNumber = realLeftNumber - leftNumberNow;
		double chaPayment = chaNumber * price;
		
		
		String newRemark = "资产盘点，数量："+leftNumberNow+"==》"+realLeftNumber+"，修改时间："+sdf.format(new Date())+"，备注："+remark;
		
		MemberCard changeMember = new MemberCard();
		changeMember.setId(memberCard.getId());
		changeMember.setTreatmentBalance(memberCard.getTreatmentBalance() + chaPayment);
		int update = updateMemberCard(changeMember);
		if(update == 0){
			throw new RuntimeException("更新失败");
		}
		
		ProjectDetailConsumption detailChange = new ProjectDetailConsumption();
		detailChange.setId(detail.getId());
		detailChange.setNumber(realLeftNumber);
		detailChange.setRemark(newRemark);
		update = ProjectSellDao.updateProjectDetailConsumption(detailChange);
		if(update == 0){
			throw new RuntimeException("更新失败");
		}
		
		return true;
	}



	static private int addZhaoMeiProjectDetail(String memberCardId,String shopId,String projectId,Double price,Integer buyNumber,Integer number,Integer serviceTime,String remark,Date createDate,String type){
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
		detail.setRemark("资产盘点，数量：0==》"+buyNumber+"，修改时间："+sdf.format(date)+"，备注："+remark);
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
		
		addConsumption(shopId, null, memberCardId, detail.getConsumptionId(), date, shopId, 0);
		
		return addProjectDetailConsumption;
	}
}
