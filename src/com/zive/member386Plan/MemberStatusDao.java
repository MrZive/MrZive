package com.zive.member386Plan;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.MyProjectDone;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectDone;
import com.zive.dataOut.entity.ProjectDoneDetail;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectSellDao;
import com.zive.member386Plan.entity.MemberCard386Plan;
import com.zive.member386Plan.entity.MemberPassStatus;
import com.zive.member386Plan.entity.MemberPassStatus.EnumType;



public class MemberStatusDao extends BaseDao{

	public static void main(String[] args) throws IOException {
		MemberPassStatus plan = new MemberPassStatus();
		plan.setType(MemberPassStatus.EnumType.is386Pass);
		List<MemberPassStatus> getList = getList(plan);
		
		for (MemberPassStatus p : getList) {
			System.out.println(p.getMemberCardId());
		}
		
		closeSession();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	static public List<MemberPassStatus> getList(MemberPassStatus plan) {
		return getSession().selectList("com.zive.member386Plan.MemberStatusDao.getList", plan);
	}
	static public int add(MemberPassStatus plan) {
		if(plan.getCreateDate()==null){
			plan.setCreateDate(new Date());
		}
		return getSession().insert("com.zive.member386Plan.MemberStatusDao.add", plan);
	}
	//----------------------------------------------------------------------------------自定义方法-----------------------------------------------------------------------------
	
	static public int add(MemberCard memberCard, EnumType enumType){
		MemberPassStatus status = new MemberPassStatus();
		status.setIsNewPass(memberCard.getIsNewPass());
		status.setIsNewAbandon(memberCard.getIsNewAbandon());
		status.setIsNewSleep(memberCard.getIsNewSleep());
		
		MemberCard386Plan plan = memberCard.getMemberCard386Plan();
		if(plan!=null){
			status.setIs386Pass(plan.getIs386Pass());
			status.setIs386Abandon(plan.getIs386Abandon());
			status.setIs386Sleep(plan.getIs386Sleep());
		}
		
		return add(memberCard, status, enumType);
	}
	
	static private int add(MemberCard memberCard, MemberPassStatus status, EnumType enumType){
		status.setMemberCardId(memberCard.getId());
		//普通状态
		status.setIsPass(memberCard.getIsPass());
		status.setIsAbandon(memberCard.getIsAbandon());
		status.setIsSleep(memberCard.getIsSleep());
		status.setPassConsumptionId(memberCard.getPassConsumptionId());
		status.setPassShopId(memberCard.getPassShopId());
		status.setPassTime(memberCard.getPassTime());
		//必盈状态
		status.setNewPassConsumptionId(memberCard.getNewPassConsumptionId());
		status.setNewPassShopId(memberCard.getNewPassShopId());
		status.setNewPassTime(memberCard.getNewPassTime());
		
		//386状态
		MemberCard386Plan plan = memberCard.getMemberCard386Plan();
		if(plan!=null){
			status.setIs386Tuoke(plan.getIs386Tuoke());
			status.setTuoke386ConsumptionId(plan.getTuoke386ConsumptionId());
			status.setTuoke386ShopId(plan.getTuoke386ShopId());
			status.setTuoke386Time(plan.getTuoke386Time());
			status.setIs386First(plan.getIs386First());
			status.setFirst386ConsumptionId(plan.getFirst386ConsumptionId());
			status.setFirst386ShopId(plan.getFirst386ShopId());
			status.setFirst386Time(plan.getFirst386Time());
			status.setIs386Second(plan.getIs386Second());
			status.setSecond386ConsumptionId(plan.getSecond386ConsumptionId());
			status.setSecond386ShopId(plan.getSecond386ShopId());
			status.setSecond386Time(plan.getSecond386Time());
			status.setPass386ConsumptionId(plan.getPass386ConsumptionId());
			status.setPass386ShopId(plan.getPass386ShopId());
			status.setPass386Time(plan.getPass386Time());
		}
		status.setType(enumType);
		
		return add(status);
	}
}