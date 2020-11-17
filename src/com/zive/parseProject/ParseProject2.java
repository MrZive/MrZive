package com.zive.parseProject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.management.RuntimeErrorException;

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
import com.zive.shop.entity.ProjectConsumption;
import com.zive.shop.entity.ProjectDetailConsumption;

public class ParseProject2 extends BaseDao{

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
			
			
			parseTable.setNewConsumptionId(map.getString("consumptionId"));
			parseTable.setNewOrderDetailId(id);
			parseTable.setNewBuyOrderId(map.getString("consumptionProjectId"));
			
			
			int updateConsumptionDetail = updateConsumptionProjectDetail(id, newProject.getId());
			if(updateConsumptionDetail>0){
				flag = true;
			}
			if(!flag){
				throw new RuntimeException();
			}
		}
		
		return parseTable;
	}
	
	
	static List<JSONObject> getProjectList(){
		List<JSONObject> list = getSession().selectList("com.zive.parseProject.getProjectList");
		return list;
	}
	
	static int addConsumption(Consumption consumption){
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
	
	static int updateConsumptionProjectDetail(String id,String projectId){
		Map<String,Object> map = new HashMap<>();
		map.put("id", id);
		map.put("projectId", projectId);
		
		int update = getSession().update("com.zive.parseProject.updateConsumptionProjectDetail",map);
		
		return update;
	}
}


