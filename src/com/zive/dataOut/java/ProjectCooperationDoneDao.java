package com.zive.dataOut.java;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zive.dataOut.entity.Consumption;
import com.zive.dataOut.entity.CooperationProject;
import com.zive.dataOut.entity.MemberCard;
import com.zive.dataOut.entity.MyProjectCooperationDone;
import com.zive.dataOut.entity.ProductDetailConsumption;
import com.zive.dataOut.entity.ProjectCooperationDone;
import com.zive.dataOut.entity.ProductInfo;
import com.zive.dataOut.entity.ProjectCooperationDetailConsumption;
import com.zive.dataOut.entity.ProjectDetailConsumption;
import com.zive.dataOut.entity.ProjectCooperationDone;
import com.zive.dataOut.entity.ProjectInfo;



public class ProjectCooperationDoneDao extends BaseDao{

	public static void main(String[] args) throws IOException {
//		ProjectCooperationDone get = new ProjectCooperationDone();
//		get.setId("G1909257520130066");
//		List<ProjectCooperationDone> getList = getProjectCooperationDone(get);
//		System.out.println(JSONArray.toJSONString(getList));
//		
//		ProjectCooperationDoneDetail getDetail = new ProjectCooperationDoneDetail();
//		getDetail.setOrderId("G1909257520130066");
//		List<ProjectCooperationDoneDetail> getDetailList = getProjectCooperationDoneDetail(getDetail);
//		System.out.println(JSONArray.toJSONString(getDetailList));
		
		List<MyProjectCooperationDone> myProjectCooperationDoneList = new ArrayList<>();
		JSONArray jsonArray = new JSONArray();
		
		ProjectCooperationDone get = new ProjectCooperationDone();
//		get.setMemberCardId("f9fe1dab-4145-4f42-8f58-7833559ab846");
		get.setServiceShopId("110101");
		List<ProjectCooperationDone> getList = getProjectCooperationDone(get);
		
		for (ProjectCooperationDone projectDone : getList) {
			
			Consumption consumption = getConsumptionById(projectDone.getConsumptionId());
			
			MyProjectCooperationDone myDone = new MyProjectCooperationDone();
			
			ProjectCooperationDetailConsumption projectDetailConsumption = ProjectCooperationSellDao.getProjectCooperationDetailConsumptionById(projectDone.getDetailId());
			
			myDone.fromSuper(consumption,projectDetailConsumption,projectDone);
			
			myProjectCooperationDoneList.add(myDone);
			
			jsonArray.add(JSON.parseObject(JSON.toJSONString(myDone)));
		}
		
		ExcelUtilForDO.toFile(jsonArray, MyProjectCooperationDone.class);
		closeSession();
	}
	
	
	//------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//	static public ProjectCooperationDone getProjectCooperationDoneById(String id){
//		ProjectCooperationDone ProjectCooperationDone = new ProjectCooperationDone();
//		ProjectCooperationDone.setId(id);
//		ProjectCooperationDone one = getSession().selectOne("com.zive.dataOut.project.getProjectCooperationDone", ProjectCooperationDone);
//		return one;
//	}
	
	static public List<ProjectCooperationDone> getProjectCooperationDone(ProjectCooperationDone ProjectCooperationDone){
		List<ProjectCooperationDone> list = getSession().selectList("com.zive.dataOut.project.getProjectCooperationDone", ProjectCooperationDone);
		return list;
	}
	
}