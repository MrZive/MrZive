package com.zive.parseProject.oldTakeNew;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.ProjectInfo;
import com.zive.dataOut.entity.Shop;
import com.zive.dataOut.java.BaseDao;
import com.zive.dataOut.java.ExcelUtilForDO;
import com.zive.dataOut.java.ProjectInfoDao;
import com.zive.parseProject.Parse;
import com.zive.shop.entity.ProjectConsumption;
import com.zive.shop.entity.ProjectDetailConsumption;

public class ParseProject extends BaseDao{

	public static void main(String[] args) throws IOException {
		
		//e33d5f72-5097-4112-8b43-e7df0ddb64e7
		//190330
//		Shop shopById = getShopById("16654ccd-c0aa-4a8b-a7b5-5979acb883a2");
		
		List<JSONObject> projectList = getProjectList();
		
		List<Parse> parseTable = new ArrayList<Parse>();
		for (JSONObject map : projectList) {
		
//			JSONObject map = projectList.get(0);
			
//			if(!map.getString("memberCardId").equals("d49bd4f2-a43c-461d-be96-f6a96ebdf285") 
//					&& !map.getString("memberCardId").equals("0f3bbe84-47b7-4a43-a2e6-9a0398d46024")
//					&& !map.getString("memberCardId").equals("9d7696f1-f9fb-4ded-be01-9b7ed483f054")){
//				continue;
//			}
			
			Parse parseSanJiao = parseSanJiao(map);
			if(parseSanJiao!=null){
				parseTable.add(parseSanJiao);
			}
		}
		
		JSONArray parseArray = JSON.parseArray(JSON.toJSONString(parseTable));
		ExcelUtilForDO.toFile(parseArray, Parse.class);
		
		getSession().commit();
	}
	
	public static Parse parseSanJiao(JSONObject map){
		
		Parse parseTable = null;
		
		Shop shop = getShopById(map.getString("shopId"));
		
		System.out.println(map.toString());
	
		boolean flag = false;
		
		String id = map.getString("id");
		String projectId = map.getString("projectId");
		String projectNo = map.getString("projectNo");
		String projectName = map.getString("projectName");
		String memberCardId = map.getString("memberCardId");
		String shopId = map.getString("shopId");
		
		Integer buyNumber = map.getInteger("buyNumber");
		Integer number = map.getInteger("number");
		
		
		
		if(projectNo.equals("10100051") || projectNo.equals("00000304") || projectNo.equals("00000303") || projectNo.equals("00000302") || projectNo.equals("00000301") || projectNo.equals("10100042_1")){
			
			ProjectInfo newProject = ProjectInfoDao.getProjectInfoByNo("10100042");
			
			MemberCard memberCard = getMemberCardById(memberCardId);
			
			parseTable = new Parse();
			parseTable.setNumber(number+"");
			parseTable.setShopId(shopId);
			parseTable.setShopName(shop.getName());
			parseTable.setName(memberCard.getName());
			parseTable.setPhone(memberCard.getPhone());
			parseTable.setOldOrderDetailId(id);
			parseTable.setOldConsumptionId(map.getString("consumptionId"));
			parseTable.setOldBuyOrderId(map.getString("consumptionProjectId"));
			parseTable.setOldProjectId(projectId);
			parseTable.setOldProjectName(projectName);
			parseTable.setNewProjectId(newProject.getId());
			parseTable.setNewProjectName(newProject.getName());
			
//			Map<String,Object> conMap = new HashMap<>();
//			conMap.put("consumptionIdLike", "_oldParseNew");
//			conMap.put("shopId", shopId);
//			conMap.put("memberCardId", memberCardId);
//			conMap.put("projectId", newProject.getId());
//			List<ProjectDetailConsumption> oldList = getConsumptionProjectDetailList(conMap);
//			if(oldList!=null && oldList.size()>0){
//				if(oldList.size()>1){
//					throw new RuntimeException();
//				}
//				ProjectDetailConsumption projectDetailConsumption = oldList.get(0);
//				String oldId = projectDetailConsumption.getId();
//				
//				Integer oldBuyNumber = projectDetailConsumption.getBuyNumber() + buyNumber;
//				Integer oldNumber = projectDetailConsumption.getNumber() + number;
//				
//				parseTable.setNewConsumptionId(projectDetailConsumption.getConsumptionId());
//				parseTable.setNewOrderDetailId(oldId);
//				parseTable.setNewBuyOrderId(projectDetailConsumption.getConsumptionProjectId());
//				
//				
//				int updateConsumptionDetail = updateConsumptionProjectDetail(oldId, oldBuyNumber, oldNumber);
//				if(updateConsumptionDetail>0){
//					flag = true;
//				}
//			}else{
				long time = new Date().getTime();
				String consumptionId = "A"+shop.getNo()+time+"_oldParseNew";
				parseTable.setNewConsumptionId(consumptionId);
				
				System.out.println(consumptionId);
				Consumption consumption = initConsumption(consumptionId, memberCardId, shopId);
				int addConsumption = addConsumption(consumption);
				if(addConsumption>0){
					String orderId = "B"+shop.getNo()+time+"_oldParseNew";
					parseTable.setNewBuyOrderId(orderId);
					System.out.println(orderId);
					ProjectConsumption initProjectConsumption = initProjectConsumption(consumptionId, orderId, memberCardId);
					int addProjectConsumption = addProjectConsumption(initProjectConsumption);
					if(addProjectConsumption>0){
						ProjectDetailConsumption initProjectDetailConsumption = initProjectDetailConsumption(consumptionId, orderId, shopId, memberCardId, "oldParseNew", newProject.getId(), buyNumber, number, 45,newProject);
						parseTable.setNewOrderDetailId(initProjectDetailConsumption.getId());
						int addProjectDetailConsumption = addProjectDetailConsumption(initProjectDetailConsumption);
						if(addProjectDetailConsumption>0){
							flag = true;
						}
					}
				}
//			}
			int updateConsumptionDetail2 = updateConsumptionProjectDetail(id, buyNumber, 0);
			if(updateConsumptionDetail2>0){
				flag = true;
			}
			if(!flag){
				throw new RuntimeException();
			}
		}
		
		return parseTable;
	}
	
	
	public static Consumption initConsumption(String consumptionId,String memberCardId,String shopId){
		Consumption cc = new Consumption();
		cc.setId(consumptionId);
		cc.setConsumptionDate(new Date());
		cc.setCreateDate(new Date());
		cc.setIsCooperation(0);
		cc.setIsLinkFail(0);
		cc.setMemberCardId(memberCardId);
		cc.setReceiptShopType(0);
		cc.setShopId(shopId);
		cc.setStatus(1);
		cc.setIsTuoke(0);
		cc.setCreateUserId("必盈店旧项目转新项目");
		cc.setMakerId("必盈店旧项目转新项目");
		
//		收款门店类型，0是消费门店，1是星和
		cc.setReceiptShopType(0);
		
		return cc;
	}
	
	public static ProjectConsumption initProjectConsumption(String consumptionId,String orderId,String memberCardId){
		ProjectConsumption projectConsumption = new ProjectConsumption();
		projectConsumption.setId(orderId);
		projectConsumption.setConsumptionId(consumptionId);
		projectConsumption.setStorePay(0.0);
		projectConsumption.setBankCardPay(0.0);
		projectConsumption.setCashPay(0.0);
		projectConsumption.setPointPay(0.0);
		projectConsumption.setOwe(0.0);
		projectConsumption.setBuyOwe(0.0);
		projectConsumption.setWeChatPay(0.0);
		projectConsumption.setAliPay(0.0);
		projectConsumption.setRealPayment(0.0);
		projectConsumption.setPayment(0.0);
		projectConsumption.setEffectiveEarn(0.0);
		projectConsumption.setIsBook(0);
		projectConsumption.setMemberCardId(memberCardId);
		projectConsumption.setRemark("必盈店旧项目转新项目");
		projectConsumption.setIsDetailPay(1);
		return projectConsumption;
	}
	
	public static ProjectDetailConsumption initProjectDetailConsumption(String consumptionId,String orderId,String shopId,String memberCardId,String buyType,String projectId,Integer buyNumber,Integer number,Integer serviceTime,ProjectInfo info){
		ProjectDetailConsumption d = new ProjectDetailConsumption();
		d.setId(UUID.randomUUID().toString());
		d.setPrice(0.0);
		d.setMarketPrice(0.0);
		d.setPromotionPrice(0.0);
		d.setExperiencePrice(0.0);
		d.setBankCardPay(0.0);
		d.setStorePay(0.0);
		d.setCashPay(0.0);
		d.setPointPay(0.0);
		d.setPayment(0.0);
		d.setRealPayment(0.0);
		d.setOwe(0.0);
		d.setConsumptionId(consumptionId);
		d.setIsPay(1);
		d.setMemberCardId(memberCardId);
		d.setShopId(shopId);
		d.setRemark("必盈店旧项目转新项目");
		d.setCreateDate(new Date());
		d.setEndDate(getEndDate(new Date(), 24));
		d.setIsCashCoupon(0);
		d.setIsSend(0);
		d.setIsBook(0);
		d.setBuyType(buyType);
		d.setEffectiveEarn(0.0);
		
		d.setWeChatPay(0.0);
		d.setPayment(0.0);
		d.setRealPayment(0.0);
		boolean isSet = false;
		if (isSet) {
//			d.setConsumptionSetId(posterBuyInfo.getBuyOrderId());
//			d.setActivityId(activityId);
		}else {
			d.setActivityId("");
			d.setConsumptionProjectId(orderId);
		}
		d.setProjectId(projectId);
		d.setServiceTime(serviceTime);
		d.setPrice(0.0);
		d.setBuyNumber(buyNumber);
		d.setNumber(number);
		d.setIsPay(1);
		d.setMemberCardId(memberCardId);
		d.setChannelId(0);
		d.setIsCount(1);
		
		return d;
	}
	
	/**
	 * 根据有效期计算结束时间
	 * @author hcxue
	 * @date 2019年1月31日 上午11:40:34
	 * @param consumptionDate
	 * @param effectiveMonths
	 * @return
	 */
	public static java.sql.Date getEndDate(Date consumptionDate, Integer effectiveMonths){
		Calendar rightNow = Calendar.getInstance();
//		  rightNow.setTime(new Date());
		  rightNow.setTime(consumptionDate);
		  int year =effectiveMonths /12;
		  int month = effectiveMonths %12;
		  if (year>0){
			  rightNow.add(Calendar.YEAR,year);//日期加年
		  }
		  rightNow.add(Calendar.MONTH,month);//日期加月
		  Date date=rightNow.getTime();
		  java.sql.Date dateSql = new java.sql.Date(date.getTime());
		  return dateSql;
	}
	
	
	static List<JSONObject> getProjectList(){
		List<JSONObject> list = getSession().selectList("com.zive.parseProject.getProjectList");
		return list;
	}
	
	static int addParseProjectConsumption(Consumption consumption){
		int insert = getSession().insert("com.zive.parseProject.addConsumption",consumption);
		return insert;
	}
	
	static int addProjectConsumption(ProjectConsumption projectcConsumption){
		int insert = getSession().insert("com.zive.parseProject.addProjectConsumption",projectcConsumption);
		return insert;
	}
	
	static int addProjectDetailConsumption(ProjectDetailConsumption projectcDetailConsumption){
		int insert = getSession().insert("com.zive.parseProject.addProjectDetailConsumption",projectcDetailConsumption);
		return insert;
	}
	
	static List<ProjectDetailConsumption> getConsumptionProjectDetailList(ProjectDetailConsumption detail){
		JSONObject parseObject = JSONObject.parseObject(JSON.toJSONString(detail));
		List<ProjectDetailConsumption> list = getSession().selectList("com.zive.parseProject.getConsumptionProjectDetailList",parseObject.getInnerMap());
		return list;
	}
	
	static List<ProjectDetailConsumption> getConsumptionProjectDetailList(Map<String,Object> map){
		List<ProjectDetailConsumption> list = getSession().selectList("com.zive.parseProject.getConsumptionProjectDetailList",map);
		return list;
	}
	
	static int updateConsumptionProjectDetail(String id,int buyNumber,int number){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("buyNumber", buyNumber);
		map.put("number", number);
		
		int update = getSession().update("com.zive.parseProject.updateConsumptionProjectDetail",map);
		
		return update;
	}
}


